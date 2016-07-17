package com.fred.rxreduxcounter.injection;

import com.fred.rxreduxcounter.views.CounterView;
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
