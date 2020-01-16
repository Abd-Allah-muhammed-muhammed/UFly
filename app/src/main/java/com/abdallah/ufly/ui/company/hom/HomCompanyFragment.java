package com.abdallah.ufly.ui.company.hom;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.TripInfoAdapter;
import com.abdallah.ufly.databinding.HomCompanyFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.ui.company.add_trip.AddTripFragment;
import com.abdallah.ufly.ui.company.settingCompany.SettingCompanyFragment;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.my_trip.MyTripFragment;
import com.abdallah.ufly.ui.setting.SettingHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

import java.util.ArrayList;
import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class HomCompanyFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    PrefManager prefManager;
    private HomCompanyViewModel mViewModel;
    PathModel outline;
    HomCompanyFragmentBinding binding ;
    public static HomCompanyFragment newInstance() {
        return new HomCompanyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.hom_company_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(HomCompanyViewModel.class);
        binding.setLifecycleOwner(this);

        binding.bottomNav.inflateMenu(R.menu.nav_menu);

        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        prefManager = new PrefManager(getContext());
        String id_company = prefManager.getID_Company();
        fetchData(id_company ,binding.progHome);


        binding.addFbTrip.setOnClickListener(this);
        return  binding.getRoot();

    }





    private void fetchData(String id_company, ProgressBar progHome){

        mViewModel.getMyTrip(id_company,progHome).observeForever(new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponse) {



                binding.revTripInfo.setLayoutManager(new LinearLayoutManager(getContext()));
                TripInfoAdapter adapter = new TripInfoAdapter(2);
                binding.revTripInfo.setAdapter(adapter);

                adapter.setTripInfoList((ArrayList<TripsResponse>) tripsResponse);

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

                replace(new SettingCompanyFragment(),R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_setting_company));


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
                replace(new HomCompanyFragment(),R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_hom_company));


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

//                replace(new MyTripFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_mytrip));



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

    @Override
    public void onClick(View v) {

        // send id to AddTripFragment
        AddTripFragment fragment  = new AddTripFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",3);
        fragment.setArguments(bundle);
        replace(fragment,R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_add_trip));

    }
}
