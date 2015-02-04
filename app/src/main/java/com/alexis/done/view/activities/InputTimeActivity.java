package com.alexis.done.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;

public class InputTimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_time);

        TimePicker inputTime = (TimePicker) findViewById(R.id.timePicker_inputTime);
        inputTime.setIs24HourView(true);

        Button select = (Button) findViewById(R.id.button_select_inputTime);
        select.setOnClickListener( ButtonsListener.getInstance() );
    }

}
