package com.abdallah.ufly.ui.home;

import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.TripInfo;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.repository.TripsRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<TripInfo>>  data;
    private TripsRepository repository ;


    public HomeViewModel() {

        data = new MutableLiveData<>();
        repository = new TripsRepository();

    }


    public LiveData<List<TripsResponse>> getdata(ProgressBar progHome) {

        return repository.getTrips(progHome);
    }
}