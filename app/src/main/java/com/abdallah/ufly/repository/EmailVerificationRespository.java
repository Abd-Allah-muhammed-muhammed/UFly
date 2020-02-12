package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.emailvalidetion.EmailVerificationModel;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.ui.company.hom.HomCompanyFragment;
import com.abdallah.ufly.ui.home.HomeFragment;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.helper.HelperMethod.replace;
import static com.abdallah.ufly.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class EmailVerificationRespository {


    public static EmailVerificationRespository instance;

    Api api ;
    private EmailVerificationRespository (){

        api = getClient().create(Api.class);

    }



    public static  EmailVerificationRespository getInstance (){

        if (instance==null){
            instance = new EmailVerificationRespository();
        }


        return instance;
    }

    @SuppressLint("CheckResult")
    public void checkCode(String id, final Context context, final PrefManager prefManager) {

        api.checkCodeEmail(id).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new SingleObserver<EmailVerificationModel>() {
            @Override
            public void onSubscribe(Disposable d) {


            }

            @Override
            public void onSuccess(EmailVerificationModel emailVerificationModel) {


                if (emailVerificationModel.getStatus()==0) {

                    prefManager.setIsValidMail(true);
                    replace(new HomeFragment(), R.id.frame_main, ((FragmentActivity) context).getSupportFragmentManager().beginTransaction(), context.getString(R.string.tag_home));




                }else {
                    prefManager.setIsValidMail(false);


                    GeneralDialogFragment generalDialogFragment =
                            GeneralDialogFragment.newInstance(context.getString(R.string.paytabs_error),emailVerificationModel.getMessage(), R.drawable.ic_error);
                    generalDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),"dialog");


                }

            }

            @Override
            public void onError(Throwable e) {

                prefManager.setIsValidMail(false);

                GeneralDialogFragment generalDialogFragment =
                        GeneralDialogFragment.newInstance(context.getString(R.string.paytabs_error),context.getString(R.string.try_again), R.drawable.ic_error);
                generalDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),"dialog");



            }
        });


    }
}
