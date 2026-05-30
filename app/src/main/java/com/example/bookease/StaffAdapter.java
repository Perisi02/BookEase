package com.example.bookease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {

    public interface OnStaffClickListener {
        void onStaffClick(StaffMember staff);
    }

    private List<StaffMember> staffList;
    private OnStaffClickListener listener;

    public StaffAdapter(List<StaffMember> staffList, OnStaffClickListener listener) {
        this.staffList = staffList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        StaffMember staff = staffList.get(position);

        holder.text1.setText("Staff ID: " + staff.staffId);
        holder.text2.setText("Last Name: " + staff.lastName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onStaffClick(staff);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return staffList.size();
    }

    public static class StaffViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
