package com.abdallah.ufly.ui.company.add_trip;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.AddTripFragmentBinding;

public class AddTripFragment extends Fragment {

    private AddTripViewModel mViewModel;

    AddTripFragmentBinding binding;
    public static AddTripFragment newInstance() {
        return new AddTripFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.add_trip_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(AddTripViewModel.class);


        Bundle arguments = getArguments();

        // get id from adapter and HomCompanyFragment
        // id in adapter is 2
        //id in fragment is 3
        int tripId = arguments != null ? arguments.getInt("TripId") : 0;
        int id = arguments != null ? arguments.getInt("id") : 0;
        String trip_desc = arguments != null ? arguments.getString("Trip_desc") : "no Includes";
        String trip_from = arguments.getString("Trip_from");
        String trip_to = arguments.getString("Trip_to");
        String price = arguments.getString("price");
        String includes = arguments.getString("includes");

        //
        //
        //
        String dateFrome = arguments.getString("dateFrome");
        String dateUntil = arguments.getString("dateUntil");
        String passengers = arguments.getString("passengers");


        mViewModel.setDesc(id , tripId , trip_desc,trip_from,trip_to, price,includes ,
                dateFrome,dateUntil,passengers,getContext());
        binding.setAddTrip(mViewModel);

        return binding.getRoot();
    }


}
