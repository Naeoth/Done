package com.alexis.done.view.activities;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;

import com.alexis.done.R;
import com.alexis.done.model.Task;

public class UpdateTaskActivity extends AddTaskActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initControllers();
        initDefaultDisplay();
    }

    @Override
    protected void initDefaultDisplay() {
        TextView displayTitleView = (TextView) findViewById(R.id.title_view_addTask);
        displayTitleView.setText(R.string.title_view_update_task);

        Bundle bundle = getIntent().getExtras();
        Task currentTask = bundle.getParcelable("task");

        EditText title = (EditText) findViewById(R.id.input_title_addTask);
        title.setText( currentTask.getTitle() );

        Spinner typeList = (Spinner) findViewById(R.id.list_type_addTask);
        typeList.setSelection( currentTask.getType() );

        TextView date = (TextView) findViewById(R.id.display_date_addTask);
        date.setText( currentTask.getDate() );

        TextView time = (TextView) findViewById(R.id.display_time_addTask);
        time.setText( currentTask.getTime() );

        TextView duration = (TextView) findViewById(R.id.display_duration_addTask);
        duration.setText( currentTask.getDuration() );

        EditText description = (EditText) findViewById(R.id.input_description_addTask);
        description.setText( currentTask.getDescription() );

        SeekBar progress = (SeekBar) findViewById(R.id.progressBar_addTask);
        progress.setProgress( currentTask.getProgress() );
    }
}
