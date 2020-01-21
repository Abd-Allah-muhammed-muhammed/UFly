package com.abdallah.ufly.ui.company.hom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.abdallah.ufly.R;
import com.abdallah.ufly.ui.company.logincompany.LoginCompanyActivity;
import com.abdallah.ufly.ui.home.HomeFragment;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class HomCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);
        setContentView(R.layout.activity_hom_company);

            replace(new HomCompanyFragment(),R.id.container_home_company,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_hom_company));


    }


    @Override
    public void onBackPressed() {

        HomCompanyFragment Fragment  = (HomCompanyFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_hom_company));

        if (Fragment != null && Fragment.isVisible()) {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        }else {

            super.onBackPressed();
        }


    }
}
