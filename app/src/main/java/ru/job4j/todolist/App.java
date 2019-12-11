package ru.job4j.todolist;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class App extends Application {

    private Context context;
    private static StoreComponent component;

    @Provides
    @Singleton
    public Context provideAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        component = DaggerStoreComponent.create();
    }

    public static StoreComponent getStore() {
        return component;
    }
}
