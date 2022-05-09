package com.example.limiter.adminFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.limiter.DBServices;
import com.example.limiter.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminLoginActivity extends AppCompatActivity {

    Button adminLogin;
    EditText adminPassword,adminEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login2);
        adminLogin=findViewById(R.id.adminLogin);
        adminPassword=findViewById(R.id.adminPassword);
        adminEmail=findViewById(R.id.adminEmail);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass;
                email=adminEmail.getText().toString().trim().toLowerCase();
                pass=adminPassword.getText().toString();
                ResultSet rs;
                String query="select pass from limiter.admin" +
                        " where email='"+email+"' ";
                try {
                    Connection conn = DBServices.openDB();
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = stmt.executeQuery(query);
                    if(!rs.next())
                    {
                        Toast.makeText(getApplicationContext(),"Admin's email does not exists",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        String password=rs.getString("pass");
                        if(pass.equals(password))
                        {
                            Intent intent=new Intent(getApplicationContext(), AdminMainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong Password !",Toast.LENGTH_SHORT).show();

                        }
                        DBServices.closeDB(conn);

                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }
}