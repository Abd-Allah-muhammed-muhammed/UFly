package com.abdallah.ufly.ui.my_account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.my_info.MyInfoResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.home.HomeFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.helper.HelperMethod.replace;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class MyAccountViewModel extends ViewModel {
    Api api;

    PrefManager prefManager ;

   public MutableLiveData<String> full_name ;

    public MyAccountViewModel() {

        full_name = new MutableLiveData<>();
        api = ApiClient.getClient().create(Api.class);



    }

    @SuppressLint("CheckResult")
    public void getmyInfo(Context context){
        prefManager = new PrefManager(context);

        String token = prefManager.getToken();
        api.getMyinfo(token).subscribeOn(io()).observeOn(mainThread()).subscribeWith(new Observer<MyInfoResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyInfoResponse myInfoResponse) {
                String fullName = myInfoResponse.getData().getFullName();

                full_name.setValue(fullName);
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


        replace(new HomeFragment(), R.id.frame_main,((FragmentActivity)view.getContext()).getSupportFragmentManager().beginTransaction());

    }

}
