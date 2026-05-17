package com.example.bookease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
        1 - Find user from Firebase
            - take username and find matching username in Firebase
            - nothing found  -> error, user not found
            - username found -> continue
        2 - Compare input details with stored details
            - String(input_username).equals(stored_username) && String(input_password).equals(stored_password)
            - incorrect -> error, incorrect password
            - correct   -> continue
        3 - Go to user specific screen/dashboard
            - use user type for user dashboard
        Intent intent = new Intent(LoginActivity.this, user_dashboard.class);
        startActivity(intent);
        finish()
    */
    public void loginUser(View view) {
        // Temp Toast
        Toast.makeText(this, "Yet to complete", Toast.LENGTH_SHORT).show();

    }

    /**
     *
     */
    public void openSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}