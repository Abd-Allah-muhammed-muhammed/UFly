package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.model.includes.Include;
import com.abdallah.ufly.model.includes.IncludesResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;


public class TripDescriptionRepository {


    public TripDescriptionRepository() {

        dataIncludes = new MutableLiveData<>();
//

        api = ApiClient.getClient().create(Api.class);
    }

    Api api;
    MutableLiveData<List<Include>> dataIncludes;

    @SuppressLint("CheckResult")
    public MutableLiveData<List<Include>> getIncludes(String trip_id) {


//
        api.getIncludesById(trip_id).enqueue(new Callback<IncludesResponse>() {
            @Override
            public void onResponse(Call<IncludesResponse> call, Response<IncludesResponse> response) {


                dataIncludes.setValue(response.body().getIncludes());
            }

            @Override
            public void onFailure(Call<IncludesResponse> call, Throwable t) {

            }
        });
//
        return dataIncludes;
    }




}
