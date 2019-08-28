package com.challenge.travel_buddy.bus.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.di.BusPointActivityComponent;
import com.challenge.travel_buddy.bus.di.DaggerBusPointActivityComponent;
import com.challenge.travel_buddy.bus.services.model.busresponse.Inv;
import com.challenge.travel_buddy.bus.util.Utils;
import com.challenge.travel_buddy.bus.view.adapter.BusListAdapter;
import com.challenge.travel_buddy.bus.viewmodal.BusListViewModel;
import com.challenge.travel_buddy.train.trainsearch.view.ui.OnSwipeTouchListener;
import com.challenge.travel_buddy.train.trainsearch.view.ui.TrainSearch;
import com.challenge.travel_buddy.train.trainsearch.view.ui.helper.Helper;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

public class BusResultActivity extends AppCompatActivity {

    private String fromStation;
    private String toStation;
    private String travelDate;
    private String availDate;
    private String fromStationId;
    private String toStationId;
    private BusListAdapter adapter;
    private RecyclerView busListRecycler;
    private ProgressBar best_bus_progress;
    private Inv invObj;
    private TextView busName;
    private TextView busType;
    private TextView total_bus_seats;
    private TextView total_window_seats;
    private TextView bus_bp;
    private TextView bus_dp;
    private TextView busArrivalTime;
    private TextView busDepartureTime;
    private TextView bus_travel_hrs;
    private TextView bus_cost;
    private TextView busRating;
    private TextView total_bus_seats_lbl;
    private TextView total_window_seats_lbl;
    private TextView bus_time_divider;

    private ShimmerFrameLayout mShimmerViewContainer;

    public TextView src_dest_top_bus;
    public TextView travel_date_top_bus;
    public Button next_day_btn_bus;
    public Button prev_day_btn_bus;

    public LiveData<String> searchDate;
    public TextView noDataTv;
    public ImageView nodataicon;
    public RelativeLayout best_bus_linearLayout;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        fromStation = intent.getStringExtra("from");
        toStation = intent.getStringExtra("to");
        travelDate = formatDate(intent.getStringExtra("date"));
        availDate = intent.getStringExtra("avail_formated_date");
        fromStationId = intent.getStringExtra("fromStationId");
        toStationId = intent.getStringExtra("toStationId");
        busListRecycler = findViewById(R.id.busListRV);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        busListRecycler.setLayoutAnimation(animation);
        busListRecycler.setLayoutManager(new LinearLayoutManager(this));
        searchDate = new MutableLiveData<>();


        best_bus_progress = findViewById(R.id.best_bus_progress);

        busName = findViewById(R.id.busName);
        busType = findViewById(R.id.busType);
        total_bus_seats = findViewById(R.id.total_bus_seats);
        total_window_seats = findViewById(R.id.total_window_seats);
        bus_bp = findViewById(R.id.bus_bp);
        bus_dp = findViewById(R.id.bus_dp);
        busArrivalTime = findViewById(R.id.bus_arrival_time);
        busDepartureTime = findViewById(R.id.bus_departure_time);
        bus_travel_hrs = findViewById(R.id.bus_travel_hrs);
        bus_cost = findViewById(R.id.bus_cost);
        busRating = findViewById(R.id.busRating);
        total_bus_seats_lbl = findViewById(R.id.total_bus_seats_lbl);
        total_window_seats_lbl = findViewById(R.id.total_window_seats_lbl);
        bus_time_divider = findViewById(R.id.bus_time_divider);

        src_dest_top_bus = findViewById(R.id.src_dest_top_bus);
        travel_date_top_bus = findViewById(R.id.travel_date_top_bus);
        next_day_btn_bus = findViewById(R.id.next_day_btn_bus);
        prev_day_btn_bus = findViewById(R.id.prev_day_btn_bus);

        noDataTv = findViewById(R.id.noDataTv_bus);
        nodataicon = findViewById(R.id.nodataicon_bus);
        best_bus_linearLayout = findViewById(R.id.best_bus_linearLayout);

        src_dest_top_bus.setText(Helper.getBusStationName(fromStation) + " -> "+ Helper.getBusStationName(toStation));
        travel_date_top_bus.setText(Helper.getDashDate(availDate));

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container_bus);
        mShimmerViewContainer.startShimmerAnimation();

        setPrevBtnFunctionality(travelDate);

        BusPointActivityComponent busPointActivityComponent = DaggerBusPointActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        busPointActivityComponent.inject(this);

        final BusListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BusListViewModel.class);

        viewModel.setSerchDate(travelDate);

