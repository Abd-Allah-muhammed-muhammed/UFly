package com.abdallah.ufly.ui.book;

import com.abdallah.ufly.model.book.BookModelResponse;
import com.abdallah.ufly.model.login.LoginResponse;

public interface BookResultCallBacks {

    void onError(String msg);
    void response(BookModelResponse response);
}
