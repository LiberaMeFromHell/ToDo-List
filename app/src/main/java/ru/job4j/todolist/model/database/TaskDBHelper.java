package ru.job4j.todolist.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDBHelper extends SQLiteOpenHelper {
    public static final String DB = "tasks.db";
    public static final int VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskDBSchema.TaskTable.TITLE
                    + " (" + " id integer primary key autoincrement, "
                    + TaskDBSchema.TaskTable.Cols.NAME + " TEXT , "
                    + TaskDBSchema.TaskTable.Cols.DESC + " TEXT , "
                    + TaskDBSchema.TaskTable.Cols.CREATED + " LONG);";

    public TaskDBHelper(Context context) {
        super(context, DB, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
