package com.example.limiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedData.setUser();

    }

    public void startLoginActivity(View view) {

        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }



}