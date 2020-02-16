package com.abdallah.ufly.ui.company.add_trip;

import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddTripFactory extends ViewModelProvider.NewInstanceFactory {


    ProgressBar progressBar ;
    ImageView image_trip;

    public AddTripFactory(ProgressBar progressBar , ImageView image_trip) {
        this.progressBar = progressBar;
        this.image_trip = image_trip;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTripViewModel(progressBar,image_trip);
    }
}
