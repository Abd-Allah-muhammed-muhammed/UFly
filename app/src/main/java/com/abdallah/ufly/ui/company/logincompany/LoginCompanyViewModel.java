package com.abdallah.ufly.ui.company.logincompany;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.login_company.LoginCompany;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.ui.company.hom.HomCompanyActivity;
import com.muddzdev.styleabletoast.StyleableToast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class LoginCompanyViewModel extends ViewModel {

    Api api ;
    PrefManager prefManager;
    public MutableLiveData<String> id_company   ;


    ProgressBar progressBar ;

    public LoginCompanyViewModel( ProgressBar view) {

        progressBar = view;
        view.setVisibility(View.GONE);

        id_company = new MutableLiveData<>();

        api= getClient().create(Api.class);
    }




    public void loginCompany(View view){


        Context context = view.getContext();
        if (!isNetworkAvailable(context)) {

            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(context.getString(R.string.no_intrnet),context.getString(R.string.paytabs_err_no_internet), R.drawable.ic_no_internet);
            generalDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),"dialog");

        }else {
            if (TextUtils.isEmpty(id_company.getValue())){

                StyleableToast.makeText(view.getContext(), view.getContext().getString(R.string.inter_your_company_id), Toast.LENGTH_LONG, R.style.error_company).show();


            }else {


                login(id_company.getValue(),view );

            }

        }





    }



    @SuppressLint("CheckResult")
    private void login(final String id_company, final View view){
       progressBar.setVisibility(View.VISIBLE);



        prefManager = new PrefManager(view.getContext());


        api.loginCompany(id_company).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<LoginCompany>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginCompany loginCompany) {

              progressBar.setVisibility(View.GONE);

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
                progressBar.setVisibility(View.GONE);
                prefManager.setIslogedCompany(false);

                StyleableToast.makeText(view.getContext(), view.getContext().getString(R.string.try_again), Toast.LENGTH_LONG, R.style.error_company).show();

            }

            @Override
            public void onComplete() {


            }
        });



    }

    public void getIdCompany (CharSequence charSequence , int start, int before, int count){


        id_company.setValue(String.valueOf(charSequence));

    }




    public void connectUs (View view){



    }
}
