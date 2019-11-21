package ru.job4j.todolist;

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

import ru.job4j.todolist.model.Store;
import ru.job4j.todolist.model.Task;
import ru.job4j.todolist.model.TaskStore;

public class MainActivity extends AppCompatActivity {

    public static final int EDIT_TASK = 123;
    public static final int NEW_TASK = 124;

    TaskRecyclerAdapter taskRecyclerAdapter;

    FloatingActionButton fab;
    Store tasks = new TaskStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        taskRecyclerAdapter = new TaskRecyclerAdapter(tasks.getTasks(), this);
        recyclerView.setAdapter(taskRecyclerAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult: " + resultCode + ", asdsad " + requestCode);
        switch (requestCode) {

            case (EDIT_TASK):
                int position = data.getIntExtra("position", 0);
                String strName = data.getStringExtra("editedName");
                String strDesc = data.getStringExtra("editDesc");
                taskRecyclerAdapter.getTasks().get(position).setName(strName);
                taskRecyclerAdapter.getTasks().get(position).setDesc(strDesc);
                taskRecyclerAdapter.notifyDataSetChanged();
                break;

            case(NEW_TASK):
                Task task = new Task(
                        data.getStringExtra("editedName"),
                        data.getStringExtra("editDesc"),
                        new Date());
                taskRecyclerAdapter.addTask(task);
                taskRecyclerAdapter.notifyDataSetChanged();
        }
    }
}

