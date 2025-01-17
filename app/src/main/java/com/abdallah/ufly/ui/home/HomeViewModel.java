package com.abdallah.ufly.ui.home;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.repository.TripsRepository;

import java.util.List;


public class HomeViewModel extends ViewModel {

    private TripsRepository repository ;


    public HomeViewModel() {

        repository =  TripsRepository.getInstance();

    }


    public LiveData<List<TripsResponse>> getdata(ProgressBar progHome, RelativeLayout noTrip, String query, int i) {


        return   repository.getTrips(progHome, noTrip,query,i);


    }

    public LiveData<List<TripsResponse>> getdataSuggetion(String query, TextView tvSugg) {


        return   repository.getTripsSuggetion(query,tvSugg);


    }







}