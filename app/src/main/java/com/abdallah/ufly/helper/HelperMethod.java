package com.abdallah.ufly.helper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HelperMethod {


    public static void replace(Fragment fragment, int id, FragmentTransaction fragmentTransaction,String tag) {
        FragmentTransaction transaction = fragmentTransaction;
        transaction.replace(id, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();

    }


public static void fullScreen(Activity activity){

    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
}



    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




    public static RequestBody convertToRequestBody(String part) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), part);
        return requestBody;
    }

    public static MultipartBody.Part convertFileToMultipart(String pathImageFile, String Key) {

        File file = new File(pathImageFile);

        RequestBody reqFileselect = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part Imagebody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileselect);

        return Imagebody;
    }

}
