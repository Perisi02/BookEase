package com.example.bookease.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookease.R;
import com.example.bookease.StaffManagement;

public class Admin_Dashboard extends AppCompatActivity {

    Button btnLogout;

    TextView tvTotalUsers;
    TextView tvTotalCustomers;
    TextView tvTotalStaff;
    TextView tvTotalAdmins;
    TextView tvTotalBookings;
    TextView tvTotalPending;
    TextView tvTotalMenuItems;

    Button btnManageBooking;
    Button btnManageMenu;
    Button btnManageStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linkLayoutIDs();
    }

    private void linkLayoutIDs() {
        btnLogout = findViewById(R.id.btn_logout);

        tvTotalUsers = findViewById(R.id.tv_totalUsers);
        tvTotalCustomers = findViewById(R.id.tv_totalCustomers);
        tvTotalStaff = findViewById(R.id.tv_totalStaff);
        tvTotalAdmins = findViewById(R.id.tv_totalAdmins);
        tvTotalBookings = findViewById(R.id.tv_totalBookings);
        tvTotalPending = findViewById(R.id.tv_totalPending);
        tvTotalMenuItems = findViewById(R.id.tv_totalMenuItems);

        btnManageBooking = findViewById(R.id.btn_manageBooking);
        btnManageMenu = findViewById(R.id.btn_manageMenu);
        btnManageStaff = findViewById(R.id.btn_manageStaff);
    }

    /**
     *
     *
     */
    public void logoutAdmin(View view) {
        Toast.makeText(this, "Clicked!\nLogout Admin", Toast.LENGTH_SHORT).show();
    }

    /**
     * Nothing yet for the three buttons.
     *
     */
    public void startManageBooking(View view) {
        Intent intent = new Intent(Admin_Dashboard.this, Admin_Booking_Main.class);
        startActivity(intent);
    }

    /**
     *
     *
     */
    public void startManageMenu(View view) {
        Toast.makeText(this, "Clicked!\nManage Menu", Toast.LENGTH_SHORT).show();
    }

    /**
     *
     *
     */
    public void startManageStaff(View view) {
        Intent intent = new Intent(Admin_Dashboard.this, StaffManagement.class);
        startActivity(intent);
    }
}