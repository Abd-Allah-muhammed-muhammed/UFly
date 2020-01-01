package com.abdallah.ufly.ui.my_trip;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.abdallah.ufly.ui.book.BookViewModel;

public class MyTripViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private MyTripResultCallBacks myTripResultCallBacks;

    public MyTripViewModelFactory(MyTripResultCallBacks myTripResultCallBacks) {
        this.myTripResultCallBacks = myTripResultCallBacks;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MyTripViewModel(myTripResultCallBacks);
    }
}
