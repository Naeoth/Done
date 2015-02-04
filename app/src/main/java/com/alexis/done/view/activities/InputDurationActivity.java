package com.alexis.done.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;

public class InputDurationActivity extends Activity {

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
    }

}
