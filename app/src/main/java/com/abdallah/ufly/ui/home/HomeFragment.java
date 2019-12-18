package com.abdallah.ufly.ui.home;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.TripInfoAdapter;
import com.abdallah.ufly.databinding.FragmentHomeBinding;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.ui.my_account.MyAccountFragment;
import com.abdallah.ufly.ui.my_trip.MyTripFragment;
import com.abdallah.ufly.ui.setting.SettingHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.muddzdev.styleabletoast.StyleableToast;
import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import java.util.ArrayList;
import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class HomeFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    PathModel outline;

    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        binding.bottomNav.inflateMenu(R.menu.nav_menu);

        binding.bottomNav.setOnNavigationItemSelectedListener(this);


        binding.progHome.setVisibility(View.VISIBLE);
        fetchData();
        return binding.getRoot();
    }
    private void fetchData() {
        final TripInfoAdapter tripInfoAdapter = new TripInfoAdapter();
        binding.revTripInfo.setAdapter(tripInfoAdapter);
        binding.revTripInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.revTripInfo.setHasFixedSize(true);
        homeViewModel.getdata().observe(this, new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {
                binding.progHome.setVisibility(View.GONE);
                tripInfoAdapter.setTripInfoList((ArrayList<TripsResponse>) tripsResponses);
                tripInfoAdapter.notifyDataSetChanged();
            }
        });
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

                    replace(new SettingHomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction());


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
                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction());


                break;

            case R.id.nav_mytrips:
                menuItem.setTitle("");
                // animation
                draw();

                binding.lineId.setX(binding.bottomNav.mFirstCurvePoitControl1.x);
                binding.fab.setVisibility(View.GONE);
                binding.fab2.setVisibility(View.GONE);
                binding.fab3.setVisibility(View.VISIBLE);
                drawAnimation(binding.fab3);

                replace(new MyTripFragment(),R.id.frame_main,getFragmentManager().beginTransaction());



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
