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

public class AddTaskActivity extends DisplayTaskActivity {

    protected void initDefaultDisplay() {
        TextView displayTitleView = (TextView) findViewById(R.id.title_view_addTask);

        if ( !getIntent().hasExtra("aTask") ) {
            currentTask = new Task(-1, "", 0, "", "", "", "", 0, "");

            displayTitleView.setText(R.string.title_view_add_task);

            Date currentDate = new Date();

            TextView displayInputtedDate = (TextView) findViewById(R.id.display_date_addTask);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            displayInputtedDate.setText( dateFormat.format(currentDate) );

            TextView displayInputtedTime = (TextView) findViewById(R.id.display_time_addTask);
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
            displayInputtedTime.setText( hourFormat.format(currentDate) );
        }
        else {
            currentTask = getIntent().getParcelableExtra("aTask");

            displayTitleView.setText(R.string.title_view_update_task);

            refreshView(currentTask);
        }
    }

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
            case R.id.action_done:
                EditText title = (EditText) findViewById(R.id.input_title_addTask);

                EditText url = (EditText) findViewById(R.id.input_url_addTask);
                String taskUrl = url.getText().toString();

                if (title.length() == 0) {
                    Toast.makeText(this, R.string.emptyTitle_messageError, Toast.LENGTH_LONG).show();
                }
                else if ( !Patterns.WEB_URL.matcher(taskUrl).matches() && !taskUrl.equals("") ) {
                    Toast.makeText(this, R.string.wrongUrl_messageError, Toast.LENGTH_LONG).show();
                }
                else {
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

                    Intent returnTask = new Intent();
                    returnTask.putExtra("returnedTask", currentTask);
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
