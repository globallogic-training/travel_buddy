package com.challenge.travel_buddy.train.view.ui.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import com.challenge.travel_buddy.bus.view.ui.SearchBusActivity;
import com.challenge.travel_buddy.train.view.ui.SearchActivity;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        // Create a new instance of DatePickerDialog and return it
        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if(getActivity() instanceof SearchBusActivity){
            SearchBusActivity.setJourneyDate(view,year,month,day);
        }
        if(getActivity() instanceof SearchActivity){
            SearchActivity.setJourneyDate(view,year,month,day);
        }
    }
}
