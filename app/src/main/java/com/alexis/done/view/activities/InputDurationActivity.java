package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;

import com.alexis.done.R;

public class InputDurationActivity extends ActionBarActivity implements OnClickListener {

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
        select.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_duration, menu);
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
        NumberPicker inputHour = (NumberPicker) findViewById(R.id.input_h_inputDuration);
        NumberPicker inputMin = (NumberPicker) findViewById(R.id.input_m_inputDuration);
        Intent returnDuration = new Intent();

        returnDuration.putExtra( "durationHour", inputHour.getValue() );
        returnDuration.putExtra( "durationMin", inputMin.getValue() );
        setResult(RESULT_OK, returnDuration);

        finish();
    }
}
