package com.abdallah.ufly.ui.my_account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.MyAccountFragmentBinding;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.home.HomeViewModel;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class MyAccountFragment extends Fragment {

    private MyAccountViewModel mViewModel;

    MyAccountFragmentBinding binding;
    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_account_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(MyAccountViewModel.class);


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction());
            }
        });

       return binding.getRoot();

    }



}
