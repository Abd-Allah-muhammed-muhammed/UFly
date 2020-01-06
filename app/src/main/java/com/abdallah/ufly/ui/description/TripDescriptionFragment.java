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
import com.abdallah.ufly.databinding.TripDescriptionFragmentBinding;
import com.abdallah.ufly.ui.book.BookFragment;
import com.abdallah.ufly.ui.home.HomeFragment;

import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class TripDescriptionFragment extends Fragment {

    private TripDescriptionViewModel mViewModel;


    TripDescriptionFragmentBinding binding ;
    private int id_includse;
    private int tripId;

    public static TripDescriptionFragment newInstance() {
        return new TripDescriptionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.trip_description_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(TripDescriptionViewModel.class);
        binding.setDescBook(mViewModel);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction());
            }
        });


        tripId = getArguments().getInt("TripId",0);

        String trip_desc = getArguments().getString("Trip_desc");
        String Trip_from = getArguments().getString("Trip_from");
        String Trip_to = getArguments().getString("Trip_to");

        String includes = getArguments().getString("includes");

        binding.descInclude.setText(includes);
        binding.descFrom.setText(Trip_from);
        binding.descTo.setText(Trip_to);



        binding.descTvTripInfo.setText(trip_desc);

        final String price = getArguments().getString(getString(R.string.price));

        mViewModel.getPrice(price);

        binding.descBtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BookFragment bookFragment = new BookFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("trip_id",tripId);
                bundle.putString(getString(R.string.price),binding.countPrice.getText().toString());
                bundle.putString("number",binding.countPassenger.getText().toString().trim());

                bookFragment.setArguments(bundle);
                replace(bookFragment,R.id.frame_main,getFragmentManager().beginTransaction());

            }
        });







        return binding.getRoot();
    }





}
