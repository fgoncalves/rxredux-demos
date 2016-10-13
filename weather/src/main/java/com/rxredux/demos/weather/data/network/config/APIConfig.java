package com.rxredux.demos.weather.data.network.config;

/**
 * Simple class holding all configuration related with the open weather api
 */
public class APIConfig {
  public static final String API_KEY = "e0ff4b5c4d103cc4cec445e2a77edab6";
  public static final String BASE_URL = "http://api.openweathermap.org";
  public static final String API_VERSION = "2.5";
  public static final String WEATHER_PATH = BASE_URL + "/data/" + API_VERSION + "/weather";
}
