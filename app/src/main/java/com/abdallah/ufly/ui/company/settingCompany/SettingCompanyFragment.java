package com.abdallah.ufly.ui.company.settingCompany;

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
import com.abdallah.ufly.databinding.SettingCompanyFragmentBinding;
import com.abdallah.ufly.ui.company.hom.HomCompanyFragment;
import com.abdallah.ufly.ui.home.HomeFragment;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class SettingCompanyFragment extends Fragment {

    private SettingCompanyViewModel mViewModel;


    SettingCompanyFragmentBinding binding ;
    public static SettingCompanyFragment newInstance() {
        return new SettingCompanyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater,R.layout.setting_company_fragment,container, false);
        mViewModel = ViewModelProviders.of(this).get(SettingCompanyViewModel.class);


        SettingAdapter settingAdapter = new SettingAdapter(2);
        binding.rvSetting.setAdapter(settingAdapter);
        binding.rvSetting.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvSetting.setHasFixedSize(true);
        settingAdapter.setSettingModelsList(mViewModel.getSettinData(getContext(),"My Account").getValue());
        settingAdapter.notifyDataSetChanged();




        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new HomCompanyFragment(),R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_hom_company));
            }
        });


        return binding.getRoot();
    }







}
