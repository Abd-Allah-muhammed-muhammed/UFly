package com.abdallah.ufly.ui.company.myAccount;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.MyAccountCompanyFragmentBinding;
import com.abdallah.ufly.helper.dialog.GeneralDialogFragment;
import com.abdallah.ufly.ui.company.settingCompany.SettingCompanyFragment;
import com.abdallah.ufly.ui.setting.SettingHomeFragment;

import static com.abdallah.ufly.helper.HelperMethod.isNetworkAvailable;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class MyAccountCompanyFragment extends Fragment {

    private MyAccountCompanyViewModel mViewModel;

    public static MyAccountCompanyFragment newInstance() {
        return new MyAccountCompanyFragment();
    }

    MyAccountCompanyFragmentBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater,R.layout.my_account_company_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(MyAccountCompanyViewModel.class);
        binding.setLifecycleOwner(this);


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replace(new SettingCompanyFragment(),R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_setting_company));
            }
        });
        binding.setMyinfo(mViewModel);



        if (!isNetworkAvailable(getContext())){


            binding.progMyCpmpAccount.setVisibility(View.GONE);
            GeneralDialogFragment generalDialogFragment =
                    GeneralDialogFragment.newInstance(getString(R.string.no_intrnet),getString(R.string.paytabs_err_no_internet),R.drawable.ic_no_internet);
            generalDialogFragment.show(getFragmentManager(),"dialog");

        }else {
            mViewModel.getmyInfoCompany(getContext(),binding.progMyCpmpAccount);


        }



        return binding.getRoot();
    }



}
