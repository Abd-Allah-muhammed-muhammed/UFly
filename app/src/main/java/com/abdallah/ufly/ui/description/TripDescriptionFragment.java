package com.abdallah.ufly.ui.description;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.TripDescriptionFragmentBinding;
import com.abdallah.ufly.ui.email_verification.EmailVerificationFragment;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.ui.book.BookFragment;
import com.abdallah.ufly.ui.home.HomeFragment;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class TripDescriptionFragment extends Fragment {

    private TripDescriptionViewModel mViewModel;


    TripDescriptionFragmentBinding binding ;
    private int tripId;
    private int numberAvailability;

    private PrefManager prefManager;

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
                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));
            }
        });


        prefManager = new PrefManager(getContext());

        tripId = getArguments().getInt("TripId",0);

        numberAvailability = getArguments().getInt("numberAvailability",0);

        String trip_desc = getArguments().getString("Trip_desc");
        String Trip_from = getArguments().getString("Trip_from");
        String Trip_to = getArguments().getString("Trip_to");

        String includes = getArguments().getString("includes");
        final String id_comp = getArguments().getString("id_comp");



        binding.descInclude.setText(includes);
        binding.descFrom.setText(Trip_from);
        binding.descTo.setText(Trip_to);



        binding.descTvTripInfo.setText(trip_desc);

        final String price = getArguments().getString(getString(R.string.price));

        mViewModel.getPrice(price,numberAvailability);

        binding.descBtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (prefManager.isvalidMAil()) {


                    BookFragment bookFragment = new BookFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("trip_id",tripId);
                    bundle.putString("id_comp",id_comp);
                    bundle.putString(getString(R.string.price),binding.countPrice.getText().toString());
                    bundle.putString("number",binding.countPassenger.getText().toString().trim());

                    bookFragment.setArguments(bundle);
                    replace(bookFragment,R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_book));


                }else {


                    EmailVerificationFragment fragment = new EmailVerificationFragment();

                    replace(fragment,R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.emailverification));



                }



            }
        });







        return binding.getRoot();
    }





}
