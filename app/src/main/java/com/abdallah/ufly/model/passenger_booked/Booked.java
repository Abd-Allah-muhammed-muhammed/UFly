
package com.abdallah.ufly.model.passenger_booked;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Booked {

    @SerializedName("uui_id")
    @Expose
    private String uui_id;
    @SerializedName("is_paid")
    @Expose
    private String is_paid;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("id_payment")
    @Expose
    private String id_payment;

    public String getUui_id() {
        return uui_id;
    }

    public void setUui_id(String uui_id) {
        this.uui_id = uui_id;
    }

    public String getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(String is_paid) {
        this.is_paid = is_paid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId_payment() {
        return id_payment;
    }

    public void setId_payment(String id_payment) {
        this.id_payment = id_payment;
    }

}
