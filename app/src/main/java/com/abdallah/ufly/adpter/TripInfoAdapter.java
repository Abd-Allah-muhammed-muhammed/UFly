package com.abdallah.ufly.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemTripsBinding;
import com.abdallah.ufly.model.TripInfo;
import com.abdallah.ufly.model.trips.TripsResponse;

import java.util.ArrayList;

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

    @Override
    public void onBindViewHolder(@NonNull TripInfoAdapter.TripInfoViewHolder holder, int position) {

        final TripsResponse tripInfo = tripInfoArrayList.get(position);
        holder.itemTripsBinding.setTripinfo(tripInfo);
        holder.itemTripsBinding.itemTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(v.getContext(), ""+tripInfo.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void setTripInfoList(ArrayList<TripsResponse> employees) {
        this.tripInfoArrayList = employees;
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
