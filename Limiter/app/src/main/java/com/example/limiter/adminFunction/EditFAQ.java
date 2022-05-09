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

public class EditFAQ extends AppCompatActivity {
    EditText etQues,etAns;
    Button btnSubmitFaq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_faq);
        etQues = findViewById(R.id.etQues);
        etAns = findViewById(R.id.etAns);
        btnSubmitFaq = findViewById(R.id.btnSubmitFaq);
        btnSubmitFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques,ans;
                ques=etQues.getText().toString();
                ans=etAns.getText().toString();
                try {
                    // ResultSet rs;
                    String query="insert into limiter.faq (ques,ans) values('"+ques+"','"+ans+"')";
                    System.out.println(query);
                    Connection conn= DBServices.openDB();
                    Statement st=conn.createStatement();

                    int res= st.executeUpdate(query);
                    DBServices.closeDB(conn);

                    Toast.makeText(EditFAQ.this, "FAQ Added successfully ", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}