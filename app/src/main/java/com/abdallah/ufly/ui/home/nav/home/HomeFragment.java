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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        fetchData();
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