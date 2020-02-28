package com.abdallah.ufly.ui.my_trip.my_trips_fragment.old;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.my_trip.MyTripsResponse;
import com.abdallah.ufly.retrofit.Api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class MyOldTripsViewModel extends ViewModel {



    Api api ;
    MutableLiveData<MyTripsResponse> data ;
    @SuppressLint("CheckResult")
    public LiveData<MyTripsResponse> getMyOldTRips(String token, Context context, final RelativeLayout noTrip) {

        data = new MutableLiveData<>();
        api = getClient().create(Api.class);

        api.getMyoldTrips(token).subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new Observer<MyTripsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyTripsResponse myTripsResponse) {

                        data.setValue(myTripsResponse);


                    }

                    @Override
                    public void onError(Throwable e) {

                        noTrip.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });



        return data;
    }

}
