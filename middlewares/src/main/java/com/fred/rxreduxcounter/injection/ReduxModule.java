package com.fred.rxreduxcounter.injection;

import com.fred.rxredux.Middleware;
import com.fred.rxredux.Store;
import com.fred.rxredux.StoreImpl;
import com.fred.rxredux.transformers.SchedulerTransformer;
import com.fred.rxreduxcounter.Actions;
import com.fred.rxreduxcounter.CounterState;
import com.fred.rxreduxcounter.RootReducer;
import com.fred.rxreduxcounter.middlewares.LoggerMiddleware;
import dagger.Module;
import dagger.Provides;
import java.util.Collections;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Dependencies related with the redux module
 * <p/>
 * Created by fred on 16.07.16.
 */
@Module public class ReduxModule {
  @Provides @Singleton @Named("initial.state") public CounterState providesCounterInitialState() {
    return new CounterState(0);
  }

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

  @Provides @Singleton @Named("logger")
  public Middleware<CounterState, Actions.CounterAction> providesLoggerMiddleware(
      LoggerMiddleware loggerMiddleware) {
    return loggerMiddleware;
  }

  @Provides @Singleton public RootReducer providesRootReducer() {
    return new RootReducer();
  }

  @Provides @Singleton
  public Store<CounterState, Actions.CounterAction> providesStore(RootReducer rootReducer,
      SchedulerTransformer schedulerTransformer, @Named("initial.state") CounterState initialState,
      @Named("logger") Middleware<CounterState, Actions.CounterAction> loggerMiddleware) {
    return StoreImpl.create(rootReducer, initialState, schedulerTransformer,
        Collections.singletonList(loggerMiddleware));
  }
}
