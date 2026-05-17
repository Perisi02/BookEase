package com.example.bookease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView iv_hamburgerMenu;
    ImageView iv_profile;
    TextView tv_viewMenu;
    Button btn_bookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iv_hamburgerMenu = findViewById(R.id.iv_hamburgerMenu);
        iv_profile = findViewById(R.id.iv_profile);
        tv_viewMenu = findViewById(R.id.tv_viewMenu);
        btn_bookNow = findViewById(R.id.btn_bookNow);
    }

    /**
     *  Opens login or profile screen
     *  If user is logged on -> open profile
     *  else -> open login
     */
    public void openProfile(View view) {
        //  Intent intent = new Intent();
        //  intent.setClass(... profile || login );
        //  startActivity(...);

        // Temp Toast
        Toast.makeText(this, "No profile yet", Toast.LENGTH_SHORT).show();
    }

    /**
     *  Not sure what menu-screen/menu-items this is gonna open TBH
     *  Refactor the name when it does something
     */
    public void doSomething(View view) {
        // Tempt Toast
        Toast.makeText(this, "Huh?", Toast.LENGTH_SHORT).show();
    }

    /**
     *  Opens the booking/reservation screen
     */
    public void openBookingScreen(View view) {
        Intent intent = new Intent(MainActivity.this, BookingActivity.class);
        startActivity(intent);
    }

    /**
     *  Opens the menu list screen
     */
    public void openFullMenu(View view) {
        Intent intent = new Intent(MainActivity.this, MenuListActivity.class);
        startActivity(intent);
    }
}