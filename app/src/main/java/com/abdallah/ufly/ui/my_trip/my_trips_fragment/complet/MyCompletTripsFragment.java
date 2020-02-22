package com.abdallah.ufly.ui.my_trip.my_trips_fragment.complet;

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
import com.abdallah.ufly.databinding.MyCompletTripsFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.my_trip.MyTripsResponse;

import static com.abdallah.ufly.helper.Setting.COMPLETE_TRIPS;

public class MyCompletTripsFragment extends Fragment {

    private MyCompletTripsViewModel mViewModel;
    private MyCompletTripsFragmentBinding binding ;
    private PrefManager manager ;

    public static MyCompletTripsFragment newInstance() {
        return new MyCompletTripsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.my_complet_trips_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(MyCompletTripsViewModel.class);
        binding.setLifecycleOwner(this);

        manager = new PrefManager(getContext());
        String token = manager.getToken();
        mViewModel.getMyComleteTRips(token ,getContext()).observe(this, new Observer<MyTripsResponse>() {
            @Override
            public void onChanged(MyTripsResponse myTripsResponse) {

                fetchData(myTripsResponse);
            }
        });


        return binding.getRoot();
    }

    private void fetchData(MyTripsResponse myTripsResponse) {

        MyTripsAdapter adapter = new MyTripsAdapter(COMPLETE_TRIPS,getContext(),getActivity());

        binding.rcComplete.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcComplete.setAdapter(adapter);
        adapter.setMyTrips(myTripsResponse);



    }


}
