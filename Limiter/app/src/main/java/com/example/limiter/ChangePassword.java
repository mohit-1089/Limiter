package com.example.limiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePassword extends AppCompatActivity {

    EditText edNewPass,edConfNewPass;
    Button btnSubmitPass;

    void upDatePassword(String password)
    {
        try {

            String query="update user set pass='"+password+"'where email='"+SharedData.globalUser.email+"'";
            Connection conn=DBServices.openDB();
            Statement st=conn.createStatement();

            int res= st.executeUpdate(query);
            DBServices.closeDB(conn);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edNewPass=findViewById(R.id.edNewPass);
        edConfNewPass=findViewById(R.id.edConfNewPass);
        btnSubmitPass=findViewById(R.id.btnSubmitPass);


        btnSubmitPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newPass,confNewPass;
                newPass=edNewPass.getText().toString();
                confNewPass=edConfNewPass.getText().toString();

                if(newPass.equals(confNewPass))
                {
                    upDatePassword(newPass);
                    Toast.makeText(getApplicationContext(),"Password changed Successfully !",Toast.LENGTH_SHORT).show();


                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Password doesn't match !",Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}