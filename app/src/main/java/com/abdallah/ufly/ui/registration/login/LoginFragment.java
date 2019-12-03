package com.abdallah.ufly.ui.registration.login;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.RegistrationFragmentBinding;
import com.abdallah.ufly.model.login.LoginResponse;
import com.muddzdev.styleabletoast.StyleableToast;


public class LoginFragment extends Fragment implements LoginResultCallbacks{

    private LoginViewModel mViewModel;
    RegistrationFragmentBinding binding ;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this,new LoginViewModelFactory(this)).get(LoginViewModel.class);

        binding.setRegistraion(mViewModel);


        View view = binding.getRoot();

        return view;

    }



    @Override
    public void onError(String msg) {
        StyleableToast.makeText(getContext(), msg, Toast.LENGTH_LONG, R.style.error).show();



    }

    @Override
    public void status(Integer status) {


    }

    @Override
    public void response(LoginResponse response) {

        if (response.getStatus()==0){
            StyleableToast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG, R.style.success).show();
        }else {


            StyleableToast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG, R.style.error).show();
        }



    }
}
