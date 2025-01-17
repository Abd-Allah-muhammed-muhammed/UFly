package com.abdallah.ufly.ui.email_verification;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.EmailVerificationFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;

public class EmailVerificationFragment extends Fragment {

    private EmailVerificationViewModel mViewModel;

    private PrefManager prefManager;


    EmailVerificationFragmentBinding binding ;
    public static EmailVerificationFragment newInstance() {
        return new EmailVerificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.email_verification_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(EmailVerificationViewModel.class);

        prefManager = new PrefManager(getContext());

        String email = prefManager.getEmail();

        binding.setEmailV(mViewModel);
        binding.myEmail.setText(email);

        return binding.getRoot();
    }



}
