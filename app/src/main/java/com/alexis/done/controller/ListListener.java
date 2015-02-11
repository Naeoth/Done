/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class ListListener.java
 */

package com.alexis.done.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.alexis.done.model.Task;
import com.alexis.done.view.activities.DisplayTaskActivity;
import com.alexis.done.view.activities.MainActivity;

/**
 * This class handles the list actions of the application.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class ListListener implements OnItemClickListener, AdapterView.OnItemSelectedListener {

    /**
    * The unique instance of the controller.
    */
    private static ListListener ourInstance = new ListListener();

    /**
    * The private contructor.
    */
    private ListListener() {
    }

    /**
    * Gets the unique instance.
    *
    * @return The unique instance of this class.
    */
    public static ListListener getInstance() {
        return ourInstance;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Activity currentActivity = (Activity) view.getContext();

        // Launches the display activity on click on an list item.
        Intent displayTask = new Intent(currentActivity, DisplayTaskActivity.class);
        Task taskSelected = (Task) parent.getAdapter().getItem(position);
        displayTask.putExtra("aTask", taskSelected);
        currentActivity.startActivityForResult(displayTask, MainActivity.UPDATE_REQUEST_CODE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}