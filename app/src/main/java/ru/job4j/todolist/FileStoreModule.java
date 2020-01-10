package ru.job4j.todolist;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.job4j.todolist.model.FileStore;
import ru.job4j.todolist.model.database.TaskFileStore;

@Module
public class FileStoreModule {
    @Singleton
    @Provides
    public FileStore providesFileStore(Context context) {
        return new TaskFileStore(context);
    }
}
