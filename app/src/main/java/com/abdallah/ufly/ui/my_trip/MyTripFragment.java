package com.abdallah.ufly.ui.my_trip;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.MyTripFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.my_trip.MyTripResponse;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.payCash.PayCashQRActivity;
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

import static android.app.Activity.RESULT_OK;
import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static com.abdallah.ufly.helper.HelperMethod.replace;
import static com.abdallah.ufly.ui.payCash.PayCashQRActivity.PAY;

public class MyTripFragment extends Fragment  implements MyTripResultCallBacks  , View.OnClickListener {
    private MyTripViewModel mViewModel;
    PrefManager prefManager ;

    MyTripResponse myTripResponse  = new MyTripResponse();
    MyTripFragmentBinding binding;
    private String token;
    private String qr;
    private boolean CASH;
    private double PRICE;

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


        if (!isNetworkAvailable(getContext())){


            binding.textNoTrips.setVisibility(View.VISIBLE);
            binding.textNoTrips.setText(getString(R.string.paytabs_err_no_internet));


            binding.progMyTrip.setVisibility(View.GONE);
            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(getString(R.string.no_intrnet),getString(R.string.paytabs_err_no_internet),R.drawable.ic_no_internet);
            generalDialogFragment.show(getFragmentManager(),"dialog");

        }else {
            prefManager = new PrefManager(getContext());
            token = prefManager.getToken();
            mViewModel.getMyTrip(token,binding.progMyTrip);
        }








        // set on click
        binding.backMytrip.setOnClickListener(this);
        binding.myTripPayNow.setOnClickListener(this);
        binding.myTripCancel.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void onError(String msg) {

//        replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));
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


            PRICE =Double.parseDouble(response.getPrice());

            qr = response.getDataCompany().getQr();


            if (isPaid!=0){

                binding.myTripIsPaid.setText(R.string.paid);
                binding.myTripPayNow.setVisibility(View.GONE);
                binding.myTripCancel.setImageResource(R.drawable.ic_complete);
                binding.myTripCancel.setClickable(false);



            }else {

                if (response.getIdPayment().equals("Cash")) {

                    CASH = true;
                }else if (response.getIdPayment().equals("Visa")){

                    CASH = false;

                }
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
                mViewModel.cancelMyTrip(token ,binding.progCancelMyTrip ,myTripResponse.getData().getIdTrip(),binding.progMyTrip);

                break;

            case R.id.back_mytrip:

                replace(new HomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_home));

                break;

            case R.id.my_trip_pay_now:


                if (CASH){

                    Intent intent = new Intent(getContext(), PayCashQRActivity.class);
                    intent.putExtra("qr",qr);
                    startActivity(intent);

                }else {
startactivityPAymentVisa(PRICE);

                }


                break;

        }
    }

    private void startactivityPAymentVisa( double price) {


        Intent in = new Intent(getContext(), PayTabActivity.class);
        in.putExtra(PaymentParams.MERCHANT_EMAIL, getString(R.string.myemail)); //this a demo account for testing the sdk
        in.putExtra(PaymentParams.SECRET_KEY,getString(R.string.sk_id));//Add your Secret Key Here
        in.putExtra(PaymentParams.LANGUAGE,PaymentParams.ENGLISH);
        in.putExtra(PaymentParams.AMOUNT, price);
        in.putExtra(PaymentParams.ARABIC, "ar");
        in.putExtra(PaymentParams.CURRENCY_CODE, "EGP");

        // customer info
//        in.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, "009733");
//        in.putExtra(PaymentParams.CUSTOMER_EMAIL, "customer-email@example.com");
//        in.putExtra(PaymentParams.ORDER_ID, "123456");
//        in.putExtra(PaymentParams.PRODUCT_NAME, "Product 1, Product 2");

//Billing Address
//        in.putExtra(PaymentParams.ADDRESS_BILLING, "Flat 1,Building 123, Road 2345");
//        in.putExtra(PaymentParams.CITY_BILLING, "Mansoura");
//        in.putExtra(PaymentParams.STATE_BILLING, "Mansoura");
//        in.putExtra(PaymentParams.COUNTRY_BILLING, "BHR");
//        in.putExtra(PaymentParams.POSTAL_CODE_BILLING, "0020"); //Put Country Phone code if Postal code not available '00973'
//
////Shipping Address
//        in.putExtra(PaymentParams.ADDRESS_SHIPPING, "Flat 1,Building 123, Road 2345");
//        in.putExtra(PaymentParams.CITY_SHIPPING, "Manama");
//        in.putExtra(PaymentParams.STATE_SHIPPING, "Manama");
//        in.putExtra(PaymentParams.COUNTRY_SHIPPING, "BHR");
//        in.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, "00973"); //Put Country Phone code if Postal code not available '00973'

//Payment Page Style
        in.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#12262C");

//Tokenization
        in.putExtra(PaymentParams.IS_TOKENIZATION, false);
//PreAuth
        in.putExtra(PaymentParams.IS_PREAUTH, false);

        startActivityForResult(in, PaymentParams.PAYMENT_REQUEST_CODE);

    }


    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getMyTrip(token,binding.progCancelMyTrip);

    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {


            mViewModel.pay(token,PAY , getActivity(),binding.progMyTrip);


            Log.i("Tag"," successful_"+data.getStringExtra(PaymentParams.RESULT_MESSAGE));
//            Log.e("Tag", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {



            }
        }
    }


}
