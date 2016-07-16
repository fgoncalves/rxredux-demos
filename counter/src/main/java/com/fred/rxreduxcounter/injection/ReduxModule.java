package com.fred.rxreduxcounter.injection;

import com.fred.rxredux.Store;
import com.fred.rxredux.StoreImpl;
import com.fred.rxreduxcounter.Actions;
import com.fred.rxreduxcounter.CounterState;
import com.fred.rxreduxcounter.RootReducer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Dependencies related with the redux module
 * <p/>
 * Created by fred on 16.07.16.
 */
@Module
public class ReduxModule {
  @Provides @Singleton @Named("initial.state") public CounterState providesCounterInitialState() {
    return new CounterState(0);
  }

  @Provides @Singleton public RootReducer providesRootReducer() {
    return new RootReducer();
  }

  @Provides @Singleton
  public Store<CounterState, Actions.CounterAction> providesStore(RootReducer rootReducer,
      @Named("initial.state") CounterState initialState) {
    return StoreImpl.create(rootReducer, initialState, Actions.initial);
  }
}
