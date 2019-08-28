package com.challenge.travel_buddy.train.trainsearch.view.ui.helper;

import android.text.format.DateFormat;

import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;

import org.jsoup.helper.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static Date getDateFromDashDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy");
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

    public static String getShortStationName(String stationName){
        String[] shortNameArray = stationName.split("\\(");
        String shortName = shortNameArray.length > 1 ? shortNameArray[1] : shortNameArray[0].split("-")[0];
        return shortName.substring(0, shortName.length() -1);
    }

    public static String getBusStationName(String stationName){
        String[] shortNameArray = stationName.split("\\(");
        String shortName = shortNameArray.length > 1 ? shortNameArray[0] : stationName;
        return shortName;
    }

    public static String getDashDate(String date){
        Date formateDate = getDateFromString(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyy");
        return dateFormat.format(formateDate);
    }

    public static String getAvailDateString(int day, int month, int year){
        month+=1;
        String incDate = "";
        incDate = (day < 10) ? incDate.concat("0" + day) : ("" + day);
        incDate = (month < 10) ? incDate.concat("0" + month) : ("" + month);
        incDate = incDate.concat("" + year);
        return incDate;
    }


    public static String getIncreamentedDate(String date, boolean isIncDate){
        Date travelDate = Helper.getDateFromString(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(travelDate);
        calendar.add(Calendar.DATE, isIncDate ? 1 : -1);
        return getAvailDateString(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));
    }

    public static String formatTravelDate(String date){
        String formattedDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date inputDate = inputFormat.parse(date);
            formattedDate = outputFormat.format(inputDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static String getIncBusDate(String date, boolean isInc){
        SimpleDateFormat inputFormat = new SimpleDateFormat("d-MMM-yyyy");
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

}
