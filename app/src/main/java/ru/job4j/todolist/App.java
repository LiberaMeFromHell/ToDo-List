package ru.job4j.todolist;

import android.app.Application;

public class App extends Application {
    private static StoreComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerStoreComponent.create();
    }

    public static StoreComponent getStore() {
        return component;
    }
}
