package com.abdallah.ufly.ui.company.hom;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;


public class HomCompanyViewModel extends ViewModel {


    MutableLiveData<List<TripsResponse>> data;
    Api api;

    public HomCompanyViewModel() {

        api = ApiClient.getClient().create(Api.class);

        data = new MutableLiveData<>();
    }


    @SuppressLint("CheckResult")
    public MutableLiveData<List<TripsResponse>> getMyTrip(String id_company, final ProgressBar progHome, final RelativeLayout noTrip) {

        api.getMyTripsCompany(id_company).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<List<TripsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(List<TripsResponse> tripsResponse) {

                if (tripsResponse.isEmpty()) {
                    //no trips
                    noTrip.setVisibility(View.VISIBLE);
                }
                progHome.setVisibility(View.GONE);
                data.setValue(tripsResponse);
            }

            @Override
            public void onError(Throwable e) {
                progHome.setVisibility(View.GONE);

noTrip.setVisibility(View.VISIBLE);
            }

            @Override
            public void onComplete() {
                progHome.setVisibility(View.GONE);
            }
        });
        return data;
    }
}
