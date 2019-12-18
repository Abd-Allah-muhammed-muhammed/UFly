package com.abdallah.ufly.ui.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.model.TripInfo;
import com.abdallah.ufly.model.includes.Include;
import com.abdallah.ufly.model.includes.IncludesResponse;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.repository.IncludesRepository;
import com.abdallah.ufly.repository.TripsRepository;

import java.util.List;

public class TripDescriptionViewModel extends ViewModel {



    private MutableLiveData<List<Include>> data;
    private IncludesRepository repository ;


    public TripDescriptionViewModel() {

        data = new MutableLiveData<>();
        repository = new IncludesRepository();

    }


    public LiveData<List<Include>> getdata(String trip_id) {

        return repository.getIncludes(trip_id);
    }
}
