package com.example.limiter.ui.parking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.limiter.DBServices;
import com.example.limiter.Iot;
import com.example.limiter.MainActivity;
import com.example.limiter.SharedData;
import com.example.limiter.Timer;
import com.example.limiter.Vehicle;
import com.example.limiter.databinding.FragmentParkingBinding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ParkingFragment extends Fragment {

    private FragmentParkingBinding binding;
    static Button button[]= new Button[25];
    static String going_to_be_occupied[]=new String[25];
    static String occupied[]=new String[25];
    static String tot_Time[]=new String[25];
    static String start_Time[]=new String[25];
    static Integer remSec[]=new Integer[25];
    static Integer hour[]=new Integer[25];
    static Integer min[]=new Integer[25];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentParkingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        button[1] = binding.button1;
        button[2] = binding.button2;
        button[3] = binding.button3;
        button[4] = binding.button4;
        button[5] = binding.button5;
        button[6] = binding.button6;
        button[7] = binding.button7;
        button[8] = binding.button8;
        button[9] = binding.button9;
        button[10] = binding.button10;
        button[11] = binding.button11;
        button[12] = binding.button12;
        button[13] = binding.button13;
        button[14] = binding.button14;
        button[15] = binding.button15;
        button[16] = binding.button16;
        button[17] = binding.button17;
        button[18] = binding.button18;
        button[19] = binding.button19;
        button[20] = binding.button20;
        button[21] = binding.button21;
        button[22] = binding.button22;
        button[23] = binding.button23;
        button[24] = binding.button24;
        for(int i=0;i<24;i++)
        {
            hour[i]=0;
            min[i]=0;
        }
        initialiseStart();
        button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(1);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(2);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(3);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(4);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(5);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(6);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(7);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(8);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(9);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(10);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(11);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(12);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(13);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(14);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(15);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(16);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(17);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(18);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(19);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(20);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(21);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(22);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(23);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUid()) {
                    check(24);
                }
                else{
                    Toast.makeText(getActivity(),"You have already booked a slot",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //final TextView textView = binding.textGallery;

        return root;
    }
    public boolean checkUid()
    {
        boolean answer=false;
        try {
            ResultSet rs;
            String query = "select v_id,s_id from limiter.transactions where complete = 'false' and uid = "+SharedData.globalUser.uid;
            Connection conn = DBServices.openDB();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            if(!rs.next())
                answer=true;
            DBServices.closeDB(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
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
                    button[id].setText(display);
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
    public static void initialise() {
        String query = "select s_id,going_to_be_occupied,occupied,tot_time,start_time from limiter.parking_slot";
        //System.out.println(query);
        String goingToBeOccupied,occ;
        int id;
        Time totTime;
        Time startTime;
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
                //System.out.println(id+" "+going_to_be_occupied+" "+occupied);
                if(occ.equals("true"))
                {
                    startTime=rs.getTime("start_time");
                    totTime=rs.getTime("tot_time");
                    tot_Time[id]=totTime.toString();
                    start_Time[id]=startTime.toString();
                    Calendar cal= Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
                    Date curr = cal.getTime();
                    DateFormat date = new SimpleDateFormat("HH:mm:ss");
                    date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
                    int curTime=getInSec(date.format(curr));
                    int strt_time=getInSec(start_Time[id]);
                    int totalTime=getInSec(tot_Time[id]);
                    int rem_sec=totalTime-((curTime-strt_time+86400)%86400);
                    remSec[id]=rem_sec;
                    hour[id]=rem_sec/3600;
                    min[id]=(rem_sec%3600)/60;
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
    public static int getRemSecThroughId(int id)
    {
        int remSecond = remSec[id];
        return remSecond;
    }
    public static int getTotal(int id)
    {
        int totSecond = getInSec(tot_Time[id]);
        return totSecond;
    }
    public static void runTimer(int id)
    {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
               // for (int id = 1; id <= max; id++) {
                    if (occupied[id].equals("true")) {
                        button[id].setTextSize(11);
                        if (hour[id] < 0) {
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
    public void check(int id)
    {
            //System.out.println(id+" "+going_to_be_occupied+" "+occupied);
            if(occupied[id].equals("true"))
            {
                Toast.makeText(getActivity(),"Slot is preoccupied",Toast.LENGTH_SHORT).show();
            }
            else if(going_to_be_occupied[id].equals("true"))
            {
                Toast.makeText(getActivity(),"Slot is going to be occupied",Toast.LENGTH_SHORT).show();
            }
            else{
                try {
                    button[id].setBackgroundColor(Color.YELLOW);
                    button[id].setTextColor(Color.RED);
//                    String upQuery = "update limiter.parking_slot set going_to_be_occupied = 'true' where s_id=" + id;
//                    Connection con = DBServices.openDB();
//                    Statement st = con.createStatement();
//                    int res = st.executeUpdate(upQuery);
//                    DBServices.closeDB(con);
                    going_to_be_occupied[id]="true";
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int val = Iot.generate();
                            if (val == 0) {
                                try {
                                    going_to_be_occupied[id]="false";
                                    button[id].setBackgroundColor(Color.GREEN);
                                    button[id].setTextColor(Color.WHITE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    occupied[id]="true";
                                    going_to_be_occupied[id]="false";
                                    String upQuery1 = "update limiter.parking_slot set occupied = 'true' where s_id=" + id;
                                    Connection con = DBServices.openDB();
                                    Statement st1 = con.createStatement();
                                    int res1 = st1.executeUpdate(upQuery1);
                                    DBServices.closeDB(con);
                                    button[id].setBackgroundColor(Color.RED);
                                    button[id].setTextColor(Color.WHITE);

                                    Vehicle.selectVehicle(getActivity(),id);

//                                    if(SharedData.currentVehicle.vid!=-1) {
//                                        Intent myIntent = new Intent(getActivity(), Timer.class);
//                                        myIntent.putExtra("id", id);
//                                        startActivity(myIntent);
//                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                    }, 5 * 1000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

        }

//    public void selectVehicle(int id)
//    {
//        ResultSet res=null;
//        String vehicleId[];
//        try {
//            int uid= SharedData.globalUser.uid;
//           // String query = "select count(v_id) from limiter.vehicle where uid = "+uid;
//            Connection con = DBServices.openDB();
//            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//           // res = stmt.executeQuery(query);
//            //vehicleId = new String[res.getInt("count")];
//            DBServices.closeDB(con);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        View selectVehiclePopup = LayoutInflater.from(getActivity()).inflate(R.layout.select_vehicle_popup,null);
//        final PopupWindow selectVehiclePopupWindow = new PopupWindow(selectVehiclePopup, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        selectVehiclePopupWindow.showAsDropDown(selectVehiclePopup,0,0);
//    }
    public static void updateTime(String time,int id)
    {
        try {
            Calendar cal= Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
            Date curr = cal.getTime();
            DateFormat date = new SimpleDateFormat("HH:mm");
            date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            String curTime=date.format(curr);
            //System.out.println(curTime);
            int prevTime=hour[id]*3600+min[id]*60;
            int currTime=getInSec(time);
            int total=prevTime+currTime;
            String display=(Integer)total/3600 + ":"+ (Integer)((total%3600)/60);

            tot_Time[id] = display;
            String insertQuery="insert into limiter.transactions values("+ SharedData.globalUser.uid+","
                   +SharedData.currentVehicle.vid+","+id+",'false')";
            System.out.println(insertQuery);
            String query = "update limiter.parking_slot set start_time='"+curTime+"',tot_time='" + display + "' where s_id=" + id;
            Connection con = DBServices.openDB();
            Statement st1 = con.createStatement();
            Statement st2=con.createStatement();
            int res1 = st1.executeUpdate(query);
            int res= st2.executeUpdate(insertQuery);
            DBServices.closeDB(con);
            button[id].setText(display);
            button[id].setTextSize(11);
            initialise();
            runTimer(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}