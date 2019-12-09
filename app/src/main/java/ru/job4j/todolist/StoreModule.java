package ru.job4j.todolist;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.job4j.todolist.model.Store;
import ru.job4j.todolist.model.TaskStore;

@Module
public class StoreModule {
    @Singleton
    @Provides
    public Store providesStore() {
        return new TaskStore();
    }
}
