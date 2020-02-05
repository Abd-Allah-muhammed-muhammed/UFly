package com.abdallah.ufly.adpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import com.abdallah.ufly.ui.company.add_trip.AddTripFragment;
import com.abdallah.ufly.ui.description.TripDescriptionFragment;

import java.util.ArrayList;
import java.util.Objects;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final TripInfoAdapter.TripInfoViewHolder holder, int position) {

        final TripsResponse tripInfo = tripInfoArrayList.get(position);
        holder.itemTripsBinding.setTripinfo(tripInfo);
        
        // set number of passenger booked 
        
        holder.itemTripsBinding.numberBooked.setText("("+tripInfo.getNumber_booked()+")  "+holder.itemTripsBinding.getRoot().getContext().getString(R.string.booked));
        
        // set number of availability chair 
        holder.itemTripsBinding.availableBooked.setText("("+tripInfo.getAvailable_booked()+")"+"  "+holder.itemTripsBinding.getRoot().getContext().getString(R.string.available_chair));



        if (tripInfo.getIs_complete()!=0){


            // complete

            holder.itemTripsBinding.availableBooked.setText(holder.itemTripsBinding.getRoot().getContext().getString(R.string.complete));
            holder.itemTripsBinding.completed.setVisibility(View.VISIBLE);
            holder.itemTripsBinding.transbg.setVisibility(View.VISIBLE);

        }

        holder.itemTripsBinding.itemTip.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (id==1){


                    if (tripInfo.getIs_complete()!=0){


                        // TODO: 30/01/20  create class dialog

                        Dialog dialog = new Dialog(holder.itemTripsBinding.getRoot().getContext());
                        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                        dialog.setContentView(R.layout.my_dialog);
                        dialog.show();



                    }else {

                        // passenger
                        TripDescriptionFragment fragment = new TripDescriptionFragment();
                        bundle.putInt("TripId",tripInfo.getId());
                        bundle.putString("Trip_desc",tripInfo.getDescription());
                        bundle.putString("Trip_from",tripInfo.getDepartuer());
                        bundle.putString("Trip_to",tripInfo.getArrival());
                        bundle.putString("price",tripInfo.getPrice());
                        bundle.putString("includes",tripInfo.getIncludes());
                        bundle.putString("id_comp",tripInfo.getCompany_id());
                        bundle.putInt("numberAvailability",tripInfo.getAvailable_booked());


                        fragment.setArguments(bundle);
                        replace(fragment,R.id.frame_main,((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction()
                                ,v.getContext().getString(R.string.tag_desc));

                    }

                }else {



                    // company
                    AddTripFragment addTripFragment = new AddTripFragment();
                    bundle.putInt("TripId",tripInfo.getId());
                    bundle.putInt("id",id);
                    bundle.putString("Trip_desc",tripInfo.getDescription());
                    bundle.putString("Trip_from",tripInfo.getDepartuer());
                    bundle.putString("Trip_to",tripInfo.getArrival());
                    bundle.putString("price",tripInfo.getPrice());
                    bundle.putString("includes",tripInfo.getIncludes());
                    bundle.putString("dateFrome",tripInfo.getDateFrom());
                    bundle.putString("dateUntil",tripInfo.getDateUntil());
                    bundle.putString("passengers",tripInfo.getPassengers());
                    bundle.putString("time_in",tripInfo.getTime_in());
                    bundle.putString("time_out",tripInfo.getTime_out());

                    addTripFragment.setArguments(bundle);

                    replace(addTripFragment,R.id.container_home_company,((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction()
                            ,v.getContext().getString(R.string.tag_add_trip));

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
