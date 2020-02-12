package com.abdallah.ufly.ui.company.hom;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.TripInfoAdapter;
import com.abdallah.ufly.databinding.HomCompanyFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.ui.company.add_trip.AddTripFragment;
import com.abdallah.ufly.ui.company.settingCompany.SettingCompanyFragment;


import java.util.ArrayList;
import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class HomCompanyFragment extends Fragment implements  View.OnClickListener {

    PrefManager prefManager;
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
        binding.setLifecycleOwner(this);


        prefManager = new PrefManager(getContext());
        String id_company = prefManager.getID_Company();


        if (!isNetworkAvailable(getContext())) {


            binding.progHome.setVisibility(View.GONE);
            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(getString(R.string.no_intrnet), getString(R.string.paytabs_err_no_internet), R.drawable.ic_no_internet);
            generalDialogFragment.show(getFragmentManager(), "dialog");

        }else {

            fetchData(id_company ,binding.progHome,binding.noTrip);


        }

        binding.addFbTrip.setOnClickListener(this);
        binding.settingFb.setOnClickListener(this);
        binding.refreshFbTrip.setOnClickListener(this);
        return  binding.getRoot();

    }





    private void fetchData(String id_company, final ProgressBar progHome, final TextView noTrip){

        mViewModel.getMyTrip(id_company,progHome,noTrip).observeForever(new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponse) {


                if (!tripsResponse.isEmpty())
                {

                    progHome.setVisibility(View.GONE);
                }else {

                    noTrip.setVisibility(View.VISIBLE);
                }
                binding.revTripInfo.setLayoutManager(new LinearLayoutManager(getContext()));
                TripInfoAdapter adapter = new TripInfoAdapter(2);
                binding.revTripInfo.setAdapter(adapter);

                adapter.setTripInfoList((ArrayList<TripsResponse>) tripsResponse);

            }
        });



            }




    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.add_fb_trip:
                // send id to AddTripFragment
                AddTripFragment fragment  = new AddTripFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id",3);
                fragment.setArguments(bundle);
                replace(fragment,R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_add_trip));

                break;


            case R.id.setting_fb:

                replace(new SettingCompanyFragment(),R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_setting_company));

                break;


            case R.id.refresh_fb_trip:
                replace(new HomCompanyFragment(),R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_hom_company));

                break;

        }

    }
}
