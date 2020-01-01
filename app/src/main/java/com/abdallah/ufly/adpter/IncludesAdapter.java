package com.abdallah.ufly.adpter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemIncludBinding;
import com.abdallah.ufly.model.includes.Include;

import java.util.List;


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
