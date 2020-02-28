package com.abdallah.ufly.ui.my_trip.myTripsDescreption;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.cashPay.CashPay;
import com.abdallah.ufly.model.delet_trip.DelelteMyTripResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.ui.home.HomeActivity;
import com.muddzdev.styleabletoast.StyleableToast;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class TripDescreptionViewModel extends ViewModel {



    Api api  ;


    @SuppressLint("CheckResult")
    public void cancelMyTrip(final String token, final ProgressBar progMyTrip, int trip_id, final Activity activity ,String id ) {

        api = getClient().create(Api.class);

        progMyTrip.setVisibility(View.VISIBLE);
        api.cancleMyTrip(token,trip_id , id).subscribeOn(io()).observeOn(mainThread())
                .subscribeWith(new SingleObserver<DelelteMyTripResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(DelelteMyTripResponse delelteMyTripResponse) {



                        progMyTrip.setVisibility(View.GONE);

                        activity.startActivity(new Intent(activity, HomeActivity.class));


                        Toast.makeText(activity, ""+delelteMyTripResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(Throwable e) {
                        progMyTrip.setVisibility(View.GONE);


                        GeneralDialogFragment generalDialogFragment =
                                GeneralDialogFragment.newInstance(activity.getString(R.string.paytabs_err_unknown),activity.getString(R.string.try_again),R.drawable.ic_error);
                        generalDialogFragment.show(((FragmentActivity)activity).getSupportFragmentManager(),"dialog");

                    }
                });
    }






    @SuppressLint("CheckResult")
    public void pay(final String uui_id, int pay, final Activity activity,String id) {
        api = getClient().create(Api.class);

        api.cashPay(uui_id,pay,id).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new SingleObserver<CashPay>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(CashPay cashPay) {


                StyleableToast.makeText(activity, cashPay.getMsg(), Toast.LENGTH_LONG, R.style.success).show();

                activity.startActivity(new Intent(activity, HomeActivity.class));


            }
            @Override
            public void onError(Throwable e) {

                StyleableToast.makeText(activity, activity.getString(R.string.try_again), Toast.LENGTH_LONG, R.style.error).show();

                activity.startActivity(new Intent(activity, HomeActivity.class));




            }
        });


    }



}
