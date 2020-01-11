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
        binding.setAddTrip(mViewModel);


        return binding.getRoot();
    }


}
