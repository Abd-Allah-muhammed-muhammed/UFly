package com.abdallah.ufly.registration;

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
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.RegistrationFragmentBinding;
import com.abdallah.ufly.model.Registration;

import java.util.Objects;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;


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
        fullScreen(getActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);

        binding.setRegistraion(mViewModel);

        mViewModel.getUser().observe(this, new Observer<Registration>() {
            @Override
            public void onChanged(Registration registration) {


                if (TextUtils.isEmpty(Objects.requireNonNull(registration).getPhone())) {
                    binding.etPhone.setError("Enter a Phone Number");
                    binding.etPhone.requestFocus();
                }
                else if (!registration.isPHhoneValid()) {
                    binding.etPhone.setError("Enter a Valid Phone Number");
                    binding.etPhone.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(registration).getPassword())) {
                    binding.etPassword.setError("Enter a Password");
                    binding.etPassword.requestFocus();
                }
                else if (!registration.isPasswordLengthGreaterThan5()) {
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
