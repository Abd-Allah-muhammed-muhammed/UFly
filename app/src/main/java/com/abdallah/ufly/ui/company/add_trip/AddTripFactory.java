package com.abdallah.ufly.ui.company.add_trip;

import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddTripFactory extends ViewModelProvider.NewInstanceFactory {


    ProgressBar progressBar ;

    public AddTripFactory(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTripViewModel(progressBar);
    }
}
