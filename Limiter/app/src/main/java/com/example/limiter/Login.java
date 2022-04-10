package com.example.limiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    Button login;
    EditText eml,pswd;
    ProgressBar progressBar;
    TextView tvForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login =  findViewById(R.id.loginBtn);
        eml = findViewById(R.id.email);
        pswd =  findViewById(R.id.password);
        progressBar=findViewById(R.id.pbLogin);
        progressBar.setVisibility(View.GONE);
        tvForgot=findViewById(R.id.tvForgotPassword);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=eml.getText().toString().trim();
                String password=pswd.getText().toString();

                User u=DBServices.checkLogin(email);

                if(u==null)
                    Toast.makeText(getApplicationContext(),"Email doesn't exists",Toast.LENGTH_SHORT).show();
                else
                {
                    if(u.password.equals(password))
                    {
                        SharedData.globalUser=u;
                        Toast.makeText(getApplicationContext(),"Welcome "+u.first_name,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),sidebar.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=eml.getText().toString().trim();
                String password=pswd.getText().toString();

                if(email.length()==0)
                    Toast.makeText(getApplicationContext(),"Email required",Toast.LENGTH_SHORT).show();

                else if(DBServices.checkEmail(email))
                {
                    SharedData.globalUser.email=email;
                    Intent intent =new Intent(getApplicationContext(),Otp.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Email doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void startSignUpActivity(View view) throws SQLException {


        Intent intent=new Intent(this, SignUp.class);
        startActivity(intent);


    }

}