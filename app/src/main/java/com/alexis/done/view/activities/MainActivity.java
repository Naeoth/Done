package com.alexis.done.view.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Spinner;

import com.alexis.done.R;
import com.alexis.done.controller.ListListener;
import com.alexis.done.controller.SpinnersListener;
import com.alexis.done.model.Task;
import com.alexis.done.model.db.TasksDAO;
import com.alexis.done.view.custom.TaskAdapter;

public class MainActivity extends ActionBarActivity {

    public static final int ADD_REQUEST_CODE = 1;

    public static final int UPDATE_REQUEST_CODE = 2;

    public static final int INPUT_DATE_REQUEST_CODE = 3;

    public static final int INPUT_TIME_REQUEST_CODE = 4;

    public static final int INPUT_DURATION_REQUEST_CODE = 5;

    private final TasksDAO taskDAO = new TasksDAO(this);

    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();
        initControllers();
    }

    private void initList() {
        taskDAO.open();

        taskAdapter = new TaskAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list_listTask);
        listView.setAdapter(taskAdapter);
        registerForContextMenu(listView);

        taskDAO.close();
    }

    private void initControllers() {
        ListView listView = (ListView) findViewById(R.id.list_listTask);
        listView.setOnItemClickListener(ListListener.getInstance());

        Spinner stateSpinner = (Spinner) this.findViewById(R.id.state_filter_listTask);
        stateSpinner.setOnItemSelectedListener( SpinnersListener.getInstance() );

        Spinner typeSpinner = (Spinner) this.findViewById(R.id.type_filter_listTask);
        typeSpinner.setOnItemSelectedListener( SpinnersListener.getInstance() );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         Intent addTask = new Intent(this, AddTaskActivity.class);
         startActivityForResult(addTask, ADD_REQUEST_CODE);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        contextMenu.add(0, 0, 0, R.string.update_action);
        contextMenu.add(0, 1, 1, R.string.delete_action);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Task selectedTask = (Task) taskAdapter.getItem(info.position);

        switch( item.getItemId() ) {
            case 0:
                Intent updateTask = new Intent(this, AddTaskActivity.class);
                updateTask.putExtra("aTask", selectedTask);
                startActivityForResult(updateTask, UPDATE_REQUEST_CODE);
                break;
            case 1:
                removeTask(selectedTask);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Task returnedTask = data.getParcelableExtra("returnedTask");

            if (requestCode == ADD_REQUEST_CODE) {
                addTask(returnedTask);
            }
            else if (requestCode == UPDATE_REQUEST_CODE) {
                updateTask(returnedTask);
            }
        }
    }

    protected void addTask(Task taskToAdd) {
        taskDAO.open();
        taskDAO.insertTask(taskToAdd);
        refreshList();
        taskDAO.close();
    }

    protected void removeTask(Task taskToRemove) {
        taskDAO.open();
        taskDAO.deleteTask(taskToRemove);
        refreshList();
        taskDAO.close();
    }

    protected void updateTask(Task taskToUpdate) {
        taskDAO.open();
        taskDAO.updateTask(taskToUpdate);
        refreshList();
        taskDAO.close();
    }

    public void refreshList() {
        Spinner stateSpinner = (Spinner) findViewById(R.id.state_filter_listTask);
        int stateFilter = stateSpinner.getSelectedItemPosition();

        Spinner typeSpinner = (Spinner) findViewById(R.id.type_filter_listTask);
        int typeFilter = typeSpinner.getSelectedItemPosition();

        taskDAO.open();
        taskAdapter.setListTask( taskDAO.getTasks(stateFilter, typeFilter) );
        taskDAO.close();
    }
}
