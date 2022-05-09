package com.example.limiter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limiter.adapter.SelectVehicleRvAdapter;
import com.example.limiter.ui.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Vehicle {
    public int vid;
    int uid;
    String vehicleNo;
    String model;
    String type;

    public int getVid() {
        return vid;
    }

    public int getUid() {
        return uid;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public Vehicle()
    {
        this.vid = -1;
        this.uid = -1;
        this.vehicleNo = null;
        this.type = null;
        this.model = null;

    }

    public Vehicle(int vid, int uid, String vehicleNo, String model, String type)
    {
        this.vid = vid;
        this.uid = uid;
        this.vehicleNo = vehicleNo;
        this.type = type;
        this.model = model;

    }

    public boolean addVehicle(Vehicle v)
    {
        boolean flg;
        try {

            Connection conn=DBServices.openDB();

            String query="insert INTO limiter.vehicle (uid,vehicle_No,vehicle_Type,model_Name) values('"
                    +v.uid+"','" +v.vehicleNo+"','"+v.type+"','"
                    +v.model+"')";

            Statement st=conn.createStatement();

            int res= st.executeUpdate(query);

            if(res==1)
                  flg=true;
            else flg=false;
            DBServices.closeDB(conn);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            flg=false;
        }

        return flg;


    }

    public static ArrayList<Vehicle> getVehicleOfUser(int uid)
    {
        ArrayList<Vehicle>allVehicle=new ArrayList<>();

        String query="select v_id,vehicle_no,vehicle_type,model_name from limiter.vehicle" +
                " where uid="+uid;

        ResultSet rs;
        try {
            Connection conn = DBServices.openDB();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);

            while(rs.next())
            {
                Vehicle tmp_v=new Vehicle(rs.getInt("v_id"),SharedData.globalUser.uid,
                        rs.getString("vehicle_no"),
                        rs.getString("model_name"),
                        rs.getString("vehicle_type"));

                System.out.println(rs.getString("vehicle_no"));

                allVehicle.add(tmp_v);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



        return allVehicle;
    }

    public static void selectVehicle(Context context,int s_id)
    {
       Dialog dialog=new Dialog(context);

       dialog.setContentView(R.layout.select_and_register);
       dialog.show();

       RecyclerView recyclerView;
       recyclerView=dialog.findViewById(R.id.rvSelectVehicle);
       recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
       recyclerView.setHasFixedSize(true);


        ArrayList<Vehicle>v;
        v=Vehicle.getVehicleOfUser(SharedData.globalUser.uid);

        System.out.println(v.size());


        SelectVehicleRvAdapter selectVehicleRvAdapter=new SelectVehicleRvAdapter(v, dialog.getContext(),dialog,s_id);
        recyclerView.setAdapter(selectVehicleRvAdapter);

        Button btnRegisVehicle=dialog.findViewById(R.id.btnRegisVehicle);
        btnRegisVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String upQuery1 = "update limiter.parking_slot set occupied = 'false' where s_id=" + s_id;
                    Connection con = DBServices.openDB();
                    Statement st1 = con.createStatement();
                    int res1 = st1.executeUpdate(upQuery1);
                    DBServices.closeDB(con);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(context, VehicleRegisActivity.class);
                context.startActivity(intent);



            }
        });

    }





}
