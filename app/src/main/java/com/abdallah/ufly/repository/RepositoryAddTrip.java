package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.addTrip.AddTripResponse;
import com.abdallah.ufly.model.addTrip.ModelAddTrip;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.ui.company.hom.HomCompanyFragment;
import com.muddzdev.styleabletoast.StyleableToast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.helper.HelperMethod.replace;
import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class RepositoryAddTrip {


    AddTripResponse addTripResponseRE;

    Api api;

    public RepositoryAddTrip() {

        api = getClient().create(Api.class);

        addTripResponseRE = new AddTripResponse();
    }


    @SuppressLint("CheckResult")
    public void addTrip(ModelAddTrip modelAddTrip, String companyID, final Button view, final MutableLiveData<Integer> progress) {


        progress.setValue(0);
        view.setText("");


        String from = modelAddTrip.getFrom();
        String to = modelAddTrip.getTo();
        String datFrom = modelAddTrip.getDatFrom();
        String datUntil = modelAddTrip.getDatUntil();
        String passengers = modelAddTrip.getPassengers();
        String price = modelAddTrip.getPrice();
        String description = modelAddTrip.getDescription();
        String includse = modelAddTrip.getIncludse();


        api.addTrip(from, to, datFrom, datUntil, passengers, price, description, companyID, includse).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<AddTripResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AddTripResponse addTripResponse) {


                progress.setValue(8);
                view.setText(view.getContext().getString(R.string.add_trip));


                if (addTripResponse.getStatus() == 0) {


                    StyleableToast.makeText(view.getContext(), addTripResponse.getMessage(), Toast.LENGTH_LONG, R.style.success_company).show();

                    replace(new HomCompanyFragment(), R.id.container_home_company, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction(), view.getContext().getString(R.string.tag_hom_company));

                } else {


                    StyleableToast.makeText(view.getContext(), addTripResponse.getMessage(), Toast.LENGTH_LONG, R.style.error_company).show();


                }

            }

            @Override
            public void onError(Throwable e) {
                progress.setValue(8);
                view.setText(view.getContext().getString(R.string.add_trip));


                StyleableToast.makeText(view.getContext(),view.getContext().getString(R.string.try_again), Toast.LENGTH_LONG, R.style.error_company).show();

            }

            @Override
            public void onComplete() {

                progress.setValue(8);
                view.setText(view.getContext().getString(R.string.add_trip));


            }
        });


    }
}
