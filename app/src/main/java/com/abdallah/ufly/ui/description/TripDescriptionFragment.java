package com.abdallah.ufly.ui.description;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.TripDescriptionFragmentBinding;
import com.abdallah.ufly.helper.GlideApp;
import com.abdallah.ufly.ui.email_verification.EmailVerificationFragment;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.ui.book.BookFragment;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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


          prefManager = new PrefManager(getContext());

        tripId = getArguments().getInt("TripId",0);

        numberAvailability = getArguments().getInt("numberAvailability",0);

        String trip_desc = getArguments().getString("Trip_desc");
        String Trip_from = getArguments().getString("Trip_from");
        String Trip_to = getArguments().getString("Trip_to");

        String includes = getArguments().getString("includes");
        final String id_comp = getArguments().getString("id_comp");
        String image = getArguments().getString("image");

        String time_in = getArguments().getString("time_in");

        String time_out = getArguments().getString("time_out");



        String dateFrome = getArguments().getString("dateFrome");
        String dateUntil = getArguments().getString("dateUntil");



        binding.descTimeIn.setText(time_in);
        binding.descTimeOut.setText(time_out);


        binding.descDateIn.setText(dateFrome);
        binding.descDateOut.setText(dateUntil);


        GlideApp.with(getContext()).load(image)



                .error(R.drawable.test)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                        binding.progDescImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        binding.progDescImage.setVisibility(View.GONE);

                        return false;
                    }
                })

                .into(binding.descImage);





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
