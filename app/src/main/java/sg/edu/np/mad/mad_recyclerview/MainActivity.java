package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView toDoListRecyclerView;
    private EditText addToDoDesc;
    private Button addTaskBut;
    private TaskAdapter taskListAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addTaskBut = (Button) findViewById(R.id.addTaskBut);
        addToDoDesc = (EditText) findViewById(R.id.addTaskDesc);
        toDoListRecyclerView = (RecyclerView) findViewById(R.id.toDoListLayout);
        final ArrayList<Task> taskList = new ArrayList<>();
        final Task t = new Task("Buy milk");
        final Task t1 = new Task("Send postage");
        final Task t2 = new Task("Buy Android development book");
        taskList.add(t);
        taskList.add(t1);
        taskList.add(t2);




        addTaskBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NewTaskDesc = addToDoDesc.getText().toString();

                if (NewTaskDesc.length() >0){
                    taskList.add(new Task(NewTaskDesc));
                    taskListAdapt.notifyDataSetChanged();
                    showNewEntry(toDoListRecyclerView,taskList);
                }
            }
        });

        taskListAdapt = new TaskAdapter(taskList);
        LinearLayoutManager toDoListLayout = new LinearLayoutManager(this);

        toDoListRecyclerView.setLayoutManager(toDoListLayout);
        toDoListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        toDoListRecyclerView.setAdapter(taskListAdapt);

    }
    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }
}
