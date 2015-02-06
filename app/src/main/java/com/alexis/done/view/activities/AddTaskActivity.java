package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
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

public class AddTaskActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initDefaultDisplay();
        initControllers();
    }

    protected void initDefaultDisplay() {
        TextView displayTitleView = (TextView) findViewById(R.id.title_view_addTask);
        displayTitleView.setText(R.string.title_view_add_task);

        Date currentDate = new Date();

        TextView displayInputtedDate = (TextView) findViewById(R.id.display_date_addTask);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        displayInputtedDate.setText( dateFormat.format(currentDate) );

        TextView displayInputtedTime = (TextView) findViewById(R.id.display_time_addTask);
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        displayInputtedTime.setText( hourFormat.format(currentDate) );

        TextView inputUrl = (TextView) findViewById(R.id.input_url_addTask);
        inputUrl.setAutoLinkMask(Linkify.WEB_URLS);
        inputUrl.setMovementMethod( LinkMovementMethod.getInstance() );
        inputUrl.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Linkify.addLinks(s, Linkify.WEB_URLS);
            }
        });
        //inputUrl.
    }

    protected void initControllers() {
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
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ) {
            case R.id.action_done:
                EditText title = (EditText) findViewById(R.id.input_title_addTask);
                String taskTitle = title.getText().toString();

                Spinner typeList = (Spinner) findViewById(R.id.list_type_addTask);
                int taskType = typeList.getSelectedItemPosition();

                TextView date = (TextView) findViewById(R.id.display_date_addTask);
                String taskDate = date.getText().toString();

                TextView time = (TextView) findViewById(R.id.display_time_addTask);
                String taskTime = time.getText().toString();

                TextView duration = (TextView) findViewById(R.id.display_duration_addTask);
                String taskDuration = duration.getText().toString();

                EditText description = (EditText) findViewById(R.id.input_description_addTask);
                String taskDescription = description.getText().toString();

                SeekBar progress = (SeekBar) findViewById(R.id.progressBar_addTask);
                int taskProgress = progress.getProgress();

                EditText url = (EditText) findViewById(R.id.input_url_addTask);
                String taskUrl = url.getText().toString();

                if (title.length() == 0) {
                    Toast.makeText(this, R.string.emptyTitle_messageError, Toast.LENGTH_LONG).show();
                }
                else if ( !Patterns.WEB_URL.matcher(taskUrl).matches() && !taskUrl.equals("") ) {
                    Toast.makeText(this, R.string.wrongUrl_messageError, Toast.LENGTH_LONG).show();
                }
                else {
                    Intent returnTask = new Intent();
                    Task newTask = new Task(0, taskTitle, taskType, taskDate, taskTime, taskDuration, taskDescription, taskProgress, taskUrl);
                    returnTask.putExtra("returnedTask", newTask);
                    setResult(RESULT_OK, returnTask);
                    finish();
                }
                break;

            case R.id.action_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if ( data.hasExtra("dateDay") && data.hasExtra("dateMonth") && data.hasExtra("dateYear") ) {
                TextView displayInputtedDate = (TextView) findViewById(R.id.display_date_addTask);
                String date = data.getExtras().getInt("dateDay") + "/" + data.getExtras().getInt("dateMonth") + "/" + data.getExtras().getInt("dateYear");
                displayInputtedDate.setText( formatDisplay(date, "dd/MM/yyyy") );
            }
            else if ( data.hasExtra("timeHour") && data.hasExtra("timeMin") ) {
                TextView displayInputtedTime = (TextView) findViewById(R.id.display_time_addTask);
                String time = data.getExtras().getInt("timeHour") + ":" + data.getExtras().getInt("timeMin");
                displayInputtedTime.setText( formatDisplay(time, "HH:mm") );
            }
            else if ( data.hasExtra("durationHour") && data.hasExtra("durationMin") ) {
                TextView displayInputtedDuration = (TextView) findViewById(R.id.display_duration_addTask);
                String hour = data.getExtras().getInt("durationHour") + "h" ;
                String min = formatDisplay(Integer.toString(data.getExtras().getInt("durationMin")), "mm");
                displayInputtedDuration.setText(hour + min);
            }
        }
    }

    private String formatDisplay(String input, String typeFormat) {
        String ret = "";

        try {
            SimpleDateFormat outputFormat = new SimpleDateFormat(typeFormat);
            ret = outputFormat.format( outputFormat.parse(input) );
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return ret;
    }

}
