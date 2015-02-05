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
    private String title;
    private String date;
    private String time;
    private String duration;
    private String description;
    private String url;


    // ---------- CONSTRUCTOR

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

    private Task(Parcel in) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        dest.writeString(url);
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