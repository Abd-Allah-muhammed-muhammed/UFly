package com.abdallah.ufly.ui.registration.sign_up;


import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.registration.RegistarResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.home.HomeActivity;
import com.muddzdev.styleabletoast.StyleableToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    public SignUpViewModel() {
        fullName = new MutableLiveData<>();
      address = new MutableLiveData<>();
        phone = new MutableLiveData<>();
        email = new MutableLiveData<>();
         password = new MutableLiveData<>();
        rePassword = new MutableLiveData<>();
         progress = new MutableLiveData<>();
        signText = new MutableLiveData<>();

    }

    private Api api;
    public MutableLiveData<String> fullName ;
    public MutableLiveData<String> address ;
    public MutableLiveData<String> phone ;
    public MutableLiveData<String> email ;
    public MutableLiveData<String> password;
    public MutableLiveData<String> rePassword ;
    public MutableLiveData<Integer> progress;
    public MutableLiveData<String> signText ;
    private PrefManager prefManager;


    public void onClick(View view) {


        if (fullName.getValue() != null && address.getValue() != null && email.getValue()
                != null && phone.getValue() != null && password.getValue() != null) {

            if (!password.getValue().equals(rePassword.getValue())){


                StyleableToast.makeText(view.getContext(), "your password not matching", Toast.LENGTH_LONG, R.style.error).show();


            }else if(!email.getValue().matches(emailPattern)){

                StyleableToast.makeText(view.getContext(), "Please enter correct email", Toast.LENGTH_LONG, R.style.error).show();


            }else {
                callSignUp(view,fullName.getValue(),address.getValue(),email.getValue(),phone.getValue(),password.getValue());

            }

        }else {

            StyleableToast.makeText(view.getContext(), "Please enter all data", Toast.LENGTH_LONG, R.style.error).show();
        }


    }

    public void setProgress(MutableLiveData<Integer> progress) {
        this.progress = progress;
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

        progress.setValue(0);
        signText.setValue("");
        Call<RegistarResponse> call = api.singUp(emailValue, fullName,
                passwordValue, addressValue, phoneValue);

        call.enqueue(new Callback<RegistarResponse>() {
            @Override
            public void onResponse(Call<RegistarResponse> call, Response<RegistarResponse> response) {


                String message = response.body().getMessage();
                progress.setValue(8);
                signText.setValue(view.getContext().getString(R.string.sign_up));


                if (response.body().getStatus()==0){
                    StyleableToast.makeText(view.getContext(), message, Toast.LENGTH_LONG, R.style.success).show();

                    String uuid = response.body().getData().getUuid();
                     prefManager = new PrefManager(view.getContext());
                    prefManager.saveToken(uuid);
                    prefManager.setIsLoged(true);
                    prefManager.setIslogedCompany(false);
                    prefManager.removeIdCompany();
                    Intent intent = new Intent(view.getContext(), HomeActivity.class);

                    view.getContext().startActivity(intent);



                }else {
                    prefManager = new PrefManager(view.getContext());

                    prefManager.setIsLoged(false);

                    StyleableToast.makeText(view.getContext(), message, Toast.LENGTH_LONG, R.style.error).show();

                }

            }

            @Override
            public void onFailure(Call<RegistarResponse> call, Throwable t) {
                progress.setValue(8);
                signText.setValue("SIGN UP");
                StyleableToast.makeText(view.getContext(), " please Try Again Later", Toast.LENGTH_LONG, R.style.error).show();
                prefManager = new PrefManager(view.getContext());
                prefManager.setIsLoged(false);
            }

        });


    }


}




