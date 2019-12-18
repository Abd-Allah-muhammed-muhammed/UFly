
package com.abdallah.ufly.model.includes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Include {

    @SerializedName("id_trip")
    @Expose
    private String idTrip;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
