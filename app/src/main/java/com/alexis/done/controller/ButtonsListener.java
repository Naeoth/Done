/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class ButtonsListener.java
 */

package com.alexis.done.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.alexis.done.R;
import com.alexis.done.view.activities.MainActivity;
import com.alexis.done.view.activities.InputDateActivity;
import com.alexis.done.view.activities.InputDurationActivity;
import com.alexis.done.view.activities.InputTimeActivity;
import com.alexis.done.view.activities.WebViewActivity;

/**
 * This class handles the buttons actions of the application.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class ButtonsListener implements OnClickListener {

    /**
    * The unique instance of the controller.
    */
    private static ButtonsListener ourInstance = new ButtonsListener();

    /**
    * The private contructor.
    */
    private ButtonsListener() {}

    /**
    * Gets the unique instance.
    *
    * @return The unique instance of this class.
    */
    public static ButtonsListener getInstance() {
        return ourInstance;
    }

    @Override
    public void onClick(View v) {
        Activity currentActivity = (Activity) v.getContext();

        switch ( v.getId() ) {
            // Input date button.
            case R.id.button_input_date_addTask:
                // Launches the input date activity.
                Intent dateInputActivity = new Intent(currentActivity, InputDateActivity.class);
                currentActivity.startActivityForResult(dateInputActivity, MainActivity.INPUT_DATE_REQUEST_CODE);
                break;

            // Input time button.
            case R.id.button_input_time_addTask:
                // Launches the input time activity.
                Intent timeInputActivity = new Intent(currentActivity, InputTimeActivity.class);
                currentActivity.startActivityForResult(timeInputActivity, MainActivity.INPUT_TIME_REQUEST_CODE);
                break;

            // Input duration button.
            case R.id.button_input_duration_addTask:
                // Launches the input duration activity.
                Intent durationInputActivity = new Intent(currentActivity, InputDurationActivity.class);
                currentActivity.startActivityForResult(durationInputActivity, MainActivity.INPUT_DURATION_REQUEST_CODE);
                break;

            // Select date button.
            case R.id.button_select_inputDate:
                // Gets the date.
                DatePicker inputDate = (DatePicker) currentActivity.findViewById(R.id.datePicker_inputDate);
                Intent returnDate = new Intent();

                // And returns it to the calling activity.
                returnDate.putExtra("dateDay", inputDate.getDayOfMonth());
                returnDate.putExtra("dateMonth", inputDate.getMonth() + 1);
                returnDate.putExtra("dateYear", inputDate.getYear());
                currentActivity.setResult(Activity.RESULT_OK, returnDate);

                currentActivity.finish();
                break;

            // Select time button.
            case R.id.button_select_inputTime:
                // Gets the time.
                TimePicker inputTime = (TimePicker) currentActivity.findViewById(R.id.timePicker_inputTime);
                Intent returnTime = new Intent();

                // And returns it to the calling activity.
                returnTime.putExtra("timeHour", inputTime.getCurrentHour());
                returnTime.putExtra("timeMin", inputTime.getCurrentMinute());
                currentActivity.setResult(Activity.RESULT_OK, returnTime);

                currentActivity.finish();
                break;

            // Select duration button.
            case R.id.button_select_inputDuration:
                // Gets the duration.
                NumberPicker inputHour = (NumberPicker) currentActivity.findViewById(R.id.input_h_inputDuration);
                NumberPicker inputMin = (NumberPicker) currentActivity.findViewById(R.id.input_m_inputDuration);
                Intent returnDuration = new Intent();

                // And returns it to the calling activity.
                returnDuration.putExtra("durationHour", inputHour.getValue());
                returnDuration.putExtra("durationMin", inputMin.getValue());
                currentActivity.setResult(Activity.RESULT_OK, returnDuration);

                currentActivity.finish();
                break;

            // Web view button.
            case R.id.button_webView_url_addTask:
                // Gets the URL.
                Intent webViewActivity = new Intent(currentActivity, WebViewActivity.class);
                EditText inputUrl = (EditText) currentActivity.findViewById(R.id.input_url_addTask);

                // Launches the web view activity.
                webViewActivity.putExtra( "url", inputUrl.getText().toString() );

                currentActivity.startActivity(webViewActivity);
                break;
        }
    }
}
