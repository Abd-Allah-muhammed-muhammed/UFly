package com.abdallah.ufly.ui.registration.login;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.login.Login;
import com.abdallah.ufly.repository.LoginRepository;
import com.abdallah.ufly.ui.registration.sign_up.SignUpFragment;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<Integer> progress = new MutableLiveData<>();

    public MutableLiveData<String> loginText = new MutableLiveData<>();

    Login user;
    LoginResultCallbacks loginResultCallbacks;
    LoginRepository loginRepository;
    private String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public LoginViewModel(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;
        user = new Login();
        loginRepository = new LoginRepository();
        progress.setValue(8);
        loginText.setValue("LOGIN");
    }



    public void setEmail(CharSequence s, int start, int before, int count) {

        user.setEmail(String.valueOf(s));


    }

    public void setPassword(CharSequence s, int start, int before, int count) {

        user.setPassword(String.valueOf(s));


    }



    public void login(View view){





        if (TextUtils.isEmpty(user.getEmail())) {
            loginResultCallbacks.onError("Pleas Enter Your Email");

        }else if (!user.getEmail().matches(emailPattern)){

            loginResultCallbacks.onError("Pleas Enter Correct Mail");


        }else if (TextUtils.isEmpty(user.getPassword())){
            loginResultCallbacks.onError("Pleas Enter Your Password");

        }else {


                loginRepository.callSignUp(user.getEmail(),user.getPassword(),loginResultCallbacks ,progress, loginText);


        }



    }

     public  void signUp(View view){


        replace(new SignUpFragment(),R.id.container, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
        );


    }

}
