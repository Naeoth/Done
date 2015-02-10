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
 * Created by alexis on 09/02/15.
 *
 */
public class ListListener implements OnItemClickListener, AdapterView.OnItemSelectedListener {

    private static ListListener ourInstance = new ListListener();

    public static ListListener getInstance() {
        return ourInstance;
    }

    private ListListener() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Activity currentActivity = (Activity) view.getContext();

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
