package com.abdallah.ufly.ui.company.logincompany;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.login_company.LoginCompany;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.ui.company.hom.HomCompanyActivity;
import com.muddzdev.styleabletoast.StyleableToast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class LoginCompanyViewModel extends ViewModel {

    Api api ;
    PrefManager prefManager;
    public MutableLiveData<String> id_company   ;

    public LoginCompanyViewModel() {

        id_company = new MutableLiveData<>();
        api= getClient().create(Api.class);
    }




    public void loginCompany(View view){


        if (TextUtils.isEmpty(id_company.getValue())){

            StyleableToast.makeText(view.getContext(), "Please Enter Your ID", Toast.LENGTH_LONG, R.style.error_company).show();


        }else {


            login(id_company.getValue(),view );

        }



    }



    @SuppressLint("CheckResult")
    private void login(final String id_company, final View view){

        prefManager = new PrefManager(view.getContext());


        api.loginCompany(id_company).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<LoginCompany>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginCompany loginCompany) {



                if (loginCompany.getStatus()!=0){

                    StyleableToast.makeText(view.getContext(), loginCompany.getMessage(), Toast.LENGTH_LONG, R.style.error_company).show();

                    prefManager.setIslogedCompany(false);

                }else {

                    StyleableToast.makeText(view.getContext(), loginCompany.getMessage(), Toast.LENGTH_LONG, R.style.success_company).show();
                    view.getContext().startActivity(new Intent(view.getContext(), HomCompanyActivity.class));
                    prefManager.saveIdCompany(id_company);
                    prefManager.setIslogedCompany(true);

                }

            }

            @Override
            public void onError(Throwable e) {

                prefManager.setIslogedCompany(false);

                StyleableToast.makeText(view.getContext(), "Please Try Again Later", Toast.LENGTH_LONG, R.style.error_company).show();

            }

            @Override
            public void onComplete() {


            }
        });



    }

    public void getIdCompany (CharSequence charSequence , int start, int before, int count){


        id_company.setValue(String.valueOf(charSequence));

    }

}
