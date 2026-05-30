package com.example.bookease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Extend to the .Adapter<RVHolder>
// alt+insert to auto generate the x3 @Override methods
public class Admin_Booking_RVAdapter extends RecyclerView.Adapter<Admin_Booking_RVHolder> {

    // List for all the bookings
    ArrayList<Admin_Booking_Model> bookingList;

    // alt+insert -> generate constructor
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
        holder.resId.setText(booking.getId());
        holder.name.setText(booking.getName());
        holder.date.setText(booking.getDate());
        holder.time.setText(booking.getTime());
        holder.guests.setText(booking.getGuests());
        holder.status.setText(booking.getStatus());

        // Not 100% sure on the buttons but it is what it is I guess
        // Just feels like it breaks the flow of the code, but TBH IDEK
        holder.btnAction.setOnClickListener(v -> {
            holder.btnAction.setVisibility(View.GONE);
            holder.btnCancel.setVisibility(View.VISIBLE);
            holder.btnConfirm.setVisibility(View.VISIBLE);
        });

        holder.btnConfirm.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Confirm " + booking.getId(), Toast.LENGTH_SHORT).show();
        });

        holder.btnCancel.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Cancel " + booking.getId(), Toast.LENGTH_SHORT).show();
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
