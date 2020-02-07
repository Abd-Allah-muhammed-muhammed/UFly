package com.abdallah.ufly.ui.my_trip;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.MyTripFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.my_trip.MyTripResponse;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.payCash.PayCashQRActivity;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class MyTripFragment extends Fragment  implements MyTripResultCallBacks  , View.OnClickListener {
    private MyTripViewModel mViewModel;
    PrefManager prefManager ;

    MyTripResponse myTripResponse  = new MyTripResponse();
    MyTripFragmentBinding binding;
    private String token;
    private String qr;

    public static MyTripFragment newInstance() {
        return new MyTripFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_trip_fragment, container, false);
        binding.setLifecycleOwner(this);

        mViewModel = ViewModelProviders.of(this ,new MyTripViewModelFactory(this)).get(MyTripViewModel.class);

        binding.myTrip.setVisibility(View.GONE);
        binding.myTripPayNow.setVisibility(View.GONE);

        prefManager = new PrefManager(getContext());
        token = prefManager.getToken();
        mViewModel.getMyTrip(token);



        // set on click
        binding.backMytrip.setOnClickListener(this);
        binding.myTripPayNow.setOnClickListener(this);
        binding.myTripCancel.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void onError(String msg) {

        replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));
    }

    @Override
    public void response(MyTripResponse response) {

        myTripResponse =response;
        if (response.getData().getArrival()!=null){
            binding.progMyTrip.setVisibility(View.GONE);
            binding.myTrip.setVisibility(View.VISIBLE);
            binding.myTripPayNow.setVisibility(View.VISIBLE);
            binding.setMyTrip(response);
            int isPaid = response.getIsPaid();

            qr = response.getDataCompany().getQr();


            if (isPaid!=0){

                binding.myTripIsPaid.setText(R.string.paid);
                binding.myTripPayNow.setVisibility(View.GONE);
                binding.myTripCancel.setImageResource(R.drawable.ic_complete);
                binding.myTripCancel.setClickable(false);

            }else {

                binding.myTripIsPaid.setText(R.string.not_paid);
            }
        }else {

            binding.progMyTrip.setVisibility(View.GONE);
            binding.myTrip.setVisibility(View.GONE);
            binding.myTripPayNow.setVisibility(View.GONE);
            binding.textNoTrips.setVisibility(View.VISIBLE);
            binding.backHome.setVisibility(View.VISIBLE);
            binding.backHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));
                }
            });

        }




    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.my_trip_cancel :
                mViewModel.cancelMyTrip(token ,binding.progCancelMyTrip ,myTripResponse.getData().getIdTrip());
                mViewModel.getMyTrip(token);

                break;

            case R.id.back_mytrip:

                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));

                break;

            case R.id.my_trip_pay_now:


                Intent intent = new Intent(getContext(), PayCashQRActivity.class);
                intent.putExtra("qr",qr);
                startActivity(intent);


                break;

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getMyTrip(token);

    }
}
