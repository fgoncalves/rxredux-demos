package com.rxredux.demos.todolist.injection;

import com.rxredux.demos.todolist.views.MainView;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { ReduxModule.class, AppModule.class })
public interface AppComponent {
  void inject(MainView mainView);
}
