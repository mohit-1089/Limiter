package com.example.limiter.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limiter.R;
import com.example.limiter.SharedData;
import com.example.limiter.Vehicle;

import java.util.ArrayList;

public class SelectVehicleRvAdapter extends RecyclerView.Adapter<SelectVehicleRvAdapter.vehicleViewHolder>{

    private static ArrayList<Vehicle>v;
    private Context context;
    private static Dialog dialog;


    public SelectVehicleRvAdapter(ArrayList<Vehicle> v, Context context, Dialog dialog) {
        this.v = v;
        this.context = context;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public SelectVehicleRvAdapter.vehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vehicle_row, parent, false);
        return new vehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectVehicleRvAdapter.vehicleViewHolder holder, int position) {

        String vNo=v.get(position).getVehicleNo();
        String vModel=v.get(position).getModel();
        String vType=v.get(position).getType();

        holder.tvVehicleType.setText(vType);
        holder.tvVehicleModel.setText(vModel);
        holder.tvVehicleNo.setText(vNo);

    }

    @Override
    public int getItemCount() {
        return v.size();
    }

    public static class vehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvVehicleNo;
        TextView tvVehicleModel;
        TextView tvVehicleType;


        public vehicleViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            tvVehicleNo=itemView.findViewById(R.id.tvVehicleNo);
            tvVehicleModel=itemView.findViewById(R.id.tvVehicleModel);
            tvVehicleType=itemView.findViewById(R.id.tvVehicleType);


            // tvAns.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int pos=getAbsoluteAdapterPosition();
            SharedData.currentVehicle=v.get(pos);
            System.out.println(SharedData.currentVehicle.getModel());
            dialog.cancel();
        }
    }



}
