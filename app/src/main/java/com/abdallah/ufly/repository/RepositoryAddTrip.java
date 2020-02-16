package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.abdallah.ufly.helper.HelperMethod.convertFileToMultipart;
import static com.abdallah.ufly.helper.HelperMethod.convertToRequestBody;
import static com.abdallah.ufly.helper.HelperMethod.replace;
import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class RepositoryAddTrip {


    AddTripResponse addTripResponseRE;

    private static RepositoryAddTrip instance;


    Api api;

    private RepositoryAddTrip() {

        api = getClient().create(Api.class);

        addTripResponseRE = new AddTripResponse();
    }


    public static RepositoryAddTrip getInstance(){

        if (instance==null){


            instance = new RepositoryAddTrip();
        }



        return instance ;
    }

    @SuppressLint("CheckResult")
    public void addTrip(ModelAddTrip modelAddTrip, String companyID, final Button view, final ProgressBar progressBar , ImageView imageView , String imageFile_path) {



        progressBar.setVisibility(View.VISIBLE);
        view.setText("");

        RequestBody includse ;
        RequestBody from = convertToRequestBody(modelAddTrip.getFrom());
        RequestBody to = convertToRequestBody(modelAddTrip.getTo());
        RequestBody datFrom =convertToRequestBody( modelAddTrip.getDatFrom());
        RequestBody datUntil =convertToRequestBody(modelAddTrip.getDatUntil());
        RequestBody passengers = convertToRequestBody(modelAddTrip.getPassengers());
        RequestBody price = convertToRequestBody(modelAddTrip.getPrice());
        RequestBody description = convertToRequestBody(modelAddTrip.getDescription());
        if (modelAddTrip.getIncludse()!=null&&!modelAddTrip.getIncludse().equals("")){

             includse = convertToRequestBody(modelAddTrip.getIncludse());

        }else {
            includse =   convertToRequestBody("no includes\n");

        }


        RequestBody timeIn = convertToRequestBody(modelAddTrip.getTimeIn());
        RequestBody timeOut =convertToRequestBody( modelAddTrip.getTimeOut());

        RequestBody comp_id = convertToRequestBody(companyID);

        MultipartBody.Part image = convertFileToMultipart(imageFile_path, "image");

        api.addTrip(from, to, datFrom, datUntil, passengers, price, description, comp_id, includse,timeIn,timeOut ,image).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<AddTripResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AddTripResponse addTripResponse) {


                progressBar.setVisibility(View.GONE);
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
                progressBar.setVisibility(View.GONE);
                view.setText(view.getContext().getString(R.string.add_trip));


                StyleableToast.makeText(view.getContext(),view.getContext().getString(R.string.paytabs_err_unknown), Toast.LENGTH_LONG, R.style.error_company).show();

            }

            @Override
            public void onComplete() {

                progressBar.setVisibility(View.GONE);
                view.setText(view.getContext().getString(R.string.add_trip));


            }
        });


    }
}
