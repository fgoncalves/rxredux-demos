package com.rxredux.demos.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import com.rxredux.demos.todolist.views.MainView;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MainView view = (MainView) LayoutInflater.from(this).inflate(R.layout.activity_main, null);
    ((TodoListApplication) getApplication()).getAppComponent().inject(view);
    setContentView(view);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }
}
