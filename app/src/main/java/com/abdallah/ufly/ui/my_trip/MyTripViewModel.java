package com.abdallah.ufly.ui.my_trip;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.cashPay.CashPay;
import com.abdallah.ufly.model.delet_trip.DelelteMyTripResponse;
import com.abdallah.ufly.model.my_trip.MyTripResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.muddzdev.styleabletoast.StyleableToast;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.retrofit.ApiClient.getClient;
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
    public void cancelMyTrip(final String token, final ProgressBar progMyTrip , int trip_id) {


        progMyTrip.setVisibility(View.VISIBLE);
        api.cancleMyTrip(token,trip_id).subscribeOn(io()).observeOn(mainThread())
                .subscribeWith(new SingleObserver<DelelteMyTripResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(DelelteMyTripResponse delelteMyTripResponse) {

                        progMyTrip.setVisibility(View.GONE);
                        getMyTrip(token);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progMyTrip.setVisibility(View.GONE);

                        Toast.makeText(progMyTrip.getContext(), "error"+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }






    @SuppressLint("CheckResult")
    public void pay(final String uui_id, int pay, final Activity activity) {
        api = getClient().create(Api.class);

        api.cashPay(uui_id,pay).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new SingleObserver<CashPay>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(CashPay cashPay) {

                StyleableToast.makeText(activity, cashPay.getMsg(), Toast.LENGTH_LONG, R.style.success).show();
                getMyTrip(uui_id);
            }
            @Override
            public void onError(Throwable e) {

                StyleableToast.makeText(activity, activity.getString(R.string.try_again), Toast.LENGTH_LONG, R.style.error).show();


            }
        });


    }
}
