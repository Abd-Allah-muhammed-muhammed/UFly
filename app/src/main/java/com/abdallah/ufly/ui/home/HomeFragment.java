package com.abdallah.ufly.ui.home;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.TripInfoAdapter;
import com.abdallah.ufly.databinding.FragmentHomeBinding;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.trips.TripsResponse;


import java.util.ArrayList;
import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    FragmentHomeBinding binding ;
    private TripInfoAdapter tripInfoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.progHome.setVisibility(View.VISIBLE);




        if (!isNetworkAvailable(getContext())){


            binding.noTrip.setVisibility(View.VISIBLE);
            binding.noTrip.setText(getString(R.string.paytabs_err_no_internet));


            binding.progHome.setVisibility(View.GONE);
            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(getString(R.string.no_intrnet),getString(R.string.paytabs_err_no_internet),R.drawable.ic_no_internet);
            generalDialogFragment.show(getFragmentManager(),"dialog");

        }else {
            fetchData();
            searchTrip();
        }




        return binding.getRoot();
    }

    private void searchTrip() {

        binding.searchTrip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    String text = v.getText().toString();

                    if (!text.equals("")&&text!=null) search(text);

                    hideKeyboardFrom(getContext(),v);
                    v.setText("");

                    return true;
                }
                return false;
            }
        });


    }

    private void search(String query) {



        binding.progHome.setVisibility(View.VISIBLE);
        homeViewModel.getdata(binding.progHome,binding.noTrip,query).observe(getViewLifecycleOwner(), new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {

                tripInfoAdapter = new TripInfoAdapter(1);
                binding.revTripInfo.setAdapter(tripInfoAdapter);
                binding.revTripInfo.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.revTripInfo.setHasFixedSize(true);

                tripInfoAdapter.setTripInfoList((ArrayList<TripsResponse>) tripsResponses);
                tripInfoAdapter.notifyDataSetChanged();

            }
        });

    }

    private void fetchData() {

        tripInfoAdapter = new TripInfoAdapter(1);
        binding.revTripInfo.setAdapter(tripInfoAdapter);
        binding.revTripInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.revTripInfo.setHasFixedSize(true);

        homeViewModel.getdata(binding.progHome,binding.noTrip,binding.searchTrip.getText().toString()).observe(this, new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {


                if (tripsResponses.isEmpty()){


                    binding.noTrip.setVisibility(View.VISIBLE);

                }
                tripInfoAdapter.setTripInfoList((ArrayList<TripsResponse>) tripsResponses);
                tripInfoAdapter.notifyDataSetChanged();

            }


        });
    }



    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
