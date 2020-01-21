package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.login.LoginResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.registration.login.LoginResultCallbacks;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class LoginRepository {



    Api api;

    @SuppressLint("CheckResult")
    public void callSignUp(String emailValue, String passwordValue, final LoginResultCallbacks callbacks, final MutableLiveData<Integer> progress, final MutableLiveData<String> loginText, final View view) {

        api = ApiClient.getClient().create(Api.class);

        progress.setValue(0);
        loginText.setValue("");
         api.login(emailValue,
                passwordValue).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<LoginResponse>() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onNext(LoginResponse loginResponse) {


                 callbacks.status(loginResponse.getStatus());
                    callbacks.response(loginResponse);
                    progress.setValue(8);
                    loginText.setValue(view.getContext().getString(R.string.login));

             }

             @Override
             public void onError(Throwable e) {

                 progress.setValue(8);
                    loginText.setValue(view.getContext().getString(R.string.login));
                    callbacks.onError(view.getContext().getString(R.string.try_again));

             }


             @Override
             public void onComplete() {

             }
         });


//
//        try{
//            call.enqueue(new Callback<LoginResponse>() {
//                @Override
//                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//                    callbacks.status(response.body().getStatus());
//                    callbacks.response(response.body());
//                    progress.setValue(8);
//                    loginText.setValue("LOGIN");
//
//                }
//
//                @Override
//                public void onFailure(Call<LoginResponse> call, Throwable t) {
//                    progress.setValue(8);
//                    loginText.setValue("LOGIN");
//                    callbacks.onError("Try Again");
//                }
//            });
//
//        }catch (Exception e){
//            progress.setValue(8);
//            loginText.setValue("LOGIN");
//            callbacks.onError("Try Again");
//        }
        
    }
    }
