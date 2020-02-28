package com.abdallah.ufly.ui.my_trip.my_trips_fragment.old;

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
import com.abdallah.ufly.databinding.MyOldTripsFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.my_trip.MyTripsResponse;

import static com.abdallah.ufly.helper.Setting.OLD_TRIPS;
import static com.abdallah.ufly.helper.Setting.PANDING_TRIPS;

public class MyOldTripsFragment extends Fragment {

    private MyOldTripsViewModel mViewModel;
    private MyOldTripsFragmentBinding binding ;
    private PrefManager manager ;


    public static MyOldTripsFragment newInstance() {
        return new MyOldTripsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.my_old_trips_fragment,container,false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(MyOldTripsViewModel.class);


        manager = new PrefManager(getContext());
        String token = manager.getToken();

        mViewModel.getMyOldTRips(token ,getContext(),binding.noTrip).observe(this, new Observer<MyTripsResponse>() {
            @Override
            public void onChanged(MyTripsResponse myTripsResponse) {

                fetchData(myTripsResponse);
            }
        });

        return  binding.getRoot();
    }

    private void fetchData(MyTripsResponse myTripsResponse) {


        if (myTripsResponse.getStatus()==0 &&!myTripsResponse.getDataTrips().isEmpty()){
            MyTripsAdapter adapter = new MyTripsAdapter(OLD_TRIPS,getContext(),getActivity());

            binding.rcOld.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rcOld.setAdapter(adapter);

            adapter.setMyTrips(myTripsResponse);

        }else {

            binding.noTrip.setVisibility(View.VISIBLE);
        }

    }

}
