
package com.abdallah.ufly.model.trips;

import android.view.View;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripsResponse {


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("departuer")
    @Expose
    private String departuer;
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

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    @SerializedName("includes")
    @Expose
    private String includes;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    @SerializedName("company_id")
    @Expose
    private String company_id;


    @SerializedName("is_complete")
    @Expose
    private int is_complete;


    @SerializedName("available_booked")
    @Expose
    private int available_booked;
    @SerializedName("number_booked")
    @Expose
    private int number_booked;


    @SerializedName("time_in")
    @Expose
    private String time_in;

    @SerializedName("time_out")
    @Expose
    private String time_out;


  @SerializedName("image")
    @Expose
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime_in() {
        return time_in;
    }

    public void setTime_in(String time_in) {
        this.time_in = time_in;
    }

    public String getTime_out() {
        return time_out;
    }

    public void setTime_out(String time_out) {
        this.time_out = time_out;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartuer() {
        return departuer;
    }

    public void setDepartuer(String departuer) {
        this.departuer = departuer;
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

    public int getIs_complete() {
        return is_complete;
    }

    public void setIs_complete(int is_complete) {
        this.is_complete = is_complete;
    }

    public int getAvailable_booked() {
        return available_booked;
    }

    public void setAvailable_booked(int available_booked) {
        this.available_booked = available_booked;
    }

    public int getNumber_booked() {
        return number_booked;
    }

    public void setNumber_booked(int number_booked) {
        this.number_booked = number_booked;
    }
}
