package com.abdallah.ufly.ui.email_verification;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.repository.EmailVerificationRespository;


public class EmailVerificationViewModel extends ViewModel {


    private static String MAIL = "";
    String ID = "" ;
    PrefManager prefManager ;

    EmailVerificationRespository respository ;



    public void chick (View view){

        Context context = view.getContext();

        prefManager = new PrefManager(context);

        String idValidation = prefManager.getIdValidation();

        if (idValidation.equals(ID)){
            respository = EmailVerificationRespository.getInstance();

            respository.checkCode(ID,context,prefManager);



        }else {

            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(context.getString(R.string.wrong_code),context.getString(R.string.please_enter_a_valid_code),R.drawable.ic_error);
            generalDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),"dialog");



        }



    }



    public void setID(CharSequence s, int start, int before, int count) {

         ID = String.valueOf(s);


    }


    public void setMail(CharSequence s, int start, int before, int count) {

         MAIL = String.valueOf(s);


    }



    public void changeMail (View view){


        respository = EmailVerificationRespository.getInstance();

        Context context = view.getContext();


        if (MAIL.equals("")){

            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(context.getString(R.string.change_email),context.getString(R.string.chang_your_mail_first),R.drawable.ic_error);
            generalDialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(),"dialog");


        }else {

            prefManager = new PrefManager(context);
            String token = prefManager.getToken();

            respository.resend(token,MAIL,context,prefManager);


        }
    }

}
