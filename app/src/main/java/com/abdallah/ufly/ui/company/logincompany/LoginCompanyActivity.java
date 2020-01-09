package com.abdallah.ufly.ui.company.logincompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.abdallah.ufly.R;
import com.abdallah.ufly.ui.book.BookFragment;
import com.abdallah.ufly.ui.company.hom.HomCompanyActivity;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.registration.RegistrationActivity;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class LoginCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);
        setContentView(R.layout.login_company_activity);
        if (savedInstanceState == null) {
            replace(LoginCompanyFragment.newInstance(),R.id.container_login_company,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_login_company));

        }
    }




    @Override
    public void onBackPressed() {
        LoginCompanyFragment fragment = (LoginCompanyFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_login_company));
        if (fragment != null && fragment.isVisible()) {

            startActivity(new Intent(this, RegistrationActivity.class));

        }

    }
}
