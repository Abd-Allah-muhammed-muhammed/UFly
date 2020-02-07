
package com.abdallah.ufly.model.my_trip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCompany {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("copmany_address")
    @Expose
    private String copmanyAddress;
    @SerializedName("company_phone")
    @Expose
    private String companyPhone;
    @SerializedName("qr")
    @Expose
    private String qr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCopmanyAddress() {
        return copmanyAddress;
    }

    public void setCopmanyAddress(String copmanyAddress) {
        this.copmanyAddress = copmanyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

}
