package com.abdallah.ufly.ui.book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.home.HomeFragment;
import com.abdallah.ufly.ui.registration.sign_up.SignUpFragment;
import com.muddzdev.styleabletoast.StyleableToast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static com.abdallah.ufly.helper.HelperMethod.replace;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class BookViewModel extends ViewModel {



    public MutableLiveData<Integer> progress = new MutableLiveData<>();


    Bundle bundle ;
    private PrefManager prefManager;
    Api api ;

    public void setArgments(Bundle bundle) {
        this.bundle = bundle;


    }

    private String ID_PAYMENT  = "0";

    BookResultCallBacks callBacks ;

    public BookViewModel( BookResultCallBacks callBacks) {

        this.callBacks = callBacks;

        progress.setValue(8);


    }

    public void booking (View view){


        Context mContext = view.getContext();
        if (!isNetworkAvailable(mContext)){

            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(mContext.getString(R.string.no_intrnet),mContext.getString(R.string.paytabs_err_no_internet),R.drawable.ic_no_internet);
            generalDialogFragment.show(((FragmentActivity)mContext).getSupportFragmentManager(),"dialog");




        }else {



            if (ID_PAYMENT .equals("0")) {

                String msg = "check your way to pay first";
                StyleableToast.makeText(view.getContext(), msg, Toast.LENGTH_LONG, R.style.error).show();


            }else {

                prefManager = new PrefManager(view.getContext());
                String token = prefManager.getToken();
                int  trip_id = bundle.getInt("trip_id");
                String price = bundle.getString("price");
                String number = bundle.getString("number");
                String id_comp = bundle.getString("id_comp");

                bookNow(String.valueOf(trip_id),token,ID_PAYMENT,price,number,id_comp,view.getContext());

            }

        }




    }




    @SuppressLint("CheckResult")
    public void bookNow (String id_trip
            , String token, String ID_PAYMENT, String price, String number, String id_comp, final Context context
                         ){

        progress.setValue(0);

        api = ApiClient.getClient().create(Api.class);

        api.book(id_trip,token,ID_PAYMENT,price,number,id_comp).subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new Observer<BookModelResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

//                        d.dispose();
                    }

                    @Override
                    public void onNext(BookModelResponse bookModelResponse) {
                        progress.setValue(8);

                        callBacks.response(bookModelResponse);

                    }

                    @Override
                    public void onError(Throwable e) {

                        progress.setValue(8);
                        callBacks.onError(context.getString(R.string.try_again));
                    }

                    @Override
                    public void onComplete() {
                        progress.setValue(8);
                    }
                });


    }


    public void back(View view){


        replace(new HomeFragment(),R.id.frame_main, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction(),view.getContext().getString(R.string.tag_home));


    }

    public void shapCash(boolean isChecked, CheckBox bookVisa) {


        if (isChecked){

            bookVisa.setChecked(false);
            ID_PAYMENT = "Cash";
        }else {

            ID_PAYMENT = "0";
        }
    }

    public void shapVisa(boolean isChecked, CheckBox bookCash) {


        if (isChecked){

            bookCash.setChecked(false);
            ID_PAYMENT = "Visa";
        }else {

            ID_PAYMENT = "0";
        }

    }
}
