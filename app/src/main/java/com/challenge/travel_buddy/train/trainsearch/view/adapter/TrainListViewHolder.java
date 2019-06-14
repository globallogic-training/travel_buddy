package com.challenge.travel_buddy.train.trainsearch.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;

public class TrainListViewHolder extends RecyclerView.ViewHolder {

    public TextView trainName;
    public TextView trainNumber;
    public TextView SL_Available;
    public TextView _3A_Availability;
    public TextView travel_src;
    public TextView travel_des;
    public TextView arrivalTime;
    public TextView departureTime;
    public TextView travelHrs;

    public TrainListViewHolder(@NonNull View itemView) {
        super(itemView);
        trainName = itemView.findViewById(R.id.trainName);
        trainNumber = itemView.findViewById(R.id.trainNumber);
        SL_Available = itemView.findViewById(R.id.SL_Availability);
        _3A_Availability = itemView.findViewById(R.id._3A_Availability);
        travel_src = itemView.findViewById(R.id.travel_src);
        travel_des = itemView.findViewById(R.id.travel_des);
        arrivalTime = itemView.findViewById(R.id.arrival_time);
        departureTime = itemView.findViewById(R.id.departure_time);
        travelHrs = itemView.findViewById(R.id.travel_hrs);
    }
}
