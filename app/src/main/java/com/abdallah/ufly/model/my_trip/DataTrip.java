
package com.abdallah.ufly.model.my_trip;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTrip implements Parcelable {

    @SerializedName("id_trip")
    @Expose
    private String idTrip;
    @SerializedName("departure")
    @Expose
    private String departure;
    @SerializedName("arrival")
    @Expose
    private String arrival;
    @SerializedName("date_from")
    @Expose
    private String dateFrom;
    @SerializedName("date_until")
    @Expose
    private String dateUntil;
    @SerializedName("passengers")
    @Expose
    private String passengers;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("includes")
    @Expose
    private String includes;
    @SerializedName("is_complete")
    @Expose
    private String isComplete;
    @SerializedName("available_booked")
    @Expose
    private String availableBooked;
    @SerializedName("number_booked")
    @Expose
    private String numberBooked;
    @SerializedName("time_in")
    @Expose
    private String timeIn;
    @SerializedName("time_out")
    @Expose
    private String timeOut;
    @SerializedName("image")
    @Expose
    private String image;

    protected DataTrip(Parcel in) {
        idTrip = in.readString();
        departure = in.readString();
        arrival = in.readString();
        dateFrom = in.readString();
        dateUntil = in.readString();
        passengers = in.readString();
        price = in.readString();
        description = in.readString();
        companyId = in.readString();
        includes = in.readString();
        isComplete = in.readString();
        availableBooked = in.readString();
        numberBooked = in.readString();
        timeIn = in.readString();
        timeOut = in.readString();
        image = in.readString();
    }

    public static final Creator<DataTrip> CREATOR = new Creator<DataTrip>() {
        @Override
        public DataTrip createFromParcel(Parcel in) {
            return new DataTrip(in);
        }

        @Override
        public DataTrip[] newArray(int size) {
            return new DataTrip[size];
        }
    };

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(String dateUntil) {
        this.dateUntil = dateUntil;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getAvailableBooked() {
        return availableBooked;
    }

    public void setAvailableBooked(String availableBooked) {
        this.availableBooked = availableBooked;
    }

    public String getNumberBooked() {
        return numberBooked;
    }

    public void setNumberBooked(String numberBooked) {
        this.numberBooked = numberBooked;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idTrip);
        dest.writeString(departure);
        dest.writeString(arrival);
        dest.writeString(dateFrom);
        dest.writeString(dateUntil);
        dest.writeString(passengers);
        dest.writeString(price);
        dest.writeString(description);
        dest.writeString(companyId);
        dest.writeString(includes);
        dest.writeString(isComplete);
        dest.writeString(availableBooked);
        dest.writeString(numberBooked);
        dest.writeString(timeIn);
        dest.writeString(timeOut);
        dest.writeString(image);
    }
}
