
package com.abdallah.ufly.model.my_trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id_trip")
    @Expose
    private Integer idTrip;
    @SerializedName("departure")
    @Expose
    private String departure;
    @SerializedName("arrival")
    @Expose
    private String arrival;
    @SerializedName("date_until")
    @Expose
    private String dateUntil;
    @SerializedName("date_from")
    @Expose
    private String dateFrom;
    @SerializedName("passengers")
    @Expose
    private String passengers;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Integer idTrip) {
        this.idTrip = idTrip;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(String dateUntil) {
        this.dateUntil = dateUntil;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
