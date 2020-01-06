
package com.abdallah.ufly.model.trips;

import android.view.View;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripsResponse {


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

}
