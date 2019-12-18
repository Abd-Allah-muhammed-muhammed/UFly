package com.abdallah.ufly.adpter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemIncludBinding;
import com.abdallah.ufly.databinding.ItemTripsBinding;
import com.abdallah.ufly.model.includes.Include;
import com.abdallah.ufly.model.includes.IncludesResponse;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.ui.description.TripDescriptionFragment;

import java.util.ArrayList;
import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class IncludesAdapter extends RecyclerView.Adapter<IncludesAdapter.IncludesViewHolder> {

    private List<Include> includesResponses;


    @NonNull
    @Override
    public IncludesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ItemIncludBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_includ, parent, false);
        return new IncludesViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(@NonNull IncludesAdapter.IncludesViewHolder holder, int position) {


        Include response = includesResponses.get(position);
        holder.binding.setIncludes(response);


//        final TripsResponse tripInfo = tripInfoArrayList.get(position);
//        holder.itemTripsBinding.setTripinfo(tripInfo);
//        holder.itemTripsBinding.itemTip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                TripDescriptionFragment fragment = new TripDescriptionFragment();
//                Bundle bundle = new Bundle();
//
//                bundle.putInt("TripId",tripInfo.getId());
//                bundle.putString("Trip_desc",tripInfo.getDescription());
//                fragment.setArguments(bundle);
//                replace(fragment,R.id.frame_main,((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction());
//
//            }
//        });

    }


    public void setIncludesList(List<Include> includesList) {
        this.includesResponses = includesList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (includesResponses != null) {
            return includesResponses.size();
        } else {
            return 0;
        }
    }

    public class IncludesViewHolder extends RecyclerView.ViewHolder {
        private ItemIncludBinding  binding;
        public IncludesViewHolder(@NonNull ItemIncludBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }





}
