package com.abdallah.ufly.ui.company.logincompany;

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
import com.abdallah.ufly.databinding.LoginCompanyFragmentBinding;

public class LoginCompanyFragment extends Fragment {

    private LoginCompanyViewModel mViewModel;


    public static LoginCompanyFragment newInstance() {
        return new LoginCompanyFragment();
    }

    LoginCompanyFragmentBinding  binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_company_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(LoginCompanyViewModel.class);

        binding.setLogin(mViewModel);
        return  binding.getRoot();

    }


}
