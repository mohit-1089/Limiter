package com.example.limiter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Otp extends AppCompatActivity {
    EditText r_otp;
    Button btnSubmit,btnResend;
    TextView tvEmail;
    int sOtp;
    
    // email from which otp to be send to the user
    String sEmail="EMAIL",sPassword="PASSWORD";

    private int sendOtp(String email)
    {
        int otpVal=(int)(Math.random()*1000000);

        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sEmail,sPassword);
            }
        });

        try {

            Message message=new MimeMessage(session);

            message.setFrom(new InternetAddress(sEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("OTP for Limiter ");

            message.setText(""+otpVal);

            Transport.send(message);

            //Toast.makeText(getApplicationContext(),""+otpVal,Toast.LENGTH_LONG).show();

        }
       catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return otpVal;
    }

   void matchOtp(int sOtp)
    {
        if(sOtp==Integer.parseInt(r_otp.getText().toString().trim()))
        {
            Intent intent=new Intent(getApplicationContext(),ChangePassword.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Incorrect OTP !",Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        r_otp=(EditText) findViewById(R.id.etOtp);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        btnResend=(Button)findViewById(R.id.btnResend);
        tvEmail=findViewById(R.id.tvEmail);
        tvEmail.setText("send to : "+SharedData.globalUser.email);

        Otp otp=new Otp();
        sOtp=sendOtp(SharedData.globalUser.email);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                matchOtp(sOtp);
            }
        });

        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               sOtp=sendOtp(SharedData.globalUser.email);

            }
        });

    }


}
