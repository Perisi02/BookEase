package com.example.bookease;

public class StaffMember {
    public String firebaseUid;
    public String staffId;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String type;
    public String staffRole;

    public StaffMember() {
        // Required for Firebase
    }
    public StaffMember(String firebaseUid, String staffId, String firstName, String lastName, String email, String phone, String type, String staffRole) {
        this.firebaseUid = firebaseUid;
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.staffRole = staffRole;
    }
}