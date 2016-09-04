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

  /**
   * Delete a todo from the list. The text must match exactly the text on the todo
   *
   * @param text Todo's text
   * @return A delete action for the given todo
   */
  public static TodoAction deleteTodo(String text) {
    return new TodoAction(TodoActionType.DELETE_TODO, text);
  }
}
