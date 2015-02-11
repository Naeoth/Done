/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class MainActivity.java
 */

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

/**
 * This class is the main activity of the application.
 * It is launched when we start the application.
 * It also contains the task list.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class MainActivity extends ActionBarActivity {

    /**
    * The request code for the add activity.
    */
    public static final int ADD_REQUEST_CODE = 1;

    /**
    * The request code for the add activity in update mode.
    */
    public static final int UPDATE_REQUEST_CODE = 2;

    /**
    * The request code for the input date activity.
    */
    public static final int INPUT_DATE_REQUEST_CODE = 3;

    /**
    * The request code for the input time activity.
    */
    public static final int INPUT_TIME_REQUEST_CODE = 4;

    /**
    * The request code for the input duration activity.
    */
    public static final int INPUT_DURATION_REQUEST_CODE = 5;

    /**
    * The DAO which links the application and the SQLite database.
    */
    private final TasksDAO taskDAO = new TasksDAO(this);

    /**
    * The list adapter.
    */
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();
        initControllers();
    }

    /**
    * Initializes the list.
    */
    private void initList() {
        taskDAO.open();

        // Links the list and the adapter.
        taskAdapter = new TaskAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list_listTask);
        listView.setAdapter(taskAdapter);
        // Allows list to have sub menu on long click.
        registerForContextMenu(listView);

        taskDAO.close();
    }

    /**
    * Initializes the controllers with the view.
    */
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
        // Action add a task.
        Intent addTask = new Intent(this, AddTaskActivity.class);
        startActivityForResult(addTask, ADD_REQUEST_CODE);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // The actions contained in the submenu on a long click.
        contextMenu.add(0, 0, 0, R.string.update_action);
        contextMenu.add(0, 1, 1, R.string.delete_action);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Gets the action selected in the submenus.
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Task selectedTask = (Task) taskAdapter.getItem(info.position);

        switch( item.getItemId() ) {
            // If update action.
            case 0:
                // Displays the add activity in update mode.
                Intent updateTask = new Intent(this, AddTaskActivity.class);
                updateTask.putExtra("aTask", selectedTask);
                startActivityForResult(updateTask, UPDATE_REQUEST_CODE);
                break;

            // If remove action.
            case 1:
                // Removes the item from the list.
                removeTask(selectedTask);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Gets the returner task from the activities.
            Task returnedTask = data.getParcelableExtra("returnedTask");

            if (requestCode == ADD_REQUEST_CODE) {
                addTask(returnedTask);
            }
            else if (requestCode == UPDATE_REQUEST_CODE) {
                updateTask(returnedTask);
            }
        }
    }

    /**
    * Inserts in the list and in the database the task specified.
    *
    * @param taskToAdd The task to add.
    */
    private void addTask(Task taskToAdd) {
        taskDAO.open();
        taskDAO.insertTask(taskToAdd);
        refreshList();
        taskDAO.close();
    }

    /**
    * Removes in the list and in the database the task specified.
    *
    * @param taskToRemove The task to remove.
    */
    private void removeTask(Task taskToRemove) {
        taskDAO.open();
        taskDAO.deleteTask(taskToRemove);
        refreshList();
        taskDAO.close();
    }

    /**
    * Updates in the list and in the database the task specified.
    *
    * @param taskToUpdate The task to update.
    */
    private void updateTask(Task taskToUpdate) {
        taskDAO.open();
        taskDAO.updateTask(taskToUpdate);
        refreshList();
        taskDAO.close();
    }

    /**
    * Refreshes the list to displays the modifications (depending on the filter of course).
    */
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