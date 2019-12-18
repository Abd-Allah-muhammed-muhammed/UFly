package com.abdallah.ufly.ui.setting;

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
import com.abdallah.ufly.databinding.SettingHomeFragmentBinding;
import com.abdallah.ufly.ui.my_account.MyAccountViewModel;

public class SettingHomeFragment extends Fragment {

    private SettingHomeViewModel mViewModel;
    SettingHomeFragmentBinding binding ;

    public static SettingHomeFragment newInstance() {
        return new SettingHomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.setting_home_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(SettingHomeViewModel.class);




        return binding.getRoot();
    }



}
