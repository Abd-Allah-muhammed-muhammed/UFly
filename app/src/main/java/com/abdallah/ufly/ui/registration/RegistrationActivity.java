package com.abdallah.ufly.ui.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.login.Login;
import com.abdallah.ufly.ui.home.HomeActivity;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.registration.login.LoginFragment;
import com.muddzdev.styleabletoast.StyleableToast;

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


                replace(LoginFragment.newInstance(),R.id.container,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_login));


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
        }else {

            replace(LoginFragment.newInstance(),R.id.container,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_login));


        }
    }
}
