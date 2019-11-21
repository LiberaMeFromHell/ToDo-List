package ru.job4j.todolist.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskStore implements Store {

    private List<Task> tasks = new ArrayList();

    public TaskStore() {
        tasks.add(new Task("Вынести мусор", "Вынести мусор", new Date(123412341234134L)));
        tasks.add(new Task("Помыть машину", "Помыть машину", new Date(123412371234134L)));
        tasks.add(new Task("Сходить в магазин", "Сходить в магазин", new Date(123412541234134L)));
        tasks.add(new Task("Покормить кота", "Покормить кота", new Date(123412341034134L)));
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
    }
}
