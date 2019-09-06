package com.challenge.travel_buddy.flight.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FlightHelper {

    public static String getIncrementedDate(String curreentDate){
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        String outputDate = "";
        try{
            Date formattedDate = inputFormat.parse(curreentDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formattedDate);
            calendar.add(Calendar.DATE, 15);
            outputDate = inputFormat.format(calendar.getTime());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return outputDate;
    }

    public static String getFlightDateChanged(String date, boolean isInc){
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        String outputDate = "";
        try{
            Date formattedDate = inputFormat.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formattedDate);
            calendar.add(Calendar.DATE, isInc ? 1 : -1);
            outputDate = inputFormat.format(calendar.getTime());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return outputDate;

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
            case "IX":
                return "Air India Express";
            case "G9":
                return "Air Arabia";
            case "FZ":
                return "FlyDubai";
            case "WY":
                return "Omnar Air";
            case "UK":
                return "Airlines UK";
            case "EK":
                return "Emirates";
            case "GF":
                return "Gulf Air";
            case "HM":
                return "Air Seychelles";
            default:
                return "Undefined";
        }

    }
}
