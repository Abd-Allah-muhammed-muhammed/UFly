package com.abdallah.ufly.ui.registration.login;

import android.content.Context;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private LoginResultCallbacks loginResultCallbacks ;

    private Context context ;
    EditText etPassword;
    public LoginViewModelFactory(LoginResultCallbacks loginResultCallbacks, Context context, EditText etPassword) {
        this.loginResultCallbacks = loginResultCallbacks;
        this.context = context;
        this.etPassword = etPassword;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(loginResultCallbacks, context,etPassword);
    }
}
