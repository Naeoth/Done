package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

import com.alexis.done.R;

public class InputTimeActivity extends ActionBarActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_time);

        TimePicker inputTime = (TimePicker) findViewById(R.id.timePicker_inputTime);
        inputTime.setIs24HourView(true);

        Button select = (Button) findViewById(R.id.button_select_inputTime);
        select.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_time, menu);
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
        TimePicker inputTime = (TimePicker) findViewById(R.id.timePicker_inputTime);
        Intent returnTime = new Intent();

        returnTime.putExtra( "timeHour", inputTime.getCurrentHour() );
        returnTime.putExtra( "timeMin", inputTime.getCurrentMinute() );
        setResult(RESULT_OK, returnTime);

        finish();
    }
}
