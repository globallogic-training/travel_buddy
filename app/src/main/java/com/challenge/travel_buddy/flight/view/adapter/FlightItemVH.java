package com.challenge.travel_buddy.flight.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;

public class FlightItemVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView flightName;
    public TextView depTime;
    public TextView arrTime;
    public TextView fly_duration;
    public TextView flightCost;
    public TextView depFrom;
    public TextView arrTo;
    public TextView airlinesName;
    public TextView duration;
    public TextView journeyStop;


    public FlightItemVH(@NonNull View itemView) {
        super(itemView);
        flightName = itemView.findViewById(R.id.busName);
        depFrom = itemView.findViewById(R.id.bus_bp);
        arrTo = itemView.findViewById(R.id.bus_dp);
        fly_duration = itemView.findViewById(R.id.bus_travel_hrs);
        flightCost = itemView.findViewById(R.id.bus_cost);
        depTime = itemView.findViewById(R.id.bus_departure_time);
        arrTime = itemView.findViewById(R.id.bus_arrival_time);
        airlinesName = itemView.findViewById(R.id.airlinesName);
        duration = itemView.findViewById(R.id.duration);
        journeyStop = itemView.findViewById(R.id.journey_stop);

    }

    @Override
    public void onClick(View v) {

    }
}
