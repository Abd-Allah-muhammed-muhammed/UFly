package com.abdallah.ufly.ui.company.book_company;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.passenger_booked.Datum;
import com.abdallah.ufly.model.passenger_booked.PassengerBookedResponse;
import com.abdallah.ufly.retrofit.Api;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class BooksCompanyViewModel extends ViewModel {

    Api api ;
    MutableLiveData<List<Datum>> data ;
    public BooksCompanyViewModel() {

        data = new MutableLiveData<>();
        api = getClient().create(Api.class);

    }





    @SuppressLint("CheckResult")
    public LiveData<List<Datum>> infoBooked(final Context context, int idTrip, String companyId, final TextView emptyPassengers, final ProgressBar progBooked){



        api.getPassengerBooked(companyId,idTrip).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<PassengerBookedResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PassengerBookedResponse passengerBookedResponse) {
                progBooked.setVisibility(View.GONE);


                if (passengerBookedResponse.getStatus()!=0){


                    emptyPassengers.setVisibility(View.VISIBLE);
                    StyleableToast.makeText(context, passengerBookedResponse.getMsg(), Toast.LENGTH_LONG, R.style.error_company).show();

                }else {

                    data.setValue(passengerBookedResponse.getData());


                }


            }

            @Override
            public void onError(Throwable e) {

                progBooked.setVisibility(View.GONE);

                StyleableToast.makeText(context, context.getString(R.string.try_again), Toast.LENGTH_LONG, R.style.error_company).show();

            }

            @Override
            public void onComplete() {

            }
        });

        return data;
    }
}
