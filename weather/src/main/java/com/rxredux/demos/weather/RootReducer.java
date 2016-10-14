package com.rxredux.demos.weather;

import com.fred.rxredux.Reducer;
import com.rxredux.demos.weather.actions.WeatherAction;
import com.rxredux.demos.weather.data.models.WeatherData;
import com.rxredux.demos.weather.states.Weather;
import com.rxredux.demos.weather.states.WeatherCondition;

/**
 * Root reducer for the weather app
 */
public class RootReducer implements Reducer<Weather, WeatherAction> {
  @Override public Weather call(WeatherAction action, Weather weather) {
    switch (action.getType()) {
      case FETCH_WEATHER_INFO:
        return Weather.builder().fetching(true).build();
      case DISPLAY_ERROR:
        return Weather.builder().error(true).build();
      case GOT_WEATHER_INFO:
        WeatherData weatherData = action.getWeatherData();
        return Weather.builder()
            .weatherCondition(WeatherCondition.CLEAR_SKY) //TODO: change this
            .description(weatherData.getWeather().get(0).getDescription())
            .humidity(weatherData.getMain().getHumidity())
            .windSpeed(weatherData.getWind().getSpeed().floatValue())
            .temperatureInKelvin(weatherData.getMain().getTemp().floatValue())
            .build();
    }
    return weather;
  }
}
