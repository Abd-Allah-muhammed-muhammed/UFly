package com.abdallah.ufly.ui.my_trip.my_trips_fragment.panding;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.MyTripsAdapter;
import com.abdallah.ufly.databinding.MyPandingTripsFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.my_trip.MyTripsResponse;

import static com.abdallah.ufly.helper.Setting.COMPLETE_TRIPS;
import static com.abdallah.ufly.helper.Setting.PANDING_TRIPS;

public class MyPandingTripsFragment extends Fragment {

    private MyPandingTripsViewModel mViewModel;
    private MyPandingTripsFragmentBinding binding ;
    private PrefManager manager ;


    public static MyPandingTripsFragment newInstance() {
        return new MyPandingTripsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.my_panding_trips_fragment,container,false);

        mViewModel = ViewModelProviders.of(this).get(MyPandingTripsViewModel.class);

        binding.setLifecycleOwner(this);

        manager = new PrefManager(getContext());
        String token = manager.getToken();

        mViewModel.getMyPandimgTRips(token ,getContext()).observe(this, new Observer<MyTripsResponse>() {
            @Override
            public void onChanged(MyTripsResponse myTripsResponse) {

                fetchData(myTripsResponse);
            }
        });

        return binding.getRoot();
    }
    private void fetchData(MyTripsResponse myTripsResponse) {



        if (myTripsResponse.getStatus()==0){
            MyTripsAdapter adapter = new MyTripsAdapter(PANDING_TRIPS,getContext(),getActivity());

            binding.rcPanding.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rcPanding.setAdapter(adapter);

            adapter.setMyTrips(myTripsResponse);


        }else {


            binding.noTrip.setVisibility(View.VISIBLE);
        }




    }

}
