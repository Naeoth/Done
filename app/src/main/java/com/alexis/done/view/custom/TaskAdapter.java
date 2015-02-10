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
 * Created by Alexis on 05/02/2015.
 *
 */
public class TaskAdapter extends BaseAdapter {

    private ArrayList<Task> listTask;
    private LayoutInflater inflater;

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

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_view, parent, false);
        }
        else {
            view = convertView;
        }

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

        ImageView iconView = (ImageView) view.findViewById(R.id.icon_listView);
        iconView.setBackgroundColor(Color.parseColor("#00CC00"));
        int alpha = alphaCalculation( listTask.get(position).getProgress() );
        iconView.getBackground().setAlpha(alpha);

        return view;
    }

    public void setListTask(ArrayList<Task> listTask){
        this.listTask = listTask;
        this.notifyDataSetChanged();
    }

    private int alphaCalculation(int progress) {
        return (progress * 255)/100;
    }
}