//        setPrevBtnFunctionality(availDate);
        searchDate = viewModel.getSearchDate();

        searchDate.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String changedDate) {
                viewModel.getAllBuses(fromStation,toStation,fromStationId,toStationId,changedDate)
                        .observe(BusResultActivity.this, new Observer<List<Inv>>() {
                            @Override
                            public void onChanged(List<Inv> invs) {
                                if(invs!=null && invs.size() > 0){
                                    adapter = new BusListAdapter(BusResultActivity.this, invs);
                                    busListRecycler.setAdapter(adapter);
                                    travelDate = changedDate;
                                    busListRecycler.setVisibility(View.VISIBLE);
                                    mShimmerViewContainer.setVisibility(View.GONE);
                                    travel_date_top_bus.setText(changedDate);
                                    noDataTv.setVisibility(View.GONE);
                                    noDataTv.setVisibility(View.GONE);
                                    best_bus_linearLayout.setVisibility(View.VISIBLE);
                                }
                                else{
                                    busListRecycler.setVisibility(View.GONE);
                                    mShimmerViewContainer.setVisibility(View.GONE);
                                    nodataicon.setVisibility(View.VISIBLE);
                                    noDataTv.setVisibility(View.VISIBLE);
                                    best_bus_linearLayout.setVisibility(View.GONE);

                                }
                            }
                        });

                viewModel.getFutureBuses(fromStation,toStation,fromStationId,toStationId,changedDate)
                        .observe(BusResultActivity.this, new Observer<Map<Date, List<Inv>>>() {
                            @Override
                            public void onChanged(Map<Date, List<Inv>> superBusList) {
                                getOptimizedResult(superBusList);
                            }
                        });
            }
        });


        next_day_btn_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incBusTravelDate = Helper.getIncBusDate(travelDate, true);
                viewModel.setSerchDate(incBusTravelDate);
                performNextPrev(incBusTravelDate);
            }
        });

        prev_day_btn_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decrementedDate = Helper.getIncBusDate(travelDate, false);
                viewModel.setSerchDate(decrementedDate);
                performNextPrev(decrementedDate);
            }
        });

        busListRecycler.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                String increamentedDate = Helper.getIncBusDate(travelDate, true);
                viewModel.setSerchDate(increamentedDate);
                performNextPrev(increamentedDate);
                String dashedIncDate = Helper.getDashDate(increamentedDate);
                Toast.makeText(BusResultActivity.this, "Results for : "+ dashedIncDate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Date date = Helper.getDateFromDashDate(travelDate);
                if(date.getTime() > System.currentTimeMillis()){
                    String decrementedDate = Helper.getIncBusDate(travelDate, false);
                    viewModel.setSerchDate(decrementedDate);
                    performNextPrev(decrementedDate);
                    String dashedDecrDate = Helper.getDashDate(decrementedDate);
                    Toast.makeText(BusResultActivity.this, "Results for : "+ dashedDecrDate, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BusResultActivity.this, "Sorry!.. No train results for past date.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String formatDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("d-MMM-yyyy");
        String formatedDate = "";
        try {
            Date date1 = formatter.parse(date);
            formatedDate = formatter1.format(date1);
            Log.d("formated date", formatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatedDate;
    }

    void getOptimizedResult(Map<Date, List<Inv>> superBusList){
        List<Inv> invObjList = new ArrayList<>();
        Set<Date> keys = superBusList.keySet();
        invObj = superBusList.get(keys.iterator().next()).get(0);
        for(Date date : superBusList.keySet()){

            List<Inv> buses = superBusList.get(date);
            Inv bestBus = getBestBus(buses);

            if(bestBus != null && invObj != null ){
                invObj = ( invObj.getMinfr() < bestBus.getMinfr() ) ? invObj : bestBus;
            }
        }

        busName.setText(invObj.getTvs());
        busType.setText(invObj.getBt());

        total_bus_seats.setText(invObj.getNsa().toString());
        total_window_seats.setText(invObj.getWnSt().toString());
        total_bus_seats_lbl.setText("Total Seats : ");
        total_window_seats_lbl.setText("Window Seats : ");
        bus_time_divider.setText("|");
//        bus_bp.setText(invObj.getStdBp());
//        bus_dp.setText(invObj.getStdDp());
        bus_cost.setText("₹ "+invObj.getMinfr().toString());
//        busArrivalTime.setText("Arrv Date: "+Utils.formatArrDepTimeWithDate(invObj.getAt()));
        busDepartureTime.setText("Date: "+Utils.formatArrDepTimeWithDate(invObj.getDt()));
        bus_travel_hrs.setText(Utils.converMinsToHrs(invObj.getDuration()));
        if(invObj.getRt().getTotRt() != null)
            busRating.setText(  Math.floor(invObj.getRt().getTotRt() * 10) / 10 +"\u2605");



        best_bus_progress.setVisibility(View.GONE);
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        best_bus_linearLayout.setVisibility(View.VISIBLE);

    }

    private Inv getBestBus(List<Inv> buses) {
        Inv invObjDay = buses.get(0) ;
        for(Inv bus : buses){
            int isHighRate = Double.compare(bus.getRt().getTotRt(), 4d);
            if(invObjDay != null && bus != null && isHighRate > 0  ){
              invObjDay = invObjDay.getMinfr() < bus.getMinfr() ? invObjDay : bus;
            }
        }
        return invObjDay;
    }

    public void setPrevBtnFunctionality(String dateOfSearch){
        Date date = Helper.getDateFromDashDate(dateOfSearch);
        prev_day_btn_bus.setEnabled(date.getTime() > System.currentTimeMillis());
    }

    public void performNextPrev(String date){
        busListRecycler.setVisibility(View.GONE);
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
        setPrevBtnFunctionality(date);
    }

}
