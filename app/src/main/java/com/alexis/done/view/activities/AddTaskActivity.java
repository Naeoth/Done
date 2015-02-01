package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.alexis.done.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskActivity extends ActionBarActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        TextView displayInputtedDate = (TextView) findViewById(R.id.display_date_addTask);
        TextView displayInputtedTime = (TextView) findViewById(R.id.display_time_addTask);

        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

        displayInputtedDate.setText(dateFormat.format(currentDate));
        displayInputtedTime.setText( hourFormat.format(currentDate) );

        Button inputDate = (Button) findViewById(R.id.button_input_date_addTask);
        inputDate.setOnClickListener(this);

        Button inputTime = (Button) findViewById(R.id.button_input_time_addTask);
        inputTime.setOnClickListener(this);

        Button inputDuration = (Button) findViewById(R.id.button_input_duration_addTask);
        inputDuration.setOnClickListener(this);
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
    public void onClick(View v) {
        if (v.getId() == R.id.button_input_date_addTask) {
            Intent dateInputActivity = new Intent(this, InputDateActivity.class);
            startActivityForResult(dateInputActivity, 1);
        }
        else if (v.getId() == R.id.button_input_time_addTask) {
            Intent timeInputActivity = new Intent(this, InputTimeActivity.class);
            startActivityForResult(timeInputActivity, 2);
        }
        else if (v.getId() == R.id.button_input_duration_addTask) {
            Intent durationInputActivity = new Intent(this, InputDurationActivity.class);
            startActivityForResult(durationInputActivity, 3);
        }
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
                String min = formatDisplay( Integer.toString( data.getExtras().getInt("durationMin") ), "mm");
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
