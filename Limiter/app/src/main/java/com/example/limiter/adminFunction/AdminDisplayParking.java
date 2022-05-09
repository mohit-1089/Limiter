package com.example.limiter.adminFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.example.limiter.DBServices;
import com.example.limiter.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AdminDisplayParking extends AppCompatActivity {
    static Button button[]= new Button[25];
    static String going_to_be_occupied[]=new String[25];
    static String occupied[]=new String[25];
    static String tot_Time[]=new String[25];
    static String start_Time[]=new String[25];
    static Integer remSec[]=new Integer[25];
    static Integer hour[]=new Integer[25];
    static Integer min[]=new Integer[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display_parking);
        button[1] = findViewById(R.id.button1);
        button[2] = findViewById(R.id.button2);
        button[3] = findViewById(R.id.button3);
        button[4] = findViewById(R.id.button4);
        button[5] = findViewById(R.id.button5);
        button[6] = findViewById(R.id.button6);
        button[7] = findViewById(R.id.button7);
        button[8] = findViewById(R.id.button8);
        button[9] = findViewById(R.id.button9);
        button[10] = findViewById(R.id.button10);
        button[11] = findViewById(R.id.button11);
        button[12] = findViewById(R.id.button12);
        button[13] = findViewById(R.id.button13);
        button[14] = findViewById(R.id.button14);
        button[15] = findViewById(R.id.button15);
        button[16] = findViewById(R.id.button16);
        button[17] = findViewById(R.id.button17);
        button[18] = findViewById(R.id.button18);
        button[19] = findViewById(R.id.button19);
        button[20] = findViewById(R.id.button20);
        button[21] = findViewById(R.id.button21);
        button[22] = findViewById(R.id.button22);
        button[23] = findViewById(R.id.button23);
        button[24] = findViewById(R.id.button24);
        for(int i=0;i<24;i++)
        {
            hour[i]=0;
            min[i]=0;
        }
        initialiseStart();
    }
    public static void initialiseStart() {
        String query = "select s_id,going_to_be_occupied,occupied,tot_time,start_time from limiter.parking_slot";
        //System.out.println(query);
        String goingToBeOccupied,occ;
        int id;
        Time totTime;
        Time startTime;
        int maximum=-1;
        try {
            Connection conn = DBServices.openDB();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next())
            {
                id=rs.getInt("s_id");
                System.out.println(id);
                goingToBeOccupied=rs.getString("going_to_be_occupied");
                occ=rs.getString("occupied");
                going_to_be_occupied[id]=goingToBeOccupied;
                occupied[id]=occ;
                maximum= Math.max(maximum,id);
                //System.out.println(id+" "+going_to_be_occupied+" "+occupied);
                if(occ.equals("true"))
                {
                    startTime=rs.getTime("start_time");
                    totTime=rs.getTime("tot_time");
                    tot_Time[id]=totTime.toString();
                    start_Time[id]=startTime.toString();
                    button[id].setEnabled(true);
                    button[id].setBackgroundColor(Color.RED);
                    button[id].setTextColor(Color.WHITE);
                    Calendar cal= Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
                    Date curr = cal.getTime();
                    DateFormat date = new SimpleDateFormat("HH:mm:ss");
                    date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
                    int curTime=getInSec(date.format(curr));
                    button[id].setTextSize(11);
                    int strt_time=getInSec(start_Time[id]);
                    int totalTime=getInSec(tot_Time[id]);
                    int rem_sec=totalTime-((curTime-strt_time+86400)%86400);
                    remSec[id]=rem_sec;
                    hour[id]=rem_sec/3600;
                    min[id]=(rem_sec%3600)/60;
                    String display="";
                    if(hour[id]<10)
                        display = "0"+hour[id]+":";
                    else
                        display = hour[id]+":";

                    if(min[id]<10)
                        display += "0"+min[id];
                    else
                        display+=min[id];
                    if(remSec[id]<=0)
                    {
                        button[id].setText("00:00");
                    }
                    else {
                        button[id].setText(display);
                    }
                    runTimer(id);

                }
                else if(goingToBeOccupied.equals("true"))
                {
                    button[id].setEnabled(true);
                    button[id].setBackgroundColor(Color.YELLOW);
                    button[id].setTextColor(Color.RED);
                }
                else if(goingToBeOccupied.equals("false")) {
                    button[id].setEnabled(true);
                    button[id].setBackgroundColor(Color.GREEN);
                    button[id].setTextColor(Color.WHITE);
                    button[id].setText("P");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static int getInSec(String t)
    {
        String units[]=t.split(":");
        int hour=Integer.parseInt(units[0]);
        int min=Integer.parseInt(units[1]);
        int ans= hour*3600 + min*60;
        return ans;

    }
    public static void runTimer(int id)
    {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // for (int id = 1; id <= max; id++) {
                if (occupied[id].equals("true")) {
                    remSec[id]=hour[id]*3600 + min[id]*60;
                    button[id].setTextSize(11);
                    if (remSec[id] <= 0) {
                        button[id].setText("00:00");
                    } else if (min[id] == 0) {
                        min[id] = 59;
                        hour[id]--;
                        String display = "";
                        if (hour[id] < 10)
                            display = "0" + hour[id] + ":";
                        else
                            display = hour[id] + ":";

                        if (min[id] < 10)
                            display += "0" + min[id];
                        else
                            display += min[id];
                        button[id].setText(display);
                        runTimer(id);
                    } else {
                        min[id]--;
                        String display = "";
                        if (hour[id] < 10)
                            display = "0" + hour[id] + ":";
                        else
                            display = hour[id] + ":";

                        if (min[id] < 10)
                            display += "0" + min[id];
                        else
                            display += min[id];
                        button[id].setText(display);
                        runTimer(id);
                    }

                }
            }
            //}
        };
        handler.postDelayed(runnable,60*1000);
    }
    }