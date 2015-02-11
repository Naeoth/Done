/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class SeekBarsListener.java
 */

package com.alexis.done.controller;

import android.app.Activity;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.alexis.done.R;

/**
 * This class handles the seek bars actions of the application.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class SeekBarsListener implements OnSeekBarChangeListener {

    /**
    * The unique instance of the controller.
    */
    private static SeekBarsListener ourInstance = new SeekBarsListener();

    /**
    * The private contructor.
    */
    private SeekBarsListener() {}

    /**
    * Gets the unique instance.
    *
    * @return The unique instance of this class.
    */
    public static SeekBarsListener getInstance() {
        return ourInstance;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Gets the activity where the action is done.
        Activity currentActivity = (Activity) seekBar.getContext();

        // When we change the value of the seek bar, we change the value of the progress displayed.
        TextView displayProgress = (TextView) currentActivity.findViewById(R.id.value_progressBar_addTask);
        displayProgress.setText( String.valueOf(progress) + "%" );
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

}