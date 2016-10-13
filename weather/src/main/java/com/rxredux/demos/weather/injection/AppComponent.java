package com.rxredux.demos.weather.injection;

import com.rxredux.demos.weather.views.MainView;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { ApiModule.class, ReduxModule.class })
public interface AppComponent {
  void inject(MainView view);
}
