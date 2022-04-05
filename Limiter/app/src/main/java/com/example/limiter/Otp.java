package com.example.limiter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;

public class Otp extends AppCompatActivity {
    EditText otp;
    Button btnSubmit,btnResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp=(EditText) findViewById(R.id.etOtp);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        btnResend=(Button)findViewById(R.id.btnResend);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}