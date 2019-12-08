package com.abdallah.ufly.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsRepository {
    Api api;
    MutableLiveData<List<TripsResponse>>  data;
    List<TripsResponse> tripsResponseList ;

    public MutableLiveData<List<TripsResponse>> getTrips (){


        tripsResponseList = new ArrayList<>();
        data = new MutableLiveData<>();
        api = ApiClient.getClient().create(Api.class);
        api.getAllTrips().enqueue(new Callback<List<TripsResponse>>() {
            @Override
            public void onResponse(Call<List<TripsResponse>> call, Response<List<TripsResponse>> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TripsResponse>> call, Throwable t) {

            }
        });

        return data;
    }
}
