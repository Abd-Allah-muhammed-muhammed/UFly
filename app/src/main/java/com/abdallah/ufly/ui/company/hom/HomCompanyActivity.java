package com.abdallah.ufly.ui.company.hom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abdallah.ufly.R;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class HomCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);
        setContentView(R.layout.activity_hom_company);
        replace(new HomCompanyFragment(),R.id.container_home_company,getSupportFragmentManager().beginTransaction(),"hmo_company");


    }
}
