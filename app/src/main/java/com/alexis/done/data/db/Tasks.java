package com.alexis.done.data.db;

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

    public static final String TASKS = "profil";
    public static final String TASKS_ID = "id";
    public static final String TASKS_TITLE = "title";
    public static final String TASKS_TYPE = "type";
    public static final String TASKS_DESCRIPTION = "description";
    public static final String TASKS_DURATION = "duration";
    public static final String TASKS_DATE = "date";
    public static final String TASKS_HOUR = "hour";







    // ---------- TABLE CREATION
    private static final String TASKS_CREATE = "CREATE TABLE " + TASKS + "("
            + TASKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TASKS_TITLE + " TEXT NOT NULL,"
            + TASKS_TYPE + " TEXT NOT NULL,"
            + TASKS_DESCRIPTION + " TEXT DEFAULT 'No description',"
            + TASKS_DURATION + " INTEGER DEFAULT '0',"
            + TASKS_DATE + " TEXT DEFAULT 'No date',"
            + TASKS_HOUR + " TEXT DEFAULT 'No hour',"
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