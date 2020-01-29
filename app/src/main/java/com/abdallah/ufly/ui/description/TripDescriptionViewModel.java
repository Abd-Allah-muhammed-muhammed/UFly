package com.abdallah.ufly.ui.description;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

public class TripDescriptionViewModel extends ViewModel {



    private int count= 1;


    int count_price  ;

    public MutableLiveData<String> the_number;
    public MutableLiveData<String> price;
    private int numberAvailability;

    public TripDescriptionViewModel() {

        the_number = new MutableLiveData<>();
        this.price = new MutableLiveData<>();
        the_number.setValue(String.valueOf(count));

//        price.setValue();

    }









    public void plus (View v){



        if (count<numberAvailability){
            count++;
            the_number.setValue(""+count);


            int price = count_price * count;

            this.price.setValue(""+price);
        }


    }

    public void minus (View v){


        if (count>1){
            count--;
            the_number.setValue(""+count);

            int price = count_price * count;

            this.price.setValue(""+price);

        }




    }

    public void getPrice(String price, int numberAvailability) {

        this.price.setValue(price);
        this.count_price = Integer.parseInt(price);
        this.numberAvailability = numberAvailability;

    }
}
