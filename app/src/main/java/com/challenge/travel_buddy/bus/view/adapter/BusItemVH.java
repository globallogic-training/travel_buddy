package com.challenge.travel_buddy.bus.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;

public class BusItemVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView busName;
    public TextView busType;
    public TextView total_bus_seats;
    public TextView total_window_seats;
    public TextView bus_bp;
    public TextView bus_dp;
    public TextView busArrivalTime;
    public TextView busDepartureTime;
    public TextView bus_travel_hrs;
    public TextView bus_cost;
    public TextView busRating;


    public BusItemVH(@NonNull View itemView) {
        super(itemView);
        busName = itemView.findViewById(R.id.busName);
        busType = itemView.findViewById(R.id.busType);
        total_bus_seats = itemView.findViewById(R.id.total_bus_seats);
        total_window_seats = itemView.findViewById(R.id.total_window_seats);
        bus_bp = itemView.findViewById(R.id.bus_bp);
        bus_dp = itemView.findViewById(R.id.bus_dp);
        busArrivalTime = itemView.findViewById(R.id.bus_arrival_time);
        busDepartureTime = itemView.findViewById(R.id.bus_departure_time);
        bus_travel_hrs = itemView.findViewById(R.id.bus_travel_hrs);
        bus_cost = itemView.findViewById(R.id.bus_cost);
        busRating = itemView.findViewById(R.id.busRating);
    }

    @Override
    public void onClick(View v) {

    }
}
