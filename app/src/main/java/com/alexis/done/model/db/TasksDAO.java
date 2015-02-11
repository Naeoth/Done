/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class TasksDAO.java
 */

package com.alexis.done.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alexis.done.model.Task;

import java.util.ArrayList;

/**
 * This class allows to make some operations on the SQLite internal database.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class TasksDAO {

    // ---------- ATTRIBUTES

    private final static int VERSION = 1;
    private final static String NOM = "done.db";
    private SQLiteDatabase db;
    private Tasks handler;

    // ---------- TABLE

    // ----- TASKS

    public static final String TASKS = "tasks";
    public static final String TASKS_ID = "id";
    public static final String TASKS_TITLE = "title";
    public static final String TASKS_TYPE = "type";
    public static final String TASKS_DATE = "date";
    public static final String TASKS_TIME = "time";
    public static final String TASKS_DURATION = "duration";
    public static final String TASKS_DESCRIPTION = "description";
    public static final String TASKS_PROGRESS = "progress";
    public static final String TASKS_URL = "url";


    // ---------- CONSTRUCTOR

    /**
    * The class's constructor.
    *
    * @param context The context of the current activity.
    */
    public TasksDAO(Context context) {
        this.handler = new Tasks(context, NOM, null, VERSION);
    }


    // ---------- METHODS

    /**
    * This method allows to use the next methods. In fact, it makes the database writable.
    *
    * @return SQLiteDataBase The instance of the SQLiteDataBase.
    */
    public SQLiteDatabase open() {
        db = handler.getWritableDatabase();
        return db;
    }

    /**
    * This method makes the database unwritable.
    *
    */
    public void close() {
        db.close();
    }


    // ---------- GETTERS AND SETTERS

    /**
    * It returns the instance of the SQLiteDataBase.
    *
    * @return SQLiteDataBase The instance of the SQLiteDataBase.
    */
    public SQLiteDatabase getDb() {
        return db;
    }

    /**
    * This method returns the stored tasks list. There's two filters, by type or by progress.
    *
    * @param state The lvl of progression of the tasks you want.
    * @param type  The type of he tasks you want.
    * @return ArrayList<Task> The list of the tasks you want.
    */
    public ArrayList<Task> getTasks(int state, int type) {
        ArrayList<Task> ret = new ArrayList<Task>();
        int tmpId, tmpType, tmpProgress;
        String tmpTitle, tmpDescription, tmpDuration, tmpDate, tmpTime, tmpUrl;
        int min = 0;
        int max = 100;
        String condType = "";

        // Handles the progress filters.
        switch (state) {
            case 1:
                min = 1;
                max = 99;
                break;

            case 2:
                min = 0;
                max = 0;
                break;

            case 3:
                min = 100;
                max = 100;
                break;
        }

        // Handles the type filters.
        if (type != 0) {
            condType = TASKS_TYPE + " = " + (type - 1)  +" AND ";
        }

        Cursor c = db.query(TASKS, new String[] {TASKS_ID, TASKS_TITLE, TASKS_TYPE, TASKS_DATE, TASKS_TIME, TASKS_DURATION, TASKS_DESCRIPTION, TASKS_PROGRESS, TASKS_URL}, condType + TASKS_PROGRESS + " >= " + min + " AND " + TASKS_PROGRESS + " <= " + max, null, null, null, null);
        while ( c.moveToNext() ){

            tmpId = c.getInt(0);
            tmpTitle = c.getString(1);
            tmpType = c.getInt(2);
            tmpDate = c.getString(3);
            tmpTime = c.getString(4);
            tmpDuration = c.getString(5);
            tmpDescription = c.getString(6);
            tmpProgress = c.getInt(7);
            tmpUrl = c.getString(8);

            Task tmp = new Task(tmpId, tmpTitle, tmpType, tmpDate, tmpTime, tmpDuration, tmpDescription, tmpProgress, tmpUrl);
            ret.add(tmp);
        }
        c.close();

        return ret;
    }

    /**
    * This method allows you to insert a Task in the database.
    *
    * @param taskToInsert The task which needs to be added to the database.
    * @return double The result state of the insert operation.
    */
    public double insertTask(Task taskToInsert) {
        long ret = 0;

        if(taskToInsert !=  null){
            // On rentre la task dans la bdd
            ContentValues values = new ContentValues();
            values.put(TASKS_TITLE, taskToInsert.getTitle());
            values.put(TASKS_TYPE, taskToInsert.getType());
            values.put(TASKS_DATE, taskToInsert.getDate());
            values.put(TASKS_TIME, taskToInsert.getTime());
            values.put(TASKS_DURATION, taskToInsert.getDuration());
            values.put(TASKS_DESCRIPTION, taskToInsert.getDescription());
            values.put(TASKS_PROGRESS, taskToInsert.getProgress());
            values.put(TASKS_URL, taskToInsert.getUrl());

            ret = db.insert(TASKS, null, values);
        }

        return ret;
    }

    /**
    * This method allows you to delete a Task of the database.
    *
    * @param taskToDelete The task which needs to be deleted to the database.
    */
    public void deleteTask(Task taskToDelete) {
        db.delete(TASKS, TASKS_ID + "=" + taskToDelete.getId(), null);
    }

    /**
    * This method allows you to update a Task of the database.
    *
    * @param taskToUpdate The task which needs to be updated to the database.
    */
    public void updateTask(Task taskToUpdate){

        ContentValues values = new ContentValues();

        values.put(TASKS_TITLE , taskToUpdate.getTitle());
        values.put(TASKS_TYPE, taskToUpdate.getType());
        values.put(TASKS_DATE, taskToUpdate.getDate());
        values.put(TASKS_TIME, taskToUpdate.getTime());
        values.put(TASKS_DURATION, taskToUpdate.getDuration());
        values.put(TASKS_DESCRIPTION, taskToUpdate.getDescription());
        values.put(TASKS_PROGRESS, taskToUpdate.getProgress());
        values.put(TASKS_URL, taskToUpdate.getUrl());

        db.update(TASKS, values, TASKS_ID + "=" +  taskToUpdate.getId(), null);
    }

}