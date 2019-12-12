package ru.job4j.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import ru.job4j.todolist.model.database.SqlStore;

public class EditorActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private TextView name;
    private TextView desc;
    private int requestCode;
    private int id;

    private SqlStore sqlStore;

    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        photo = findViewById(R.id.photo);

        sqlStore =  new SqlStore(this);

        String strName = getIntent().getStringExtra("name");
        String strDesc = getIntent().getStringExtra("desc");
        requestCode = getIntent().getIntExtra("requestCode", 0);
        id = getIntent().getIntExtra("id", 0);

        if (strName != null && strDesc != null) {
            name.setText(strName);
            desc.setText(strDesc);
        }

        photo.setOnClickListener(btn -> {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    }
                }
        );
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photo.setImageBitmap(imageBitmap);
        }
    }
}
