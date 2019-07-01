package com.challenge.travel_buddy.flight.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.flight.services.model.AirportModel;
import com.challenge.travel_buddy.train.services.model.SearchStationModel;

import java.util.List;

public class AirportListAdapter extends RecyclerView.Adapter<AirportViewHolder> {

    private final boolean mIsFrom;
    Context mContext;
    List<AirportModel> mStations;

    public AirportListAdapter(Context context, List<AirportModel> airportList, boolean isFrom) {
        mContext = context;
        mStations = airportList;
        mIsFrom = isFrom;
    }

    @NonNull
    @Override
    public AirportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.station_list_item, viewGroup,false);
        return new AirportViewHolder(view,mContext,mIsFrom);

    }

    @Override
    public void onBindViewHolder(@NonNull AirportViewHolder stationViewHolder, int position) {
        AirportModel model = mStations.get(position);
        stationViewHolder.airportName.setText(model.getXtr().getCn() + " ("+ model.getIata()+")");
    }

    @Override
    public int getItemCount() {
        return mStations.size();
    }
}

