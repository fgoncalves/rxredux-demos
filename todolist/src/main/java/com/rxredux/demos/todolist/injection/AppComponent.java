package com.rxredux.demos.todolist.injection;

import com.rxredux.demos.todolist.states.TodoList;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { ReduxModule.class }) public interface AppComponent {
  void inject(TodoList todoList);
}
