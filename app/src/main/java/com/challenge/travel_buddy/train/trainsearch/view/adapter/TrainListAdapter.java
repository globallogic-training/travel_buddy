package com.challenge.travel_buddy.train.trainsearch.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainDataModel;

import java.util.List;

public class TrainListAdapter extends RecyclerView.Adapter<TrainListViewHolder> {

    Context mContext;
    List<TrainDataModel> mTrainList;

    public TrainListAdapter(Context mContext, List<TrainDataModel> mTrainList) {
        this.mContext = mContext;
        this.mTrainList = mTrainList;
    }

    @NonNull
    @Override
    public TrainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.train_item, parent,false);
        return new TrainListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainListViewHolder trainListViewHolder, int i) {
        TrainDataModel train = mTrainList.get(i);
        trainListViewHolder.trainName.setText(train.getAttributes().getLocalName());
        trainListViewHolder.trainNumber.setText(train.getAttributes().getNumber());
        trainListViewHolder.travel_src.setText(train.getBoard());
        trainListViewHolder.travel_des.setText(train.getDeboard());
        trainListViewHolder.travelHrs.setText(train.getDistance() + " Km");
        trainListViewHolder.arrivalTime.setText(train.getAttributes().getArrivalTime());
        trainListViewHolder.departureTime.setText(train.getAttributes().getDepartTime());

        String sl = (train.getAvailable_seats() != null && train.getAvailable_seats().containsKey("SL|GN"))&&
                train.getAvailable_seats().get("SL|GN").getA() != ""? train.getAvailable_seats()
                .get("SL|GN").getA(): "No Data";

        String _3A = (train.getAvailable_seats() != null && train.getAvailable_seats().containsKey("3A|GN"))&&
                train.getAvailable_seats().get("3A|GN").getA() != ""?  train.getAvailable_seats()
                .get("3A|GN").getA(): "No Data";

        changeColor(sl,trainListViewHolder.SL_Available);
        changeColor(_3A,trainListViewHolder._3A_Availability);
        trainListViewHolder.SL_Available.setText(sl);
        trainListViewHolder._3A_Availability.setText(_3A);

    }

    @Override
    public int getItemCount() {
        return mTrainList.size();
    }

    public void changeColor(String data, TextView field){
        if(data.startsWith("A")){
            field.setTextColor(Color.rgb(27,94,32));
        }
        else{
            field.setTextColor(Color.rgb(198,40,40));
        }

    }
}
