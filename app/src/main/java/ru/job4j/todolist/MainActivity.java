package ru.job4j.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import javax.inject.Inject;

import ru.job4j.todolist.model.IStore;
import ru.job4j.todolist.model.Task;
import ru.job4j.todolist.model.database.SqlStore;
import ru.job4j.todolist.model.database.TaskDBSchema;

public class MainActivity extends AppCompatActivity {

    public static final int EDIT_TASK = 123;
    public static final int NEW_TASK = 124;

    private TaskRecyclerAdapter taskRecyclerAdapter;

    private FloatingActionButton fab;

    @Inject
    IStore tasks;

    @Inject
    IStore sqlStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getStore().injectTo(this);
        //sqlStore = new SqlStore(this);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                intent.putExtra("requestCode", NEW_TASK);
                startActivityForResult(intent, NEW_TASK);
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerAdapter = new TaskRecyclerAdapter(this, sqlStore);
        recyclerView.setAdapter(taskRecyclerAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult: " + resultCode + ", asdsad " + requestCode);
        switch (requestCode) {

            case (EDIT_TASK):
                int id = data.getIntExtra("id", 0);
                String strName = data.getStringExtra("editedName");
                String strDesc = data.getStringExtra("editDesc");

                sqlStore.editTask(new Task(id, strName, strDesc, null));
                taskRecyclerAdapter.notifyDataSetChanged();
                break;

            case (NEW_TASK):
                ContentValues value = new ContentValues();
                value.put(TaskDBSchema.TaskTable.Cols.NAME, data.getStringExtra("editedName"));
                value.put(TaskDBSchema.TaskTable.Cols.DESC, data.getStringExtra("editDesc"));
                value.put(TaskDBSchema.TaskTable.Cols.CREATED, (new Date()).getTime());

                sqlStore.addTask(TaskDBSchema.TaskTable.TITLE, null, value);
                taskRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
