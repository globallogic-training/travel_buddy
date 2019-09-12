package com.challenge.travel_buddy.hybrid.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.challenge.travel_buddy.flight.view.ui.AirportListActivity;

public class HybridHelper {

    public static void setStationIntent(Activity context, boolean isFrom, Class<?> activityClass){
        Intent intent = new Intent(context, activityClass);
        intent.putExtra("isFrom", isFrom);
        context.startActivityForResult(intent,2);
    }
}
