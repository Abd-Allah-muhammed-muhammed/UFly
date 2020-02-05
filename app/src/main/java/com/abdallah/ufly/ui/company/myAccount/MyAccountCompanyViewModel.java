package com.abdallah.ufly.ui.company.myAccount;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.company_info.CompanyAccountResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.company.hom.HomCompanyFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.helper.HelperMethod.replace;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class MyAccountCompanyViewModel extends ViewModel {
    Api api;

    PrefManager prefManager ;

    public MutableLiveData<String> companyNAme ;
    public MyAccountCompanyViewModel() {



        companyNAme = new MutableLiveData<>();
        api = ApiClient.getClient().create(Api.class);
    }




    @SuppressLint("CheckResult")
    public void  getmyInfoCompany (Context context){

        prefManager = new PrefManager(context);

        String id_company = prefManager.getID_Company();
        api.getMyinfoCompany(id_company).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<CompanyAccountResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CompanyAccountResponse companyAccountResponse) {

                String company_name = companyAccountResponse.getData().getCompany_name();

                companyNAme.setValue(company_name);
            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onComplete() {

            }
        });

    }



    public void done(View view){


        replace(new HomCompanyFragment(), R.id.container_home_company,((FragmentActivity)view.getContext()).getSupportFragmentManager().beginTransaction(),view.getContext().getString(R.string.tag_hom_company));

    }


}
