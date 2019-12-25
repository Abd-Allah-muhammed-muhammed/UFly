package com.abdallah.ufly.ui.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.ui.home.HomeActivity;
import com.abdallah.ufly.ui.registration.login.LoginFragment;

import io.reactivex.annotations.NonNull;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class RegistrationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        if (savedInstanceState == null) {
            fullScreen(this);


                replace(LoginFragment.newInstance(),R.id.container,getSupportFragmentManager().beginTransaction());


        }
    }
}
