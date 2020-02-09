package com.abdallah.ufly.ui.my_account;
import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.MyAccountFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.my_info.MyInfoResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;
import com.abdallah.ufly.ui.setting.SettingHomeFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.abdallah.ufly.helper.HelperMethod.replace;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class MyAccountFragment extends Fragment {

    private MyAccountViewModel mViewModel;

    MyAccountFragmentBinding binding;
    private int PICK_IMAGE = 1;



    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_account_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(MyAccountViewModel.class);


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new SettingHomeFragment(),R.id.frame_main,getFragmentManager().beginTransaction(),getString(R.string.tag_setting));
            }
        });
        mViewModel.getmyInfo(getContext(),binding.progMyAccount);
        binding.setMyInfo(mViewModel);



//        binding.myAccImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
//
//
//            }
//        });

       return binding.getRoot();

    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//
//        if (PICK_IMAGE==requestCode){
//
//
//            Uri uri = data.getData();
//            Log.d("ufly", "onActivityResult: uri is : "+uri);
//
//            binding.myAccImg.setImageURI(uri);
//        }
//
//
//    }
}
