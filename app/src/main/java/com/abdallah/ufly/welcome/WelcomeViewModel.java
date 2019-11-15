package com.abdallah.ufly.welcome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.MyViewPagerAdapter;
import com.abdallah.ufly.registration.RegistrationActivity;

public class WelcomeViewModel extends ViewModel implements ViewPager.OnPageChangeListener {
    Activity activity ;
    ViewPager viewPager;
    public int[] layouts;
    LinearLayout layout ;
    MyViewPagerAdapter myViewPagerAdapter ;
    TextView [] dots;
  public MutableLiveData<String> btnText = new MutableLiveData<>();


    public WelcomeViewModel() {
    }

    public void getInstance(Activity activity , ViewPager  viewPager , LinearLayout layout  ) {
        this.viewPager = viewPager;
        this.activity = activity;
        this.layout =layout;

        addLayouts();
        barTansparent();
        addBottomDots(0);
        changeStatusBarColor();

        btnText.setValue(activity.getString(R.string.next));
    }

    public void barTansparent(){
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    public int []  addLayouts() {

       return layouts =   new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};
    }




    public void addBottomDots(int currentPage )  {

        dots = new TextView[layouts.length];

        int[] colorsActive =activity.getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive =activity.getResources().getIntArray(R.array.array_dot_inactive);

        layout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(activity);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            layout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }


    public int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


    public void initAdapter( ) {
        myViewPagerAdapter = new MyViewPagerAdapter(layouts , activity);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    public void launchHomeScreen() {
//        prefManager.setFirstTimeLaunch(false);
       activity.startActivity(new Intent(activity, RegistrationActivity.class));
       activity.finish();
    }


    public void btnnext(){


        int current = getItem(+1);
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);


        } else {
            
            launchHomeScreen();
        }

    }

    public void btnskip(){

        launchHomeScreen();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {

        addBottomDots(position);
        // changing the next button text 'NEXT' / 'GOT IT'



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
