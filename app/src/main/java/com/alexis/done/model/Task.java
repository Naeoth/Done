/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class Task.java
 */

package com.alexis.done.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class models a task.
 * It parcelable to be bundle between the activities.
 *
 * @version 1.0 - 11/01/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 */
public class Task implements Parcelable {

    // ---------- ATTRIBUTES

    /**
     * The id in the database of the task.
     */
    private int id;

    /**
     * The type of the task.
     */
    private int type;

    /**
     * The progress of the task (between 0 and 100).
     */
    private int progress;

    /**
     * The title of the task (must not be empty).
     */
    private String title;

    /**
     * The date of the task.
     */
    private String date;

    /**
     * The time of the task.
     */
    private String time;

    /**
     * The duration of the task.
     */
    private String duration;

    /**
     * The description of the task.
     */
    private String description;

    /**
     * The url of the task.
     */
    private String url;

    /**
     * The parcelable creator to parcelable this class.
     */
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };


    // ---------- CONSTRUCTOR

    /**
     * Constructs a task.
     *
     * @param id The id of the task.
     * @param title The title of the task.
     * @param type The type of the task.
     * @param date The date of the task.
     * @param time The time of the task.
     * @param duration The duration of the task.
     * @param description The description of the task.
     * @param progress The progress of the task.
     * @param url The url of the task.
     */
    public Task(int id, String title, int type, String date, String time, String duration, String description, int progress, String url) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.description = description;
        this.progress = progress;
        this.url = url;
    }

    /**
     * Constructs a task from a loading parcelable task.
     *
     * @param in The loading parcelable task.
     */
    private Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        type = in.readInt();
        date = in.readString();
        time = in.readString();
        duration = in.readString();
        description = in.readString();
        progress = in.readInt();
        url = in.readString();
    }

    // ---------- METHODS

    // ----- GETTERS AND SETTERS

    /**
     * Gets the id of the task.
     *
     * @return The id of the task.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the task.
     *
     * @param id The new id of the task.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title The new title of the task.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the type of the task.
     *
     * @param type The new type of the task.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets the date of the task.
     *
     * @return The date of the task.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the task.
     *
     * @param date The new date of the task.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time of the task.
     *
     * @return The time of the task.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the task.
     *
     * @param time The new time of the task.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the duration of the task.
     *
     * @return The duration of the task.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the task.
     *
     * @param duration The new duration of the task.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets the duration of the task.
     *
     * @return The duration of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the duration of the task.
     *
     * @param duration The new duration of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the progress of the task.
     *
     * @return The progress of the task.
     */
    public int getProgress() {
        return progress;
    }

    /**
     * Sets the progress of the task.
     *
     * @param progress The new progress of the task.
     */
    public void setProgress(int progress) {
        this.progress = progress;
    }

    /**
     * Gets the url of the task.
     *
     * @return The url of the task.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url of the task.
     *
     * @param url The new url of the task.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns a display for the task.
     *
     * @return The title of the task.
     */
    public String toString(){
        return this.title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeInt(type);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(duration);
        dest.writeString(description);
        dest.writeInt(progress);
        dest.writeString(url);
    }

}