package com.example.bookease.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookease.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_firstName;
    EditText et_lastName;
    EditText et_email;
    EditText et_password;
    Button btn_signUp;
    TextView btn_login;
    FirebaseAuth auth;
    DatabaseReference usersRef;

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
        auth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference("users");
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
        if (password.length() < 6) {
            et_password.setError("Password must be at least 6 characters");
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // something something create new user type="customer"


        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser currentUser = auth.getCurrentUser();
                    if(currentUser == null){
                        Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String userId = currentUser.getUid();
                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("username", username);
                    userMap.put("firstName", firstName);
                    userMap.put("lastName", lastName);
                    userMap.put("email", email);
                    userMap.put("type", type);
                    usersRef.child(userId).setValue(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(SignUpActivity.this, "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *
     */
    public void openLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}