package com.challenge.travel_buddy.train.trainsearch.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.trainsearch.di.DaggerTrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.services.model.Available_Status;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainDataModel;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train_Number_Search;
import com.challenge.travel_buddy.train.trainsearch.view.adapter.TrainListAdapter;
import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainListViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

public class TrainSearch extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private String fromStationCode;
    private String toStationCode;
    private String travelDate;
    private String availDate;
    private TrainListAdapter adapter;
    private Train fetchedTrains;
    private Map <String, Map<String, Available_Status>> availableSeats;
    private RecyclerView trainListRecycler;
    private Boolean isSeatsAvilable;
    private Boolean isTrainsAvailable;
    ArrayList<TrainDataModel> serchedTrainList = new ArrayList<>();
    private Map <String, Map<String, Available_Status>> bestSL;
    private Map<String, Available_Status> bestWithClassSL;
    private Map <String, Map<String, Available_Status>> best3A;
    private Map<String, Available_Status> bestWithClass3A;
    private Map<String, String> bestSLDate;
    private Map<String, String> best3aDate;

    private TextView best_SL_trainNumber;
    public TextView best_SL_trainName;
    public TextView best_SL_Availability;
    public TextView best_3A_trainNumber;
    public TextView best_3A_trainName;

    public TextView best_SL_lbl;
    public TextView best_SL_date_lbl;
    public TextView best_3A_lbl;
    public TextView best_3A_date_lbl;

    public TextView best_3A_availibility;
    public TextView best_SL_date;
    public TextView best_3A_date;
    public ProgressBar progressBar;
    public ProgressBar train_list_progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);
        
        Intent intent = getIntent();
        fromStationCode = intent.getStringExtra("from");
        toStationCode = intent.getStringExtra("to");
        travelDate = intent.getStringExtra("date");
        availDate = intent.getStringExtra("avail_formated_date");
        trainListRecycler = findViewById(R.id.trainListRV);
        trainListRecycler.setLayoutManager(new LinearLayoutManager(this));
        isSeatsAvilable = false;
        isTrainsAvailable = false;
        bestSL = new HashMap<>();
        bestWithClassSL = new HashMap<>();
        best3A = new HashMap<>();
        bestWithClass3A = new HashMap<>();
        bestSLDate = new HashMap<>();
        best3aDate = new HashMap<>();

        best_SL_trainNumber = findViewById(R.id.best_SL_trainNumber);
        best_SL_trainName = findViewById(R.id.best_SL_trainName);
        best_SL_Availability = findViewById(R.id.best_SL_Availability);
        best_3A_trainNumber = findViewById(R.id.best_3A_trainNumber);
        best_3A_trainName = findViewById(R.id.best_3A_trainName);
        best_3A_availibility = findViewById(R.id.best_3A_availibility);
        best_SL_date = findViewById(R.id.best_SL_date);
        best_3A_date = findViewById(R.id.best_3A_date);
        progressBar = findViewById(R.id.best_avail_progress);
        train_list_progressBar = findViewById(R.id.train_list_progressBar);

        best_SL_lbl = findViewById(R.id.best_SL_lbl);
        best_SL_date_lbl = findViewById(R.id.best_SL_date_lbl);
        best_3A_lbl = findViewById(R.id.best_3A_lbl);
        best_3A_date_lbl = findViewById(R.id.best_3A_date_lbl);


        progressBar.setVisibility(View.VISIBLE);
        train_list_progressBar.setVisibility(View.VISIBLE);

        TrainSearchActivityComponent trainSearchActivityComponent = DaggerTrainSearchActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        trainSearchActivityComponent.inject(this);

        final TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TrainListViewModel.class);

        viewModel.getTrainList(getStationCode(fromStationCode), getStationCode(toStationCode), travelDate).observe(this, new Observer<Train>() {
            @Override
            public void onChanged(@Nullable Train train) {
                fetchedTrains = train;
                isTrainsAvailable = true;
                setBothData();
            }
        });

        viewModel.getAvailability(getStationCode(fromStationCode), getStationCode(toStationCode), availDate)
                .observe(this, new Observer<Map <String, Map<String, Available_Status>>>() {
                    @Override
                    public void onChanged(@Nullable Map <String, Map<String, Available_Status>> availabilityModels) {
                        availableSeats = availabilityModels;
                        isSeatsAvilable = true;
                        setBothData();
                    }
                });

        viewModel.getBestTrainInMonth(getStationCode(fromStationCode), getStationCode(toStationCode), availDate)

                .observe(this, new Observer<List<TrainAvailabilityModel>>() {
                    @Override
                    public void onChanged(List<TrainAvailabilityModel> data) {
                        boolean is_best_3A_found = false;
                        boolean is_best_SL_found = false;
                        if(data.size() == 30) {
                            for(int i=0; i< data.size(); i++){
                                Collections.sort(data, new Comparator<TrainAvailabilityModel>() {
                                    @Override
                                    public int compare(TrainAvailabilityModel o1, TrainAvailabilityModel o2) {
                                        return getDateFromString(o1.getDate()).compareTo(getDateFromString(o2.getDate()));
                                    }
                                });
                                Map<String, Map<String, Available_Status>> x = data.get(i).getData();
                                String trainDate = getDateFromTimeStamp(data.get(i).getDate());
                                Set<String> keys = x.keySet();
                                for(String key: keys){
                                    Map<String, Available_Status> avail = x.get(key);
                                    if(avail.containsKey("SL|GN")) {
                                        if (!is_best_SL_found && avail.get("SL|GN") != null && avail.get("SL|GN").getA() != null && avail.get("SL|GN").getA().startsWith("A")) {
                                            bestWithClassSL.put("SL|GN", avail.get("SL|GN"));
                                            bestSLDate.put("date",trainDate);
                                            bestSL.put(key, bestWithClassSL);
                                            is_best_SL_found = true;
                                        }
                                    }
                                    if(avail.containsKey("3A|GN")){
                                        if(!is_best_3A_found && avail.get("3A|GN")!=null && avail.get("3A|GN").getA()!=null && avail.get("3A|GN").getA().startsWith("A")){
                                            bestWithClass3A.put("3A|GN",avail.get("3A|GN"));
                                            best3A.put(key,bestWithClass3A);
                                            best3aDate.put("date", trainDate);
                                            is_best_3A_found = true;
                                        }
                                    }
                                }
                                Log.d("train", data.toString());
                            }

                            if(bestSL.size() > 0){
                                Set<String> keys = bestSL.keySet();
                                String bestTrainNumberSL = keys.iterator().next() != "" ? keys.iterator().next() : "Not Found";
                                Available_Status bestSLObj = bestSL.get(keys.iterator().next()).get("SL|GN");
                                best_SL_trainNumber.setText(bestTrainNumberSL);
                                best_SL_Availability.setText(bestSLObj.getA());
                                best_SL_date_lbl.setText(" on ");
                                best_SL_lbl.setText("SL : ");
                                best_SL_date.setText(bestSLDate.get("date"));
                                viewModel.getTrainFromCodeSL(bestTrainNumberSL)
                                        .observe(TrainSearch.this, new Observer<Train_Number_Search>() {
                                            @Override
                                            public void onChanged(Train_Number_Search train_number_search) {
                                                best_SL_trainName.setText(train_number_search.getCn()!=null ? train_number_search.getCn() : train_number_search.getN());
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        });
                            }

                            if(best3A.size() > 0){
                                Set<String> keys = best3A.keySet();
                                String bestTrainNumber3A = keys.iterator().next() != "" ? keys.iterator().next() : "Not Found";
                                Available_Status _3AAvail = best3A.get(keys.iterator().next()).get("3A|GN");
                                best_3A_trainNumber.setText(bestTrainNumber3A);
                                best_3A_availibility.setText(_3AAvail.getA());
                                best_3A_date.setText(best3aDate.get("date"));
                                best_3A_date_lbl.setText(" on ");
                                best_3A_lbl.setText("3A : ");
                                viewModel.getTrainFromCode3A(bestTrainNumber3A)
                                        .observe(TrainSearch.this, new Observer<Train_Number_Search>() {
                                            @Override
                                            public void onChanged(Train_Number_Search train_number_search) {
                                                best_3A_trainName.setText(train_number_search.getCn()!=null ? train_number_search.getCn() : train_number_search.getN());
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        });
                            }
                        }
                    }

                });



        System.out.println("In Activity");
    }
    private String getStationCode(String stationName){
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

//        String regex = "\\s*\\bstations\\b\\s*";
//        String content = stationName.replaceAll(regex, "");
//        String regex1 = "\\s*\\b-\\b\\s*";
//        String content1 = content.replaceAll(regex1, "-");
        return stationCode;
    }

    public void setBothData() {
        if(isSeatsAvilable && isTrainsAvailable){
            for(TrainDataModel trainModel: fetchedTrains.getData().getTrainsBetweenStations()){
                String trainNumber = trainModel.getAttributes().getNumber();
                Map<String, Available_Status> avail = availableSeats.get(trainNumber);
                if(avail != null){
                    trainModel.setAvailable_seats(avail);
                    serchedTrainList.add(trainModel);
                }
            }
            fetchedTrains.getData().setTrainsBetweenStations(serchedTrainList);
            adapter = new TrainListAdapter(TrainSearch.this, fetchedTrains.getData().getTrainsBetweenStations());
            trainListRecycler.setAdapter(adapter);
            isSeatsAvilable = false;
            isTrainsAvailable = false;
            train_list_progressBar.setVisibility(View.GONE);
        }
    }

    /* Function to convert date string to given date string format */
    public String getDateFromTimeStamp(String timeStamp){
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

    /* Function to convert string to given date format */
    public Date getDateFromString(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date orignalDate = null;
        try{

            orignalDate =  formatter.parse(date);
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return orignalDate;
    }
}
