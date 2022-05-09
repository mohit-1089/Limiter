package com.example.limiter.adminFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.limiter.DBServices;
import com.example.limiter.R;
import com.example.limiter.SharedData;
import com.example.limiter.Timer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class SetMaxTime extends AppCompatActivity {

    EditText etVehicleType,etEnterTime;
    Button btnSetTime;
    TimePickerDialog timePicker;
    Calendar calendar;
    int hour;
    int min;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_max_time);
        etVehicleType=findViewById(R.id.etVehicleType);
        etEnterTime=findViewById(R.id.etEnterTime);
        btnSetTime=findViewById(R.id.btnSetTime);
        etEnterTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timePicker=new TimePickerDialog(SetMaxTime.this , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour=i;
                        min=i1;
                        calendar= Calendar.getInstance();
                        calendar.set(0,0,0,hour,min);
                        etEnterTime.setText(DateFormat.format("HH:mm",calendar));
                    }
                },24,0,true);
                timePicker.updateTime(hour,min);
                timePicker.show();

            }
        });
        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type= etVehicleType.getText().toString();
                String time = etEnterTime.getText().toString().trim();
                String realType="none";
                if(type.equals("Car"))
                {
                    realType="Car";
                }
                else if(type.equals("Bus"))
                {
                    realType="Bus";
                }
                else if(type.equals("Bike"))
                {
                    realType="Bike";
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid Vehicle Type Check Hint", Toast.LENGTH_SHORT).show();
                }
                if(!(realType.equals("none")))
                {
                    try {

                        String query="update limiter.maxtime set max_time='"+time+"'where vehicle_type='"+realType+"'";
                        Connection conn= DBServices.openDB();
                        Statement st=conn.createStatement();

                        int res= st.executeUpdate(query);
                        DBServices.closeDB(conn);
                        Toast.makeText(getApplicationContext(), "Max Time Updated", Toast.LENGTH_SHORT).show();


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

    }
}