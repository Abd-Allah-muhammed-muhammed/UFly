package com.abdallah.ufly.ui.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.abdallah.ufly.R;

import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.ui.registration.login.LoginFragment;


import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class RegistrationActivity extends AppCompatActivity implements GeneralDialogFragment.OnDialogFragmentClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);
        setContentView(R.layout.registration_activity);
        if (savedInstanceState == null) {



            replace(LoginFragment.newInstance(), R.id.container, getSupportFragmentManager().beginTransaction(), getString(R.string.tag_login));


        }
    }




    @Override
    public void onBackPressed() {


        LoginFragment myFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_login));
        if (myFragment != null && myFragment.isVisible()) {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {

            replace(LoginFragment.newInstance(), R.id.container, getSupportFragmentManager().beginTransaction(), getString(R.string.tag_login));


        }
    }


    @Override
    public void onOkClicked(GeneralDialogFragment dialog) {
        dialog.dismiss();
    }
}

