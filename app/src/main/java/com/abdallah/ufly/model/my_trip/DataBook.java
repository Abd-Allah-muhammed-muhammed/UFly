
package com.abdallah.ufly.model.my_trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataBook {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_trip")
    @Expose
    private String idTrip;
    @SerializedName("uui_id")
    @Expose
    private String uuiId;
    @SerializedName("ufly_id")
    @Expose
    private String uflyId;
    @SerializedName("id_payment")
    @Expose
    private String idPayment;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("is_paid")
    @Expose
    private String isPaid;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("id_company")
    @Expose
    private String idCompany;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public String getUuiId() {
        return uuiId;
    }

    public void setUuiId(String uuiId) {
        this.uuiId = uuiId;
    }

    public String getUflyId() {
        return uflyId;
    }

    public void setUflyId(String uflyId) {
        this.uflyId = uflyId;
    }

    public String getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(String idPayment) {
        this.idPayment = idPayment;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

}
