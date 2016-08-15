package com.rxredux.demos.todolist.views;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fred.rxredux.Store;
import com.rxredux.demos.todolist.R;
import com.rxredux.demos.todolist.actions.todos.TodoAction;
import com.rxredux.demos.todolist.states.TodoList;
import com.rxredux.demos.todolist.views.adapters.TodoListAdapter;
import javax.inject.Inject;
import rx.Subscription;

/**
 * The main view of the app
 */
public class MainView extends CoordinatorLayout {
  @Inject Store<TodoList, TodoAction> store;
  @Inject RecyclerView.Adapter<TodoListAdapter.ViewHolder> adapter;

  @BindView(R.id.fab) FloatingActionButton fab;
  @BindView(R.id.todo_list) RecyclerView todoListView;

  private Subscription subscription;

  public MainView(Context context) {
    super(context);
  }

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
    todoListView.setLayoutManager(new LinearLayoutManager(getContext()));
  }

  @Override public void onAttachedToWindow() {
    super.onAttachedToWindow();
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        final EditText editText =
            (EditText) LayoutInflater.from(getContext()).inflate(R.layout.todo_edit_text, null);
        new AlertDialog.Builder(getContext()).setView(editText)
            .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
              @Override public void onClick(DialogInterface dialogInterface, int i) {
                String text = editText.getText().toString().trim();
                if (text.isEmpty()) return;

                dispatchTodo(text);
              }
            })
            .show();
      }
    });
    todoListView.setAdapter(adapter);
    if (isInEditMode()) return;
    subscription = store.subscribe(new TodoListSubscriber());
  }

  @Override public void onDetachedFromWindow() {
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
      todoListView.getAdapter().notifyDataSetChanged();
    }
  }

  private void dispatchTodo(String text) {
    TodoAction action = TodoAction.addTodo(text);
    store.dispatch(action);
  }
}
