package ru.job4j.todolist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import ru.job4j.todolist.model.ConfirmDeletionDialogFragment;
import ru.job4j.todolist.model.IStore;
import ru.job4j.todolist.model.database.SqlStore;

import static ru.job4j.todolist.MainActivity.EDIT_TASK;

public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.TaskHolder> implements ConfirmDeletionDialogFragment.ConfirmHintDialogListener {

    private AppCompatActivity activity;
    private IStore sqlStore;

    public TaskRecyclerAdapter(AppCompatActivity activity, IStore store) {
        this.activity = activity;
        this.sqlStore = store;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, final int position) {
        TextView name = holder.itemView.findViewById(R.id.name);
        name.setText(sqlStore.getTasks().get(position).getName());
        TextView desc = holder.itemView.findViewById(R.id.desc);
        desc.setText(sqlStore.getTasks().get(position).getDesc());

        LinearLayout task_layout = holder.itemView.findViewById(R.id.task_layout);
        task_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("name", sqlStore.getTasks().get(position).getName());
                intent.putExtra("desc", sqlStore.getTasks().get(position).getDesc());
                activity.startActivity(intent);
            }
        });

        task_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                DialogFragment dialog = new ConfirmDeletionDialogFragment(TaskRecyclerAdapter.this, position);
                dialog.show(activity.getSupportFragmentManager(), "dialog_tag");
                return false;
            }
        });

        ImageButton editButton = holder.itemView.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditorActivity.class);
                intent.putExtra("name", sqlStore.getTasks().get(position).getName());
                intent.putExtra("desc", sqlStore.getTasks().get(position).getDesc());
                intent.putExtra("id", sqlStore.getTasks().get(position).getId());
                intent.putExtra("requestCode", EDIT_TASK);
                activity.startActivityForResult(intent, EDIT_TASK);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sqlStore.getTasks().size();
    }

    @Override
    public void onPositiveDialogClick(int position) {
        sqlStore.removeTask(sqlStore.getTasks().get(position));
        notifyDataSetChanged();
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        TaskHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
