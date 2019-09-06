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
            holder.depFrom.setText((String) model.get("cityFrom"));
            holder.arrTo.setText((String) model.get("cityTo"));
            holder.flightCost.setText(""+ ( (int) model.get("price")));
            holder.airlinesName.setText(FlightHelper.getAirlinesName((List<String>)model.get("airlines")));
            holder.duration.setText("Dur: "+(String) model.get("fly_duration"));
            holder.depTime.setText("Dep: "+Utils.epochToString( ""+model.get("aTime")));
            holder.arrTime.setText("Arr: "+Utils.epochToString(""+model.get("dTime")));
            if(((List)model.get("route")).size() == 2 ){
                List<Map<String,Object>> routeList = (List)model.get("route");
                if(((String)routeList.get(0).get("cityTo")).equals((String) routeList.get(1).get("cityFrom"))){
                    holder.journeyStop.setText(model.get("cityFrom")+" -> "+(String)routeList.get(0).get("cityTo")+" -> "+model.get("cityTo")+ "   "+((int)routeList.size()-1)+" stop");
                    holder.journeyStop.setVisibility(View.VISIBLE);
                }
            }else if(((List)model.get("route")).size() == 3){
                List<Map<String,Object>> routeList = (List)model.get("route");
                if(((String)routeList.get(0).get("cityTo")).equals((String) routeList.get(1).get("cityFrom"))
                    & ((String)routeList.get(1).get("cityTo")).equals((String) routeList.get(2).get("cityFrom"))){
                    holder.journeyStop.setText(model.get("cityFrom")+" -> "+(String)routeList.get(0).get("cityTo")+" -> "+
                            (String)routeList.get(1).get("cityTo")+" -> "+model.get("cityTo")+ "   "+((int)routeList.size()-1)+" stop");
                    holder.journeyStop.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

}
