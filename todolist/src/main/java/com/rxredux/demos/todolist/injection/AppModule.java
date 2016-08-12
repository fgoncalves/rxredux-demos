package com.rxredux.demos.todolist.injection;

import android.support.v7.widget.RecyclerView;
import com.rxredux.demos.todolist.views.adapters.TodoListAdapter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class AppModule {
  @Provides @Singleton
  public RecyclerView.Adapter<TodoListAdapter.ViewHolder> providesTodoListAdapter(
      TodoListAdapter adapter) {
    return adapter;
  }
}
