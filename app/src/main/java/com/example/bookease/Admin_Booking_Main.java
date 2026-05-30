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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// Based on that fruits recycler demo in the class Discord
public class Admin_Booking_Main extends AppCompatActivity {

    // variables for layout components
    Button btnLogout;
    Button btnBack;
    RecyclerView rvBookings;
    Button btnAddDummyData;

    // variables for Firebase real time db
    FirebaseDatabase database;
    DatabaseReference ref;

    // variables for the recycler
    Admin_Booking_RVAdapter adapter;
    ArrayList<Admin_Booking_Model> bookingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_booking_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Link layout items
        btnLogout = findViewById(R.id.btn_logout);
        btnBack = findViewById(R.id.btn_back);
        rvBookings = findViewById(R.id.rv_bookings);
        btnAddDummyData = findViewById(R.id.btn_addTestBooking);

        // link up Firebase
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("bookings");

        // setup for recycler view: arraylist & adapter
        bookingList = new ArrayList<>();
        adapter = new Admin_Booking_RVAdapter(bookingList);

        // set adapter & layout manager for recycler view
        rvBookings.setAdapter(adapter);
        rvBookings.setLayoutManager(new LinearLayoutManager(this));

        // setup value event listener for the database reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookingList.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Admin_Booking_Model bookingModel = postSnapshot.getValue(Admin_Booking_Model.class);

                    if (bookingModel != null) {
                        bookingModel.setBookingID(postSnapshot.getKey());
                        bookingList.add(bookingModel);
                    }
                }

                adapter.setData(bookingList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Admin_Booking_Main.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     *
     *
     */
    public void goBack(View view) {
        Intent intent = new Intent(Admin_Booking_Main.this, Admin_Dashboard.class);
        startActivity(intent);
        finish();
    }

    /**
     *
     *
     */
    public void logoutAdmin(View view) {
        Intent intent = new Intent(Admin_Booking_Main.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Adds 3x booking entries for testing.
     * Might be removed out after use or used as a way to add/update_details for booking
     */
    public void addTestBooking(View view) {
        // dummy #1
        String dummyId1 = ref.push().getKey();
        Admin_Booking_Model dummy1 = new Admin_Booking_Model(
                "",
                "Leati",
                "25/12/2025",
                "8:00pm",
                "6",
                "Pending"
        );
        if (dummyId1 != null) {
            ref.child(dummyId1).setValue(dummy1);
        }
        // dummy #2
        String dummyId2 = ref.push().getKey();
        Admin_Booking_Model dummy2 = new Admin_Booking_Model(
                "",
                "Leati",
                "31/12/2025",
                "9:00pm",
                "12",
                "Pending"
        );
        if (dummyId2 != null) {
            ref.child(dummyId2).setValue(dummy2);
        }
        // dummy #3
        String dummyId3 = ref.push().getKey();
        Admin_Booking_Model dummy3 = new Admin_Booking_Model(
                "",
                "Leati",
                "24/12/2025",
                "10:00pm",
                "3",
                "Pending"
        );
        if (dummyId3 != null) {
            ref.child(dummyId3).setValue(dummy3);
        }
    }
}