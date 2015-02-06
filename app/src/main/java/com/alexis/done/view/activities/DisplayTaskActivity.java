package com.alexis.done.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.alexis.done.R;
import com.alexis.done.model.Task;

public class DisplayTaskActivity extends UpdateTaskActivity {

    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Bundle bundle = getIntent().getExtras();
        currentTask = bundle.getParcelable("aTask");

        initDefaultDisplay();
    }

    protected void initDefaultDisplay() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.initDefaultDisplay();

        TextView displayTitleView = (TextView) findViewById(R.id.title_view_addTask);
        displayTitleView.setText(R.string.title_view_display_task);

        EditText title = (EditText) findViewById(R.id.input_title_addTask);
        title.setFocusableInTouchMode(false);

        Spinner typeList = (Spinner) findViewById(R.id.list_type_addTask);
        typeList.setEnabled(false);

        Button buttonDate = (Button) findViewById(R.id.button_input_date_addTask);
        buttonDate.setVisibility(View.INVISIBLE);

        Button buttonTime = (Button) findViewById(R.id.button_input_time_addTask);
        buttonTime.setVisibility(View.INVISIBLE);

        Button buttonDuration = (Button) findViewById(R.id.button_input_duration_addTask);
        buttonDuration.setVisibility(View.INVISIBLE);

        EditText description = (EditText) findViewById(R.id.input_description_addTask);
        description.setHint(R.string.hint_description_view_display_task);
        description.setFocusableInTouchMode(false);

        SeekBar progress = (SeekBar) findViewById(R.id.progressBar_addTask);
        progress.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        EditText url = (EditText) findViewById(R.id.input_url_addTask);
        url.setHint(R.string.hint_url_view_display_task);
        url.setFocusableInTouchMode(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_update) {
            Intent updateTask = new Intent(this, UpdateTaskActivity.class);
            updateTask.putExtra("aTask", currentTask);
            startActivityForResult(updateTask, 1);
        }
        else {
            finish();
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            currentTask = data.getParcelableExtra("returnedTask");
            refreshView(currentTask);
            // Code a compléter
        }
    }

}