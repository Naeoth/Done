package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.alexis.done.R;
import com.alexis.done.model.Task;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent tmp = new Intent(this, DisplayTaskActivity.class);
        Task tmp2 = new Task(0, "TEST", 2, "03/02/95", "12:00", "24h", "Anniversaire Alexis", 100, "");
        tmp.putExtra("aTask", tmp2);
        startActivityForResult(tmp, 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent addTask = new Intent(this, AddTaskActivity.class);
        startActivityForResult(addTask, 1);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Task addedTask = data.getParcelableExtra("taskToAdd");
        }
    }
}
