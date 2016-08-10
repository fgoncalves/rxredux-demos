package com.rxredux.demos.middlewares;

import com.fred.rxredux.Reducer;

/**
 * The application root reducer
 * <p/>
 * Created by fred on 16.07.16.
 */
public class RootReducer implements Reducer<CounterState, Actions.CounterAction> {
  @Override
  public CounterState call(Actions.CounterAction counterAction, CounterState counterState) {
    switch (counterAction.getType()) {
      case INCREMENT:
        return new CounterState(counterState.getValue() + 1);
      case DECREMENT:
        return new CounterState(counterState.getValue() - 1);
    }
    throw new RuntimeException("Unknown action type: " + counterAction.getType());
  }
}
