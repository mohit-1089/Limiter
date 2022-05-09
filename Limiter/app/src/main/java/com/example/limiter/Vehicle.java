package com.example.limiter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limiter.adapter.SelectVehicleRvAdapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Vehicle {
    int vid;
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

    public ArrayList<Vehicle> getVehicleOfUser(int uid)
    {
        ArrayList<Vehicle>allVehicle=new ArrayList<>();



        return allVehicle;
    }

    public static void selectVehicle(Context context)
    {
       Dialog dialog=new Dialog(context);

       dialog.setContentView(R.layout.select_and_register);
       dialog.show();

       RecyclerView recyclerView;
       recyclerView=dialog.findViewById(R.id.rvSelectVehicle);
       recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
       recyclerView.setHasFixedSize(true);






        ArrayList<Vehicle>v=new ArrayList<>();
        Vehicle v1=new Vehicle(-1,2,"UP78CV5992","Hero Honda","Bike");
        Vehicle v2=new Vehicle(-1,2,"HR45CV5992","Tata Safari","Car");
        Vehicle v3=new Vehicle(-1,2,"DL12MJ5992","Volvo","Bus");
        v.add(v1);
        v.add(v2);
        v.add(v3);
        v.add(v1);
        v.add(v2);
        v.add(v3);

        SelectVehicleRvAdapter selectVehicleRvAdapter=new SelectVehicleRvAdapter(v, dialog.getContext(),dialog);
        recyclerView.setAdapter(selectVehicleRvAdapter);

        Button btnRegisVehicle=dialog.findViewById(R.id.btnRegisVehicle);
        btnRegisVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, VehicleRegisActivity.class);
                context.startActivity(intent);

            }
        });

    }





}
