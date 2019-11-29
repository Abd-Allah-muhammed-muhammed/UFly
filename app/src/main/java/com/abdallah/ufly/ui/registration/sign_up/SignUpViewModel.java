package com.abdallah.ufly.ui.registration.sign_up;


import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.registration.RegistarResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.splash.SplashScreen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    Api api;
    public MutableLiveData<String> fullName = new MutableLiveData<>();
    public MutableLiveData<String> address = new MutableLiveData<>();
    public MutableLiveData<String> phone = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> rePassword = new MutableLiveData<>();


    public void onClick(View view) {


        if (fullName.getValue() != null && address.getValue() != null && email.getValue()
                != null && phone.getValue() != null && password.getValue() != null) {

            if (password.getValue().equals(rePassword.getValue())){

                callSignUp(view,fullName.getValue(), address.getValue(), email.getValue(), phone.getValue(), password.getValue());

            }else {


                Toast.makeText(view.getContext(), "your password not matching", Toast.LENGTH_LONG).show();
            }

//            if (data.getValue() != null) {
////                RegistarResponse value = data.getValue();
////
////                Toast.makeText(view.getContext(), "status is : " + value.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////        } else
////            Toast.makeText(view.getContext(), "Please enter all data", Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(view.getContext(), "Please enter all data", Toast.LENGTH_LONG).show();
        }


    }

    public void setFullName(CharSequence s, int start, int before, int count) {

        fullName.setValue(String.valueOf(s));
    }


    public void setAddress(CharSequence s, int start, int before, int count) {

        address.setValue(String.valueOf(s));
    }

    public void setPhone(CharSequence s, int start, int before, int count) {

        phone.setValue(String.valueOf(s));
    }

    public void setEmail(CharSequence s, int start, int before, int count) {

        email.setValue(String.valueOf(s));
    }

    public void setPassword(CharSequence s, int start, int before, int count) {

        password.setValue(String.valueOf(s));
    }

    public void setRePassword(CharSequence s, int start, int before, int count) {

        rePassword.setValue(String.valueOf(s));
    }



    public void callSignUp(final View  view, String fullName, String addressValue, String emailValue, String phoneValue, String passwordValue) {
        api = ApiClient.getClient().create(Api.class);

        Call<RegistarResponse> call = api.singUp(emailValue, fullName,
                passwordValue, addressValue, phoneValue);

        call.enqueue(new Callback<RegistarResponse>() {
            @Override
            public void onResponse(Call<RegistarResponse> call, Response<RegistarResponse> response) {


                String message = response.body().getMessage();

                Toast.makeText(view.getContext(), "message is :"+message, Toast.LENGTH_SHORT).show();


                if (response.body().getStuts()==0){

                    Intent intent = new Intent(view.getContext(), SplashScreen.class);

                    view.getContext().startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<RegistarResponse> call, Throwable t) {


            }

        });


    }


}




