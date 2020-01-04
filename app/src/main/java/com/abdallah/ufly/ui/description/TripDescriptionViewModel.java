package com.abdallah.ufly.ui.description;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.model.includes.Include;
import com.abdallah.ufly.repository.TripDescriptionRepository;

import java.util.List;

public class TripDescriptionViewModel extends ViewModel {



    private MutableLiveData<List<Include>> data;
    private TripDescriptionRepository repository ;
    private int count= 1;


    int count_price  ;

    public MutableLiveData<String> the_number;
    public MutableLiveData<String> price;

    public TripDescriptionViewModel() {

        data = new MutableLiveData<>();
        repository = new TripDescriptionRepository();
        the_number = new MutableLiveData<>();
        this.price = new MutableLiveData<>();
        the_number.setValue(String.valueOf(count));

//        price.setValue();

    }





    public LiveData<List<Include>> getdata(String trip_id) {

        return repository.getIncludes(trip_id);
    }



    public void plus (View v){



        if (count<=5){
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

    public void getPrice(String price) {

        this.price.setValue(price);
        this.count_price = Integer.parseInt(price);

    }
}
