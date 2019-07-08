package com.challenge.travel_buddy.train.trainsearch.view.ui.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    /* Function to convert string to given date format */
    public static Date getDateFromString(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date orignalDate = null;
        try{

            orignalDate =  formatter.parse(date);
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return orignalDate;
    }

    /* Function to convert date string to given date string format */
    public static String getDateFromTimeStamp(String timeStamp){
        Date orignalDate = null;
        String strigParsedDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat slashFormatter = new SimpleDateFormat("dd-MMMM-yyy");
        try {
            orignalDate = formatter.parse(timeStamp);
            System.out.println(orignalDate);
            strigParsedDate = slashFormatter.format(orignalDate);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return strigParsedDate;
    }

    public static String getStationCode(String stationName){
        String stationCode = stationName.replaceAll("\\(|\\)","");
        String [] words = stationCode.split("\\s");
        if(words[1].contains("-")){
            if(stationName.contains("- All stations")){
                return words[0]+"-All";
            }else if(stationName.contains("- All")){
                return words[0]+"-All";
            }
        }else if(words.length > 2 && words[2].contains("-")){
            if(stationName.contains("- All stations")){
                return words[0]+" "+words[1]+"-All";
            }else if(stationName.contains("- All")){
                return words[0]+" "+words[1]+"-All";
            }
        }else if(stationName.contains("(")) {
            return words[words.length - 1];
        }

        return stationCode;
    }
}
