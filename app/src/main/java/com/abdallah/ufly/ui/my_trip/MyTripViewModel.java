package com.abdallah.ufly.ui.my_trip;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.delet_trip.DelelteMyTripResponse;
import com.abdallah.ufly.model.my_trip.MyTripResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class MyTripViewModel extends ViewModel {

    Api api ;
    MyTripResultCallBacks callBacks ;
    public MyTripViewModel(MyTripResultCallBacks callBacks) {

        this.callBacks = callBacks;
        api = ApiClient.getClient().create(Api.class);

    }




    @SuppressLint("CheckResult")
    public void getMyTrip(String token){


        api.getMyTrips(token).subscribeOn(io()).observeOn(mainThread())
                .subscribeWith(new Observer<MyTripResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyTripResponse myTripResponse) {

                        callBacks.response(myTripResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                        callBacks.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }


    @SuppressLint("CheckResult")
    public void cancelMyTrip(final String token) {

        api.cancleMyTrip(token).subscribeOn(io()).observeOn(mainThread())
                .subscribeWith(new Observer<DelelteMyTripResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DelelteMyTripResponse delelteMyTripResponse) {


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getMyTrip(token);
                    }
                });
    }
}
