package com.challenge.travel_buddy.bus.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String formatArrDepTime(String time){
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

    public static String formatArrDepTimeWithDate(String time){
        SimpleDateFormat stringTimetoDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat dateToString = new SimpleDateFormat("dd MMM yyyy hh:mm");
        String formattedTime = "";
        try {
            Date date = stringTimetoDate.parse(time);
            formattedTime = dateToString.format(date);
        }catch (ParseException e){
            Log.d("date parse exception", e.getMessage());
        }
        return formattedTime;
    }

    public static String converMinsToHrs(Integer mins){
        Integer hrs = (mins/60);
        Integer minutes = (mins%60);
        return ""+ hrs + "h " + minutes + "m";
    }

    public static String epochToString(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        String date = sdf.format(new Date(Long.parseLong(time) * 1000l));
        return date;
    }
}
