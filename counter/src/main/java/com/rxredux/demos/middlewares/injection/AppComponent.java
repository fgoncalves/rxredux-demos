package com.rxredux.demos.middlewares.injection;

import com.rxredux.demos.middlewares.views.CounterView;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Application component
 * <p/>
 * Created by fred on 16.07.16.
 */
@Singleton @Component(modules = { ReduxModule.class }) public interface AppComponent {
  void inject(CounterView view);
}
