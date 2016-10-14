package com.rxredux.demos.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import com.rxredux.demos.weather.injection.ApiModule;
import com.rxredux.demos.weather.injection.AppComponent;
import com.rxredux.demos.weather.injection.DaggerAppComponent;
import com.rxredux.demos.weather.injection.ReduxModule;
import com.rxredux.demos.weather.views.MainView;

public class MainActivity extends AppCompatActivity {
  private AppComponent appComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    MainView view = (MainView) LayoutInflater.from(this).inflate(R.layout.activity_main, null);

    getAppComponent().inject(view);

    setContentView(view);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    appComponent = null;
  }

  public AppComponent getAppComponent() {
    if (appComponent == null) {
      appComponent = DaggerAppComponent.builder()
          .apiModule(new ApiModule())
          .reduxModule(new ReduxModule())
          .build();
    }
    return appComponent;
  }
}
