package com.challenge.travel_buddy.train.trainsearch.view.ui.helper;

import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

    /* Function to sort object in array */

    public static List<TrainAvailabilityModel> sortTrainsArrayByDate(List<TrainAvailabilityModel> data){
        Collections.sort(data, new Comparator<TrainAvailabilityModel>() {
            @Override
            public int compare(TrainAvailabilityModel o1, TrainAvailabilityModel o2) {

                if (o1 == null) {
                    return (o2 == null) ? 0 : -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return Helper.getDateFromString(o1.getDate()).compareTo(Helper.getDateFromString(o2.getDate()));
            }
        });
        return data;
    }
}
