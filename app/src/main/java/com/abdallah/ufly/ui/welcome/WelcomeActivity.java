package com.abdallah.ufly.ui.welcome;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.WelcomeActivityBinding;
import com.abdallah.ufly.helper.PrefManager;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;

public class WelcomeActivity extends AppCompatActivity  {
    WelcomeViewModel welcomeViewModel;
    private WelcomeActivityBinding binding;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fullScreen(this);
        super.onCreate(savedInstanceState);
        welcomeViewModel = ViewModelProviders.of(this).get(WelcomeViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.welcome_activity);
        binding.setWelcome(welcomeViewModel);


        welcomeViewModel.getInstance(this,binding.viewPager , binding.layoutDots );
       welcomeViewModel. initAdapter();


    }




}


