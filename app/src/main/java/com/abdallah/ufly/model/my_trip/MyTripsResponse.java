
package com.abdallah.ufly.model.my_trip;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTripsResponse {

    @SerializedName("data_trips")
    @Expose
    private List<DataTrip> dataTrips = null;
    @SerializedName("data_book")
    @Expose
    private List<DataBook> dataBook = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<DataTrip> getDataTrips() {
        return dataTrips;
    }

    public void setDataTrips(List<DataTrip> dataTrips) {
        this.dataTrips = dataTrips;
    }

    public List<DataBook> getDataBook() {
        return dataBook;
    }

    public void setDataBook(List<DataBook> dataBook) {
        this.dataBook = dataBook;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
