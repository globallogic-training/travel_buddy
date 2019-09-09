package com.challenge.travel_buddy.flight.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.util.Utils;
import com.challenge.travel_buddy.flight.helper.FlightHelper;

import java.util.List;
import java.util.Map;

public class FlightListAdapter extends RecyclerView.Adapter<FlightItemVH> {

    Context mContext;
    List<Map<String, Object>> flightList;

    public FlightListAdapter(Context context, List<Map<String, Object>> flightList) {
        this.flightList = flightList;
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
            List<Map<String,String>> tempRoutes = (List)model.get("route");
            holder.depFrom.setText((String) model.get("cityFrom"));
            holder.arrTo.setText((String) model.get("cityTo"));
            holder.flightCost.setText(""+ ( (int) model.get("price")));
            holder.airlinesName.setText(FlightHelper.getAirlinesName((List<String>)model.get("airlines")));
            holder.duration.setText("Dur: "+(String) model.get("fly_duration"));
            holder.depTime.setText("Dep: "+Utils.epochToString( ""+model.get("aTime")));
            holder.arrTime.setText("Arr: "+Utils.epochToString(""+model.get("dTime")));
            int routesSize = tempRoutes.size();
            StringBuilder resultantRoute = new StringBuilder();
            for(int index = 0 ; index < routesSize; index++){
                Map<String, String> routeTemp = tempRoutes.get(index);
                if(index == 0){
                    resultantRoute.append(routeTemp.get("cityFrom"));
                }
                resultantRoute.append(" -> "+routeTemp.get("cityTo"));
                if(index == (routesSize - 1)){
                    if(routesSize == 1) {
                        resultantRoute = new StringBuilder("");
                    }else {
                        resultantRoute.append(" " + (routesSize - 1) + " stop");
                        holder.journeyStop.setVisibility(View.VISIBLE);
                    }
                }

                holder.journeyStop.setText(resultantRoute);
            }
        }

    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }
}
