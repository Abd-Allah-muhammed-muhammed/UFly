package com.abdallah.ufly.adpter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemIncludBinding;
import com.abdallah.ufly.databinding.ItemSettingBinding;
import com.abdallah.ufly.model.includes.Include;
import com.abdallah.ufly.model.setting.SettingModel;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {

    private List<SettingModel> settingModels;


    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ItemSettingBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_setting, parent, false);
        return new SettingViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(@NonNull SettingAdapter.SettingViewHolder holder, int position) {


        SettingModel  setting_model = settingModels.get(position);

        holder.binding.setSetting(setting_model);



    }


    public void setSettingModelsList(List<SettingModel>  settingModelsList) {
        this.settingModels = settingModelsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (settingModels != null) {
            return settingModels.size();
        } else {
            return 0;
        }
    }

    public class SettingViewHolder extends RecyclerView.ViewHolder {
        private ItemSettingBinding  binding;
        public SettingViewHolder(@NonNull ItemSettingBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }





}
