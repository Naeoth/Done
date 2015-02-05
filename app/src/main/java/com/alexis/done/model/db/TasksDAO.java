package com.alexis.done.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.alexis.done.model.Task;

import java.util.ArrayList;

/**
 * Created by Alexis on 23/01/2015.
 *
 */
public class TasksDAO {

    // ---------- ATTRIBUTES

    protected final static int VERSION = 1;
    protected final static String NOM = "done.db";
    protected SQLiteDatabase db;
    protected Tasks handler;
    private Context context;

    // ---------- TABLE

    // ----- TASKS

    public static final String TASKS = "profil";
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

    public TasksDAO(Context context) {

        this.handler = new Tasks(context, NOM, null, VERSION);
        this.context = context;
    }


    // ---------- METHODS

    public SQLiteDatabase open() {
        db = handler.getWritableDatabase();
        return db;
    } // ---------------------------------------------------------- open()

    public void close() {
        db.close();
    } // ---------------------------------------------------------- close()

    public SQLiteDatabase getDb() {
        return db;
    } // ---------------------------------------------------------- getDb()


    // ----- PROFILS

    public ArrayList<Task> getTasks(){
        ArrayList<Task> ret = new ArrayList<Task>();
        Task tmp = null;
        int tmpId, tmpType, tmpProgress;
        String tmpTitle, tmpDescription, tmpDuration, tmpDate, tmpTime, tmpUrl;

        Cursor c = db.query(TASKS, new String[] {TASKS_ID, TASKS_TITLE, TASKS_TYPE, TASKS_DATE, TASKS_TIME, TASKS_DURATION, TASKS_DESCRIPTION, TASKS_PROGRESS, TASKS_URL}, null, null, null, null, null);
        while(c.moveToNext()){

            tmpId = c.getInt(0);
            tmpTitle = c.getString(1);
            tmpType = c.getInt(2);
            tmpDate = c.getString(3);
            tmpTime = c.getString(4);
            tmpDuration = c.getString(5);
            tmpDescription = c.getString(6);
            tmpProgress = c.getInt(7);
            tmpUrl = c.getString(8);

            tmp = new Task(tmpId, tmpTitle, tmpType, tmpDate, tmpTime, tmpDuration, tmpDescription, tmpProgress, tmpUrl);
            ret.add(tmp);
        }
        c.close();

        return ret;
    }

    public double insertTask(Task t){
        long ret = 0;

        if(t !=  null){

            // On rentre la task dans la bdd
            ContentValues values = new ContentValues();
            values.put(TASKS_TITLE, t.getTitle());
            values.put(TASKS_TYPE, t.getType());
            values.put(TASKS_DATE, t.getDate());
            values.put(TASKS_TIME, t.getTime());
            values.put(TASKS_DURATION, t.getDuration());
            values.put(TASKS_DESCRIPTION, t.getDescription());
            values.put(TASKS_PROGRESS, t.getProgress());
            values.put(TASKS_URL, t.getUrl());

            ret = db.insert(TASKS, null, values);

            Toast.makeText(context, "Nouvelle Task : " + t.getTitle(), Toast.LENGTH_LONG).show();

        }
        return ret;
    }
}