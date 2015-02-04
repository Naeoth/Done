package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;
import com.alexis.done.controller.SeekBarsListener;

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

    private void initDefaultDisplay() {
        TextView displayTitleView = (TextView) findViewById(R.id.title_addTask);
        displayTitleView.setText(R.string.title_view_add_task);

        TextView displayInputtedDate = (TextView) findViewById(R.id.display_date_addTask);
        TextView displayInputtedTime = (TextView) findViewById(R.id.display_time_addTask);

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        displayInputtedDate.setText( dateFormat.format(currentDate) );
        displayInputtedTime.setText( hourFormat.format(currentDate) );
    }

    private void initControllers() {
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
