package com.example.bookease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_firstName;
    EditText et_lastName;
    EditText et_email;
    EditText et_password;
    Button btn_signUp;
    TextView btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_username = findViewById(R.id.et_username);
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_signUp = findViewById(R.id.btn_signUp);
        btn_login = findViewById(R.id.tv_login);
    }

    /**
        Saves user inputs from EditTexts
        uses it to create new user(type:"customer")
     */
    public void newCustomer(View view) {
        String type = "customer";
        String username = et_username.getText().toString();
        String firstName = et_firstName.getText().toString();
        String lastName = et_lastName.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        // something something create new user type="customer"

        // Tempt Toast
        Toast.makeText(this, "Yet to complete", Toast.LENGTH_SHORT).show();

    }

    /**
     *
     */
    public void openLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}