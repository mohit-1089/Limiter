package com.example.limiter.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.limiter.DBServices;
import com.example.limiter.SharedData;
import com.example.limiter.Timer;
import com.example.limiter.databinding.FragmentHomeBinding;
import com.example.limiter.ui.parking.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    int id;
    int uid= SharedData.globalUser.uid;
    ProgressBar progressBar ;
    Button btnIncrease ;
    Button btnLeave;
    boolean carParked=false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = binding.progressBar;
        btnIncrease = binding.btnIncrease;
        btnLeave = binding.btnLeave;
        try {
            ResultSet rs;
            String query = "select v_id,s_id from limiter.transactions where complete = 'false' and uid = "+uid;
            Connection conn = DBServices.openDB();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            if(!rs.next())
            {
                id=-1;
            }
            else
            {
                id=rs.getInt("s_id");
                carParked=true;
            }

            System.out.println(id);
            DBServices.closeDB(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        update();
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(carParked) {
                    Intent myIntent = new Intent(getActivity(), Timer.class);
                    myIntent.putExtra("id", id);
                    startActivity(myIntent);
                    update();
                }
                else{
                    Toast.makeText(getActivity(),"Currently your no Car is Parked",Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(carParked) {
                    try {
                        carParked=false;
                        String upQuery1 = "update limiter.parking_slot set occupied = 'false',start_time=null,tot_time=null where s_id=" + id;
                        String upQuery2 = "update limiter.transactions set complete = 'true' where s_id=" + id;
                        Connection con = DBServices.openDB();
                        Statement st1 = con.createStatement();
                        Statement st2 = con.createStatement();
                        int res1 = st1.executeUpdate(upQuery1);
                        int res2 = st2.executeUpdate(upQuery2);
                        ParkingFragment.initialise();
                        DBServices.closeDB(con);
                        Toast.makeText(getActivity(),"Car unparked Successfully",Toast.LENGTH_SHORT).show();
                        progressBar.setProgress(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(getActivity(),"Currently no car is parked",Toast.LENGTH_SHORT).show();

                }
            }
        });

        return root;
    }
    public void update() {
        if (id == -1) {
            Toast.makeText(getActivity(),"No Car Parked",Toast.LENGTH_SHORT).show();
        } else {
            ParkingFragment.initialise();
            int maxProgress = ParkingFragment.getTotal(id);
            int currProgress = ParkingFragment.getRemSecThroughId(id);
           //int currProgress=300;
            progressBar.setMax(maxProgress);
            progressBar.setProgress(currProgress);
            runProgress(currProgress, id);

            // }
        }
    }
    public void runProgress(int currPro,int id)
    {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(currPro<=0)
                {
                    sendNotification(SharedData.globalUser.email);
                }
                else {
                    int val = currPro - 60;
                    progressBar.setProgress(val);
                    runProgress(val, id);
                }
            }
            //}
        };
        handler.postDelayed(runnable,60*1000);
    }

    private void sendNotification(String email)
    {
        String sEmail="projectlimiter@gmail.com",sPassword="limiter@123";

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
            message.setSubject("Timer Ended. Vacate space or Increase Timer");

            message.setText("Timer for your parking has ended. Kindly vacate the space or Increase the timer");
            Toast.makeText(getActivity(),"Timer for your parking has ended. Kindly vacate the space " +
                    "or Increase the timer",Toast.LENGTH_LONG).show();
            Transport.send(message);


        }
        catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}