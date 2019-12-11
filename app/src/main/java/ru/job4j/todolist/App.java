package ru.job4j.todolist;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class App extends Application {

    private static Context context;
    private static StoreComponent component;

    @Provides
    public Context provideAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        component = DaggerStoreComponent.create();
    }

    public static StoreComponent getStore() {
        return component;
    }
}
