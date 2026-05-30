package com.example.bookease;

public class Admin_Booking_Model {
    private String bookingID;
    private String customerName;
    private String date;
    private String time;
    private String guests;
    private String status;

    public Admin_Booking_Model() {
    }

    public Admin_Booking_Model(String bookingID, String customerName, String date, String time, String guests, String status) {
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.status = status;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
                "booingID='" + bookingID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
