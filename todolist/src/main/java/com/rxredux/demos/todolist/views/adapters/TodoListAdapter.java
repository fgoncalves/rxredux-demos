package com.rxredux.demos.todolist.views.adapters;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fred.rxredux.Store;
import com.rxredux.demos.todolist.R;
import com.rxredux.demos.todolist.actions.todos.TodoAction;
import com.rxredux.demos.todolist.states.Todo;
import com.rxredux.demos.todolist.states.TodoList;
import javax.inject.Inject;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
  private final Store<TodoList, TodoAction> store;

  @Inject public TodoListAdapter(Store<TodoList, TodoAction> store) {
    this.store = store;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Todo todo = store.state().getTodoList().get(position);
    holder.textView.setText(todo.getText());
    if (todo.isDone()) {
      holder.textView.setPaintFlags(holder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    } else {
      holder.textView.setPaintFlags(holder.textView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
    }
  }

  @Override public int getItemCount() {
    return store.state().getTodoList().size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.todo_text) TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.delete_button) public void deleteTodo() {
      store.dispatch(TodoAction.deleteTodo(textView.getText().toString()));
    }

    @OnClick(R.id.todo_text) public void toggleTodo() {
      store.dispatch(TodoAction.toggleTodo(textView.getText().toString()));
    }
  }
}
