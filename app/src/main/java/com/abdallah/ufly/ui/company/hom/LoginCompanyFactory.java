package com.abdallah.ufly.ui.company.hom;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.abdallah.ufly.ui.company.logincompany.LoginCompanyViewModel;
import com.abdallah.ufly.ui.registration.login.LoginViewModel;

public class LoginCompanyFactory extends   ViewModelProvider.NewInstanceFactory {


    ProgressBar view ;
    public LoginCompanyFactory( ProgressBar view) {

        this.view = view;
    }




    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginCompanyViewModel(view);
    }

}
