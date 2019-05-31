package com.challenge.travel_buddy.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;

class StationViewHolder extends RecyclerView.ViewHolder {
    public TextView stationName;
    public StationViewHolder(@NonNull View itemView) {
        super(itemView);
        stationName = itemView.findViewById(R.id.station_name);
    }
}
