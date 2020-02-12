package com.abdallah.ufly.ui.registration.login;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
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
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.login.LoginResponse;
import com.abdallah.ufly.ui.home.HomeActivity;
import com.muddzdev.styleabletoast.StyleableToast;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;


public class LoginFragment extends Fragment implements LoginResultCallbacks{

    private LoginViewModel mViewModel;
    RegistrationFragmentBinding binding ;

    PrefManager prefManager ;
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        fullScreen(getActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this,new LoginViewModelFactory(this,getContext(),binding.etPassword)).get(LoginViewModel.class);

        binding.setRegistraion(mViewModel);

        prefManager = new PrefManager(getContext());

        if (prefManager.isLoged()) {

            startActivity(new Intent(getContext(), HomeActivity.class));


        }



        View view = binding.getRoot();

        return view;

    }



    @Override
    public void onError(String msg) {


        GeneralDialogFragment generalDialogFragment =
                GeneralDialogFragment.newInstance(getString(R.string.paytabs_error),msg,R.drawable.ic_error);
        generalDialogFragment.show(getFragmentManager(),"dialog");




    }

    @Override
    public void status(Integer status) {


    }

    @Override
    public void response(LoginResponse response) {


        if (response.getStatus()==0){

            prefManager.saveToken(response.getData().getUuid());
            StyleableToast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG, R.style.success).show();
            startActivity(new Intent(getContext(), HomeActivity.class));
            prefManager.setIsLoged(true);
            prefManager.setIslogedCompany(false);
            prefManager.removeIdCompany();
        }else {

            prefManager.setIsLoged(false);


            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(getString(R.string.paytabs_error),response.getMessage(),R.drawable.ic_error);
            generalDialogFragment.show(getFragmentManager(),"dialog");

        }



    }
}
