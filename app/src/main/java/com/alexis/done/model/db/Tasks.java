/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class Tasks.java
 */

package com.alexis.done.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class allows to create an SQLite internal database to store tasks.
 * It extends SQLiteOpenHelper.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class Tasks extends SQLiteOpenHelper {


    // ---------- BASE DE DONNEES

    // ---------- TABLE

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


    // ---------- TABLE CREATION
    private static final String TASKS_CREATE = "CREATE TABLE " + TASKS + "("
            + TASKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TASKS_TITLE + " TEXT NOT NULL,"
            + TASKS_TYPE + " INTEGER NOT NULL,"
            + TASKS_DATE + " TEXT DEFAULT 'no date',"
            + TASKS_TIME + " TEXT DEFAULT 'no time',"
            + TASKS_DURATION + " TEXT DEFAULT 'no duration',"
            + TASKS_DESCRIPTION + " TEXT DEFAULT 'no desc',"
            + TASKS_PROGRESS + " INTEGER DEFAULT 0,"
            + TASKS_URL + " TEXT DEFAULT 'no url'"
            + ");";


    // ---------- CONSTRUCTOR

    /**
     * The class's constructor.
     *
     * @param context The context of the current Activity.
     * @param name The name of the database.
     * @param factory The SQLiteDatabase.CursorFactory of the database.
     * @param version The version of the database.
     */
    public Tasks(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // ---------- METHODS

    @Override
    /**
     * This method is called one time, to create the database.
     *
     * @param database The SQLite database.
     */
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TASKS_CREATE);
    }

    @Override
    /**
     * This method is called when the database needs to be upgraded.
     *
     * @param database The SQLite database.
     * @param oldVersion The old version of the database.
     * @param newVersion The new version of the database.
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Tasks.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TASKS);
        onCreate(db);
    }

}