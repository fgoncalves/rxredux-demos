package com.rxredux.demos.weather.data.network.apiclients;

import com.rxredux.demos.weather.data.models.WeatherData;
import com.rxredux.demos.weather.data.network.config.APIConfig;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherApiClient {
  @GET(APIConfig.WEATHER_PATH) Observable<WeatherData> get(@Query("lat") float latitude,
      @Query("lon") float longitude, @Query("apikey") String apiKey);
}
