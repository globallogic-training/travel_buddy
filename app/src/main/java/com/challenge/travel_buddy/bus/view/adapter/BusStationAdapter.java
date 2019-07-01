package com.challenge.travel_buddy.bus.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.services.model.BusModel;
import com.challenge.travel_buddy.train.services.model.SearchStationModel;
import com.challenge.travel_buddy.train.view.adapter.StationViewHolder;

import java.util.List;

public class BusStationAdapter extends RecyclerView.Adapter<StationViewHolder> {
    private final boolean mIsFrom;
    Context mContext;
    List<BusModel> mStations;

    public BusStationAdapter(Context context, List<BusModel> stationList,boolean isFrom) {
        mContext = context;
        mStations = stationList;
        mIsFrom = isFrom;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.station_list_item, parent, false);
        return new StationViewHolder(view, mContext, mIsFrom);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        BusModel model = mStations.get(position);
        holder.stationName.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return mStations.size();
    }
}
