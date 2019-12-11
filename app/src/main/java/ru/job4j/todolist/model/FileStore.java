package ru.job4j.todolist.model;

public interface FileStore {

    void add(Task task);
    int size();
    Task get(int index);
}