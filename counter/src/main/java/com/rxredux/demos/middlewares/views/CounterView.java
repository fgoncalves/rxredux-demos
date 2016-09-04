package com.rxredux.demos.middlewares.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fred.rxredux.Store;
import com.rxredux.demos.counter.R;
import com.rxredux.demos.rxreduxcounter.Actions;
import com.rxredux.demos.rxreduxcounter.CounterState;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;

/**
 * Counter view that subscribes to the store
 * <p/>
 * Created by fred on 16.07.16.
 */
public class CounterView extends RelativeLayout {
  @BindView(R.id.counter_value) TextView counterValue;

  private Subscriber<CounterState> storeSubscriber = new Subscriber<CounterState>() {
    @Override public void onCompleted() {

    }

    @Override public void onError(Throwable e) {
      Log.e(getClass().getSimpleName(), "failed to update counter state", e);
    }

    @Override public void onNext(CounterState counterState) {
      drawState(counterState);
    }
  };
  private Subscription storeSubscription;

  public CounterView(Context context) {
    super(context);
  }

  public CounterView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Inject Store<CounterState, Actions.CounterAction> store;

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    storeSubscription = store.subscribe(storeSubscriber);
    drawState(store.state());
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    storeSubscription.unsubscribe();
  }

  @OnClick(R.id.button_decrement) public void decrement() {
    store.dispatch(Actions.decrement);
  }

  @OnClick(R.id.button_increment) public void increment() {
    store.dispatch(Actions.increment);
  }

  private void drawState(CounterState counterState) {
    counterValue.setText(String.valueOf(counterState.getValue()));
  }
}
