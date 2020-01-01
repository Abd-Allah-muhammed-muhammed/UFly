package com.abdallah.ufly.ui.home;

import android.os.Bundle;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ActivityHomeBinding;
import com.abdallah.ufly.ui.book.BookFragment;
import com.abdallah.ufly.ui.description.TripDescriptionFragment;

import androidx.databinding.DataBindingUtil;


import androidx.appcompat.app.AppCompatActivity;


import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding binding;
    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        fullScreen(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        if (savedInstanceState == null) {
            replace(new HomeFragment(), binding.frameMain.getId(), getSupportFragmentManager().beginTransaction());


        }


    }



}


