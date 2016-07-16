package com.fred.rxreduxcounter;

import com.fred.rxredux.Reducer;
import rx.Observable;
import rx.Subscriber;

/**
 * The application root reducer
 * <p/>
 * Created by fred on 16.07.16.
 */
public class RootReducer implements Reducer<CounterState, Actions.CounterAction> {
  @Override public rx.Observable<CounterState> reduce(final Actions.CounterAction counterAction,
      final CounterState counterState) {
    return Observable.create(new Observable.OnSubscribe<CounterState>() {
      @Override public void call(Subscriber<? super CounterState> subscriber) {
        switch (counterAction.getType()) {
          case INITIAL:
            subscriber.onNext(counterState);
            break;
          case INCREMENT:
            subscriber.onNext(new CounterState(counterState.getValue() + 1));
            break;
          case DECREMENT:
            subscriber.onNext(new CounterState(counterState.getValue() - 1));
            break;
        }
        subscriber.onCompleted();
      }
    });
  }
}
