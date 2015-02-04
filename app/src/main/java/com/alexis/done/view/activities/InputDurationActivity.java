package com.alexis.done.view.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.NumberPicker;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;

public class InputDurationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_duration);

        NumberPicker inputHour = (NumberPicker) findViewById(R.id.input_h_inputDuration);
        inputHour.setMinValue(0);
        inputHour.setMaxValue(1000);

        NumberPicker inputMin = (NumberPicker) findViewById(R.id.input_m_inputDuration);
        inputMin.setMinValue(0);
        inputMin.setMaxValue(59);

        Button select = (Button) findViewById(R.id.button_select_inputDuration);
        select.setOnClickListener( ButtonsListener.getInstance() );

        getSupportActionBar().hide();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
}
