package com.example.limiter;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.limiter.ui.parking.ParkingFragment;

import java.sql.Time;
import java.util.Calendar;

public class Timer extends AppCompatActivity {
    EditText editTextTime;
    Button btnTime;
    Calendar calendar;
    int hour;
    int min;
    int id;
    TimePickerDialog timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_timer);
        editTextTime = findViewById(R.id.editTextTime);
        btnTime = findViewById(R.id.btnTime);
        id= getIntent().getIntExtra("id",0);
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker=new TimePickerDialog(Timer.this , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour=i;
                        min=i1;
                        calendar=Calendar.getInstance();
                        calendar.set(0,0,0,hour,min);
                        editTextTime.setText(DateFormat.format("HH:mm",calendar));
                    }
                },24,0,true);
                timePicker.updateTime(hour,min);
                timePicker.show();

            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = editTextTime.getText().toString().trim();
                ParkingFragment.updateTime(time,id);
                finish();
            }
        });

    }
}

