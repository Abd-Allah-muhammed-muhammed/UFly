package com.abdallah.ufly.ui.home.nav.home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.TripInfoAdapter;
import com.abdallah.ufly.databinding.FragmentHomeBinding;
import com.abdallah.ufly.model.trips.TripsResponse;
import java.util.ArrayList;
import java.util.List;
import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fullScreen(getActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);


        fetchData();
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
        final TripInfoAdapter tripInfoAdapter = new TripInfoAdapter();
        binding.revTripInfo.setAdapter(tripInfoAdapter);
        binding.revTripInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.revTripInfo.setHasFixedSize(true);
        homeViewModel.getdata().observe(this, new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {
                tripInfoAdapter.setTripInfoList((ArrayList<TripsResponse>) tripsResponses);
                tripInfoAdapter.notifyDataSetChanged();
            }
        });
    }
}