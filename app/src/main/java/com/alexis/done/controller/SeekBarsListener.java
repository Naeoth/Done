package com.alexis.done.controller;

import android.app.Activity;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.alexis.done.R;

/**
 * Created by alexis on 02/02/15.
 *
 */
public class SeekBarsListener implements OnSeekBarChangeListener {

    private static SeekBarsListener ourInstance = new SeekBarsListener();

    public static SeekBarsListener getInstance() {
        return ourInstance;
    }

    private SeekBarsListener() {}

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Activity currentActivity = (Activity) seekBar.getContext();

        TextView displayProgress = (TextView) currentActivity.findViewById(R.id.value_progressBar_addTask);
        displayProgress.setText( String.valueOf(progress) + "%" );
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
