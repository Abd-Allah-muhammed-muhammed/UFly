
package com.abdallah.ufly.model.my_trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTrip {

    @SerializedName("id_trip")
    @Expose
    private String idTrip;
    @SerializedName("departure")
    @Expose
    private String departure;
    @SerializedName("arrival")
    @Expose
    private String arrival;
    @SerializedName("date_from")
    @Expose
    private String dateFrom;
    @SerializedName("date_until")
    @Expose
    private String dateUntil;
    @SerializedName("passengers")
    @Expose
    private String passengers;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("includes")
    @Expose
    private String includes;
    @SerializedName("is_complete")
    @Expose
    private String isComplete;
    @SerializedName("available_booked")
    @Expose
    private String availableBooked;
    @SerializedName("number_booked")
    @Expose
    private String numberBooked;
    @SerializedName("time_in")
    @Expose
    private String timeIn;
    @SerializedName("time_out")
    @Expose
    private String timeOut;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(String dateUntil) {
        this.dateUntil = dateUntil;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getAvailableBooked() {
        return availableBooked;
    }

    public void setAvailableBooked(String availableBooked) {
        this.availableBooked = availableBooked;
    }

    public String getNumberBooked() {
        return numberBooked;
    }

    public void setNumberBooked(String numberBooked) {
        this.numberBooked = numberBooked;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
