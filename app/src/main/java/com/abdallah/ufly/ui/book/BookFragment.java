package com.abdallah.ufly.ui.book;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.abdallah.ufly.R;
import com.abdallah.ufly.databinding.BookFragmentBinding;

public class BookFragment extends Fragment {

    private BookViewModel mViewModel;

    BookFragmentBinding binding ;

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.book_fragment,container,false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(BookViewModel.class);

        binding.setBook(mViewModel);
        binding.bookBook.setText("");


        binding.bookCash.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               mViewModel.shapCash(binding.bookCash ,binding.bookVisa ,binding.bookBook);
                return false;
            }
        });


        binding.bookVisa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mViewModel.shapVisa(binding.bookCash ,binding.bookVisa ,binding.bookBook);
                return false;
            }
        });


       return binding.getRoot();
    }




}
