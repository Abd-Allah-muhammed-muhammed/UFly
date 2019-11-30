package com.abdallah.ufly.ui.registration.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private LoginResultCallbacks loginResultCallbacks ;

    public LoginViewModelFactory(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(loginResultCallbacks);
    }
}
