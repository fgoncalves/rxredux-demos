package com.rxredux.demos.todolist;

import com.fred.rxredux.Reducer;
import com.rxredux.demos.todolist.actions.todos.TodoAction;
import com.rxredux.demos.todolist.states.Todo;
import com.rxredux.demos.todolist.states.TodoList;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the root reducer for the todo list
 */
public class RootReducer implements Reducer<TodoList, TodoAction> {
  @Override public TodoList call(TodoAction todoAction, TodoList todoList) {
    switch (todoAction.getType()) {
      case ADD_TODO: {
        Todo newTodo = new Todo.Builder().text(todoAction.getText()).build();
        List<Todo> newTodoList = new ArrayList<>(todoList.getTodoList());
        newTodoList.add(newTodo);

        return todoList(newTodoList);
      }
    }

    return todoList;
  }

  private TodoList todoList(List<Todo> list) {
    return new TodoList.Builder().todoList(list).build();
  }
}
