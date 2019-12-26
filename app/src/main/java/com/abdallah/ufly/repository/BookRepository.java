package com.abdallah.ufly.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.retrofit.Api;
import com.abdallah.ufly.retrofit.ApiClient;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class BookRepository {

    Api api ;
    MutableLiveData<BookModelResponse> databook;


    public BookRepository() {
        api = ApiClient.getClient().create(Api.class);

        databook = new MutableLiveData<>();

    }





}
