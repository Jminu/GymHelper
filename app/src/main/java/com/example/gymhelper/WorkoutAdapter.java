package com.example.gymhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gymhelper.WorkOut;

import java.util.List;

public class WorkoutAdapter extends ArrayAdapter<WorkOut> {
    private int selectedPosition = -1;
    public WorkoutAdapter(Context context, List<WorkOut> object) {
        super(context, 0, object);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.workout_item, parent, false);
        }

        WorkOut workout = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView infoTextView = convertView.findViewById(R.id.infoTextView);
        TextView countTextView = convertView.findViewById(R.id.countTextView);

        nameTextView.setText(workout.getName());
        infoTextView.setText(workout.getWorkInfo());
        countTextView.setText("수행 횟수 : " + String.valueOf(workout.getCount())); //getCount는 int를 반환하니까 String으로 바꿔줘야함

        //count와 reps가 같아지면 '수행완료'됐다는 뜻으로 초록색으로 바꿈
        if(workout.getReps() >= workout.getCount()) {
            countTextView.setTextColor(getContext().getResources().getColor(android.R.color.holo_green_dark));
        }
        else {
            countTextView.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
        }

        if(position == selectedPosition) {
            convertView.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_blue_light));
        }
        else {
            convertView.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        }
        return convertView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }
}
