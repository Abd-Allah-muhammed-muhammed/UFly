
package com.abdallah.ufly.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    @SerializedName("id_mail_valid")
    @Expose
    private String id_mail_valid;


@SerializedName("is_mail_valid")
    @Expose
    private int is_mail_valid;

@SerializedName("address")
    @Expose
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_mail_valid() {
        return id_mail_valid;
    }

    public void setId_mail_valid(String id_mail_valid) {
        this.id_mail_valid = id_mail_valid;
    }


    public int getIs_mail_valid() {
        return is_mail_valid;
    }

    public void setIs_mail_valid(int is_mail_valid) {
        this.is_mail_valid = is_mail_valid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
