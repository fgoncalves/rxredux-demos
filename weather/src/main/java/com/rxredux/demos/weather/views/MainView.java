package com.rxredux.demos.weather.views;

import android.content.Context;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fred.rxredux.Store;
import com.rxredux.demos.weather.R;
import com.rxredux.demos.weather.actions.WeatherAction;
import com.rxredux.demos.weather.states.Weather;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;

public class MainView extends ConstraintLayout {
  /**
   * For now only show the weather in Lisbon
   */
  private final static Pair<Float, Float> COORDINATES = new Pair<>(38.7223f, -9.1393f);

  @BindView(R.id.temperature) TextView temperature;
  @BindView(R.id.humidity) TextView humidity;
  @BindView(R.id.wind_speed) TextView windSpeed;
  @BindView(R.id.description) TextView description;
  @BindView(R.id.weather_logo) ImageView weatherLogo;
  @BindView(R.id.weather_main_content) View weatherMainContent;
  @BindView(R.id.weather_progress_bar) View progressBar;

  @Inject Store<Weather, WeatherAction> store;

  private Subscription subscription;

  public MainView(Context context) {
    super(context);
  }

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (isInEditMode()) return;

    subscription = store.subscribe(new StoreSubscriber());
    store.dispatch(WeatherAction.fetch(COORDINATES.first, COORDINATES.second));
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    subscription.unsubscribe();
  }

  @OnClick(R.id.refresh) public void refresh() {
    store.dispatch(WeatherAction.fetch(COORDINATES.first, COORDINATES.second));
  }

  private class StoreSubscriber extends Subscriber<Weather> {
    @Override public void onCompleted() {

    }

    @Override public void onError(Throwable e) {

    }

    @Override public void onNext(Weather weather) {
      if (weather.hasErrors()) {
        progressBar.setVisibility(GONE);
        weatherMainContent.setVisibility(GONE);
        new AlertDialog.Builder(getContext()).setMessage(R.string.something_went_wrong)
            .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialogInterface, int i) {
                store.dispatch(WeatherAction.fetch(COORDINATES.first, COORDINATES.second));
              }
            })
            .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialogInterface, int i) {
                // TODO: Do something....
              }
            })
            .show();
        return;
      }
      if (weather.isFetching()) {
        progressBar.setVisibility(VISIBLE);
        weatherMainContent.setVisibility(GONE);
      } else {
        progressBar.setVisibility(GONE);
        weatherMainContent.setVisibility(VISIBLE);
        description.setText(weather.getDescription());
        temperature.setText(
            getContext().getString(R.string.temperature, weather.getTemperatureInCelsius()));
        humidity.setText(String.valueOf(weather.getHumidity()));
        windSpeed.setText(getContext().getString(R.string.wind_speed, weather.getWindSpeed()));
      }
    }
  }
}
