package com.abdallah.ufly.ui.company.hom;

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
import com.abdallah.ufly.databinding.HomCompanyFragmentBinding;

public class HomCompanyFragment extends Fragment {

    private HomCompanyViewModel mViewModel;

    HomCompanyFragmentBinding binding ;
    public static HomCompanyFragment newInstance() {
        return new HomCompanyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.hom_company_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(HomCompanyViewModel.class);

        return  binding.getRoot();

    }



}
