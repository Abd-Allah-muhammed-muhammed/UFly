package com.abdallah.ufly.ui.description;

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


    public TripDescriptionViewModel() {

        data = new MutableLiveData<>();
        repository = new TripDescriptionRepository();

    }


    public LiveData<List<Include>> getdata(String trip_id) {

        return repository.getIncludes(trip_id);
    }



//    public void bookClick(View v){
//
//
//    }
}
