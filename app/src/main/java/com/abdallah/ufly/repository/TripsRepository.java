package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class TripsRepository {
    Api api;
    MutableLiveData<List<TripsResponse>> data;


    @SuppressLint("CheckResult")
    public MutableLiveData<List<TripsResponse>> getTrips(final ProgressBar progHome, final TextView noTrip) {

data = new MutableLiveData<>();
//

        api = ApiClient.getClient().create(Api.class);
//
        api.getAllTrips().subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new Observer<List<TripsResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TripsResponse> tripsResponses) {


                        if (tripsResponses.isEmpty()){

                            noTrip.setVisibility(View.VISIBLE);

                        }
                        progHome.setVisibility(View.GONE);

                        data.setValue(tripsResponses);

                    }

                    @Override
                    public void onError(Throwable e) {

                        noTrip.setVisibility(View.VISIBLE);

                        progHome.setVisibility(View.GONE);

                    }

                    @Override
                    public void onComplete() {


                    }
                });
//
        return data;
    }
}
