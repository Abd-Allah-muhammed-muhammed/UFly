package com.abdallah.ufly.ui.registration.sign_up;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.cities.CitiesResponse;
import com.abdallah.ufly.model.registration.RegistarResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.home.HomeActivity;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class SignUpViewModel extends ViewModel {
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private boolean visib;
    private boolean reVisib;

    MutableLiveData<List<CitiesResponse>> dataCities ;


    EditText pass , rePass;
    public SignUpViewModel() {
        fullName = new MutableLiveData<>();
        address = new MutableLiveData<>();
        phone = new MutableLiveData<>();
        email = new MutableLiveData<>();
        password = new MutableLiveData<>();
        rePassword = new MutableLiveData<>();
        progress = new MutableLiveData<>();
        signText = new MutableLiveData<>();
        dataCities = new MutableLiveData<>();

    }

    private Api api;
    public MutableLiveData<String> fullName;
    public MutableLiveData<String> address;
    public MutableLiveData<String> phone;
    public MutableLiveData<String> email;
    public MutableLiveData<String> password;
    public MutableLiveData<String> rePassword;
    public MutableLiveData<Integer> progress;
    public MutableLiveData<String> signText;
    private PrefManager prefManager;


    public void onClick(View view) {




        Context mContext = view.getContext();

        if (!isNetworkAvailable(mContext)){


            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(mContext.getString(R.string.no_intrnet),mContext.getString(R.string.paytabs_err_no_internet),R.drawable.ic_no_internet);
            generalDialogFragment.show(((FragmentActivity)mContext).getSupportFragmentManager(),"dialog");

        }else {



            if (fullName.getValue() != null && address.getValue() != null && email.getValue()
                    != null && phone.getValue() != null && password.getValue() != null) {

                if (!password.getValue().equals(rePassword.getValue())) {


                    StyleableToast.makeText(view.getContext(), "your password not matching", Toast.LENGTH_LONG, R.style.error).show();


                } else if (!email.getValue().matches(emailPattern)) {

                    StyleableToast.makeText(view.getContext(), "Please enter correct email", Toast.LENGTH_LONG, R.style.error).show();




                } else {
                    callSignUp(view, fullName.getValue(), address.getValue(), email.getValue(), phone.getValue(), password.getValue());

                }

            } else {

                StyleableToast.makeText(view.getContext(), "Please enter all data", Toast.LENGTH_LONG, R.style.error).show();
            }

        }







    }

    public void setProgress(MutableLiveData<Integer> progress) {
        this.progress = progress;
    }

    public void setFullName(CharSequence s, int start, int before, int count) {

        fullName.setValue(String.valueOf(s));
    }


    public void setAddress(String address1) {

        address.setValue(address1);
    }

    public void setPhone(CharSequence s, int start, int before, int count) {

        phone.setValue(String.valueOf(s));
    }

    public void setEmail(CharSequence s, int start, int before, int count) {

        email.setValue(String.valueOf(s));
    }

    public void setPassword(CharSequence s, int start, int before, int count) {

        password.setValue(String.valueOf(s));
    }

    public void setRePassword(CharSequence s, int start, int before, int count) {

        rePassword.setValue(String.valueOf(s));
    }


    public void callSignUp(final View view, String fullName, String addressValue, String emailValue, String phoneValue, String passwordValue) {
        api = ApiClient.getClient().create(Api.class);

        progress.setValue(0);
        signText.setValue("");
        Call<RegistarResponse> call = api.singUp(emailValue, fullName,
                passwordValue, addressValue, phoneValue);

        call.enqueue(new Callback<RegistarResponse>() {
            @Override
            public void onResponse(Call<RegistarResponse> call, Response<RegistarResponse> response) {

                prefManager = new PrefManager(view.getContext());

                progress.setValue(8);
                signText.setValue(view.getContext().getString(R.string.sign_up));


                if (response.body().getStatus() == 0) {

                    String uuid = response.body().getData().getUuid();
                    prefManager.saveToken(uuid);
                    prefManager.setIsLoged(true);
                    prefManager.setIslogedCompany(false);
                    prefManager.removeIdCompany();
                    prefManager.saveIDValidition(response.body().getData().getId_mail_valid());
                    prefManager.savEmail(response.body().getData().getEmail());

                    int is_mail_valid = response.body().getData().getIs_mail_valid();

                    if (is_mail_valid!=0){

                        prefManager.setIsValidMail(true);

                    }else {
                        prefManager.setIsValidMail(false);


                    }
                    Intent intent = new Intent(view.getContext(), HomeActivity.class);

                    view.getContext().startActivity(intent);
                    prefManager.saveAddress(response.body().getData().getAddress());



                } else {

                    prefManager.setIsLoged(false);

                    StyleableToast.makeText(view.getContext(), response.body().getMessage(), Toast.LENGTH_LONG, R.style.error).show();

                }

            }

            @Override
            public void onFailure(Call<RegistarResponse> call, Throwable t) {
                progress.setValue(8);
                signText.setValue(view.getContext().getString(R.string.sign_up));
                StyleableToast.makeText(view.getContext(), view.getContext().getString(R.string.try_again), Toast.LENGTH_LONG, R.style.error).show();
                prefManager = new PrefManager(view.getContext());
                prefManager.setIsLoged(false);
            }

        });


    }


    public void passVisible(View view) {


        if (visib) {
            pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


            visib = false;
        } else {


            pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            visib = true;

        }


    }

    public void rePassVisible(View view) {


        if (reVisib) {
            rePass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


            reVisib = false;
        } else {


            rePass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            reVisib = true;

        }


    }


    public void setEditText(EditText etPassword, EditText etRePassword) {




        this.pass = etPassword;

        this.rePass = etRePassword;


    }




    @SuppressLint("CheckResult")
    public LiveData<List<CitiesResponse>> getListCities(){


        api = ApiClient.getClient().create(Api.class);
        api.getCities().subscribeOn(io()).observeOn(mainThread())
                .subscribeWith(new SingleObserver<List<CitiesResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<CitiesResponse> citiesResponses) {

                        dataCities.setValue(citiesResponses);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        return dataCities;
    }
}


