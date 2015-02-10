package com.alexis.done.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.alexis.done.view.activities.MainActivity;

/**
 * Created by alexis on 09/02/15.
 * *
 */
public class SpinnersListener implements OnItemSelectedListener {

    private static SpinnersListener ourInstance = new SpinnersListener();

    public static SpinnersListener getInstance() {
        return ourInstance;
    }

    private SpinnersListener() {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MainActivity currentActivity = (MainActivity) view.getContext();

        currentActivity.refreshList();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
