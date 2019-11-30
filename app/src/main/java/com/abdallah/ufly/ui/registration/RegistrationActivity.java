package com.abdallah.ufly.ui.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abdallah.ufly.R;
import com.abdallah.ufly.ui.registration.login.LoginFragment;

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
