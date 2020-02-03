package com.abdallah.ufly.ui.company.add_trip;

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
import com.abdallah.ufly.databinding.AddTripFragmentBinding;
import com.abdallah.ufly.ui.company.hom.HomCompanyFragment;
import com.abdallah.ufly.ui.company.settingCompany.SettingCompanyFragment;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class AddTripFragment extends Fragment implements View.OnClickListener {

    private AddTripViewModel mViewModel;

    AddTripFragmentBinding binding;
    public static AddTripFragment newInstance() {
        return new AddTripFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.add_trip_fragment,container,false);
        mViewModel = ViewModelProviders.of(this , new AddTripFactory(binding.progAdd)).get(AddTripViewModel.class);


        binding.back.setOnClickListener(this);

        Bundle arguments = getArguments();

        // get id from adapter and HomCompanyFragment
        // id in adapter is 2
        //id in fragment is 3
        int tripId = arguments != null ? arguments.getInt("TripId") : 0;
        int id = arguments != null ? arguments.getInt("id") : 0;
        String trip_desc = arguments != null ? arguments.getString("Trip_desc") : "no Includes";
        String trip_from = arguments.getString("Trip_from");
        String trip_to = arguments.getString("Trip_to");
        String price = arguments.getString("price");
        String includes = arguments.getString("includes");

        //
        String dateFrome = arguments.getString("dateFrome");
        String dateUntil = arguments.getString("dateUntil");
        String passengers = arguments.getString("passengers");
        String time_in = arguments.getString("time_in");
        String time_out = arguments.getString("time_out");


        mViewModel.setDesc(id , tripId , trip_desc,trip_from,trip_to, price,includes ,
                dateFrome,dateUntil,passengers,getContext(),time_in,time_out);
        binding.setAddTrip(mViewModel);

        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {



        replace(new HomCompanyFragment(),R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_hom_company));


    }
}
