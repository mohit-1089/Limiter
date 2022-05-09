package com.example.limiter.adminFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.limiter.R;

public class AdminMainActivity extends AppCompatActivity {

    Button btnAdminDisplay,btnAddSlot,btnSetMaxTime,btnEditFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_function);

        btnAdminDisplay = findViewById(R.id.btnAdminDisplay);
        btnAddSlot = findViewById(R.id.btnAddSlot);
        btnEditFaq = findViewById(R.id.btnEditFaq);
        btnSetMaxTime = findViewById(R.id.btnSetMaxTime);

        btnAdminDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AdminDisplayParking.class );
                startActivity(intent);

            }
        });

        btnAddSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AddSlotActivity.class );
                startActivity(intent);

            }
        });

        btnEditFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),EditFAQ.class );
                startActivity(intent);

            }
        });

        btnSetMaxTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),SetMaxTime.class );
                startActivity(intent);

            }
        });

    }
}