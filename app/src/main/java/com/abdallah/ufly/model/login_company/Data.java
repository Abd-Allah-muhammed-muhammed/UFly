
package com.abdallah.ufly.model.login_company;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("company_address")
    @Expose
    private String companyAddress;
    @SerializedName("company_phone")
    @Expose
    private String companyPhone;



    @SerializedName("qr")
    @Expose
    private String qr;

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

}
