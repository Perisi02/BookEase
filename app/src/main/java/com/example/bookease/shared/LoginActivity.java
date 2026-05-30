package com.example.bookease.shared;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookease.MainActivity;
import com.example.bookease.R;
import com.example.bookease.admin.Admin_Dashboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText et_email;
    EditText et_password;

    FirebaseAuth auth;
    DatabaseReference usersRef;

    @SuppressLint("MissingInflatedId")
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
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        auth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference("users");
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
    */
    public void loginUser(View view) {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (email.isEmpty()) {
            et_email.setError("Email is required");
            return;
        }
        if (password.isEmpty()) {
            et_password.setError("Password is required");
            return;
        }
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<com.google.firebase.auth.AuthResult>(){
            @Override
            public void onComplete(com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = auth.getCurrentUser();
                    if (currentUser == null) {
                        Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String userId = currentUser.getUid();
                    usersRef.child(userId).get().addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot snapshot) {
                            if (!snapshot.exists()) {
                                Toast.makeText(LoginActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            String type = snapshot.child("type").getValue(String.class);
                            if (type == null) {
                                Toast.makeText(LoginActivity.this, "User role not found", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (type.equalsIgnoreCase("customer")) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (type.equalsIgnoreCase("staff")) {
                                Intent intent = new Intent(LoginActivity.this, Admin_Dashboard.class);
                                startActivity(intent);
                                finish();
                            } else if (type.equalsIgnoreCase("admin")) {
                                Intent intent = new Intent(LoginActivity.this, Admin_Dashboard.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Unknown role: " + type, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new com.google.android.gms.tasks.OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(LoginActivity.this, "Failed to read user type", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed. Please check your email and password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *
     */
    public void openSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}