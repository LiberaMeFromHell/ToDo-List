package ru.job4j.todolist;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.job4j.todolist.model.IStore;
import ru.job4j.todolist.model.database.SqlStore;

@Module
public class StoreModule {
    @Singleton
    @Provides
    public IStore providesSQLStore(Context context) {
        return new SqlStore(context);
    }
}
