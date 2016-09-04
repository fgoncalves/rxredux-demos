package com.rxredux.demos.middlewares;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.rxredux.demos.middlewares.injection.AppComponent;
import com.rxredux.demos.middlewares.injection.DaggerAppComponent;
import com.rxredux.demos.middlewares.injection.ReduxModule;
import com.rxredux.demos.middlewares.views.CounterView;

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.main_content) FrameLayout mainContent;
  private AppComponent appComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    ButterKnife.bind(this);

    CounterView view = (CounterView) LayoutInflater.from(this).inflate(R.layout.counter, null);
    getAppComponent().inject(view);

    mainContent.addView(view);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    appComponent = null;
  }

  private AppComponent getAppComponent() {
    if (appComponent == null) {
      appComponent = DaggerAppComponent.builder().reduxModule(new ReduxModule()).build();
    }

    return appComponent;
  }
}
