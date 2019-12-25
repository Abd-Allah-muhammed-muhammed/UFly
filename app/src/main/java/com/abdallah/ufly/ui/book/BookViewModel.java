package com.abdallah.ufly.ui.book;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;

public class BookViewModel extends ViewModel {

    private int ID_PAYMENT  = 0;

    public BookViewModel() {
    }

    public void booking (View view){



        if (ID_PAYMENT !=0) {
            Toast.makeText(view.getContext(), "ID PAYMENT IS : "+ID_PAYMENT, Toast.LENGTH_SHORT).show();


        }else {

            Toast.makeText(view.getContext(), "check your way to pay first", Toast.LENGTH_SHORT).show();

        }
    }




    public void shapVisa(CheckedTextView bookCash, CheckedTextView bookVisa, Button bookBook) {

        bookVisa.setBackground((bookVisa.getContext().getDrawable(R.drawable.bg_btn_book)));
        bookCash.setBackground((bookCash.getContext().getDrawable(R.drawable.bg_btn_book_whit)));
        bookCash.setTextColor(Color.parseColor("#12262C"));
        bookVisa.setTextColor(Color.parseColor("#ffffff"));
        bookBook.setBackground((bookBook.getContext().getDrawable(R.drawable.bg_btn_book)));
        bookBook.setTextColor(Color.parseColor("#ffffff"));
        bookBook.setText("BOOK NEW");
        ID_PAYMENT = 2;
    }

    public void shapCash(CheckedTextView bookCash, CheckedTextView bookVisa , Button bookBook) {

        bookCash.setBackground((bookCash.getContext().getDrawable(R.drawable.bg_btn_book)));
        bookVisa.setBackground((bookVisa.getContext().getDrawable(R.drawable.bg_btn_book_whit)));
        bookCash.setTextColor(Color.parseColor("#ffffff"));
        bookVisa.setTextColor(Color.parseColor("#12262C"));
        bookBook.setBackground((bookBook.getContext().getDrawable(R.drawable.bg_btn_book)));
        bookBook.setTextColor(Color.parseColor("#ffffff"));
        bookBook.setText("BOOK NEW");
        ID_PAYMENT = 1;
    }

}
