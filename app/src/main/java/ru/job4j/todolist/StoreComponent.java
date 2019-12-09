package ru.job4j.todolist;

import android.content.ContentProvider;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = StoreModule.class)

@Singleton
public interface StoreComponent {
    void injectTo(MainActivity activity);
}