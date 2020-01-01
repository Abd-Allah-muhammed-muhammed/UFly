package com.abdallah.ufly.ui.my_trip;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
import com.abdallah.ufly.databinding.MyTripFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.my_trip.MyTripResponse;
import com.abdallah.ufly.ui.book.BookViewModel;
import com.abdallah.ufly.ui.book.BookViewModelFactory;
import com.abdallah.ufly.ui.home.HomeFragment;

import static com.abdallah.ufly.helper.HelperMethod.replace;

public class MyTripFragment extends Fragment  implements MyTripResultCallBacks{

    private MyTripViewModel mViewModel;
    PrefManager prefManager ;

    MyTripResponse myTripResponse  = new MyTripResponse();
    MyTripFragmentBinding binding;
    public static MyTripFragment newInstance() {
        return new MyTripFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_trip_fragment, container, false);
        binding.setLifecycleOwner(this);

        mViewModel = ViewModelProviders.of(this ,new MyTripViewModelFactory(this)).get(MyTripViewModel.class);


        prefManager = new PrefManager(getContext());
        final String token = prefManager.getToken();
        mViewModel.getMyTrip(token);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction());

            }
        });



        binding.myTripCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mViewModel.cancelMyTrip(token);

            }
        });
        return binding.getRoot();
    }


    @Override
    public void onError(String msg) {


    }

    @Override
    public void response(MyTripResponse response) {

        if (response.getData().getArrival()!=null){
            binding.setMyTrip(response);
            binding.myTriplayout.setVisibility(View.VISIBLE);

            int isPaid = response.getIsPaid();

            if (isPaid!=0){

                binding.myTripIsPaid.setText(R.string.paid);
                binding.myTripPayNow.setVisibility(View.GONE);
//                binding.myTripCancel.setImageResource(R.drawable.bg_paid);

            }else {

                binding.myTripIsPaid.setText(R.string.not_paid);
            }
        }else {

            binding.myTriplayout.setVisibility(View.GONE);
        }




    }
}
