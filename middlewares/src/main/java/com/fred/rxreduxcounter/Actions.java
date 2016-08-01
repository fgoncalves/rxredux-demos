package com.fred.rxreduxcounter;

import com.fred.rxredux.Action;

/**
 * Counter actions
 * <p/>
 * Created by fred on 16.07.16.
 */
public class Actions {
  public enum ActionType {
    INCREMENT,
    DECREMENT,
    UNKNOWN_ACTION
  }

  public static class CounterAction extends Action<ActionType> {
    public CounterAction(ActionType type) {
      super(type);
    }
  }

  public static final CounterAction increment = new CounterAction(ActionType.INCREMENT);
  public static final CounterAction decrement = new CounterAction(ActionType.DECREMENT);
  public static final CounterAction unknown = new CounterAction(ActionType.UNKNOWN_ACTION);
}
