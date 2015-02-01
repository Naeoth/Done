package com.alexis.done.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexis on 23/01/2015.
 *
 */
public class Task {

    // ---------- ATTRIBUTES
    private int id, duration;
    private String title, type, description, date, hour;


    // ---------- CONSTRUCTOR

    public Task(int id, String title, String type, String description, int duration, String date, String hour){
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.hour = hour;
    }

    // ---------- METHODS

    // ----- GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}