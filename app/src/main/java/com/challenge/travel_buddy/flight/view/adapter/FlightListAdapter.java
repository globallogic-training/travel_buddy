package com.challenge.travel_buddy.flight.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.util.Utils;

import java.util.List;
import java.util.Map;

public class FlightListAdapter extends RecyclerView.Adapter<FlightItemVH> {

    Context mContext;
    List<Map<String, Object>> flightList;

    public FlightListAdapter(Context context, List<Map<String, Object>> flightList) {
        this.flightList = flightList;
//        this.flightList = flightData.getData().getGoing().getResults();
        this.mContext = context;
    }


    @NonNull
    @Override
    public FlightItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.flight_item, parent, false);
        return new FlightItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightItemVH holder, int position) {
        Map<String, Object> model = flightList.get(position);
        if(model != null){
            holder.depFrom.setText((String) model.get("cityFrom"));
            holder.arrTo.setText((String) model.get("cityTo"));
            holder.flightCost.setText(""+ ( (int) model.get("price")));
            holder.airlinesName.setText(getAirlinesName((List<String>)model.get("airlines")));
            holder.duration.setText("Dur: "+(String) model.get("fly_duration"));
            holder.depTime.setText(Utils.epochToString( ""+model.get("aTime")));
            holder.arrTime.setText(Utils.epochToString(""+model.get("dTime")));
        }
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static String getAirlinesName(List<String> airlines){

        switch (airlines.get(0)){
            case "G8":
                return "Go Air";
            case "AI":
                return "Air India";
            case "6E":
                return "Indigo";
            case "SG":
                return "Singapore Airlines";
            case "I5":
                return "Air Asia";
                default:
                    return "Undefined";
        }

    }
}
