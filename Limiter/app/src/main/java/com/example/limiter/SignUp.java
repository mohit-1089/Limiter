package com.example.limiter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SignUp extends AppCompatActivity {
    Button signUp;
    EditText fname,lname,eml,mNo,pswd,cpswd;

    //User currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        signUp=(Button) findViewById(R.id.signUpBtn);
        fname=(EditText) findViewById(R.id.firstName);
        lname=(EditText) findViewById(R.id.lastName);
        eml= (EditText) findViewById(R.id.email);
        pswd= (EditText) findViewById(R.id.password);
        mNo= (EditText) findViewById(R.id.mobileNo);
        cpswd= (EditText) findViewById(R.id.conf_password);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String first_name,last_name,email,mobileNo,password,confirm_password;
                first_name=fname.getText().toString().trim();
                last_name=lname.getText().toString().trim();
                email=eml.getText().toString().trim();
                mobileNo=mNo.getText().toString();
                password=pswd.getText().toString();
                confirm_password=cpswd.getText().toString();

                if(first_name.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"First Name is Required",Toast.LENGTH_SHORT).show();
                }
                else if (last_name.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Last Name is Required",Toast.LENGTH_SHORT).show();
                }
                else if(email.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Email is Required",Toast.LENGTH_SHORT).show();
                }
                else if(mobileNo.length()!=10)
                {
                    Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(confirm_password))
                {
                    Toast.makeText(getApplicationContext(),"Confirm Password doesn't match",Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<6)
                {
                    Toast.makeText(getApplicationContext(),"Password is Weak",Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        User u= new User(-1,first_name,last_name,email,mobileNo,password);
                        int res =DBServices.addUser(u);
                        if(res>0)
                        {
                            Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();

                        }

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Registration Unsuccessful",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }


            }
        });





    }
}