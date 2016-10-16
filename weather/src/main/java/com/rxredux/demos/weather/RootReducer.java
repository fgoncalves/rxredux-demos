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
            .weatherCondition(getWeatherCondition(weatherData.getWeather().get(0).getId()))
            .description(weatherData.getWeather().get(0).getDescription())
            .humidity(weatherData.getMain().getHumidity())
            .windSpeed(weatherData.getWind().getSpeed().floatValue())
            .temperatureInKelvin(weatherData.getMain().getTemp().floatValue())
            .build();
    }
    return weather;
  }

  private WeatherCondition getWeatherCondition(int id) {
    if (id == 800) return WeatherCondition.CLEAR_SKY;
    if (id == 801) return WeatherCondition.FEW_CLOUDS;
    if (id == 802) return WeatherCondition.SCATTERED_CLOUDS;
    if (id == 511) return WeatherCondition.SNOW;
    if (between(803, id, 804)) return WeatherCondition.BROKEN_CLOUDS;
    if (between(700, id, 799)) return WeatherCondition.MIST;
    if (between(600, id, 699)) return WeatherCondition.SNOW;
    if (between(530, id, 532)) return WeatherCondition.SHOWER_RAIN;
    if (between(300, id, 399)) return WeatherCondition.SHOWER_RAIN;
    if (between(200, id, 299)) return WeatherCondition.THUNDERSTORM;
    if (between(500, id, 504)) return WeatherCondition.RAIN;
    return WeatherCondition.UNKNOWN;
  }

  private boolean between(int a, int c, int b) {
    return a <= c && c <= b;
  }
}
