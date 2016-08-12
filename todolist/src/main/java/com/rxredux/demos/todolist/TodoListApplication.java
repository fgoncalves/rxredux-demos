package com.rxredux.demos.todolist;

import android.app.Application;
import com.rxredux.demos.todolist.injection.AppComponent;
import com.rxredux.demos.todolist.injection.DaggerAppComponent;
import com.rxredux.demos.todolist.injection.ReduxModule;

public class TodoListApplication extends Application {
  private AppComponent appComponent;

  public AppComponent getAppComponent() {
    if (appComponent == null) {
      appComponent = DaggerAppComponent.builder().reduxModule(new ReduxModule()).build();
    }
    return appComponent;
  }
}
