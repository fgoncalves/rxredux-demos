package com.rxredux.demos.weather.data.network.services;

import com.rxredux.demos.weather.data.models.WeatherData;
import rx.Observable;

/**
 * Service to get the weather data
 */
public interface WeatherService {
  /**
   * Get the current weather data
   *
   * @param latitude Latitude of the place to get the weather for
   * @param longitude Longitude of the place to get the weather for
   * @return An observable for the current weather
   */
  Observable<WeatherData> getCurrentWeather(float latitude, float longitude);
}
