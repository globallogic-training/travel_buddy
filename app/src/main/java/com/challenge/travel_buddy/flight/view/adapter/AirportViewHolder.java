package com.challenge.travel_buddy.flight.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.view.ui.SearchActivity;

public class AirportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView airportName;
    public TextView cityName;
    public Context mContext;
    private boolean mIsFrom;
    public AirportViewHolder(@NonNull View itemView, Context context, boolean isFrom) {
        super(itemView);
        cityName = itemView.findViewById(R.id.city_name);
        airportName = itemView.findViewById(R.id.airport_name);
        mContext = context;
        mIsFrom = isFrom;
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, SearchActivity.class);
        intent.putExtra("station", trimStringAfterComma(((TextView) v.findViewById(R.id.city_name)).getText()+""));
        intent.putExtra("isFrom", mIsFrom);
        ((Activity)mContext).setResult(Activity.RESULT_OK, intent);
        ((Activity)mContext).finish();
    }

    private String trimStringAfterComma(String str){
        String newstr = "";
        if (null != str && str.length() > 0 )
        {
            int endIndex = str.lastIndexOf(",");
            if (endIndex != -1)
            {
                 newstr = str.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
            }
        }
        return newstr;
    }
}