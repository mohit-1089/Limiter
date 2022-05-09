package com.example.limiter.adminFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.limiter.DBServices;
import com.example.limiter.R;

import java.sql.Connection;
import java.sql.Statement;

public class AddSlotActivity extends AppCompatActivity {
    Button btnSubmitSlot;
    EditText editTextNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_slot);
        btnSubmitSlot=findViewById(R.id.btnSubmitSlot);
        editTextNumber=findViewById(R.id.editTextNumber);
        btnSubmitSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i= Integer.parseInt(editTextNumber.getText().toString());
                if(i<=8)
                {
                    Toast.makeText(AddSlotActivity.this, "Slot already exists", Toast.LENGTH_SHORT).show();
                }
                else if(i>=25)
                {
                    Toast.makeText(AddSlotActivity.this, "No such slots", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        // ResultSet rs;
                        String query="insert INTO limiter.parking_slot (s_id,going_to_be_occupied,occupied) values("+i+",'false','false')";
                        Connection conn= DBServices.openDB();
                        Statement st=conn.createStatement();

                        int res= st.executeUpdate(query);
                        DBServices.closeDB(conn);
                        Toast.makeText(AddSlotActivity.this, "Slot Added", Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}