/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class InputDateActivity.java
 */

package com.alexis.done.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;

/**
 * This class is the activity to input a date.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class InputDateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_date);

        // Links the controller with the button.
        Button select = (Button) findViewById(R.id.button_select_inputDate);
        select.setOnClickListener( ButtonsListener.getInstance() );
    }

}
