
package com.abdallah.ufly.model.passenger_booked;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("passenger")
    @Expose
    private Passenger passenger;
    @SerializedName("booked")
    @Expose
    private Booked booked;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Booked getBooked() {
        return booked;
    }

    public void setBooked(Booked booked) {
        this.booked = booked;
    }

}
