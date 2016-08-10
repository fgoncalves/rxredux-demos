package com.rxredux.demos.todolist.injection;

import com.fred.rxredux.Store;
import com.fred.rxredux.StoreImpl;
import com.fred.rxredux.transformers.SchedulerTransformer;
import com.rxredux.demos.todolist.RootReducer;
import com.rxredux.demos.todolist.actions.todos.TodoAction;
import com.rxredux.demos.todolist.states.Todo;
import com.rxredux.demos.todolist.states.TodoList;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Module providing the redux dependencies
 */
@Module public class ReduxModule {
  @Provides @Singleton public SchedulerTransformer providesIoToMainSchedulerTransformer() {
    return new SchedulerTransformer() {
      @Override public <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
          public Observable<T> call(Observable<T> observable) {
            return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
          }
        };
      }
    };
  }

  @Provides @Singleton public RootReducer provideRootReducer() {
    return new RootReducer();
  }

  @Named("rootreducer.initial.state") @Provides @Singleton public TodoList providesInitialState() {
    return new TodoList.Builder().todoList(new ArrayList<Todo>()).build();
  }

  @Provides @Singleton public Store<TodoList, TodoAction> provideStore(RootReducer rootReducer,
      SchedulerTransformer transformer, @Named("rootreducer.initial.state") TodoList initialState) {
    return StoreImpl.create(rootReducer, initialState, transformer);
  }
}
