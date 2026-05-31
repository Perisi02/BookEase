package com.example.bookease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookease.shared.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Staff_Dashboard extends AppCompatActivity {
    RecyclerView rv_todayBookings;
    Button btn_manageMenu;
    Button btn_logout;

    ArrayList<TodayBooking> bookingList;
    TodayBookingAdapter bookingAdapter;

    DatabaseReference bookingsRef;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_staff_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rv_todayBookings = findViewById(R.id.rv_todayBookings);
        btn_manageMenu = findViewById(R.id.btn_manageMenu);
        btn_logout = findViewById(R.id.btn_logout);

        auth = FirebaseAuth.getInstance();
        bookingsRef = FirebaseDatabase.getInstance().getReference("bookings");

        bookingList = new ArrayList<>();
        bookingAdapter = new TodayBookingAdapter(bookingList);

        rv_todayBookings.setLayoutManager(new LinearLayoutManager(this));
        rv_todayBookings.setAdapter(bookingAdapter);

        loadTodayConfirmedBookings();

        btn_manageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Staff_Dashboard.this, MenuManagement.clss);
                //startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent = new Intent(Staff_Dashboard.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadTodayConfirmedBookings() {
        String todayDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        bookingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookingList.clear();

                for (DataSnapshot bookingSnapshot : snapshot.getChildren()) {
                    String bookingId = bookingSnapshot.getKey();
                    String customerName = bookingSnapshot.child("customerName").getValue(String.class);
                    String date = bookingSnapshot.child("date").getValue(String.class);
                    String time = bookingSnapshot.child("time").getValue(String.class);
                    String guests = bookingSnapshot.child("guests").getValue(String.class);
                    String status = bookingSnapshot.child("status").getValue(String.class);

                    if (date == null || status == null) {
                        continue;
                    }

                    boolean isToday = date.equals(todayDate);
                    boolean isConfirmed = status.equalsIgnoreCase("Confirm");

                    if (isToday && isConfirmed) {
                        TodayBooking booking = new TodayBooking(
                                bookingId, customerName, date, time, guests, status
                        );
                        bookingList.add(booking);
                    }
                }

                bookingAdapter.notifyDataSetChanged();

                if (bookingList.size() == 0) {
                    Toast.makeText(Staff_Dashboard.this, "No confirmed reservations for today",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Staff_Dashboard.this, "Failed to load bookings: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}