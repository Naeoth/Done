/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class InputDurationActivity.java
 */

package com.alexis.done.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;

/**
 * This class is the activity to input a duration.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class InputDurationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_duration);

        // Sets the hour limits of the number picker.
        NumberPicker inputHour = (NumberPicker) findViewById(R.id.input_h_inputDuration);
        inputHour.setMinValue(0);
        inputHour.setMaxValue(1000);

        // Sets the minute limits of the number picker.
        NumberPicker inputMin = (NumberPicker) findViewById(R.id.input_m_inputDuration);
        inputMin.setMinValue(0);
        inputMin.setMaxValue(59);

        // Links the controller with the button.
        Button select = (Button) findViewById(R.id.button_select_inputDuration);
        select.setOnClickListener( ButtonsListener.getInstance() );
    }

}
