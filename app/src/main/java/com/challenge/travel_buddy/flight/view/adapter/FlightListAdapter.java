package com.challenge.travel_buddy.flight.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;

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
        holder.busName.setText((String) model.get("total"));
//        holder.busType.setText(model.getBt());
//        holder.total_bus_seats.setText(model.getNsa().toString());
//        holder.total_window_seats.setText(model.getWnSt().toString());
//        holder.bus_bp.setText(model.getStdBp());
//        holder.bus_dp.setText(model.getStdDp());
//        holder.bus_cost.setText(model.getMinfr().toString());
//        holder.busArrivalTime.setText(Utils.formatArrDepTime(model.getAt()));
//        holder.busDepartureTime.setText(Utils.formatArrDepTime(model.getDt()));
//        holder.bus_travel_hrs.setText(Utils.converMinsToHrs(model.getDuration()));
//        if(model.getRt().getTotRt() != null)
//            holder.busRating.setText(  Math.floor(model.getRt().getTotRt() * 10) / 10 +"\u2605");
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }
}
