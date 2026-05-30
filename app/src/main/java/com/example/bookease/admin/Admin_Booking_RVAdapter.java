package com.example.bookease.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookease.R;

import java.util.ArrayList;

// Extend to the .Adapter<RVHolder>
// alt+insert to auto generate the x3 @Override methods
public class Admin_Booking_RVAdapter extends RecyclerView.Adapter<Admin_Booking_RVHolder> {

    // List for all the bookings
    ArrayList<Admin_Booking_Model> bookingList;

    // alt+insert -> generate constructor: takes in arraylist of objects/models
    public Admin_Booking_RVAdapter(ArrayList<Admin_Booking_Model> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public Admin_Booking_RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Admin_Booking_RVHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.admin_item_booking,
                                parent,
                                false));
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_Booking_RVHolder holder, int position) {
        Admin_Booking_Model booking = bookingList.get(position);

        // Only want the action button showing until it's clicked
        holder.btnAction.setVisibility(View.VISIBLE);
        holder.btnCancel.setVisibility(View.GONE);
        holder.btnConfirm.setVisibility(View.GONE);

        // Binding all the text fields
        holder.resId.setText(booking.getBookingID());
        holder.name.setText(booking.getCustomerName());
        holder.date.setText(booking.getDate());
        holder.time.setText(booking.getTime());
        holder.guests.setText(booking.getGuests());
        holder.status.setText(booking.getStatus());

        // onClicks for the buttons
        // clicking 'action' hides itself, but shows the buttons 'cancel' & 'confirm'
        holder.btnAction.setOnClickListener(v -> {
            holder.btnAction.setVisibility(View.GONE);
            holder.btnCancel.setVisibility(View.VISIBLE);
            holder.btnConfirm.setVisibility(View.VISIBLE);
        });

        holder.btnConfirm.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Confirm " + booking.getBookingID(), Toast.LENGTH_SHORT).show();
        });

        holder.btnCancel.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Cancel " + booking.getBookingID(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public void setData(ArrayList<Admin_Booking_Model> bookingList) {
        this.bookingList = bookingList;
        notifyDataSetChanged();
    }
}
