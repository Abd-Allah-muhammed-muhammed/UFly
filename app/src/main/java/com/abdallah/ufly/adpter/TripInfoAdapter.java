package com.abdallah.ufly.adpter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemTripsBinding;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.ui.description.TripDescriptionFragment;

import java.util.ArrayList;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class TripInfoAdapter  extends RecyclerView.Adapter<TripInfoAdapter.TripInfoViewHolder> {

    private ArrayList<TripsResponse> tripInfoArrayList;


    @NonNull
    @Override
    public TripInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ItemTripsBinding itemTripsBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_trips, parent, false);
        return new TripInfoViewHolder(itemTripsBinding);
    }

    int id ;

    public TripInfoAdapter(int id) {

        this.id = id;
    }

    @Override
    public void onBindViewHolder(@NonNull TripInfoAdapter.TripInfoViewHolder holder, int position) {

        final TripsResponse tripInfo = tripInfoArrayList.get(position);
        holder.itemTripsBinding.setTripinfo(tripInfo);
        holder.itemTripsBinding.itemTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id==1){


                    TripDescriptionFragment fragment = new TripDescriptionFragment();
                    Bundle bundle = new Bundle();

                    bundle.putInt("TripId",tripInfo.getId());
                    bundle.putString("Trip_desc",tripInfo.getDescription());
                    bundle.putString("Trip_from",tripInfo.getDepartuer());
                    bundle.putString("Trip_to",tripInfo.getArrival());
                    bundle.putString("price",tripInfo.getPrice());
                    bundle.putString("includes",tripInfo.getIncludes());

                    fragment.setArguments(bundle);
                    replace(fragment,R.id.frame_main,((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction()
                            ,v.getContext().getString(R.string.tag_desc));

                }else {



                }

            }
        });

    }


    public void setTripInfoList(ArrayList<TripsResponse> tripsResponse) {
        this.tripInfoArrayList = tripsResponse;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (tripInfoArrayList != null) {
            return tripInfoArrayList.size();
        } else {
            return 0;
        }
    }

    public class TripInfoViewHolder extends RecyclerView.ViewHolder {
        private ItemTripsBinding itemTripsBinding;
        public TripInfoViewHolder(@NonNull ItemTripsBinding itemView) {
            super(itemView.getRoot());
            this.itemTripsBinding = itemView;
        }
    }





}
