package com.abdallah.ufly.ui.registration;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.RegistrationFragmentBinding;
import com.abdallah.ufly.model.Login;

import java.util.Objects;


public class RegistrationFragment extends Fragment {

    private RegistrationViewModel mViewModel;
    RegistrationFragmentBinding binding ;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);

        binding.setRegistraion(mViewModel);

        mViewModel.getUser().observe(this, new Observer<Login>() {
            @Override
            public void onChanged(Login login) {


                if (TextUtils.isEmpty(Objects.requireNonNull(login).getPhone())) {
                    binding.etPhone.setError("Enter a Phone Number");
                    binding.etPhone.requestFocus();
                }
                else if (!login.isPHhoneValid()) {
                    binding.etPhone.setError("Enter a Valid Phone Number");
                    binding.etPhone.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(login).getPassword())) {
                    binding.etPassword.setError("Enter a Password");
                    binding.etPassword.requestFocus();
                }
                else if (!login.isPasswordLengthGreaterThan5()) {
                    binding.etPassword.setError("Enter at least 6 Digit password");
                    binding.etPassword.requestFocus();
                }
                else {


                }


            }
        });
        View view = binding.getRoot();

        return view;

    }



}
