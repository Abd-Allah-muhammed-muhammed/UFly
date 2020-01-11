package com.abdallah.ufly.adpter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ItemSettingBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.setting.SettingModel;
import com.abdallah.ufly.ui.my_account.MyAccountFragment;
import com.abdallah.ufly.ui.registration.RegistrationActivity;

import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.replace;
import static com.abdallah.ufly.helper.Setting.ACCOUNT;
import static com.abdallah.ufly.helper.Setting.CONNECT_US;
import static com.abdallah.ufly.helper.Setting.HELPS;
import static com.abdallah.ufly.helper.Setting.LOG_OUT;
import static com.abdallah.ufly.helper.Setting.SETTINGS;
import static com.abdallah.ufly.helper.Setting.UFLY_GOLD;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {

    private List<SettingModel> settingModels;

    int id ;
    public SettingAdapter(int id) {

        this.id = id;
    }


    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ItemSettingBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_setting, parent, false);
        return new SettingViewHolder(binding);
    }



    @Override
    public void onBindViewHolder(@NonNull SettingAdapter.SettingViewHolder holder, final int position) {


        final SettingModel  setting_model = settingModels.get(position);

        holder.binding.setSetting(setting_model);






        holder.binding.itemSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position){



                    case ACCOUNT :



                        if (id==1){


                            replace(new MyAccountFragment(),R.id.frame_main,

                                    ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction()
                                    ,v.getContext().getString(R.string.tag_my_account));

                            settingModels.clear();
                        }


                        break;

                        case UFLY_GOLD :

                        break;

                    case SETTINGS :

                        break;

                    case HELPS :

                        break;

                    case CONNECT_US :

                        break;

                        case LOG_OUT :
                            PrefManager manager = new PrefManager(v.getContext());


                            if (id==1){
                                manager.setIsLoged(false);
                                manager.removeToken();
                                Intent intent = new Intent(v.getContext(), RegistrationActivity.class);
                                v.getContext().startActivity(intent);

                            }else {
                                manager.removeIdCompany();
                                manager.setIslogedCompany(false);
                                Intent intent = new Intent(v.getContext(), RegistrationActivity.class);
                                v.getContext().startActivity(intent);

                            }


                        break;
                }


            }
        });




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
