package com.abdallah.ufly.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemTripsBinding;

import com.abdallah.ufly.helper.GlideApp;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.ui.company.add_trip.AddTripFragment;
import com.abdallah.ufly.ui.description.TripDescriptionFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
        notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final TripInfoAdapter.TripInfoViewHolder holder, int position) {

        final TripsResponse tripInfo = tripInfoArrayList.get(position);
        holder.itemTripsBinding.setTripinfo(tripInfo);
        
        // set number of passenger booked 


        GlideApp.with(holder.itemTripsBinding.getRoot().getContext())
                .load(tripInfo.getImage())
                .error(R.drawable.test)
                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {


                        holder.itemTripsBinding.progItemTrip.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.itemTripsBinding.progItemTrip.setVisibility(View.GONE);

                        return false;
                    }
                })
                .into
                (holder.itemTripsBinding.imageTrip);




        if (tripInfo.getIs_complete()!=0){


            // complete

            holder.itemTripsBinding.availableBooked.setText(holder.itemTripsBinding.getRoot().getContext().getString(R.string.complete));
            holder.itemTripsBinding.completed.setVisibility(View.VISIBLE);

        }

        holder.itemTripsBinding.itemTip.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (id==1){


                    if (tripInfo.getIs_complete()!=0){



                        Context mContext = holder.itemTripsBinding.getRoot().getContext();
                        GeneralDialogFragment generalDialogFragment =
                                GeneralDialogFragment.newInstance("Complete", "THIS TRIP IS COMPLETE",R.drawable.ic_done);
                        generalDialogFragment.show(((FragmentActivity)mContext).getSupportFragmentManager(),"dialog");



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
                        bundle.putString("image",tripInfo.getImage());
                        bundle.putString("time_in",tripInfo.getTime_in());
                        bundle.putString("time_out",tripInfo.getTime_out());
                        bundle.putInt("numberAvailability",tripInfo.getAvailable_booked());
                        bundle.putString("dateFrome",tripInfo.getDateFrom());
                        bundle.putString("dateUntil",tripInfo.getDateUntil());


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
                    bundle.putString("image",tripInfo.getImage());

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
