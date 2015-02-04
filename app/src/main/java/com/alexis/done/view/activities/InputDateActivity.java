package com.alexis.done.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.alexis.done.R;
import com.alexis.done.controller.ButtonsListener;

public class InputDateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_date);

        Button select = (Button) findViewById(R.id.button_select_inputDate);
        select.setOnClickListener( ButtonsListener.getInstance() );
    }

}
