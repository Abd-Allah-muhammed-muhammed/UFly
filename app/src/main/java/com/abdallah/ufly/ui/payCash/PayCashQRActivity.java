package com.abdallah.ufly.ui.payCash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.muddzdev.styleabletoast.StyleableToast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class PayCashQRActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private ZBarScannerView mScannerView;

    private PayCashQrViewModel mViewModel;

    private int requestcode = 1;
    private String qr;
    PrefManager prefManager;
    private  static  final int PAY = 1;

    public static PayCashQRActivity newInstance() {
        return new PayCashQRActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_cash_qr_fragment);

         qr = getIntent().getStringExtra("qr");
         mViewModel = ViewModelProviders.of(this).get(PayCashQrViewModel.class);

        mScannerView = new ZBarScannerView(this);

        checkPermissions();


        prefManager = new PrefManager(this);

        setContentView(mScannerView);


    }



//    }

    @Override
    public void handleResult(Result result) {


        if (result.getContents().equals(qr)){

            String uui_id = prefManager.getToken();

            mViewModel.pay(uui_id,PAY , this);

        }else {

            StyleableToast.makeText(this, getString(R.string.wrong_qr_code), Toast.LENGTH_LONG, R.style.error).show();


            finish();

        }


    }




    private void checkPermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, requestcode);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (grantResults.length>1&&requestCode==requestcode){

        }else {
            checkPermissions();
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();

    }


}
