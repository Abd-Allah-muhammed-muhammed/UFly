
package com.abdallah.ufly.model.my_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("full_name")
    @Expose
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
