package com.abdallah.ufly.ui.home.nav.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.TripInfoAdapter;
import com.abdallah.ufly.databinding.FragmentHomeBinding;
import com.abdallah.ufly.model.TripInfo;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.repository.TripsRepository;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.registration.sign_up.SignUpViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.observers.BiConsumerSingleObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fullScreen(getActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);


        final TripInfoAdapter tripInfoAdapter = new TripInfoAdapter();
        binding.revTripInfo.setAdapter(tripInfoAdapter);
        binding.revTripInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.revTripInfo.setHasFixedSize(true);

        fetchData();
        homeViewModel.getdata().observe(this, new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {

                tripInfoAdapter.setTripInfoList((ArrayList<TripsResponse>) tripsResponses);
                tripInfoAdapter.notifyDataSetChanged();


            }
        });


//        homeViewModel.getdata().observe(this, new Observer<List<TripsResponse>>() {
//            @Override
//            public void onChanged(List<TripsResponse> tripInfoList) {
//
//
//                tripInfoAdapter.setTripInfoList((ArrayList<TripsResponse>) tripInfoList);
//                tripInfoAdapter.notifyDataSetChanged();
//
//            }
//        });


//
//        final List<String> list_country = new ArrayList<>();
//        list_country.add("Cairo");
//        list_country.add("Alexandria");
//        list_country.add("El mansoura");
//        list_country.add("El mahala");
//        list_country.add("Aga");
//        final AutoCompleteAdapter adapter = new AutoCompleteAdapter(getContext(), R.layout.drop_dowen, android.R.id.text1,list_country );


//        binding.aoutoTvFrom.setAdapter(adapter);
//        binding.aoutoTvFrom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//
//            }
//
//
//        });




        return binding.getRoot();
    }

    private void fetchData() {


    }
}