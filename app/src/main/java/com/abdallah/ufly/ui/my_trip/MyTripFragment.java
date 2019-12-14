package com.abdallah.ufly.ui.my_trip;

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
import com.abdallah.ufly.databinding.MyTripFragmentBinding;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.my_account.MyAccountViewModel;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class MyTripFragment extends Fragment {

    private MyTripViewModel mViewModel;

    MyTripFragmentBinding binding;
    public static MyTripFragment newInstance() {
        return new MyTripFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_trip_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(MyTripViewModel.class);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction());

            }
        });
        return binding.getRoot();
    }



}
