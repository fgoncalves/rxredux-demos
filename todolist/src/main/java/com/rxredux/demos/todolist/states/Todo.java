package com.rxredux.demos.todolist.states;

/**
 * State for a single Todo
 */
public class Todo {
  private final String text;
  private final boolean done;

  private Todo(String text, boolean done) {
    this.text = text;
    this.done = done;
  }

  public String getText() {
    return text;
  }

  public boolean isDone() {
    return done;
  }

  public static class Builder {
    private String text;
    private boolean done;

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public Builder done(boolean done) {
      this.done = done;
      return this;
    }

    public Todo build() {
      return new Todo(text, done);
    }
  }
}
