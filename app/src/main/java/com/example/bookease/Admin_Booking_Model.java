package com.example.bookease;

public class Admin_Booking_Model {
    private String id;
    private String name;
    private String date;
    private String time;
    private String guests;
    private String status;

    public Admin_Booking_Model() {
    }

    public Admin_Booking_Model(String id, String name, String date, String time, String guests, String status) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin_BookingModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
