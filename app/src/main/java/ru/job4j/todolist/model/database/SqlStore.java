package ru.job4j.todolist.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.job4j.todolist.model.IStore;
import ru.job4j.todolist.model.Task;

public class SqlStore implements IStore {

    private SQLiteDatabase store;
    private Context context;

    public SqlStore(Context context) {
        this.context = context;
        store = new TaskDBHelper(this.context).getWritableDatabase();
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<Task>();

        Cursor cursor = this.store.query(
                TaskDBSchema.TaskTable.TITLE,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tasks.add(new Task(
                    cursor.getInt(cursor.getColumnIndex(TaskDBSchema.TaskTable.Cols.ID)),
                    cursor.getString(cursor.getColumnIndex(TaskDBSchema.TaskTable.Cols.NAME)),
                    cursor.getString(cursor.getColumnIndex(TaskDBSchema.TaskTable.Cols.DESC)),
                    new Date(cursor.getLong(cursor.getColumnIndex(TaskDBSchema.TaskTable.Cols.CREATED)))
            ));
            cursor.moveToNext();
        }
        cursor.close();

        return tasks;
    }

    @Override
    public void addTask(String table, String columnHack, ContentValues value) {
        store.insert(table, columnHack, value);
    }

    @Override
    public void removeTask(Task task) {
        Log.d("stage", "delete: " + task.getId());
        store.delete(TaskDBSchema.TaskTable.TITLE, "id = ?", new String[]{String.valueOf(task.getId())});
    }

    @Override
    public void editTask(Task task) {
        Log.d("stage", "edit: " + task.getId());
        ContentValues value = new ContentValues();
        value.put(TaskDBSchema.TaskTable.Cols.NAME, task.getName());
        value.put(TaskDBSchema.TaskTable.Cols.DESC, task.getDesc());
        store.update(TaskDBSchema.TaskTable.TITLE, value, "id = ?", new String[]{String.valueOf(task.getId())});
    }
}
