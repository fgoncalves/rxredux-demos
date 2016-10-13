package com.rxredux.demos.weather;

import com.fred.rxredux.Dispatch;
import com.fred.rxredux.Middleware;
import com.fred.rxredux.Store;
import com.fred.rxredux.transformers.SchedulerTransformer;
import com.rxredux.demos.weather.actions.WeatherAction;
import com.rxredux.demos.weather.actions.WeatherActionType;
import com.rxredux.demos.weather.data.models.WeatherData;
import com.rxredux.demos.weather.data.network.services.WeatherService;
import com.rxredux.demos.weather.states.Weather;
import javax.inject.Inject;
import rx.functions.Action1;

/**
 * Lack of imagination for the name. A middleware that just
 */
public class NetworkMiddleware implements Middleware<WeatherAction, Weather> {
  private final WeatherService service;
  private final SchedulerTransformer networkSchedulerTransformer;

  @Inject public NetworkMiddleware(WeatherService service,
      SchedulerTransformer networkSchedulerTransformer) {
    this.service = service;
    this.networkSchedulerTransformer = networkSchedulerTransformer;
  }

  @Override public Weather call(Store<Weather, WeatherAction> store, WeatherAction action,
      final Dispatch<WeatherAction, Weather> dispatch) {
    if (action.getType() == WeatherActionType.FETCH_WEATHER_INFO) {
      service.getCurrentWeather(action.getLatitude(), action.getLatitude())
          .compose(networkSchedulerTransformer.<WeatherData>applySchedulers())
          .subscribe(new Action1<WeatherData>() {
            @Override public void call(WeatherData weatherData) {

            }
          }, new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {

            }
          });
    }
    return dispatch.call(action);
  }
}
