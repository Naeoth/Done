package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

import com.alexis.done.R;

public class InputDateActivity extends ActionBarActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_date);

        Button select = (Button) findViewById(R.id.button_select_inputDate);
        select.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_date, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        DatePicker inputDate = (DatePicker) findViewById(R.id.datePicker_inputDate);
        Intent returnDate = new Intent();

        returnDate.putExtra( "dateDay", inputDate.getDayOfMonth() );
        returnDate.putExtra( "dateMonth", inputDate.getMonth() + 1 );
        returnDate.putExtra( "dateYear", inputDate.getYear() );
        setResult(RESULT_OK, returnDate);

        finish();
    }
}
