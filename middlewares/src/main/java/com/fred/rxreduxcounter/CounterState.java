package com.fred.rxreduxcounter;

import com.fred.rxredux.State;

/**
 * The state for the counter.
 * <p/>
 * Created by fred on 16.07.16.
 */
public class CounterState extends State{
  private final int value;

  public CounterState(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override public String toString() {
    return "CounterState{" +
        "value=" + value +
        '}';
  }
}
