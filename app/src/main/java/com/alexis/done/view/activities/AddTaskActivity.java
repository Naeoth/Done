/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class AddTaskActivity.java
 */

package com.alexis.done.view.activities;

import android.content.Intent;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;
import com.alexis.done.controller.SeekBarsListener;
import com.alexis.done.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class handles the add and update actitivy.
 * It inherit from DisplayTaskActitivy to reuse code.
 * It has 2 modes : add and update, the difference it's the name of the view and the initialization of the view.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class AddTaskActivity extends DisplayTaskActivity {

    @Override
    protected void initDefaultDisplay() {
        TextView displayTitleView = (TextView) findViewById(R.id.title_view_addTask);

        // If it's the add mode.
        if ( !getIntent().hasExtra("aTask") ) {
            // Initializes the current task with an empty task.
            currentTask = new Task(-1, "", 0, "", "", "", "", 0, "");

            // Settings the title of the view.
            displayTitleView.setText(R.string.title_view_add_task);

            // Displays the current date and the current time.
            Date currentDate = new Date();

            TextView displayInputtedDate = (TextView) findViewById(R.id.display_date_addTask);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            displayInputtedDate.setText( dateFormat.format(currentDate) );

            TextView displayInputtedTime = (TextView) findViewById(R.id.display_time_addTask);
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
            displayInputtedTime.setText( hourFormat.format(currentDate) );
        }
        // If it's the update mode.
        else {
            // Initializes the current task with the task send by the calling activity.
            currentTask = getIntent().getParcelableExtra("aTask");

            // Settings the title of the view.
            displayTitleView.setText(R.string.title_view_update_task);

            // And displays the task information in the field.
            refreshView();
        }
    }

    @Override
    protected void initControllers() {
        super.initControllers();

        Button inputDate = (Button) findViewById(R.id.button_input_date_addTask);
        inputDate.setOnClickListener( ButtonsListener.getInstance() );

        Button inputTime = (Button) findViewById(R.id.button_input_time_addTask);
        inputTime.setOnClickListener( ButtonsListener.getInstance() );

        Button inputDuration = (Button) findViewById(R.id.button_input_duration_addTask);
        inputDuration.setOnClickListener( ButtonsListener.getInstance() );

        SeekBar progressBar = (SeekBar) findViewById(R.id.progressBar_addTask);
        progressBar.setOnSeekBarChangeListener(SeekBarsListener.getInstance() );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ) {
            // If the tick is clicked.
            case R.id.action_done:
                EditText title = (EditText) findViewById(R.id.input_title_addTask);

                EditText url = (EditText) findViewById(R.id.input_url_addTask);
                String taskUrl = url.getText().toString();

                // Checks if the title is not empty, sends an error message if not.
                if (title.length() == 0) {
                    Toast.makeText(this, R.string.emptyTitle_messageError, Toast.LENGTH_LONG).show();
                }
                // Checks if the url is a real url, sends an error  message if not.
                else if ( !Patterns.WEB_URL.matcher(taskUrl).matches() && !taskUrl.equals("") ) {
                    Toast.makeText(this, R.string.wrongUrl_messageError, Toast.LENGTH_LONG).show();
                }
                // If everything is alright we prepare the returned task.
                else {
                    // Sets the informations from the fields in the current task.

                    currentTask.setTitle( title.getText().toString() ) ;

                    Spinner typeList = (Spinner) findViewById(R.id.list_type_addTask);
                    currentTask.setType( typeList.getSelectedItemPosition() );

                    TextView date = (TextView) findViewById(R.id.display_date_addTask);
                    currentTask.setDate( date.getText().toString() );

                    TextView time = (TextView) findViewById(R.id.display_time_addTask);
                    currentTask.setTime( time.getText().toString() );

                    TextView duration = (TextView) findViewById(R.id.display_duration_addTask);
                    currentTask.setDuration( duration.getText().toString() );

                    EditText description = (EditText) findViewById(R.id.input_description_addTask);
                    currentTask.setDescription( description.getText().toString() );

                    SeekBar progress = (SeekBar) findViewById(R.id.progressBar_addTask);
                    currentTask.setProgress( progress.getProgress() );

                    currentTask.setUrl(taskUrl);

                    // Sends the current task to the calling activity.
                    Intent returnTask = new Intent();
                    returnTask.putExtra("returnedTask", currentTask);
                    setResult(RESULT_OK, returnTask);
                    finish();
                }
                break;

            // If the cross is clicked.
            case R.id.action_cancel:
                // Cancels the activity.
                setResult(RESULT_CANCELED);
                finish();
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Updates task information from information from the input activities.
        if (resultCode == RESULT_OK) {
            // Updates the date.
            if ( data.hasExtra("dateDay") && data.hasExtra("dateMonth") && data.hasExtra("dateYear") ) {
                TextView displayInputtedDate = (TextView) findViewById(R.id.display_date_addTask);
                String date = data.getExtras().getInt("dateDay") + "/" + data.getExtras().getInt("dateMonth") + "/" + data.getExtras().getInt("dateYear");
                displayInputtedDate.setText( formatDisplay(date, "dd/MM/yyyy") );
            }
            // Updates the time.
            else if ( data.hasExtra("timeHour") && data.hasExtra("timeMin") ) {
                TextView displayInputtedTime = (TextView) findViewById(R.id.display_time_addTask);
                String time = data.getExtras().getInt("timeHour") + ":" + data.getExtras().getInt("timeMin");
                displayInputtedTime.setText( formatDisplay(time, "HH:mm") );
            }
            // Updates the duration.
            else if ( data.hasExtra("durationHour") && data.hasExtra("durationMin") ) {
                TextView displayInputtedDuration = (TextView) findViewById(R.id.display_duration_addTask);
                String hour = data.getExtras().getInt("durationHour") + "h" ;
                String min = formatDisplay(Integer.toString(data.getExtras().getInt("durationMin")), "mm");
                displayInputtedDuration.setText(hour + min);
            }
        }
    }

    /**
     * Formats the input string with the format specified.
     * It uses to format the date, the time and the duration.
     *
     * @param input The string to format.
     * @param typeFormat The format of the output.
     *
     * @return The input string formatted.
     */
    private String formatDisplay(String input, String typeFormat) {
        String ret = "";

        try {
            // Creates the format.
            SimpleDateFormat outputFormat = new SimpleDateFormat(typeFormat);
            // Formats the specified string.
            ret = outputFormat.format( outputFormat.parse(input) );
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return ret;
    }

}