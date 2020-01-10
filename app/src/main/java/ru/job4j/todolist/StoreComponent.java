package ru.job4j.todolist;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {StoreModule.class,App.class,FileStoreModule.class})

@Singleton
public interface StoreComponent {
    void injectTo(MainActivity activity);
}
