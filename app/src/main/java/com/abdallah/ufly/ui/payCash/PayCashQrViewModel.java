package com.abdallah.ufly.ui.payCash;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.cashPay.CashPay;
import com.abdallah.ufly.retrofit.Api;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class PayCashQrViewModel extends ViewModel {



    Api api ;


    @SuppressLint("CheckResult")
    public void pay(String uui_id, int pay, final Activity  activity) {
        api = getClient().create(Api.class);

        api.cashPay(uui_id,pay).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new SingleObserver<CashPay>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(CashPay cashPay) {

                StyleableToast.makeText(activity, cashPay.getMsg(), Toast.LENGTH_LONG, R.style.success).show();




                activity.finish();
            }
            @Override
            public void onError(Throwable e) {

                StyleableToast.makeText(activity, activity.getString(R.string.try_again), Toast.LENGTH_LONG, R.style.error).show();

                activity.finish();

            }
        });


    }
}
