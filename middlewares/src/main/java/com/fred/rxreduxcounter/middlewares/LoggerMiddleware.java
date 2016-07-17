package com.fred.rxreduxcounter.middlewares;

import android.util.Log;
import com.fred.rxredux.Middleware;
import com.fred.rxredux.Store;
import com.fred.rxreduxcounter.Actions;
import com.fred.rxreduxcounter.CounterState;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * A simple android logger for all the actions in the redux
 * application
 * <p/>
 * Created by fred on 17.07.16.
 */
public class LoggerMiddleware implements Middleware<CounterState, Actions.CounterAction> {
  @Inject public LoggerMiddleware() {
  }

  @Override
  public Observable<CounterState> apply(final Store<CounterState, Actions.CounterAction> store,
      final CounterState counterState, final Actions.CounterAction counterAction) {
    return Observable.create(new Observable.OnSubscribe<CounterState>() {
      @Override public void call(Subscriber<? super CounterState> subscriber) {
        Log.d(LoggerMiddleware.class.getSimpleName(),
            "---> Action dispatched: " + counterAction.getType());
        Log.d(LoggerMiddleware.class.getSimpleName(), "---> State: " + store.state());
        subscriber.onNext(counterState);
        subscriber.onCompleted();
      }
    }).doAfterTerminate(new Action0() {
      @Override public void call() {
        Log.d(LoggerMiddleware.class.getSimpleName(),
            "<--- Action dispatched: " + counterAction.getType());
        Log.d(LoggerMiddleware.class.getSimpleName(), "<--- State: " + store.state());
      }
    });
  }
}
