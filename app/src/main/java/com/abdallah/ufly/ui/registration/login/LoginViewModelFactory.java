package com.abdallah.ufly.ui.registration.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private LoginResultCallbacks loginResultCallbacks ;

    private Context context ;
    public LoginViewModelFactory(LoginResultCallbacks loginResultCallbacks , Context context) {
        this.loginResultCallbacks = loginResultCallbacks;
        this.context = context;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(loginResultCallbacks, context);
    }
}
