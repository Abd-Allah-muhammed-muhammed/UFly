package com.abdallah.ufly.adpter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemMyTripsBinding;
import com.abdallah.ufly.helper.GlideApp;
import com.abdallah.ufly.model.my_trip.DataBook;
import com.abdallah.ufly.model.my_trip.DataTrip;
import com.abdallah.ufly.model.my_trip.MyTripsResponse;
import com.abdallah.ufly.ui.my_trip.myTripsDescreption.TripDescreptionActivity;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.Date;

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

        final DataTrip dataTrip = tripsResponseArrayList.getDataTrips().get(position);
        final DataBook dataBook = tripsResponseArrayList.getDataBook().get(position);
        holder.binding.setTrips(dataTrip);



        String image = dataTrip.getImage();
        GlideApp.with(context).
                load(image)

                .into(holder.binding.imageMyTrips);

        holder.binding.itemMyTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("dataTrips",dataTrip);
                bundle.putParcelable("dataBook",dataBook);
                bundle.putInt("type",typeTrips);
                bundle.putString("qr",tripsResponseArrayList.getCompanyInfo().getQr());

                Intent intent = new Intent(activity, TripDescreptionActivity.class);

                intent.putExtras(bundle);
                context.startActivity(intent);



            }
        });
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
