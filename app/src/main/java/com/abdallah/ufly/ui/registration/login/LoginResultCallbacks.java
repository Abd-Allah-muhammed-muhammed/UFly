package com.abdallah.ufly.ui.registration.login;

import com.abdallah.ufly.model.login.LoginResponse;

public interface LoginResultCallbacks {

    void onError(String msg);
    void status(Integer status);
    void response(LoginResponse response);
}
