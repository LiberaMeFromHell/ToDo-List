package ru.job4j.todolist.model;

import android.content.ContentValues;

import java.util.List;

public interface IStore {
    List<Task> getTasks();
    void addTask(String table, String columnHack, ContentValues value);
    void removeTask(Task task);
    void editTask(Task task);
}
