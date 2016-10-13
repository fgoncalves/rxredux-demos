package com.rxredux.demos.weather.injection;

import com.rxredux.demos.weather.data.network.apiclients.WeatherApiClient;
import com.rxredux.demos.weather.data.network.config.APIConfig;
import com.rxredux.demos.weather.data.network.services.WeatherService;
import com.rxredux.demos.weather.data.network.services.WeatherServiceImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class ApiModule {
  @Provides @Singleton public Retrofit providesRetrofit() {
    return new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(APIConfig.BASE_URL)
        .build();
  }

  @Provides @Singleton public WeatherApiClient providesWeatherApiClient(Retrofit retrofit) {
    return retrofit.create(WeatherApiClient.class);
  }

  @Provides @Singleton WeatherService providesWeatherService(WeatherServiceImpl service) {
    return service;
  }
}
