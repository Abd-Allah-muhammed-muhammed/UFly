package com.abdallah.ufly.ui.company.settingCompany;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.setting.SettingModel;

import java.util.ArrayList;
import java.util.List;

public class SettingCompanyViewModel extends ViewModel {



    MutableLiveData<List<SettingModel>> data ;
    private List<SettingModel> settingModelList ;

    public SettingCompanyViewModel() {


        data = new MutableLiveData<>();

        settingModelList= new ArrayList<>();
    }





    public MutableLiveData<List<SettingModel>> getSettinData(@NonNull Context context, String userName){

        SettingModel    settingModel1 = new SettingModel(userName, R.drawable.ic_back_right_24dp , R.drawable.ic_person_orange_24dp , Color.parseColor("#E8E8EA"));
        SettingModel  settingModel2 = new SettingModel(context.getString(R.string.try_gold_ufly),R.drawable.ic_back_right_24dp , R.drawable.ic_smal_logo, Color.parseColor("#E8E8EA"));
        SettingModel  settingModel3 = new SettingModel(context.getString(R.string.general),R.drawable.ic_back_right_24dp , R.drawable.ic_settings_black_24dp,Color.parseColor("#E8E8EA"));
        SettingModel  settingModel4 = new SettingModel(context.getString(R.string.helps),R.drawable.ic_back_right_24dp , R.drawable.ic_info_outline_orange_24dp,Color.parseColor("#E8E8EA"));
        SettingModel  settingModel5 = new SettingModel(context.getString(R.string.connect_us),R.drawable.ic_back_right_24dp , R.drawable.ic_connect_us, Color.parseColor("#E8E8EA"));
        SettingModel  settingModel6 = new SettingModel(context.getString(R.string.log_out),0, R.drawable.ic_back, Color.parseColor("#FEAD50"));

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
