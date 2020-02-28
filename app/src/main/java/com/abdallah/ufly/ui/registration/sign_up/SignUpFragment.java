package com.abdallah.ufly.ui.registration.sign_up;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.SignUpFragmentBinding;
import com.abdallah.ufly.model.SignUp;
import com.abdallah.ufly.model.cities.CitiesResponse;
import com.abdallah.ufly.ui.registration.login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.replace;


public class SignUpFragment extends Fragment {


    private SignUpViewModel mViewModel;
   private SignUpFragmentBinding binding;
   private MutableLiveData<Integer> progress = new MutableLiveData<>();


    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fullScreen(getActivity());
         binding = DataBindingUtil.inflate(inflater, R.layout.sign_up_fragment, container, false);
        binding.setLifecycleOwner(this);

        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        mViewModel.setEditText(binding.etPassword,binding.etRePassword);


        binding.setSign(mViewModel);

        progress.setValue(8);
        mViewModel.setProgress(progress);
        mViewModel.signText.setValue(getString(R.string.sign_up));


        mViewModel.getListCities().observe(this, new Observer<List<CitiesResponse>>() {
            @Override
            public void onChanged(List<CitiesResponse> citiesResponses) {

                List<String> cities = new ArrayList<>();
                cities.add("Choose Your city");


                for (int i = 0; i < citiesResponses.size(); i++) {

                    cities.add(citiesResponses.get(i).getName());

                }

                final ArrayAdapter<String> adapter =  new ArrayAdapter<>(getActivity(),R.layout.item_spinner,
                        cities);
                adapter.setDropDownViewResource(R.layout.drop_dowen_spenner);
                binding.etAddress.setAdapter(adapter);

                binding.etAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        if (position!=0){
                            mViewModel.setAddress(adapter.getItem(position));


                        }else {

                            mViewModel.setAddress("");


                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {



                    }


                });


            }
        });











        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(LoginFragment.newInstance(),R.id.container,getFragmentManager().beginTransaction(),getString(R.string.tag_login));

            }
        });

        return binding.getRoot();
    }



}
