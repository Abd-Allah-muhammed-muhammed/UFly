package com.abdallah.ufly.helper;

import android.app.Activity;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HelperMethod {


    public static void replace(Fragment fragment, int id, FragmentTransaction fragmentTransaction,String tag) {
        FragmentTransaction transaction = fragmentTransaction;
        transaction.replace(id, fragment,tag);
        transaction.addToBackStack(null);
        transaction.commit();

    }


public static void fullScreen(Activity activity){

    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
}

}
