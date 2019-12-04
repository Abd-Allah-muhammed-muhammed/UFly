package com.abdallah.ufly.repository;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.model.login.LoginResponse;
import com.abdallah.ufly.model.registration.RegistarResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.registration.login.LoginResultCallbacks;
import com.abdallah.ufly.ui.splash.SplashScreen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    Api api;

    public void callSignUp(String emailValue, String passwordValue, final LoginResultCallbacks callbacks, final MutableLiveData<Integer> progress, final MutableLiveData<String> loginText) {

        api = ApiClient.getClient().create(Api.class);

        progress.setValue(0);
        loginText.setValue("");
        Call<LoginResponse> call = api.login(emailValue,
                passwordValue);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                callbacks.status(response.body().getStatus());
                callbacks.response(response.body());

                progress.setValue(8);
                loginText.setValue("LOGIN");

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progress.setValue(8);
                loginText.setValue("LOGIN");
            }
        });


    }
    }
