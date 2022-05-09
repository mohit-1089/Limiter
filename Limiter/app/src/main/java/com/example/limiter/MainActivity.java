package com.example.limiter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.limiter.adminFunction.AdminLoginActivity;
import com.example.limiter.ui.register.RegisterFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedData.setUser();
        SharedData.setCurrentVehicle();

        Button adminBtn=findViewById(R.id.adminBtn);
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(), AdminLoginActivity.class);
                  startActivity(intent);

//                Fragment fragment=new RegisterFragment();
//
//                FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container,fragment).commit();

            //    Vehicle.selectVehicle(MainActivity.this,0);

            }
        });


    }

    public void startLoginActivity(View view) {

//        Intent intent=new Intent(getApplicationContext(),sidebar.class);
//        startActivity(intent);

//        RegisterFragment registerFragment=new RegisterFragment();
//        registerFragment.registerVehicle();

        Intent intent=new Intent(this,Login.class);
        startActivity(intent);



       // Vehicle.selectVehicle(MainActivity.this);
    }



}