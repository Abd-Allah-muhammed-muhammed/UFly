package com.abdallah.ufly.ui.setting;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.setting.SettingModel;

import java.util.ArrayList;
import java.util.List;

public class SettingHomeViewModel extends ViewModel {

    private MutableLiveData<List<SettingModel>> data ;
    private List<SettingModel> settingModelList ;


    public SettingHomeViewModel (){

        data = new MutableLiveData<>();
        settingModelList = new ArrayList<>();
    }



    public MutableLiveData<List<SettingModel>> getSettinData(@NonNull Context context, String userName){

        SettingModel    settingModel1 = new SettingModel(userName,R.drawable.ic_arrow_back_24px , R.drawable.ic_account_circle_24px , Color.parseColor("#12262C"));

        SettingModel  settingModel2 = new SettingModel(context.getString(R.string.try_gold_ufly),R.drawable.ic_arrow_back_gold , R.drawable.ic_logo_gold, Color.parseColor("#CCA231"));
        SettingModel  settingModel3 = new SettingModel(context.getString(R.string.action_settings),R.drawable.ic_arrow_back_24px , R.drawable.ic_settings_dark,Color.parseColor("#12262C"));
        SettingModel  settingModel4 = new SettingModel(context.getString(R.string.helps),R.drawable.ic_arrow_back_24px , R.drawable.ic_help_black_24dp,Color.parseColor("#12262C"));
        SettingModel  settingModel5 = new SettingModel(context.getString(R.string.connect_us),R.drawable.ic_arrow_back_24px , R.drawable.ic_connect_us, Color.parseColor("#12262C"));
        SettingModel  settingModel6 = new SettingModel(context.getString(R.string.log_out),0, R.drawable.ic_back_dark, Color.parseColor("#12262C"));

        settingModelList.add(settingModel1);
        settingModelList.add(settingModel2);
        settingModelList.add(settingModel3);
        settingModelList.add(settingModel4);
        settingModelList.add(settingModel5);
        settingModelList.add(settingModel6);

        data.setValue(settingModelList);


        return data;
    }

}
