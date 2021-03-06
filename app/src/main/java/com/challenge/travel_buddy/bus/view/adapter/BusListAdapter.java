package com.challenge.travel_buddy.bus.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.services.model.BusModel;
import com.challenge.travel_buddy.bus.services.model.busresponse.Inv;
import com.challenge.travel_buddy.bus.util.Utils;

import java.text.DecimalFormat;
import java.util.List;

public class BusListAdapter extends RecyclerView.Adapter<BusItemVH> {

    Context mContext;
    List<Inv> mBusList;

    public BusListAdapter(Context context, List<Inv> busList) {
        this.mBusList = busList;
        this.mContext = context;
    }


    @NonNull
    @Override
    public BusItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bus_item, parent, false);
        return new BusItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusItemVH holder, int position) {
        Inv model = mBusList.get(position);
        holder.busName.setText(model.getTvs());
        holder.busType.setText(model.getBt());
        holder.total_bus_seats.setText(model.getNsa().toString());
        holder.total_window_seats.setText(model.getWnSt().toString());
        holder.bus_bp.setText(model.getStdBp());
        holder.bus_dp.setText(model.getStdDp());
        holder.bus_cost.setText(model.getMinfr().toString());
        holder.busArrivalTime.setText(Utils.formatArrDepTime(model.getAt()));
        holder.busDepartureTime.setText(Utils.formatArrDepTime(model.getDt()));
        holder.bus_travel_hrs.setText(Utils.converMinsToHrs(model.getDuration()));
        if(model.getRt().getTotRt() != null)
            holder.busRating.setText(""+ Math.floor(model.getRt().getTotRt() * 10) / 10);
    }

    @Override
    public int getItemCount() {
        return mBusList.size();
    }
}
