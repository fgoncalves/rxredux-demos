package com.rxredux.demos.todolist.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.fred.rxredux.Store;
import com.rxredux.demos.todolist.actions.todos.TodoAction;
import com.rxredux.demos.todolist.states.TodoList;
import javax.inject.Inject;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
  private final Store<TodoList, TodoAction> store;

  @Inject public TodoListAdapter(Store<TodoList, TodoAction> store) {
    this.store = store;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
