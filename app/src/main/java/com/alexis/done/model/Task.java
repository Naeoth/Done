package com.alexis.done.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexis on 23/01/2015.
 *
 */
public class Task implements Parcelable {

    // ---------- ATTRIBUTES
    private int id, type, progress;
    private String title, date, time, duration, description;


    // ---------- CONSTRUCTOR

    public Task(int id, String title, int type, String date, String time, String duration, String description, int progress) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.description = description;
        this.progress = progress;
    }

    private Task(Parcel in) {
        this.title = in.readString();
        this.type = in.readInt();
        this.date = in.readString();
        this.time = in.readString();
        this.duration = in.readString();
        this.description = in.readString();
        this.progress = in.readInt();
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getProgress() {
        return progress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(type);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(duration);
        dest.writeString(description);
        dest.writeInt(progress);
    }

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
}