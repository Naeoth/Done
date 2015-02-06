package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alexis.done.R;
import com.alexis.done.model.Task;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent tmp = new Intent(this, DisplayTaskActivity.class);
        Task tmp2 = new Task(0, "TEST", 2, "03/02/95", "12:00", "24h", "Anniversaire Alexis", 100, "http://www.iu-vannes.fr/");
        tmp.putExtra("aTask", tmp2);
        startActivity(tmp);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ) {
            case R.id.action_add:
                Intent addTask = new Intent(this, AddTaskActivity.class);
                startActivityForResult(addTask, 1);
                break;

            case R.id.action_all:
                break;

            case R.id.action_date_sort:
                break;
            
            case R.id.action_type_0_sort:
                break;

            case R.id.action_type_1_sort:
                break;

            case R.id.action_type_2_sort:
                break;

            case R.id.action_type_3_sort:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Task addedTask = data.getParcelableExtra("returnedTask");
            Toast.makeText(this, addedTask.getTitle(), Toast.LENGTH_LONG).show();
        }
    }
}
