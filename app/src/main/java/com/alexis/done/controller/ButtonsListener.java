package com.alexis.done.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alexis.done.R;
import com.alexis.done.view.activities.InputDateActivity;
import com.alexis.done.view.activities.InputDurationActivity;
import com.alexis.done.view.activities.InputTimeActivity;
import com.alexis.done.view.activities.WebViewActivity;

/**
 * Created by alexis on 02/02/15.
 *
 */
public class ButtonsListener implements OnClickListener {

    private static ButtonsListener ourInstance = new ButtonsListener();

    private ButtonsListener() {}

    public static ButtonsListener getInstance() {
        return ourInstance;
    }

    @Override
    public void onClick(View v) {
        Activity currentActivity = (Activity) v.getContext();

        switch ( v.getId() ) {
            case R.id.button_input_date_addTask:
                Intent dateInputActivity = new Intent(currentActivity, InputDateActivity.class);
                currentActivity.startActivityForResult(dateInputActivity, 1);
                break;

            case R.id.button_input_time_addTask:
                Intent timeInputActivity = new Intent(currentActivity, InputTimeActivity.class);
                currentActivity.startActivityForResult(timeInputActivity, 2);
                break;

            case R.id.button_input_duration_addTask:
                Intent durationInputActivity = new Intent(currentActivity, InputDurationActivity.class);
                currentActivity.startActivityForResult(durationInputActivity, 3);
                break;

            case R.id.button_select_inputDate:
                DatePicker inputDate = (DatePicker) currentActivity.findViewById(R.id.datePicker_inputDate);
                Intent returnDate = new Intent();

                returnDate.putExtra("dateDay", inputDate.getDayOfMonth());
                returnDate.putExtra("dateMonth", inputDate.getMonth() + 1);
                returnDate.putExtra("dateYear", inputDate.getYear());
                currentActivity.setResult(Activity.RESULT_OK, returnDate);

                currentActivity.finish();
                break;

            case R.id.button_select_inputTime:
                TimePicker inputTime = (TimePicker) currentActivity.findViewById(R.id.timePicker_inputTime);
                Intent returnTime = new Intent();

                returnTime.putExtra("timeHour", inputTime.getCurrentHour());
                returnTime.putExtra("timeMin", inputTime.getCurrentMinute());
                currentActivity.setResult(Activity.RESULT_OK, returnTime);

                currentActivity.finish();
                break;

            case R.id.button_select_inputDuration:
                NumberPicker inputHour = (NumberPicker) currentActivity.findViewById(R.id.input_h_inputDuration);
                NumberPicker inputMin = (NumberPicker) currentActivity.findViewById(R.id.input_m_inputDuration);
                Intent returnDuration = new Intent();

                returnDuration.putExtra("durationHour", inputHour.getValue());
                returnDuration.putExtra("durationMin", inputMin.getValue());
                currentActivity.setResult(Activity.RESULT_OK, returnDuration);

                currentActivity.finish();
                break;

            case R.id.input_url_addTask:
                Intent webviewActivity = new Intent(currentActivity, WebViewActivity.class);
                EditText inputUrl = (EditText) currentActivity.findViewById(R.id.input_url_addTask);
                webviewActivity.putExtra( "url", inputUrl.getText().toString() );
                currentActivity.startActivity(webviewActivity);
                Toast.makeText(currentActivity, "OK", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
