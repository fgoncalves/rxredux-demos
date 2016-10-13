package com.rxredux.demos.weather.data.network.services;

import com.rxredux.demos.weather.data.models.WeatherData;
import com.rxredux.demos.weather.data.network.apiclients.WeatherApiClient;
import com.rxredux.demos.weather.data.network.config.APIConfig;
import javax.inject.Inject;
import rx.Observable;

public class WeatherServiceImpl implements WeatherService {
  private final WeatherApiClient apiClient;

  @Inject public WeatherServiceImpl(WeatherApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @Override public Observable<WeatherData> getCurrentWeather(float latitude, float longitude) {
    return apiClient.get(latitude, longitude, APIConfig.API_KEY);
  }
}
