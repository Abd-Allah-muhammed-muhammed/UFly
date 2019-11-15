package com.abdallah.ufly.registration;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.RegistrationFragmentBinding;
import com.abdallah.ufly.model.Registration;
import com.abdallah.ufly.registration.sign_up.SignUpFragment;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class RegistrationViewModel extends ViewModel {



    public MutableLiveData<String> phoen = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<Registration> userMutableLiveData;





    public MutableLiveData<Registration> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

    public  void login(){
        Registration  registration = new Registration(phoen.getValue(), Password.getValue());

        userMutableLiveData.setValue(registration);


    } public  void signUp(View view){


        replace(new SignUpFragment(),R.id.container, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
        );


    }

}
