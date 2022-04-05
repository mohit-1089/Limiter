package com.example.limiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    Button login;
    EditText eml,pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login =(Button) findViewById(R.id.loginBtn);
        eml= (EditText) findViewById(R.id.email);
        pswd= (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String email, password;
                    email = eml.getText().toString().trim();
                    password = pswd.getText().toString();
                    String query="select pass,first_name,last_name from limiter.user" +
                            " where email='"+email+"' ";
                    Connection conn = DBServices.openDB();
                    ResultSet rs;
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    rs=stmt.executeQuery(query);
                    if(!rs.next())
                    {
                        Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        try {
                            String dbpass, first_name, last_name;
                            first_name = rs.getString("first_name");
                            last_name = rs.getString("last_name");
                            dbpass = rs.getString("pass");
                            if (password.equals(dbpass)) {
                                String display = "Welcome "+first_name+" "+last_name;
                                Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public void startSignUpActivity(View view) throws SQLException {


        Intent intent=new Intent(this, SignUp.class);
        startActivity(intent);




    }

}