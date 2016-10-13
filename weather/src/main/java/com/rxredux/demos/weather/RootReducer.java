package com.rxredux.demos.weather;

import com.fred.rxredux.Reducer;
import com.rxredux.demos.weather.actions.WeatherAction;
import com.rxredux.demos.weather.states.Weather;

/**
 * Root reducer for the weather app
 */
public class RootReducer implements Reducer<Weather, WeatherAction> {
  @Override public Weather call(WeatherAction action, Weather weather) {
    switch (action.getType()) {
      case FETCH_WEATHER_INFO:
        return Weather.builder().fetching(true).build();
    }
    return weather;
  }
}
