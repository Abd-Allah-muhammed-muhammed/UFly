package com.abdallah.ufly.ui.my_trip.my_trips_activity;

import android.os.Bundle;

import com.abdallah.ufly.R;

import com.abdallah.ufly.adpter.TapLayoutAdapter;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;


public class MyTripsActivity extends AppCompatActivity implements  GeneralDialogFragment.OnDialogFragmentClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new TapLayoutAdapter(getSupportFragmentManager()));
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);



    }

    @Override
    public void onOkClicked(GeneralDialogFragment dialog) {
        dialog.dismiss();
    }
}