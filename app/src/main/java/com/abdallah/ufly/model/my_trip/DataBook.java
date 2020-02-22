
package com.abdallah.ufly.model.my_trip;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataBook implements Parcelable {

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

    protected DataBook(Parcel in) {
        id = in.readString();
        idTrip = in.readString();
        uuiId = in.readString();
        uflyId = in.readString();
        idPayment = in.readString();
        price = in.readString();
        isPaid = in.readString();
        number = in.readString();
        idCompany = in.readString();
    }

    public static final Creator<DataBook> CREATOR = new Creator<DataBook>() {
        @Override
        public DataBook createFromParcel(Parcel in) {
            return new DataBook(in);
        }

        @Override
        public DataBook[] newArray(int size) {
            return new DataBook[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idTrip);
        dest.writeString(uuiId);
        dest.writeString(uflyId);
        dest.writeString(idPayment);
        dest.writeString(price);
        dest.writeString(isPaid);
        dest.writeString(number);
        dest.writeString(idCompany);
    }
}
