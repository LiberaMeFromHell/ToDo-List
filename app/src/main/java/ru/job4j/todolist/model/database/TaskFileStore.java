package ru.job4j.todolist.model.database;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;

import ru.job4j.todolist.model.FileStore;
import ru.job4j.todolist.model.Task;

public class TaskFileStore implements FileStore {

    private int counter = 0;
    private Context context;

    public TaskFileStore(Context context) {
        this.context = context;
    }

    @Override
    public void add(Task task) {
        File file = new File(context.getFilesDir(), (counter++) + ".txt");
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            out.println(task.getName());
            out.println(task.getCreated());
            out.println(task.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return context.getFilesDir().listFiles().length;
    }

    @Override
    public Task get(int index) {
        Task task = new Task(null, null,null);
        File file = new File(context.getFilesDir(), index + ".txt");
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            task.setName(in.readLine());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.parseLong(in.readLine()));
            task.setCreated(cal.getTime());
            task.setClosed(Boolean.parseBoolean(in.readLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }
}