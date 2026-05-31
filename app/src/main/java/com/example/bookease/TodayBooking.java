package com.example.bookease;

public class TodayBooking {
    public String bookingId;
    public String customerName;
    public String date;
    public String time;
    public String guests;
    public String status;

    public TodayBooking() {
        // Required for Firebase
    }

    public TodayBooking(String bookingId, String customerName, String date, String time, String guests, String status) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.status = status;
    }
}
