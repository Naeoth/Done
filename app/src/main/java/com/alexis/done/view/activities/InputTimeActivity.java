/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class InputTimeActivity.java
 */

package com.alexis.done.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;

/**
 * This class is the activity to input a time.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class InputTimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_time);

        TimePicker inputTime = (TimePicker) findViewById(R.id.timePicker_inputTime);
        // Displays the input in 24hour mode.
        inputTime.setIs24HourView(true);

        // Links the controller with the button.
        Button select = (Button) findViewById(R.id.button_select_inputTime);
        select.setOnClickListener( ButtonsListener.getInstance() );
    }

}
