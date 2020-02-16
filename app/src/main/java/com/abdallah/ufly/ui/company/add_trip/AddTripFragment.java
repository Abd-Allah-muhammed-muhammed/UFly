package com.abdallah.ufly.ui.company.add_trip;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.AddTripFragmentBinding;
import com.abdallah.ufly.ui.company.hom.HomCompanyFragment;
import com.abdallah.ufly.ui.company.settingCompany.SettingCompanyFragment;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.abdallah.ufly.helper.HelperMethod.replace;

public class AddTripFragment extends Fragment implements View.OnClickListener {

    private AddTripViewModel mViewModel;

    AddTripFragmentBinding binding;
    private int id;

    public static AddTripFragment newInstance() {
        return new AddTripFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.add_trip_fragment,container,false);
        mViewModel = ViewModelProviders.of(this , new AddTripFactory(binding.progAdd ,binding.photoTrip)).get(AddTripViewModel.class);


        binding.back.setOnClickListener(this);
        binding.photoTrip.setOnClickListener(this);

        Bundle arguments = getArguments();

        // get id from adapter and HomCompanyFragment
        // id in adapter is 2
        //id in fragment is 3
        int tripId = arguments != null ? arguments.getInt("TripId") : 0;
         id = arguments != null ? arguments.getInt("id") : 0;
        String trip_desc = arguments != null ? arguments.getString("Trip_desc") : "no Includes";
        String trip_from = arguments.getString("Trip_from");
        String trip_to = arguments.getString("Trip_to");
        String price = arguments.getString("price");
        String includes = arguments.getString("includes");

        //
        String dateFrome = arguments.getString("dateFrome");
        String dateUntil = arguments.getString("dateUntil");
        String passengers = arguments.getString("passengers");
        String time_in = arguments.getString("time_in");
        String image = arguments.getString("image");
        String time_out = arguments.getString("time_out");

        if (id==2){

            Glide.with(getContext()).load(image).into(binding.photoTrip);
        }


        mViewModel.setDesc(id , tripId , trip_desc,trip_from,trip_to, price,includes ,
                dateFrome,dateUntil,passengers,getContext(),time_in,time_out);
        binding.setAddTrip(mViewModel);

        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.back:

                replace(new HomCompanyFragment(),R.id.container_home_company,getFragmentManager().beginTransaction(),getString(R.string.tag_hom_company));

                break;

            case R.id.photo_trip:
                if (id!=2){

                    openGalary();

                }
                break;
        }



    }

    private void openGalary() {
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 13);



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 13 && resultCode == RESULT_OK) {

            Uri photo = data.getData();
            binding.photoTrip.setImageURI(photo);


            File finalFile = new File(getRealPathFromURI(photo));
            String path = finalFile.getPath();

            mViewModel.getPath(path);

        }
    }


    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}
