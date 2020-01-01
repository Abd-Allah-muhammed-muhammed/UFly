
package com.abdallah.ufly.model.my_trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTripResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_trip")
    @Expose
    private Integer idTrip;
    @SerializedName("id_includes")
    @Expose
    private Integer idIncludes;
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

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    @SerializedName("is_paid")
    @Expose
    private Integer isPaid;


    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Integer idTrip) {
        this.idTrip = idTrip;
    }

    public Integer getIdIncludes() {
        return idIncludes;
    }

    public void setIdIncludes(Integer idIncludes) {
        this.idIncludes = idIncludes;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
