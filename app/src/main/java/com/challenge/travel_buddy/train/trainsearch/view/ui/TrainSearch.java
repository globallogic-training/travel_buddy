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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.services.model.Available_Status;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainDataModel;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train_Number_Search;
import com.challenge.travel_buddy.train.trainsearch.view.adapter.TrainListAdapter;
import com.challenge.travel_buddy.train.trainsearch.view.ui.helper.Helper;
import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
    public LinearLayout progressBar;
    public LinearLayout trainListProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        trainListProgressBar = findViewById(R.id.trainListProgress);

        best_SL_lbl = findViewById(R.id.best_SL_lbl);
        best_SL_date_lbl = findViewById(R.id.best_SL_date_lbl);
        best_3A_lbl = findViewById(R.id.best_3A_lbl);
        best_3A_date_lbl = findViewById(R.id.best_3A_date_lbl);


        progressBar.setVisibility(View.VISIBLE);
        trainListProgressBar.setVisibility(View.VISIBLE);

//        TrainSearchActivityComponent trainSearchActivityComponent = DaggerTrainSearchActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
//        trainSearchActivityComponent.inject(this);

        final TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TrainListViewModel.class);

        viewModel.getTrainList(Helper.getStationCode(fromStationCode), Helper.getStationCode(toStationCode), travelDate).observe(this, new Observer<Train>() {
            @Override
            public void onChanged(@Nullable Train train) {
                fetchedTrains = train;
                isTrainsAvailable = true;
                setBothData();
            }
        });

        viewModel.getAvailability(Helper.getStationCode(fromStationCode), Helper.getStationCode(toStationCode), availDate)
                .observe(this, new Observer<Map <String, Map<String, Available_Status>>>() {
                    @Override
                    public void onChanged(@Nullable Map <String, Map<String, Available_Status>> availabilityModels) {
                        availableSeats = availabilityModels;
                        isSeatsAvilable = true;
                        setBothData();
                    }
                });

        viewModel.getBestTrainInMonth(Helper.getStationCode(fromStationCode), Helper.getStationCode(toStationCode), availDate)

                .observe(this, new Observer<List<TrainAvailabilityModel>>() {
                    @Override
                    public void onChanged(List<TrainAvailabilityModel> data) {
                       setTrainListOnResponse(data,viewModel);
                    }

                });



        System.out.println("In Activity");
    }

    private void setTrainListOnResponse(List<TrainAvailabilityModel> data, TrainListViewModel viewModel){
        boolean is_best_3A_found = false;
        boolean is_best_SL_found = false;
        if(data.size() == 30) {
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

            Iterator<TrainAvailabilityModel> itr = data.iterator();
            while(itr.hasNext()){
                TrainAvailabilityModel trainmaodel = itr.next();

                if(trainmaodel == null)
                    itr.remove();
            }
            for(int i=0; i< data.size(); i++){
                Map<String, Map<String, Available_Status>> x = data.get(i).getData();
                String trainDate = Helper.getDateFromTimeStamp(data.get(i).getDate());
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
                                best_SL_trainName.setText(train_number_search.getN()!=null ? train_number_search.getN() : train_number_search.getCn());
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }else{
                progressBar.setVisibility(View.GONE);
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
            }else{
                progressBar.setVisibility(View.GONE);
            }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setBothData() {
        ArrayList<TrainDataModel> serchedTrainList = new ArrayList<>();
        if(isSeatsAvilable && isTrainsAvailable && fetchedTrains != null){

            for(TrainDataModel trainModel: fetchedTrains.getData().getTrainsBetweenStations()){
                String trainNumber = trainModel.getAttributes().getNumber();
                if(availableSeats != null) {
                    Map<String, Available_Status> avail = availableSeats.get(trainNumber);
                    if (avail != null) {
                        trainModel.setAvailable_seats(avail);
                        serchedTrainList.add(trainModel);
                    } else {
                        serchedTrainList.add(trainModel);
                    }
                }else{
                    serchedTrainList.add(trainModel);
                }

            }

            fetchedTrains.getData().setTrainsBetweenStations(serchedTrainList);
            adapter = new TrainListAdapter(TrainSearch.this, fetchedTrains.getData().getTrainsBetweenStations());
            trainListRecycler.setAdapter(adapter);
            isSeatsAvilable = false;
            isTrainsAvailable = false;
            trainListProgressBar.setVisibility(View.GONE);
        }else if( fetchedTrains == null) {
            trainListProgressBar.setVisibility(View.GONE);
        }
    }

}
