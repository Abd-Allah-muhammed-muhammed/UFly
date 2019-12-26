package com.abdallah.ufly.model.book;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookModelResponse {


    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("ufly_id")
    @Expose
    private String ufly_id;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUfly_id() {
        return ufly_id;
    }

    public void setUfly_id(String ufly_id) {
        this.ufly_id = ufly_id;
    }
}
