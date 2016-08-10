package com.rxredux.demos.todolist.actions.todos;

import com.fred.rxredux.Action;

/**
 * A todo action
 */
public class TodoAction extends Action<TodoActionType> {
  private String text;

  private TodoAction(TodoActionType type, String text) {
    super(type);
    this.text = text;
  }

  public String getText() {
    return text;
  }

  /**
   * Add a todo to the todo list
   *
   * @param text The text to display in the todo
   * @return An add todo action
   */
  public static TodoAction addTodo(String text) {
    return new TodoAction(TodoActionType.ADD_TODO, text);
  }
}
