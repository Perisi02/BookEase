package com.example.bookease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookease.customer.Customer_Dashboard;

public class MenuListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     *
     */
    public void backToMain(View view) {
        Intent intent  = new Intent(MenuListActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     *  check login status
     *  not logged in -> open login screen
     *      logged in -> open user profile/dashboard
     */
    public void openProfile(View view) {
        Intent intent = new Intent(MenuListActivity.this, Customer_Dashboard.class);
        startActivity(intent);
    }
}