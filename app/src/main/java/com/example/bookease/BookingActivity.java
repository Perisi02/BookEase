package com.example.bookease;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    TextView tv_selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initDatePicker();
        tv_selectDate = findViewById(R.id.tv_selectDate);
        tv_selectDate.setText(getCurrentDate());

    }

    /**
     *
     * @return
     */
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month += 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return dateToString(day, month, year);
    }

    /**
     *
     */
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;
                String date = dateToString(day, month, year);
                tv_selectDate.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = android.R.style.Theme_Material_Dialog_Alert;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    /**
     *
     */
    public void selectDate(View view) {
        datePickerDialog.show();
    }

    /**
     *
     * @param day
     * @param month
     * @param year
     */
    private String dateToString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    /**
     *
     * @param month
     */
    private String getMonthFormat(int month) {
        switch (month) {
            case  1: return "JAN";
            case  2: return "FEB";
            case  3: return "MAR";
            case  4: return "APR";
            case  5: return "MAY";
            case  6: return "JUN";
            case  7: return "JUL";
            case  8: return "AUG";
            case  9: return "SEP";
            case 10: return "OCT";
            case 11: return "NOV";
            case 12: return "DEC";
            default: return "";
        }
    }

    /**
     *
     */
    public void backToMain(View view) {
        Intent intent  = new Intent(BookingActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     *  check login status
     *  not logged in -> open login screen
     *      logged in -> open user profile/dashboard
     */
    public void openProfile(View view) {
        Toast.makeText(this, "Need to complete", Toast.LENGTH_SHORT).show();
    }
}