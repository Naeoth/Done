package com.alexis.done.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alexis on 23/01/2015.
 */
public class Tasks extends SQLiteOpenHelper {


    // ---------- BASE DE DONNES

    // ---------- TABLE
    // ----- PROFIL

    public static final String TASKS = "Tasks";
    public static final String TASKS_ID = "id";
    public static final String TASKS_TITLE = "title";
    public static final String TASKS_TYPE = "type";
    public static final String TASKS_DATE = "date";
    public static final String TASKS_TIME = "time";
    public static final String TASKS_DURATION = "duration";
    public static final String TASKS_DESCRIPTION = "description";
    public static final String TASKS_PROGRESS = "progress";


    // ---------- TABLE CREATION
    private static final String TASKS_CREATE = "CREATE TABLE " + TASKS + "("
                                + TASKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + TASKS_TITLE + " TEXT NOT NULL,"
                                + TASKS_TYPE + " INTEGER NOT NULL,"
                                + TASKS_DATE + " TEXT DEFAULT 'No date',"
                                + TASKS_TIME + " TEXT DEFAULT 'No time',"
                                + TASKS_DURATION + " TEXT DEFAULT 'No duration',"
                                + TASKS_DESCRIPTION + " TEXT DEFAULT 'No description',"
                                + TASKS_PROGRESS + " INTEGER DEFAULT '0',"
                                + ");";


    // ---------- CONSTRUCTOR

    public Tasks(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // ---------- METHODS

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TASKS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Tasks.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TASKS);
        onCreate(db);
    }

}