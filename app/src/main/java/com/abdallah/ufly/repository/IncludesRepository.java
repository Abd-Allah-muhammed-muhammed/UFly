package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.model.includes.Include;
import com.abdallah.ufly.model.includes.IncludesResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class IncludesRepository {



    Api api;
    MutableLiveData<List<Include>> data;


    @SuppressLint("CheckResult")
    public MutableLiveData<List<Include>> getIncludes(String trip_id) {

        data = new MutableLiveData<>();
//

        api = ApiClient.getClient().create(Api.class);
//
        api.getIncludesById(trip_id).enqueue(new Callback<IncludesResponse>() {
            @Override
            public void onResponse(Call<IncludesResponse> call, Response<IncludesResponse> response) {


                data.setValue(response.body().getIncludes());
            }

            @Override
            public void onFailure(Call<IncludesResponse> call, Throwable t) {

            }
        });
//
        return data;
    }
}
