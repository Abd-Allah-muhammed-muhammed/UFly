package com.abdallah.ufly.model;

public class TripInfo {

    private String departure ;
    private String arrival ;
    private String date_from ;
    private String date_untile ;
    private String num_passengers ;
    private String price ;

    public TripInfo(String departure, String arrival, String date_from, String date_untile,
                    String num_passengers, String price) {
        this.departure = departure;
        this.arrival = arrival;
        this.date_from = date_from;
        this.date_untile = date_untile;
        this.num_passengers = num_passengers;
        this.price = price;
    }

    public TripInfo() {
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

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_untile() {
        return date_untile;
    }

    public void setDate_untile(String date_untile) {
        this.date_untile = date_untile;
    }

    public String getNum_passengers() {
        return num_passengers;
    }

    public void setNum_passengers(String num_passengers) {
        this.num_passengers = num_passengers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
