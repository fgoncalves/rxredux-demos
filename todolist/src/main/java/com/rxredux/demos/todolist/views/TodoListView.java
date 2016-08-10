package com.rxredux.demos.todolist.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import com.fred.rxredux.Store;
import com.rxredux.demos.todolist.actions.todos.TodoAction;
import com.rxredux.demos.todolist.states.TodoList;
import javax.inject.Inject;
import rx.Subscription;

/**
 * View for the todo list
 */
public class TodoListView extends RecyclerView {
  @Inject Store<TodoList, TodoAction> store;

  private Subscription subscription;

  public TodoListView(Context context) {
    super(context);
  }

  public TodoListView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public TodoListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    subscription = store.subscribe(new TodoListSubscriber());
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    subscription.unsubscribe();
  }

  private class TodoListSubscriber extends rx.Subscriber<TodoList> {
    @Override public void onCompleted() {

    }

    @Override public void onError(Throwable e) {
      Log.e(getClass().getSimpleName(), "Error while getting state updates from the store", e);
    }

    @Override public void onNext(TodoList todoList) {
      getAdapter().notifyDataSetChanged();
    }
  }
}
