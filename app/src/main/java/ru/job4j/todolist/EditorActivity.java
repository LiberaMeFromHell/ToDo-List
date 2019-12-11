package ru.job4j.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import ru.job4j.todolist.model.database.SqlStore;

public class EditorActivity extends AppCompatActivity {

    TextView name;
    TextView desc;
    int requestCode;
    int id;

    SqlStore sqlStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);

        sqlStore =  new SqlStore(this);

        String strName = getIntent().getStringExtra("name");
        String strDesc = getIntent().getStringExtra("desc");
        requestCode = getIntent().getIntExtra("requestCode", 0);
        id = getIntent().getIntExtra("id", 0);

        if (strName != null && strDesc != null) {
            name.setText(strName);
            desc.setText(strDesc);
        }
    }

    private void saveChanges() {

        Intent intent = new Intent();

        if (requestCode == MainActivity.EDIT_TASK) {
            intent.putExtra("id", id);
        }

        intent.putExtra("editedName", name.getText().toString());
        intent.putExtra("editDesc", desc.getText().toString());

        setResult(RESULT_OK, intent);
    }

    @Override
    public void finish() {
        saveChanges();
        super.finish();
    }
}
