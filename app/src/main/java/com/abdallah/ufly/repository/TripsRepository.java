package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class TripsRepository {


    private TripsRepository(){


    }



    public static TripsRepository getInstance(){


        if (instance==null){

            instance = new TripsRepository();
        }


        return instance;

    }





   private static TripsRepository instance ;
    Api api;
    MutableLiveData<List<TripsResponse>> data;
    MutableLiveData<List<TripsResponse>> dataSuggetion;


    @SuppressLint("CheckResult")
    public MutableLiveData<List<TripsResponse>> getTrips(final ProgressBar progHome, final RelativeLayout noTrip, final String query, final int i) {


        final Context context = progHome.getContext();
        data = new MutableLiveData<>();
//

        api = ApiClient.getClient().create(Api.class);
//
        api.getAllTrips(query).subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new Observer<List<TripsResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TripsResponse> tripsResponses) {



                        if (i==0){

                            if (tripsResponses.isEmpty()){

                                noTrip.setVisibility(View.VISIBLE);
                                progHome.setVisibility(View.GONE);


                            }else {

                                progHome.setVisibility(View.GONE);

                                data.setValue(tripsResponses);

                            }
                        }else if (i==1){


                            // search case

                            if (tripsResponses.isEmpty()){

                                progHome.setVisibility(View.GONE);

                                // show dialog error

                                GeneralDialogFragment generalDialogFragment =
                                        GeneralDialogFragment.newInstance(context.getString(R.string.no_result),context.getString(R.string.no_result),R.drawable.ic_error);
                                generalDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),"dialog");



                            }else {
                                progHome.setVisibility(View.GONE);

                                data.setValue(tripsResponses);


                            }


                        }



                    }

                    @Override
                    public void onError(Throwable e) {



                        if (i!=0){

                            GeneralDialogFragment generalDialogFragment =
                                    GeneralDialogFragment.newInstance(context.getString(R.string.no_result),context.getString(R.string.no_result),R.drawable.ic_error);
                            generalDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),"dialog");

                        }


                        noTrip.setVisibility(View.VISIBLE);

                        progHome.setVisibility(View.GONE);

                    }

                    @Override
                    public void onComplete() {


                    }
                });
//


        return data;

    }

    @SuppressLint("CheckResult")
    public MutableLiveData<List<TripsResponse>> getTripsSuggetion(String query, final TextView tvSugg){


        dataSuggetion = new MutableLiveData<>();


        api.getAllTrips(query).subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new Observer<List<TripsResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TripsResponse> tripsResponses) {


                        dataSuggetion.setValue(tripsResponses);


                        if (tripsResponses.isEmpty()){

                            tvSugg.setVisibility(View.GONE);


                        }else {

                            tvSugg.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        tvSugg.setVisibility(View.GONE);




                    }

                    @Override
                    public void onComplete() {


                    }
                });



        return dataSuggetion;
    }




}
