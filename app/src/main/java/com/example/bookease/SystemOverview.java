package com.example.bookease;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SystemOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_system_overview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
        Toast.makeText(this, "Clicked!\nManage Booking", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "Clicked!\nManage Staff", Toast.LENGTH_SHORT).show();
    }
}