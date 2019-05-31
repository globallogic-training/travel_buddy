package com.challenge.travel_buddy.view.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.services.model.SearchStationModel;

import java.util.Calendar;
import java.util.List;

public class StationListAdapter extends RecyclerView.Adapter<StationViewHolder> {

    Context mContext;
    List<SearchStationModel> mStations;

    public StationListAdapter(Context context, List<SearchStationModel> stationList) {
        mContext = context;
        mStations = stationList;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.station_list_item, viewGroup,false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder stationViewHolder, int position) {
        SearchStationModel model = mStations.get(position);
        stationViewHolder.stationName.setText(model.getE());
    }

    @Override
    public int getItemCount() {
        return mStations.size();
    }
}
