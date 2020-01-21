package com.abdallah.ufly.ui.company.book_company;

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
import com.abdallah.ufly.databinding.BooksCompanyFragmentBinding;

public class BooksCompanyFragment extends Fragment {

    private BooksCompanyViewModel mViewModel;

    BooksCompanyFragmentBinding binding ;

    public static BooksCompanyFragment newInstance() {
        return new BooksCompanyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.books_company_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(BooksCompanyViewModel.class);

        return binding.getRoot();
    }


}
