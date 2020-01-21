package com.abdallah.ufly.ui.setting;

import androidx.databinding.DataBindingUtil;
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
import com.abdallah.ufly.adpter.SettingAdapter;
import com.abdallah.ufly.databinding.SettingHomeFragmentBinding;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.my_account.MyAccountViewModel;

import static com.abdallah.ufly.helper.HelperMethod.replace;

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

        SettingAdapter settingAdapter = new SettingAdapter(1);
        binding.rvSetting.setAdapter(settingAdapter);
        binding.rvSetting.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvSetting.setHasFixedSize(true);
        settingAdapter.setSettingModelsList(mViewModel.getSettinData(getContext(),getString(R.string.my_account)).getValue());
        settingAdapter.notifyDataSetChanged();



        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));
            }
        });

        return binding.getRoot();
    }



}
