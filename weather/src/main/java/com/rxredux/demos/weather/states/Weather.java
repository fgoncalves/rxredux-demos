package com.rxredux.demos.weather.states;

import com.fred.rxredux.State;

/**
 * Redux state for the weather app
 */
public class Weather extends State {
  // For now we just flag if there was an error or not...
  private final boolean error;
  private final boolean fetching;
  private final String description;
  private final float temperatureInKelvin;
  private final int humidity;
  private final float windSpeed;
  private final WeatherCondition weatherCondition;

  private Weather(boolean error, boolean fetching, String description, float temperatureInKelvin,
      int humidity, float windSpeed, WeatherCondition weatherCondition) {
    this.error = error;
    this.fetching = fetching;
    this.description = description;
    this.temperatureInKelvin = temperatureInKelvin;
    this.humidity = humidity;
    this.windSpeed = windSpeed;
    this.weatherCondition = weatherCondition;
  }

  public String getDescription() {
    return description;
  }

  public float getTemperatureInKelvin() {
    return temperatureInKelvin;
  }

  public float getTemperatureInCelsius() {
    return getTemperatureInKelvin() - 273.15f;
  }

  public int getHumidity() {
    return humidity;
  }

  public float getWindSpeed() {
    return windSpeed;
  }

  public WeatherCondition getWeatherCondition() {
    return weatherCondition;
  }

  public boolean hasErrors() {
    return error;
  }

  public boolean isFetching() {
    return fetching;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private boolean error;
    private boolean fetching;
    private String description;
    private float temperatureInKelvin;
    private int humidity;
    private float windSpeed;
    private WeatherCondition weatherCondition;

    private Builder() {
    }

    public Builder error(boolean error) {
      this.error = error;
      return this;
    }

    public Builder fetching(boolean fetching) {
      this.fetching = fetching;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder temperatureInKelvin(float temperatureInKelvin) {
      this.temperatureInKelvin = temperatureInKelvin;
      return this;
    }

    public Builder humidity(int humidity) {
      this.humidity = humidity;
      return this;
    }

    public Builder windSpeed(float windSpeed) {
      this.windSpeed = windSpeed;
      return this;
    }

    public Builder weatherCondition(WeatherCondition weatherCondition) {
      this.weatherCondition = weatherCondition;
      return this;
    }

    public Weather build() {
      return new Weather(error, fetching, description, temperatureInKelvin, humidity, windSpeed,
          weatherCondition);
    }
  }
}
