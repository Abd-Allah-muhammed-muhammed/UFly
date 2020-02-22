package com.abdallah.ufly.ui.my_trip.myTripsDescreption;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.ActivityTripDescreptionBinding;
import com.abdallah.ufly.helper.GlideApp;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.my_trip.DataBook;
import com.abdallah.ufly.model.my_trip.DataTrip;
import com.abdallah.ufly.ui.payCash.PayCashQRActivity;
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

import static com.abdallah.ufly.helper.HelperMethod.fullScreen;
import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static com.abdallah.ufly.helper.Setting.COMPLETE_TRIPS;
import static com.abdallah.ufly.helper.Setting.OLD_TRIPS;
import static com.abdallah.ufly.helper.Setting.PANDING_TRIPS;
import static com.abdallah.ufly.ui.payCash.PayCashQRActivity.PAY;

public class TripDescreptionActivity extends AppCompatActivity  implements  View.OnClickListener  ,  GeneralDialogFragment.OnDialogFragmentClickListener{

    private TripDescreptionViewModel mViewModel;

    private ActivityTripDescreptionBinding binding ;
    PrefManager prefManager ;

    private String token;
    private String qr;
    private boolean CASH;
    private double PRICE;
    private int  TRIP_ID;
    private String idBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_descreption);
        mViewModel = ViewModelProviders.of(this).get(TripDescreptionViewModel.class);

        binding.myTripCancel.setOnClickListener(this);
        binding.myTripPayNow.setOnClickListener(this);

            prefManager = new PrefManager(this);
            token = prefManager.getToken();

        getData();

    }

    private void getData() {

        Bundle data = getIntent().getExtras();
        if (data != null) {
            DataTrip dataTrips = data.getParcelable("dataTrips");
            GlideApp.with(this).load(dataTrips.getImage()).into(binding.myTripImage);
            binding.setDataTrip(dataTrips);

            TRIP_ID = Integer.parseInt(dataTrips.getIdTrip());
            DataBook dataBook = data.getParcelable("dataBook");
            binding.setDataBook(dataBook);


             idBook = dataBook.getId();
            PRICE =Double.parseDouble(dataBook.getPrice());


                if (dataBook.getIdPayment().equals("Cash")) {

                    CASH = true;
                }else if (dataBook.getIdPayment().equals("Visa")){

                    CASH = false;

                }


            qr = data.getString("qr");
        }


        int type = data.getInt("type");

        switch (type) {
            case COMPLETE_TRIPS :

                binding.oldTrip.setVisibility(View.GONE);
                binding.layoutPayNow.setVisibility(View.GONE);
                binding.layoutCancel.setVisibility(View.GONE);
                binding.myTripIsPaid.setVisibility(View.GONE);

                break;

            case PANDING_TRIPS :


                binding.oldTrip.setVisibility(View.GONE);
                binding.layoutPayNow.setVisibility(View.VISIBLE);
                binding.layoutCancel.setVisibility(View.VISIBLE);
                binding.myTripIsPaid.setText(getString(R.string.not_paid));



                break;

            case OLD_TRIPS :


                binding.oldTrip.setVisibility(View.VISIBLE);
                binding.layoutPayNow.setVisibility(View.GONE);
                binding.layoutCancel.setVisibility(View.GONE);
                binding.layoutIsPaid.setVisibility(View.GONE);

                break;
        }


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.my_trip_cancel :
                mViewModel.cancelMyTrip(token ,binding.progCancelMyTrip ,TRIP_ID,TripDescreptionActivity.this , idBook);

                break;


            case R.id.my_trip_pay_now:


                if (CASH){

                    Intent intent = new Intent(this, PayCashQRActivity.class);
                    intent.putExtra("qr",qr);
                    startActivity(intent);

                }else {
                    startactivityPAymentVisa(PRICE);

                }


                break;

        }
    }

    private void startactivityPAymentVisa( double price) {


        Intent in = new Intent(this, PayTabActivity.class);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {


            mViewModel.pay(token,PAY,this );


            Log.i("Tag"," successful_"+data.getStringExtra(PaymentParams.RESULT_MESSAGE));
//            Log.e("Tag", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {



            }
        }
    }


    @Override
    public void onOkClicked(GeneralDialogFragment dialog) {
        dialog.dismiss();
    }
}
