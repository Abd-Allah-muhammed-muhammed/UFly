package com.abdallah.ufly.ui.description;

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

import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.IncludesAdapter;
import com.abdallah.ufly.databinding.TripDescriptionFragmentBinding;
import com.abdallah.ufly.model.includes.Include;
import com.abdallah.ufly.model.includes.IncludesResponse;
import com.abdallah.ufly.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class TripDescriptionFragment extends Fragment {

    private TripDescriptionViewModel mViewModel;


    TripDescriptionFragmentBinding binding ;

    public static TripDescriptionFragment newInstance() {
        return new TripDescriptionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.trip_description_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(TripDescriptionViewModel.class);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction());
            }
        });



        int tripId = getArguments().getInt("TripId",0);

        getincludes(tripId);

        String trip_desc = getArguments().getString("Trip_desc");


        binding.descTvTripInfo.setText(trip_desc);

        return binding.getRoot();
    }

    private void getincludes(int tripId) {
        final IncludesAdapter adapter = new IncludesAdapter();
        binding.descRvInclude.setAdapter(adapter);
        binding.descRvInclude.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.descRvInclude.setHasFixedSize(true);
        mViewModel.getdata(String.valueOf(tripId)).observe(this, new Observer<List<Include>>() {
            @Override
            public void onChanged(List<Include> includes) {
                adapter.setIncludesList(includes);
                adapter.notifyDataSetChanged();

            }
        });

    }


}
