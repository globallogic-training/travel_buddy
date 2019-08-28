package com.challenge.travel_buddy.flight.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.flight.services.model.Datum;

import java.util.List;

public class AirportListAdapter extends RecyclerView.Adapter<AirportViewHolder> {

    private final boolean mIsFrom;
    Context mContext;
    List<Datum> mStations;

    public AirportListAdapter(Context context, List<Datum> airportList, boolean isFrom) {
        mContext = context;
        mStations = airportList;
        mIsFrom = isFrom;
    }

    @NonNull
    @Override
    public AirportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.airport_list_item, viewGroup,false);
        return new AirportViewHolder(view,mContext,mIsFrom);

    }

    @Override
    public void onBindViewHolder(@NonNull AirportViewHolder stationViewHolder, int position) {
        Datum model = mStations.get(position);
        stationViewHolder.cityName.setText(model.getAirportCode() + " - "+ model.getCityName()+","+model.getCountryName());
        stationViewHolder.airportName.setText(model.getAirportName());
    }

    @Override
    public int getItemCount() {
        return mStations.size();
    }
}

