package com.abdallah.ufly.ui.book;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.abdallah.ufly.ui.registration.login.LoginResultCallbacks;
import com.abdallah.ufly.ui.registration.login.LoginViewModel;

public class BookViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private BookResultCallBacks bookResultCallBacks ;

    public BookViewModelFactory(BookResultCallBacks bookResultCallBacks) {
        this.bookResultCallBacks = bookResultCallBacks;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BookViewModel(bookResultCallBacks);
    }
}
