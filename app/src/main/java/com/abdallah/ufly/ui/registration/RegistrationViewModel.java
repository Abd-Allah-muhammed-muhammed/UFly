package com.abdallah.ufly.ui.registration;

import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.Login;
import com.abdallah.ufly.ui.registration.sign_up.SignUpFragment;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class RegistrationViewModel extends ViewModel {



    public MutableLiveData<String> phoen = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<Login> userMutableLiveData;





    public MutableLiveData<Login> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

    public  void login(){

        Login login = new Login(phoen.getValue(), Password.getValue());

        userMutableLiveData.setValue(login);


    } public  void signUp(View view){


        replace(new SignUpFragment(),R.id.container, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
        );


    }

}
