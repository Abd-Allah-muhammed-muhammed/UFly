
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
    private Integer isPaid;
    @SerializedName("dataCompany")
    @Expose
    private DataCompany dataCompany;
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

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    public DataCompany getDataCompany() {
        return dataCompany;
    }

    public void setDataCompany(DataCompany dataCompany) {
        this.dataCompany = dataCompany;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
