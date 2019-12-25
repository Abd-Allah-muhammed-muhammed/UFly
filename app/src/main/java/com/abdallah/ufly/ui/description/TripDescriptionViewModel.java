package com.abdallah.ufly.ui.description;

import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdallah.ufly.R;
import com.abdallah.ufly.model.TripInfo;
import com.abdallah.ufly.model.includes.Include;
import com.abdallah.ufly.model.includes.IncludesResponse;
import com.abdallah.ufly.model.trips.TripsResponse;
import com.abdallah.ufly.repository.IncludesRepository;
import com.abdallah.ufly.repository.TripsRepository;
import com.abdallah.ufly.ui.book.BookFragment;

import java.util.List;

import static com.abdallah.ufly.helper.HelperMethod.replace;

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



//    public void bookClick(View v){
//
//
//    }
}
