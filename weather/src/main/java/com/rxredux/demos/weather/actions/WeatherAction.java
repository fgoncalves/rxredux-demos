package com.rxredux.demos.weather.actions;

import com.fred.rxredux.Action;

/**
 * Action for the weather app
 */
public class WeatherAction extends Action<WeatherActionType> {
  private float latitude;
  private float longitude;

  private WeatherAction(WeatherActionType type) {
    super(type);
  }

  public float getLongitude() {
    return longitude;
  }

  public float getLatitude() {
    return latitude;
  }

  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(float longitude) {
    this.longitude = longitude;
  }

  public static WeatherAction fetch(float latitude, float longitude) {
    WeatherAction weatherAction = new WeatherAction(WeatherActionType.FETCH_WEATHER_INFO);
    weatherAction.setLatitude(latitude);
    weatherAction.setLongitude(longitude);
    return weatherAction;
  }

  public static WeatherAction gotInfo() {
    return new WeatherAction(WeatherActionType.GOT_WEATHER_INFO);
  }
}
