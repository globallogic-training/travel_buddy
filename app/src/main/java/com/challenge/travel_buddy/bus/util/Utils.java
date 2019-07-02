package com.challenge.travel_buddy.bus.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public String format_arr_dep_time(String time){
        SimpleDateFormat stringTimetoDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat dateToString = new SimpleDateFormat("hh:mm");
        String formattedTime = "";
        try {
            Date date = stringTimetoDate.parse(time);
            formattedTime = dateToString.format(date);
        }catch (ParseException e){
            Log.d("date parse exception", e.getMessage());
        }
        return formattedTime;
    }

    public String converMinsToHrs(Integer mins){
        Integer hrs = (mins/60);
        Integer minutes = (mins%60);
        return ""+ hrs + "h " + minutes + "m";
    }
}
