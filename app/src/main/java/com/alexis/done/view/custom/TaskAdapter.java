/*
 * Programmation Web et Mobile - M4103C/M4104C
 *
 * class TaskAdapter.java
 */

package com.alexis.done.view.custom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexis.done.R;
import com.alexis.done.model.Task;

import java.util.ArrayList;

/**
 * This class handle the item of the list.
 * It is a part of the adapter pattern.
 *
 * @version 1.0 - 11/02/15
 * @author BUSSENEAU Alexis - ROBIN Alexis
 * @see com.alexis.done.view.activities.MainActivity
 */
public class TaskAdapter extends BaseAdapter {

    /**
    * The list containing the task.
    */
    private ArrayList<Task> listTask;

    /**
    * The layout inflater of the list.
    */
    private LayoutInflater inflater;

    /**
    * Constructs a task adapter.
    *
    * @param context The activity where the list is display.
    */
    public TaskAdapter(Context context) {
        listTask = new ArrayList<Task>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listTask.size();
    }

    @Override
    public Object getItem(int position) {
        return listTask.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        // Initializes the view if is not setted.
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_view, parent, false);
        }
        else {
            view = convertView;
        }

        // Fills an item from the list with information (title, description...)
        TextView titleView = (TextView) view.findViewById(R.id.title_listView);
        titleView.setText( listTask.get(position).getTitle() );

        TextView descriptionView = (TextView) view.findViewById(R.id.description_listView);
        descriptionView.setText( listTask.get(position).getDescription() );

        TextView dateView = (TextView) view.findViewById(R.id.date_listView);
        dateView.setText( listTask.get(position).getDate() );

        TextView timeView = (TextView) view.findViewById(R.id.time_listView);
        timeView.setText( listTask.get(position).getTime() );

        TextView durationView = (TextView) view.findViewById(R.id.duration_listView);
        durationView.setText( listTask.get(position).getDuration() );

        // Applies the color of the image depending on the progress.
        ImageView iconView = (ImageView) view.findViewById(R.id.icon_listView);
        iconView.setBackgroundColor( Color.parseColor("#00CC00") );             // Sets the green color.
        int alpha = alphaCalculation( listTask.get(position).getProgress() );   // Calculates the alpha value (the transparency) with the progress.
        iconView.getBackground().setAlpha(alpha);                               // Sets the alpha value of the color.

        return view;
    }

    /**
    * Calculates the alpha value to determine the color transparency.
    *
    * @param progress The progress value of the task.
    *
    * @return The alpha value.
    */
    private int alphaCalculation(int progress) {
        return (progress * 255)/100;
    }

    /**
    * Refreshes the list with the new list specified.
    *
    * @param listTask THe new list of the adapter.
    */
    public void setListTask(ArrayList<Task> listTask){
        this.listTask = listTask;
        this.notifyDataSetChanged();
    }

}