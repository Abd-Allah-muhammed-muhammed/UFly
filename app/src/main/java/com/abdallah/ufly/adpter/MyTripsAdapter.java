package com.abdallah.ufly.adpter;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyTripsViewHolder holder, int position) {

        final DataTrip dataTrip = tripsResponseArrayList.getDataTrips().get(position);
        final DataBook dataBook = tripsResponseArrayList.getDataBook().get(position);
        holder.binding.setTrips(dataTrip);

        holder.binding.def.setText("You Have "+ 24 +" Hours To Pay  or the trip will be removed");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");


//        String created_at = dataBook.getCreated_at();
//        String[] s = created_at.split(" ");
//        String s1 = s[0];
//
//        try {
//
//            Date date1 = simpleDateFormat.parse(s1);
//            Date date2 = simpleDateFormat.parse(dataTrip.getDateFrom());
//
//          long def =   printDifference(date1, date2);
//
//          holder.binding.def.setText("Still "+def +"Hours To Pay  or the trip will remove");
//
//        }catch (Exception w){
//            w.getMessage();
//        }


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




    public long printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different_ = endDate.getTime() - startDate.getTime();

//        System.out.println("startDate : " + startDate);
//        System.out.println("endDate : "+ endDate);
//        System.out.println("different : " + different);
//
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

//        long elapsedDays = different / daysInMilli;
//        different = different % daysInMilli;
//
//        long elapsedHours = different / hoursInMilli;
//        different = different % hoursInMilli;
//
//        long elapsedMinutes = different / minutesInMilli;
//        different = different % minutesInMilli;
//
//        long elapsedSeconds = different / secondsInMilli;
//
//        System.out.printf(
//                "%d days, %d hours, %d minutes, %d seconds%n",
//                elapsedDays/2, elapsedHours/2, elapsedMinutes/2, elapsedSeconds/2);


        return  different_/hoursInMilli/2;
    }
}
