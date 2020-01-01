package com.abdallah.ufly.ui.my_trip;

import com.abdallah.ufly.model.my_trip.MyTripResponse;

public interface MyTripResultCallBacks {

    void onError(String msg);
    void response(MyTripResponse response);
}
