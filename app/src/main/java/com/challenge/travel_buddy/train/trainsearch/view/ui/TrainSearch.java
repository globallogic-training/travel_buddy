package com.challenge.travel_buddy.train.trainsearch.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.challenge.travel_buddy.train.trainsearch.view.ui.helper.Helper;
import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainListViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public RelativeLayout bestTrainSection;
    public TextView noDataTv;
    public ImageView nodataicom;

    public TextView src_dest_top;
    public TextView travel_date_top;
    public Button next_day_btn;
    public Button prev_day_btn;

    private ShimmerFrameLayout mShimmerViewContainer;

    public LiveData<String> searchDate;

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
        searchDate = new MutableLiveData<>();

        best_SL_trainNumber = findViewById(R.id.best_SL_trainNumber);
        best_SL_trainName = findViewById(R.id.best_SL_trainName);
        best_SL_Availability = findViewById(R.id.best_SL_Availability);
        best_3A_trainNumber = findViewById(R.id.best_3A_trainNumber);
        best_3A_trainName = findViewById(R.id.best_3A_trainName);
        best_3A_availibility = findViewById(R.id.best_3A_availibility);
        best_SL_date = findViewById(R.id.best_SL_date);
        best_3A_date = findViewById(R.id.best_3A_date);
        progressBar = findViewById(R.id.best_avail_progress);

        best_SL_lbl = findViewById(R.id.best_SL_lbl);
        best_SL_date_lbl = findViewById(R.id.best_SL_date_lbl);
        best_3A_lbl = findViewById(R.id.best_3A_lbl);
        best_3A_date_lbl = findViewById(R.id.best_3A_date_lbl);

        bestTrainSection = findViewById(R.id.best_result_linearLayout);
        noDataTv = findViewById(R.id.noDataTv);
        nodataicom = findViewById(R.id.nodataicom);

        src_dest_top = findViewById(R.id.src_dest_top);
        travel_date_top = findViewById(R.id.travel_date_top);
        next_day_btn = findViewById(R.id.next_day_btn);
        prev_day_btn = findViewById(R.id.prev_day_btn);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.startShimmerAnimation();

        src_dest_top.setText(Helper.getShortStationName(fromStationCode) + " -> "+ Helper.getShortStationName(toStationCode));
        travel_date_top.setText(Helper.getDashDate(availDate));

        TrainSearchActivityComponent trainSearchActivityComponent = DaggerTrainSearchActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        trainSearchActivityComponent.inject(this);

        final TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TrainListViewModel.class);

        viewModel.setSerchDate(availDate);

        setPrevBtnFunctionality(availDate);

        searchDate = viewModel.getSearchDate();

        searchDate.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String changedDate) {
                String incTopDashedDate = Helper.formatTravelDate(changedDate);
                viewModel.getTrainList(Helper.getStationCode(fromStationCode), Helper.getStationCode(toStationCode), incTopDashedDate).observe(TrainSearch.this, new Observer<Train>() {
                    @Override
                    public void onChanged(@Nullable Train train) {
                        fetchedTrains = train;
                        isTrainsAvailable = true;
                        setTrainAndAvailToList(changedDate);
                        availDate = changedDate;
                    }
                });

                viewModel.getAvailability(Helper.getStationCode(fromStationCode), Helper.getStationCode(toStationCode), changedDate)
                        .observe(TrainSearch.this, new Observer<Map <String, Map<String, Available_Status>>>() {
                            @Override
                            public void onChanged(@Nullable Map <String, Map<String, Available_Status>> availabilityModels) {
                                availableSeats = availabilityModels;
                                isSeatsAvilable = true;
                                setTrainAndAvailToList(changedDate);
                                availDate = changedDate;
                            }
                        });
            }
        });

        viewModel.getBestTrainInMonth(Helper.getStationCode(fromStationCode), Helper.getStationCode(toStationCode), availDate)

                .observe(this, new Observer<List<TrainAvailabilityModel>>() {
                    @Override
                    public void onChanged(List<TrainAvailabilityModel> data) {
                        boolean is_best_3A_found = false;
                        boolean is_best_SL_found = false;
                        if(data.size() == 30) {
                            data = Helper.sortTrainsArrayByDate(data);
                            Iterator<TrainAvailabilityModel> itr = data.iterator();
                            while(itr.hasNext()){
                                TrainAvailabilityModel trainModel = itr.next();
                                if(trainModel == null)
                                    itr.remove();
                            }
                            for(int i=0; i< data.size(); i++){
                                Map<String, Map<String, Available_Status>> trainPerDay = data.get(i).getData();
                                String trainDate = Helper.getDateFromTimeStamp(data.get(i).getDate());
                                Set<String> trainNumbers = trainPerDay.keySet();
                                for(String trainNumber: trainNumbers){
                                    Map<String, Available_Status> avail = trainPerDay.get(trainNumber);
                                    if(avail.containsKey("SL|GN")) {
                                        if (!is_best_SL_found && avail.get("SL|GN") != null && avail.get("SL|GN").getA() != null && (avail.get("SL|GN").getA().startsWith("A")) || avail.get("SL|GN").getA().startsWith("C")) {
                                            setBestTrainAvailData(true,trainDate,avail,trainNumber);
                                            is_best_SL_found = true;
                                        }
                                    }
                                    if(avail.containsKey("3A|GN")){
                                        if(!is_best_3A_found && avail.get("3A|GN")!=null && avail.get("3A|GN").getA()!=null && (avail.get("3A|GN").getA().startsWith("A") || avail.get("3A|GN").getA().startsWith("C"))){
                                            setBestTrainAvailData(false,trainDate,avail,trainNumber);
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
                                                bestTrainSection.setVisibility(View.VISIBLE);
                                            }
                                        });
                            }else{
                                progressBar.setVisibility(View.GONE);
                                if(!is_best_3A_found && !is_best_SL_found){
                                    bestTrainSection.setVisibility(View.GONE);
                                }
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
                                if(!is_best_3A_found && !is_best_SL_found){
                                    bestTrainSection.setVisibility(View.GONE);
                                }
                            }
                        }
                    }

                });
        System.out.println("In Activity");

        next_day_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainListRecycler.setVisibility(View.GONE);
                mShimmerViewContainer.setVisibility(View.VISIBLE);
                mShimmerViewContainer.startShimmerAnimation();
                String increamentedDate = Helper.getIncreamentedDate(availDate, true);
                viewModel.setSerchDate(increamentedDate);
                nodataicom.setVisibility(View.GONE);
                setPrevBtnFunctionality(increamentedDate);
            }
        });

        prev_day_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainListRecycler.setVisibility(View.GONE);
                mShimmerViewContainer.setVisibility(View.VISIBLE);
                mShimmerViewContainer.startShimmerAnimation();
                String decrementedDate = Helper.getIncreamentedDate(availDate, false);
                viewModel.setSerchDate(decrementedDate);
                nodataicom.setVisibility(View.GONE);
                setPrevBtnFunctionality(decrementedDate);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setTrainAndAvailToList(String date) {
        ArrayList<TrainDataModel> serchedTrainList = new ArrayList<>();

        if( fetchedTrains == null) {
            nodataicom.setVisibility(View.VISIBLE);
            noDataTv.setVisibility(View.VISIBLE);
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);

        }
        if(isSeatsAvilable && isTrainsAvailable && fetchedTrains != null){

            for(TrainDataModel trainModel: fetchedTrains.getData().getTrainsBetweenStations()){
                String trainNumber = trainModel.getAttributes().getNumber();
                if(availableSeats != null) {
                    Map<String, Available_Status> avail = availableSeats.get(trainNumber);
                    if (avail != null) {
                        trainModel.setAvailable_seats(avail);
                    }
                    serchedTrainList.add(trainModel);
                }

            }
            if(serchedTrainList.size() != 0){
                fetchedTrains.getData().setTrainsBetweenStations(serchedTrainList);
            }
            adapter = new TrainListAdapter(TrainSearch.this, fetchedTrains.getData().getTrainsBetweenStations());
            trainListRecycler.setAdapter(adapter);
            trainListRecycler.setVisibility(View.VISIBLE);
            isSeatsAvilable = false;
            isTrainsAvailable = false;
            travel_date_top.setText(Helper.getDashDate(date));
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
            nodataicom.setVisibility(View.GONE);
            noDataTv.setVisibility(View.GONE);
        }
    }

    public void setBestTrainAvailData(boolean isSL, String date, Map<String, Available_Status> avail, String trainNumber ){
        if(isSL){
            bestWithClassSL.put("SL|GN", avail.get("SL|GN"));
            bestSLDate.put("date",date);
            bestSL.put(trainNumber, bestWithClassSL);
        }
        else {
            bestWithClass3A.put("3A|GN",avail.get("3A|GN"));
            best3aDate.put("date", date);
            best3A.put(trainNumber,bestWithClass3A);
        }
    }

    public void setPrevBtnFunctionality(String dateOfSearch){
        Date date = Helper.getDateFromString(dateOfSearch);
        prev_day_btn.setEnabled(date.getTime() > System.currentTimeMillis());
    }



}