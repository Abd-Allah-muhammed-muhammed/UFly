package com.abdallah.ufly.adpter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemMyTripsBinding;
import com.abdallah.ufly.helper.GlideApp;
import com.abdallah.ufly.model.my_trip.DataTrip;
import com.abdallah.ufly.model.my_trip.MyTripsResponse;

import java.util.ArrayList;

public class MyTripsAdapter extends RecyclerView.Adapter<MyTripsAdapter.MyTripsViewHolder> {


    MyTripsResponse tripsResponseArrayList ;
    int typeTrips ;
    Context context ;
    Activity activity ;

    public MyTripsAdapter(int typeTrips, Context context, Activity activity) {
        this.typeTrips = typeTrips;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyTripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMyTripsBinding  binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_my_trips,parent,false);



        return new  MyTripsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTripsViewHolder holder, int position) {

        DataTrip dataTrip = tripsResponseArrayList.getDataTrips().get(position);
        holder.binding.setTrips(dataTrip);

        String image = dataTrip.getImage();
        GlideApp.with(context).load(image).into(holder.binding.imageMyTrips);
    }

    @Override
    public int getItemCount() {

        if (tripsResponseArrayList != null) {
            return tripsResponseArrayList.getDataTrips().size();
        } else {
            return 0;
        }

    }


    public  void  setMyTrips (MyTripsResponse tripsResponseArrayList){


        this.tripsResponseArrayList = tripsResponseArrayList;
        notifyDataSetChanged();

    }

    public class MyTripsViewHolder extends RecyclerView.ViewHolder {
        ItemMyTripsBinding binding ;

        public MyTripsViewHolder(@NonNull ItemMyTripsBinding itemView) {

            super(itemView.getRoot());
            this.binding = itemView;

        }
    }
}
