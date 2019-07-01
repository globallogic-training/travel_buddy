package com.challenge.travel_buddy.bus.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.view.ui.SearchBusActivity;
import com.challenge.travel_buddy.train.view.ui.SearchActivity;

public class BusStationVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView stationName;
    public Context mContext;
    private boolean mIsFrom;

    public BusStationVH(@NonNull View itemView, Context context, boolean isFrom) {
        super(itemView);
        stationName = itemView.findViewById(R.id.station_name);
        mContext = context;
        mIsFrom = isFrom;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, SearchBusActivity.class);
        intent.putExtra("station", ((TextView) v.findViewById(R.id.station_name)).getText()+"");
        intent.putExtra("isFrom", mIsFrom);
//        mContext.startActivity(intent);
        ((Activity)mContext).setResult(Activity.RESULT_OK, intent);
        ((Activity)mContext).finish();
    }
}
