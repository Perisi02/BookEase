package com.example.bookease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodayBookingAdapter extends RecyclerView.Adapter<TodayBookingAdapter.TodayBookingViewHolder> {
    private List<TodayBooking> bookingList;

    public TodayBookingAdapter(List<TodayBooking> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public TodayBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_today_booking, parent, false);

        return new TodayBookingViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TodayBookingViewHolder holder, int position) {
        TodayBooking booking = bookingList.get(position);

        holder.tv_customerName.setText("Customer: " + booking.customerName);
        holder.tv_bookingDate.setText("Date: " + booking.date);
        holder.tv_bookingInfo.setText("Time: " + booking.time + " | Guests: " + booking.guests);
        holder.tv_status.setText("Status: " + booking.status);
    }
    @Override
    public int getItemCount() {
        return bookingList.size();
    }
    public static class TodayBookingViewHolder extends RecyclerView.ViewHolder {

        TextView tv_customerName;
        TextView tv_bookingDate;
        TextView tv_bookingInfo;
        TextView tv_status;

        public TodayBookingViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_customerName = itemView.findViewById(R.id.tv_customerName);
            tv_bookingDate = itemView.findViewById(R.id.tv_bookingDate);
            tv_bookingInfo = itemView.findViewById(R.id.tv_bookingInfo);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }
}
