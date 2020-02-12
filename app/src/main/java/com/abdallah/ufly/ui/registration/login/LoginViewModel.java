package com.abdallah.ufly.ui.registration.login;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.model.login.Login;
import com.abdallah.ufly.repository.LoginRepository;
import com.abdallah.ufly.ui.company.logincompany.LoginCompanyActivity;
import com.abdallah.ufly.ui.registration.sign_up.SignUpFragment;

import org.w3c.dom.Text;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<Integer> progress = new MutableLiveData<>();

    public MutableLiveData<String> loginText = new MutableLiveData<>();

    boolean visib  ;
    Login user;
    LoginResultCallbacks loginResultCallbacks;
    LoginRepository instance ;

    private String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText etPassword;
    public LoginViewModel(LoginResultCallbacks loginResultCallbacks, Context context, EditText etPassword) {
        this.loginResultCallbacks = loginResultCallbacks;
        user = new Login();
        progress.setValue(8);
        loginText.setValue(context.getString(R.string.login));
        this.etPassword = etPassword;

        instance = LoginRepository.getInstance();
    }



    public void setEmail(CharSequence s, int start, int before, int count) {

        user.setEmail(String.valueOf(s));


    }

    public void setPassword(CharSequence s, int start, int before, int count) {

        user.setPassword(String.valueOf(s));


    }



    public void passVisible(View view){


        if (visib){
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


            visib = false;
        }else {


            etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            visib = true;

        }




    }


    public void login(View view){

        Context context = view.getContext();
        if (!isNetworkAvailable(context)) {

            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(context.getString(R.string.no_intrnet),context.getString(R.string.paytabs_err_no_internet), R.drawable.ic_no_internet);
            generalDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),"dialog");

        }else {



            if (TextUtils.isEmpty(user.getEmail())) {
                loginResultCallbacks.onError("Pleas Enter Your Email");

            }else if (!user.getEmail().matches(emailPattern)){

                loginResultCallbacks.onError("Pleas Enter Correct Mail");


            }else if (TextUtils.isEmpty(user.getPassword())){
                loginResultCallbacks.onError("Pleas Enter Your Password");

            }else {


                instance.callSignUp(user.getEmail(),user.getPassword(),loginResultCallbacks ,progress, loginText ,view);


            }


        }





    }

     public  void signUp(View view){


        replace(new SignUpFragment(),R.id.container, ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
        ,view.getContext().getString(R.string.tag_sign_up));


    }




    public void loginCopmany (View view){


        view.getContext().startActivity(new Intent(view.getContext(), LoginCompanyActivity.class));
    }

}
