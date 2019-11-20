package com.abdallah.ufly.registration.sign_up;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.SignUpFragmentBinding;
import com.abdallah.ufly.model.SignUp;
import com.abdallah.ufly.registration.RegistrationViewModel;


public class SignUpFragment extends Fragment {


    private SignUpViewModel mViewModel;
    private SignUp signUp;
    SignUpFragmentBinding binding;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

         binding = DataBindingUtil.inflate(inflater, R.layout.sign_up_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        binding.setSign(mViewModel);




        return binding.getRoot();
    }



}
