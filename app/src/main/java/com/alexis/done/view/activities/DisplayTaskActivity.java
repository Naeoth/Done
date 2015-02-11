/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class DisplayTaskActivity.java
 */

package com.alexis.done.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
import com.alexis.done.controller.ButtonsListener;
import com.alexis.done.model.Task;

/**
 * This class is the activity which displays the information of a task.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class DisplayTaskActivity extends ActionBarActivity {

    /**
     * The currentTask displayed.
     */
    protected Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initDefaultDisplay();
        initControllers();
    }

    protected void initDefaultDisplay() {
        // Initializes the current task with the task sent by the calling activity (the main activity).
        currentTask = getIntent().getParcelableExtra("aTask");

        // Displays the return button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Changes the name of the title view.
        TextView displayTitleView = (TextView) findViewById(R.id.title_view_addTask);
        displayTitleView.setText(R.string.title_view_display_task);

        // Disables the edit text and hides the buttons.

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

        // Displays the information from the task on the view.
        refreshView();
    }

    /**
     * Initializes the controllers with the view.
     */
    protected void initControllers() {
        Button runWebView = (Button) findViewById(R.id.button_webView_url_addTask);
        runWebView.setOnClickListener( ButtonsListener.getInstance() );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_update) {
            // Action add a task if selected.
            Intent updateTask = new Intent(this, AddTaskActivity.class);
            updateTask.putExtra("aTask", currentTask);
            startActivityForResult(updateTask, MainActivity.UPDATE_REQUEST_CODE);
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
            // Updates the list when the user updates from the display activity.
            Intent returnTask = new Intent();
            returnTask.putExtra("returnedTask", data.getParcelableExtra("returnedTask") );
            setResult(RESULT_OK, returnTask);
            finish();
        }
    }

    /**
     * Refreshes the view with the information from the current task.
     */
    protected void refreshView() {
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

        TextView progressValue = (TextView) findViewById(R.id.value_progressBar_addTask);
        progressValue.setText( currentTask.getProgress() + "%" );

        EditText url = (EditText) findViewById(R.id.input_url_addTask);
        url.setText( currentTask.getUrl() );
    }

}