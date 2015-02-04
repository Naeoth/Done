package com.alexis.done.view.activities;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;

import com.alexis.done.R;

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
        TextView displayTitleView = (TextView) findViewById(R.id.title_addTask);
        displayTitleView.setText(R.string.title_view_update_task);

        EditText name = (EditText) findViewById(R.id.input_name_addTask);
        Spinner typeList = (Spinner) findViewById(R.id.list_type_addTask);
        TextView date = (TextView) findViewById(R.id.display_date_addTask);
        TextView time = (TextView) findViewById(R.id.display_time_addTask);
        TextView duration = (TextView) findViewById(R.id.display_duration_addTask);
        EditText description = (EditText) findViewById(R.id.input_description_addTask);
        SeekBar progress = (SeekBar) findViewById(R.id.progressBar_addTask);
    }

}
