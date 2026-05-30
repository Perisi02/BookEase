package com.example.bookease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookease.admin.Admin_Dashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class StaffManagement extends AppCompatActivity {

    RecyclerView rv_staffList;

    TextView tv_staffID;
    EditText et_firstName;
    EditText et_lastName;
    EditText et_password;
    EditText et_role;
    EditText et_email;
    EditText et_phoneNumber;

    Button btn_cancel;
    Button btn_addNewStaff;
    Button btn_updateDetails;
    Button btn_deleteStaff;

    FirebaseAuth auth;
    DatabaseReference usersRef;
    DatabaseReference counterRef;

    ArrayList<StaffMember> staffList;
    StaffAdapter staffAdapter;

    String selectedFirebaseUid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_staff_management);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        rv_staffList = findViewById(R.id.rv_staffList);

        tv_staffID = findViewById(R.id.tv_staffID);
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_password = findViewById(R.id.et_password);
        et_role = findViewById(R.id.et_role);
        et_email = findViewById(R.id.et_email);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_addNewStaff = findViewById(R.id.btn_addNewStaff);
        btn_updateDetails = findViewById(R.id.btn_updateDetails);
        btn_deleteStaff = findViewById(R.id.btn_deleteStaff);

        auth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference("users");
        counterRef = FirebaseDatabase.getInstance().getReference("counters").child("staffId");

        staffList = new ArrayList<>();

        staffAdapter = new StaffAdapter(staffList, new StaffAdapter.OnStaffClickListener() {
            @Override
            public void onStaffClick(StaffMember staff) {
                selectStaff(staff);
            }
        });

        rv_staffList.setLayoutManager(new LinearLayoutManager(this));
        rv_staffList.setAdapter(staffAdapter);

        clearForm();
        loadStaffList();
    }

    private void loadStaffList() {
        usersRef.orderByChild("type").equalTo("staff")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        staffList.clear();

                        for (DataSnapshot staffSnapshot : snapshot.getChildren()) {
                            StaffMember staff = new StaffMember();

                            staff.firebaseUid = staffSnapshot.getKey();
                            staff.staffId = staffSnapshot.child("staffId").getValue(String.class);
                            staff.firstName = staffSnapshot.child("firstName").getValue(String.class);
                            staff.lastName = staffSnapshot.child("lastName").getValue(String.class);
                            staff.email = staffSnapshot.child("email").getValue(String.class);
                            staff.phone = staffSnapshot.child("phone").getValue(String.class);
                            staff.type = staffSnapshot.child("type").getValue(String.class);
                            staff.staffRole = staffSnapshot.child("staffRole").getValue(String.class);

                            if (staff.staffId == null) {
                                staff.staffId = "";
                            }
                            if (staff.firstName == null) {
                                staff.firstName = "";
                            }
                            if (staff.lastName == null) {
                                staff.lastName = "";
                            }
                            if (staff.email == null) {
                                staff.email = "";
                            }
                            if (staff.phone == null) {
                                staff.phone = "";
                            }
                            if (staff.staffRole == null) {
                                staff.staffRole = "";
                            }

                            staffList.add(staff);
                        }

                        staffAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(StaffManagement.this,
                                "Failed to load staff: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void selectStaff(StaffMember staff) {
        selectedFirebaseUid = staff.firebaseUid;

        tv_staffID.setText(staff.staffId);
        et_firstName.setText(staff.firstName);
        et_lastName.setText(staff.lastName);
        et_email.setText(staff.email);
        et_phoneNumber.setText(staff.phone);
        et_role.setText(staff.staffRole);

        et_password.setText("");
        et_password.setHint("Leave blank when updating");

        btn_addNewStaff.setEnabled(false);
        btn_updateDetails.setEnabled(true);
        btn_deleteStaff.setEnabled(true);
    }

    public void addNewStaff(View view) {
        if (selectedFirebaseUid != null) {
            Toast.makeText(this, "Please clear the form before adding new staff", Toast.LENGTH_SHORT).show();
            return;
        }

        String firstName = et_firstName.getText().toString().trim();
        String lastName = et_lastName.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String staffRole = et_role.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String phone = et_phoneNumber.getText().toString().trim();

        if (firstName.isEmpty()) {
            et_firstName.setError("First name is required");
            return;
        }

        if (lastName.isEmpty()) {
            et_lastName.setError("Last name is required");
            return;
        }

        if (email.isEmpty()) {
            et_email.setError("Email is required");
            return;
        }

        if (password.isEmpty()) {
            et_password.setError("Password is required");
            return;
        }

        if (password.length() < 6) {
            et_password.setError("Password must be at least 6 characters");
            return;
        }
        createStaffIdAndRegister(firstName, lastName, password, staffRole, email, phone);
    }

    private void createStaffIdAndRegister(String firstName, String lastName, String password, String staffRole, String email, String phone) {

        counterRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                long currentId = 1000;

                if (snapshot.exists() && snapshot.getValue(Long.class) != null) {
                    currentId = snapshot.getValue(Long.class);
                }

                final long nextId = currentId + 1;
                final String displayStaffId = String.valueOf(nextId);

                registerStaffToAuth(firstName, lastName, password, staffRole, email, phone, displayStaffId, nextId);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StaffManagement.this,
                        "Failed to create staff ID: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void registerStaffToAuth(String firstName, String lastName, String password, String staffRole, String email, String phone, String displayStaffId, long nextId) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser newStaffUser = auth.getCurrentUser();
                    if (newStaffUser == null) {
                        Toast.makeText(StaffManagement.this, "Failed to create staff account", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String firebaseUid = newStaffUser.getUid();

                    HashMap<String, Object> staffMap = new HashMap<>();
                    staffMap.put("staffId", displayStaffId);
                    staffMap.put("firstName", firstName);
                    staffMap.put("lastName", lastName);
                    staffMap.put("email", email);
                    staffMap.put("phone", phone);
                    staffMap.put("type", "staff");
                    staffMap.put("staffRole", staffRole);

                    saveStaffToDatabase(firebaseUid, staffMap, nextId);

                } else {
                    String message = "Failed to create staff account";
                    if (task.getException() != null) {
                        message = task.getException().getMessage();
                    }
                    Toast.makeText(StaffManagement.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveStaffToDatabase(String firebaseUid, HashMap<String, Object> staffMap, long nextId) {
        usersRef.child(firebaseUid).setValue(staffMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                counterRef.setValue(nextId);
                Toast.makeText(StaffManagement.this, "Staff added successfully", Toast.LENGTH_SHORT).show();
                clearForm();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StaffManagement.this, "Failed to save staff data: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateDetails(View view) {
        if (selectedFirebaseUid == null) {
            Toast.makeText(this, "Please select a staff member first", Toast.LENGTH_SHORT).show();
            return;
        }

        String firstName = et_firstName.getText().toString().trim();
        String lastName = et_lastName.getText().toString().trim();
        String staffRole = et_role.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String phone = et_phoneNumber.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (firstName.isEmpty()) {
            et_firstName.setError("First name is required");
            return;
        }

        if (lastName.isEmpty()) {
            et_lastName.setError("Last name is required");
            return;
        }

        if (email.isEmpty()) {
            et_email.setError("Email is required");
            return;
        }

        HashMap<String, Object> updates = new HashMap<>();
        updates.put("firstName", firstName);
        updates.put("lastName", lastName);
        updates.put("email", email);
        updates.put("phone", phone);
        updates.put("staffRole", staffRole);
        updates.put("type", "staff");

        usersRef.child(selectedFirebaseUid).updateChildren(updates).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(StaffManagement.this, "Staff details updated", Toast.LENGTH_SHORT).show();
                clearForm();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StaffManagement.this, "Update failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        if (!password.isEmpty()) {
            auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(StaffManagement.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(StaffManagement.this, "Failed to send reset email: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void deleteStaff(View view) {
        if (selectedFirebaseUid == null) {
            Toast.makeText(this, "Please select a staff member first", Toast.LENGTH_SHORT).show();
            return;
        }

        usersRef.child(selectedFirebaseUid).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(StaffManagement.this, "Staff removed from database", Toast.LENGTH_SHORT).show();
                clearForm();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StaffManagement.this, "Delete failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cancelEdit(View view) {
        clearForm();
    }

    public void returnToDashboard(View view) {
        Intent intent = new Intent(StaffManagement.this, Admin_Dashboard.class);
        startActivity(intent);
        finish();
    }

    private void clearForm() {
        selectedFirebaseUid = null;

        tv_staffID.setText("");
        et_firstName.setText("");
        et_lastName.setText("");
        et_password.setText("");
        et_password.setHint("Enter Password");
        et_role.setText("");
        et_email.setText("");
        et_phoneNumber.setText("");

        btn_addNewStaff.setEnabled(true);
        btn_updateDetails.setEnabled(false);
        btn_deleteStaff.setEnabled(false);
    }
}