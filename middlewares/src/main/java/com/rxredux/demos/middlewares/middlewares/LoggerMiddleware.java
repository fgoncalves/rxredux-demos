package com.rxredux.demos.middlewares.middlewares;

import android.util.Log;
import com.fred.rxredux.Dispatch;
import com.fred.rxredux.Middleware;
import com.fred.rxredux.Store;
import com.rxredux.demos.middlewares.Actions;
import com.rxredux.demos.middlewares.CounterState;
import javax.inject.Inject;

/**
 * A simple android logger for all the actions in the redux
 * application
 * <p/>
 * Created by fred on 17.07.16.
 */
public class LoggerMiddleware implements Middleware<Actions.CounterAction, CounterState> {
  @Inject public LoggerMiddleware() {
  }

  @Override public CounterState call(Store<CounterState, Actions.CounterAction> store,
      Actions.CounterAction counterAction, Dispatch<Actions.CounterAction, CounterState> next) {
    Log.d(LoggerMiddleware.class.getSimpleName(),
        "---> Action dispatched: " + counterAction.getType());
    Log.d(LoggerMiddleware.class.getSimpleName(), "---> State: " + store.state());
    CounterState state = next.call(counterAction);
    Log.d(LoggerMiddleware.class.getSimpleName(),
        "<--- Action dispatched: " + counterAction.getType());
    Log.d(LoggerMiddleware.class.getSimpleName(), "<--- State: " + store.state());
    return state;
  }
}
