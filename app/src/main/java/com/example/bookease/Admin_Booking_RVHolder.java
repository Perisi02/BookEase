package com.example.bookease;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Admin_Booking_RVHolder extends RecyclerView.ViewHolder {
    TextView resId;
    TextView name;
    TextView date;
    TextView time;
    TextView guests;
    TextView status;

    Button btnAction;
    Button btnCancel;
    Button btnConfirm;

    public Admin_Booking_RVHolder(@NonNull View itemView) {
        super(itemView);

        resId = itemView.findViewById(R.id.tv_bookingID);
        name = itemView.findViewById(R.id.tv_customerName);
        date = itemView.findViewById(R.id.tv_date);
        time = itemView.findViewById(R.id.tv_time);
        guests = itemView.findViewById(R.id.tv_guests);
        status = itemView.findViewById(R.id.tv_status);

        btnAction = itemView.findViewById(R.id.btn_action);
        btnCancel = itemView.findViewById(R.id.btn_cancelBooking);
        btnConfirm = itemView.findViewById(R.id.btn_confirmBooking);
    }
}
