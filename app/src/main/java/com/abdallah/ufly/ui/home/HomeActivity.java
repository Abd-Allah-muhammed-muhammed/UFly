package com.abdallah.ufly.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ActivityHomeBinding;
import com.abdallah.ufly.ui.book.BookFragment;
import com.abdallah.ufly.ui.description.TripDescriptionFragment;
import com.abdallah.ufly.ui.my_account.MyAccountFragment;
import com.muddzdev.styleabletoast.StyleableToast;

import androidx.databinding.DataBindingUtil;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


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
            replace(new HomeFragment(), binding.frameMain.getId(), getSupportFragmentManager().beginTransaction(),getString(R.string.tag_home));


        }


    }


    @Override
    public void onBackPressed() {

        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_home));
        BookFragment  bookFragment = (BookFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.tag_book));
        if (homeFragment != null && homeFragment.isVisible()) {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }else  if (bookFragment !=null&& bookFragment.isVisible()){

            replace(new HomeFragment(), binding.frameMain.getId(), getSupportFragmentManager().beginTransaction(),getString(R.string.tag_home));


        }else {



            super.onBackPressed();

        }




    }
}


