package com.rxredux.demos.todolist.states;

import com.fred.rxredux.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The todo list state
 */
public class TodoList extends State {
  private final List<Todo> todoList;

  private TodoList(List<Todo> todoList) {
    this.todoList = todoList;
  }

  public List<Todo> getTodoList() {
    return Collections.unmodifiableList(todoList);
  }

  public static class Builder {
    private List<Todo> todoList;

    public Builder todoList(List<Todo> todoList) {
      this.todoList = todoList;
      return this;
    }

    public TodoList build() {
      return new TodoList((todoList == null) ? new ArrayList<Todo>() : todoList);
    }
  }
}
