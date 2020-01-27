package com.abdallah.ufly.ui.company.book_company;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.adpter.BookedInfoAdapter;
import com.abdallah.ufly.databinding.BooksCompanyFragmentBinding;
import com.abdallah.ufly.helper.PrefManager;
import com.abdallah.ufly.model.passenger_booked.Booked;
import com.abdallah.ufly.model.passenger_booked.Datum;

import java.util.ArrayList;
import java.util.List;

public class BooksCompanyFragment extends Fragment {

    private BooksCompanyViewModel mViewModel;

    BooksCompanyFragmentBinding binding;

    PrefManager prefManager;

    public static BooksCompanyFragment newInstance() {
        return new BooksCompanyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.books_company_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(BooksCompanyViewModel.class);

        prefManager = new PrefManager(getContext());
        String id_company = prefManager.getID_Company();
        int trip_id = getArguments() != null ? getArguments().getInt("trip_id") : 0;

        mViewModel.infoBooked(getContext(), trip_id, id_company,binding.emptyPassengers,binding.progBooked).observe(this, new Observer<List<Datum>>() {
            @Override
            public void onChanged(List<Datum> data) {



                if (!data.isEmpty()){

                    binding.progBooked.setVisibility(View.GONE);

                }
                BookedInfoAdapter adapter = new BookedInfoAdapter();
                binding.rvBooked.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvBooked.setAdapter(adapter);

                adapter.setBookedInfoList((ArrayList<Datum>) data);


               int finalNumber  = 0;
                for (int i = 0; i < data.size(); i++) {

                    int number =Integer.parseInt( data.get(i).getBooked().getNumber().trim());

              finalNumber=  number+finalNumber;


                }

                // TODO: 27/01/20  add  to database  1 : "is it complet " , 2 :"number of passengers"


            }
        });

        return binding.getRoot();
    }


}
