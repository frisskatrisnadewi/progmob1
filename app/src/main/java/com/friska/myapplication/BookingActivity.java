package com.friska.myapplication;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {
    TextView tv_startdate, tv_enddate;
    Button datestart, dateend;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //inite
        tv_startdate = findViewById(R.id.tv_booking_tanggal_awal_result);
        tv_enddate = findViewById(R.id.tv_booking_tanggal_akhir_result);
        datestart = findViewById(R.id.btn_book_date_start);
        dateend = findViewById(R.id.btn_book_date_end);
        myCalendar = Calendar.getInstance();


        //click
        datestart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                gotodatepicker();
            }
        });


        dateend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                gotodatepicker2();

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void gotodatepicker(){
       new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               myCalendar.set(Calendar.YEAR, year);
               myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
               myCalendar.set(Calendar.MONTH, month);               String formatTanggal = "dd-MM-yyyy";
               SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
               tv_startdate.setText( sdf.format(myCalendar.getTime()));
           }
       },
               myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
               myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void gotodatepicker2(){
        new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                myCalendar.set(Calendar.MONTH, month);               String formatTanggal = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                tv_enddate.setText( sdf.format(myCalendar.getTime()));
            }
        },
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
