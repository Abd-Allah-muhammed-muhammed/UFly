package com.abdallah.ufly.repository;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

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

    public void callSignUp(String emailValue, String passwordValue , final LoginResultCallbacks callbacks) {
        api = ApiClient.getClient().create(Api.class);

        Call<LoginResponse> call = api.login(emailValue,
                passwordValue);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                callbacks.status(response.body().getStatus());
                callbacks.response(response.body());


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


    }
    }
