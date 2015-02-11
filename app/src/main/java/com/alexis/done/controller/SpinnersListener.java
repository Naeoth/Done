/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class SpinnersListener.java
 */

package com.alexis.done.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.alexis.done.view.activities.MainActivity;

/**
 * This class handles the spinners actions of the application.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class SpinnersListener implements OnItemSelectedListener {

    /**
    * The unique instance of the controller.
    */
    private static SpinnersListener ourInstance = new SpinnersListener();

    /**
    * The private contructors.
    */
    private SpinnersListener() {
    }

    /**
    * Gets the unique instance.
    *
    * @return The unique instance of this class.
    */
    public static SpinnersListener getInstance() {
        return ourInstance;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MainActivity currentActivity = (MainActivity) view.getContext();

        // Refreshes the list after a spinner modification.
        currentActivity.refreshList();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}