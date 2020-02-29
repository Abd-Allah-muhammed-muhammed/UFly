package com.abdallah.ufly.ui.home;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.TripInfoAdapter;
import com.abdallah.ufly.adpter.TripSuggetionAdapter;
import com.abdallah.ufly.databinding.FragmentHomeBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.trips.TripsResponse;


import java.util.ArrayList;
import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    FragmentHomeBinding binding ;
    private TripInfoAdapter tripInfoAdapter;

    PrefManager manager ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        manager = new PrefManager(getContext());
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.progHome.setVisibility(View.VISIBLE);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        final RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.revTripInfo.setLayoutManager(layoutManager);
        binding.revTripMyCountry.setLayoutManager(layoutManager2);


        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.revTripInfo);


        try {



            binding.revTripInfo.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    View v = snapHelper.findSnapView(layoutManager);

                    int pos = 0;
                    if (v != null) {
                        pos = layoutManager.getPosition(v);
                    }

                    RecyclerView.ViewHolder viewHolder = binding.revTripInfo.findViewHolderForAdapterPosition(pos);

                    RelativeLayout rl1 = new RelativeLayout(getContext());
                    if (viewHolder != null) {
                        rl1 = viewHolder.itemView.findViewById(R.id.item_tip);
                    }

                    if (newState == RecyclerView.SCROLL_STATE_IDLE){
                        rl1.animate().setDuration(350).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                    }else{
                        rl1.animate().setDuration(350).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });

        }catch (Exception e) {
        }









        if (!isNetworkAvailable(getContext())){


            binding.noTrip.setVisibility(View.VISIBLE);


            binding.progHome.setVisibility(View.GONE);
            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(getString(R.string.no_intrnet),getString(R.string.paytabs_err_no_internet),R.drawable.ic_no_internet);
            generalDialogFragment.show(getFragmentManager(),"dialog");

        }else {
            fetchData();
            searchTrip();
            String address = manager.getAddress();
            suggetions(address);
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
        homeViewModel.getdata(binding.progHome,binding.noTrip,query,1).observe(getViewLifecycleOwner(), new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {

                tripInfoAdapter = new TripInfoAdapter(1);
                binding.revTripInfo.setAdapter(tripInfoAdapter);

                tripInfoAdapter.setTripInfoList((ArrayList<TripsResponse>) tripsResponses);
                tripInfoAdapter.notifyDataSetChanged();

            }
        });

    }

    private void fetchData() {

        tripInfoAdapter = new TripInfoAdapter(1);

        binding.revTripInfo.setAdapter(tripInfoAdapter);
        binding.revTripInfo.setHasFixedSize(true);

        homeViewModel.getdata(binding.progHome,binding.noTrip,binding.searchTrip.getText().toString(), 0).observe(this, new Observer<List<TripsResponse>>() {
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



    private void suggetions (String query){



        homeViewModel.getdataSuggetion(query ,binding.tvSugg).observe(getViewLifecycleOwner(), new Observer<List<TripsResponse>>() {
            @Override
            public void onChanged(List<TripsResponse> tripsResponses) {

                TripSuggetionAdapter tripInfoAdapter = new TripSuggetionAdapter();
                binding.revTripMyCountry.setAdapter(tripInfoAdapter);

                tripInfoAdapter.setTripSuggetionList((ArrayList<TripsResponse>) tripsResponses);
                tripInfoAdapter.notifyDataSetChanged();

            }
        });



    }

}
