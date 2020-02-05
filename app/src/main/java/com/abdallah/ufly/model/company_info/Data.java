
package com.abdallah.ufly.model.company_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("company_id")
    @Expose
    private String company_id;
    @SerializedName("company_name")
    @Expose
    private String company_name;
    @SerializedName("copmany_address")
    @Expose
    private String copmany_address;
    @SerializedName("company_phone")
    @Expose
    private String company_phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCopmany_address() {
        return copmany_address;
    }

    public void setCopmany_address(String copmany_address) {
        this.copmany_address = copmany_address;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

}
