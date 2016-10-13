package com.rxredux.demos.weather.injection;

import com.fred.rxredux.Store;
import com.fred.rxredux.StoreImpl;
import com.fred.rxredux.transformers.SchedulerTransformer;
import com.rxredux.demos.weather.RootReducer;
import com.rxredux.demos.weather.actions.WeatherAction;
import com.rxredux.demos.weather.states.Weather;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module public class ReduxModule {
  @Provides @Singleton public SchedulerTransformer providesIoToMainSchedulerTransformer() {
    return new SchedulerTransformer() {
      @Override public <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
          public Observable<T> call(Observable<T> observable) {
            return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
          }
        };
      }
    };
  }

  @Provides @Singleton public RootReducer provideRootReducer() {
    return new RootReducer();
  }

  @Named("weather.root.reducer.initial.state") @Provides @Singleton
  public Weather providesInitialState() {
    return Weather.builder().build();
  }

  @Provides @Singleton public Store<Weather, WeatherAction> providesStore(RootReducer rootReducer,
      SchedulerTransformer schedulerTransformer,
      @Named("weather.root.reducer.initial.state") Weather initialState) {
    return StoreImpl.create(rootReducer, initialState, schedulerTransformer);
  }
}
