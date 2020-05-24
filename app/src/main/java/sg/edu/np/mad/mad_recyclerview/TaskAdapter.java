package sg.edu.np.mad.mad_recyclerview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    public ArrayList<Task> taskList;

    public TaskAdapter(ArrayList tList) {
        this.taskList = tList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodoViewHolder taskViewHolder;
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.todolistlayout,
                parent,
                false
        );
        taskViewHolder = new TodoViewHolder(item);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, final int position) {
        Task task = taskList.get(position);
        holder.TaskDescriptionTextView.setText(task.GetTaskDescription());
        holder.TaskStatusCheckBox.setChecked(task.GetTaskStatus());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Build Alert Dialog
                String descOfSelectedItem = taskList.get(position).GetTaskDescription();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete");
                builder.setMessage(Html.fromHtml(
                        "<div style='text-align: center'>Are you sure you want to delete<br /><b>" + descOfSelectedItem + "</b>?</div>"
                ));
                builder.setIcon(android.R.drawable.ic_menu_delete); //Search on this, its useful.
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        taskList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.create().show();
            }
        };
        holder.TaskDescriptionTextView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}