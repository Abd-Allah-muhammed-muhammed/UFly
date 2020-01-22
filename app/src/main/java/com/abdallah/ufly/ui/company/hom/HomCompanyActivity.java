package com.abdallah.ufly.ui.company.hom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ActivityHomCompanyBinding;
import com.abdallah.ufly.ui.company.book_company.BooksCompanyFragment;
import com.abdallah.ufly.ui.company.logincompany.LoginCompanyActivity;
import com.abdallah.ufly.ui.company.settingCompany.SettingCompanyFragment;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class HomCompanyActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityHomCompanyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_hom_company);

            replace(new HomCompanyFragment(),R.id.container_home_company,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_hom_company));


            binding.bottomNav.setOnNavigationItemSelectedListener(this);



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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_info_person:

                replace(new SettingCompanyFragment(),R.id.container_home_company,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_setting_company));


                break;
            case R.id.nav_home:
                replace(new HomCompanyFragment(),R.id.container_home_company,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_hom_company));

                break;
            case R.id.nav_mytrips:

//                replace(new BooksCompanyFragment(),R.id.container_home_company,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_books_comp));

                break;
        }
        return false;

    }
}
