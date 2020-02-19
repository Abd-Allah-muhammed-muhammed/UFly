
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
    private Integer price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("is_complete")
    @Expose
    private Integer isComplete;
    @SerializedName("available_booked")
    @Expose
    private Integer availableBooked;
    @SerializedName("number_booked")
    @Expose
    private Integer numberBooked;
    @SerializedName("time_in")
    @Expose
    private String timeIn;
    @SerializedName("time_out")
    @Expose
    private String timeOut;


 @SerializedName("image")
    @Expose
    private String image;



 @SerializedName("includes")
    @Expose
    private String includes;

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public Integer getAvailableBooked() {
        return availableBooked;
    }

    public void setAvailableBooked(Integer availableBooked) {
        this.availableBooked = availableBooked;
    }

    public Integer getNumberBooked() {
        return numberBooked;
    }

    public void setNumberBooked(Integer numberBooked) {
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

}
