package com.abdallah.ufly.ui.home.nav.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.TripInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<TripInfo>>  data;


    public HomeViewModel() {

        data = new MutableLiveData<>();

    }


    public LiveData<List<TripInfo>> getdata() {

        List<TripInfo> tripInfosList = new ArrayList<>();
        TripInfo tripInfo = new TripInfo("Cario","Alexandria","1/1/2020","3/1/2020","50","150");
        TripInfo tripInfo2 = new TripInfo("Cario","Alexandria","1/1/2020","3/1/2020","50","150");
        TripInfo tripInfo3 = new TripInfo("Cario","Alexandria","1/1/2020","3/1/2020","50","150");
        TripInfo tripInfo4 = new TripInfo("Cario","Alexandria","1/1/2020","3/1/2020","50","150");

        tripInfosList.add(tripInfo);
        tripInfosList.add(tripInfo2);
        tripInfosList.add(tripInfo3);
        tripInfosList.add(tripInfo4);
        data.setValue(tripInfosList);

        return data;
    }
}