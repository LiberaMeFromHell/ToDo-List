package ru.job4j.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView name;
    TextView desc;
    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);

        String strName = getIntent().getStringExtra("name");
        String strDesc = getIntent().getStringExtra("desc");

        requestCode = getIntent().getIntExtra("requestCode", 0);

        if (strName != null && strDesc != null) {
            name.setText(strName);
            desc.setText(strDesc);
        }
    }
}
