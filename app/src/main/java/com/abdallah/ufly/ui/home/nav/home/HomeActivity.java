package com.abdallah.ufly.ui.home.nav.home;

import android.animation.ValueAnimator;
import android.os.Bundle;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ActivityHomeBinding;
import com.abdallah.ufly.ui.my_account.MyAccountFragment;
import com.abdallah.ufly.ui.my_trip.MyTripFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    PathModel outline;
    ActivityHomeBinding binding;
    Bundle savedInstanceState ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState =savedInstanceState ;
        fullScreen(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        binding.bottomNav.inflateMenu(R.menu.nav_menu);

        binding.bottomNav.setOnNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            replace(new HomeFragment(),binding.frameMain.getId(),getSupportFragmentManager().beginTransaction());


        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_info_person:

                menuItem.setTitle("");

                // animation
                draw(6);

                binding.lineId.setX(binding.bottomNav.mFirstCurvePoitControl1.x);
                binding.fab.setVisibility(View.VISIBLE);
                binding.fab2.setVisibility(View.GONE);
                binding.fab3.setVisibility(View.GONE);
                drawAnimation(binding.fab);


                if (savedInstanceState == null) {
                    replace(new MyAccountFragment(),binding.frameMain.getId(),getSupportFragmentManager().beginTransaction());

                }


                break;


            case R.id.vav_home:


                menuItem.setTitle("");
                // animation
                draw(2);

                binding.lineId.setX(binding.bottomNav.mFirstCurvePoitControl1.x);
                binding.fab.setVisibility(View.GONE);
                binding.fab2.setVisibility(View.VISIBLE);
                binding.fab3.setVisibility(View.GONE);
                drawAnimation(binding.fab2);

                if (savedInstanceState == null) {
                    replace(new HomeFragment(),binding.frameMain.getId(),getSupportFragmentManager().beginTransaction());

                }

                break;

            case R.id.nav_mytrips:
                menuItem.setTitle("");
                Toast.makeText(this, "clicked trips", Toast.LENGTH_SHORT).show();
                // animation
                draw();

                binding.lineId.setX(binding.bottomNav.mFirstCurvePoitControl1.x);
                binding.fab.setVisibility(View.GONE);
                binding.fab2.setVisibility(View.GONE);
                binding.fab3.setVisibility(View.VISIBLE);
                drawAnimation(binding.fab3);


                if (savedInstanceState == null) {
                    replace(new MyTripFragment(),binding.frameMain.getId(),getSupportFragmentManager().beginTransaction());

                }


                break;
        }
        return true;
    }

    private void draw() {

        binding.bottomNav.mFirstCurvePoitnStart.set(binding.bottomNav.mNavigationBarWidth*10/12 -(binding.bottomNav.CYCLE_BOTTOM_RADIUS*2) -(binding.bottomNav.CYCLE_BOTTOM_RADIUS/3),0);

        binding.bottomNav.mFirstCurvePoitnEnd.set(binding.bottomNav.mNavigationBarWidth *10/12, binding.bottomNav
                .CYCLE_BOTTOM_RADIUS + (binding.bottomNav.CYCLE_BOTTOM_RADIUS / 4));



        binding.bottomNav.mSecondCurvePoitnStart = binding.bottomNav.mFirstCurvePoitnEnd;
        binding.bottomNav.mSecondCurvePoitnEnd.set((binding.bottomNav.mNavigationBarWidth*10/12) + (binding.bottomNav.CYCLE_BOTTOM_RADIUS * 2)
                + (binding.bottomNav.CYCLE_BOTTOM_RADIUS / 3), 0);



        binding.bottomNav.mFirstCurvePoitControl1.set(binding.bottomNav.mFirstCurvePoitnStart.x
                +binding.bottomNav.CYCLE_BOTTOM_RADIUS+(binding.bottomNav.CYCLE_BOTTOM_RADIUS/4),binding.bottomNav
                .mFirstCurvePoitnStart.y);





        binding.bottomNav.mFirstCurvePoitControl2.set(binding.bottomNav.mFirstCurvePoitnEnd.x -
                (binding.bottomNav.CYCLE_BOTTOM_RADIUS*2)+binding.bottomNav.CYCLE_BOTTOM_RADIUS,binding.bottomNav.mSecondCurvePoitnEnd.y);



        binding.bottomNav.mSecondCurvePoitControl1.set(binding.bottomNav.mSecondCurvePoitnStart.x
                        +(binding.bottomNav.CYCLE_BOTTOM_RADIUS*2)-binding.bottomNav.CYCLE_BOTTOM_RADIUS,

                binding.bottomNav.mSecondCurvePoitnStart.y);


        binding.bottomNav.mSecondCurvePoitControl2.set(binding.bottomNav.mSecondCurvePoitnEnd.x -
                        (binding.bottomNav.CYCLE_BOTTOM_RADIUS + (binding.bottomNav.CYCLE_BOTTOM_RADIUS/4)),
                binding.bottomNav.mSecondCurvePoitnEnd.y

        );


    }

    private void drawAnimation(final VectorMasterView fab) {


        outline  =fab.getPathModelByName("outline");
        outline.setTrimPathEnd(0.0f);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f,1.0f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                outline.setTrimPathEnd((Float) animation.getAnimatedValue());
                fab.update();

            }
        });

        valueAnimator.start();

    }

    private void draw(int i) {

        binding.bottomNav.mFirstCurvePoitnStart.set((binding.bottomNav.mNavigationBarWidth / i)
                - (binding.bottomNav.CYCLE_BOTTOM_RADIUS * 2) - (binding.bottomNav.CYCLE_BOTTOM_RADIUS / 3), 0);


        binding.bottomNav.mFirstCurvePoitnEnd.set(binding.bottomNav.mNavigationBarWidth / i, binding.bottomNav
                .CYCLE_BOTTOM_RADIUS + (binding.bottomNav.CYCLE_BOTTOM_RADIUS / 4));


        binding.bottomNav.mSecondCurvePoitnStart = binding.bottomNav.mFirstCurvePoitnEnd;
        binding.bottomNav.mSecondCurvePoitnEnd.set((binding.bottomNav.mNavigationBarWidth / i) + (binding.bottomNav.CYCLE_BOTTOM_RADIUS * 2)
                        + (binding.bottomNav.CYCLE_BOTTOM_RADIUS / 3), 0);


        binding.bottomNav.mFirstCurvePoitControl1.set(binding.bottomNav.mFirstCurvePoitnStart.x
        +binding.bottomNav.CYCLE_BOTTOM_RADIUS+(binding.bottomNav.CYCLE_BOTTOM_RADIUS/4),binding.bottomNav
        .mFirstCurvePoitnStart.y);


        binding.bottomNav.mFirstCurvePoitControl2.set(binding.bottomNav.mFirstCurvePoitnEnd.x -
                (binding.bottomNav.CYCLE_BOTTOM_RADIUS*2)+binding.bottomNav.CYCLE_BOTTOM_RADIUS,binding.bottomNav.mSecondCurvePoitnEnd.y);



        binding.bottomNav.mSecondCurvePoitControl1.set(binding.bottomNav.mSecondCurvePoitnStart.x
                +(binding.bottomNav.CYCLE_BOTTOM_RADIUS*2)-binding.bottomNav.CYCLE_BOTTOM_RADIUS,

                binding.bottomNav.mSecondCurvePoitnStart.y);


        binding.bottomNav.mSecondCurvePoitControl2.set(binding.bottomNav.mSecondCurvePoitnEnd.x -
                (binding.bottomNav.CYCLE_BOTTOM_RADIUS + (binding.bottomNav.CYCLE_BOTTOM_RADIUS/4)),
                binding.bottomNav.mSecondCurvePoitnEnd.y

                );


    }
}
