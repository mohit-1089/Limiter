package com.example.limiter.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.limiter.R;
import com.example.limiter.SharedData;
import com.example.limiter.Vehicle;
import com.example.limiter.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    AutoCompleteTextView autoCompVehicleType;
    ArrayAdapter<String>adapterItems;
    String vehicleTypes[]={"Bike","Car","Bus"};

    EditText etVehicleNo,etModelName;
    Button btnRegisVehicle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegisterViewModel registerViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        registerVehicle();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void registerVehicle()
    {

        autoCompVehicleType = binding.autoCompVehicleNo;
        adapterItems=new ArrayAdapter<String>(getContext(),R.layout.vehicle_type,vehicleTypes);

        autoCompVehicleType.setAdapter(adapterItems);

        autoCompVehicleType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(),item,Toast.LENGTH_SHORT).show();
            }
        });

        btnRegisVehicle= binding.btnRegisVehicle;
        btnRegisVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etVehicleNo=binding.etVehicleNo;
                etModelName=binding.etModelName;
                autoCompVehicleType = binding.autoCompVehicleNo;

                int uid= SharedData.globalUser.uid;
                String vehicleNo=etVehicleNo.getText().toString();
                String vehicleType= autoCompVehicleType.getText().toString();
                String modelName=etModelName.getText().toString();


                if(vehicleNo.length()==0)
                {
                    Toast.makeText(getContext(),"Vehicle number is required !",Toast.LENGTH_SHORT).show();
                }
                else if(vehicleType.length()==0)
                {
                    Toast.makeText(getContext(),"Vehicle type is required !",Toast.LENGTH_SHORT).show();
                }
                else if(modelName.length()==0)
                {
                    Toast.makeText(getContext(),"Model name is required !",Toast.LENGTH_SHORT).show();

                }
                else{

                    Vehicle v=new Vehicle(-1,uid,vehicleNo,vehicleType,modelName);
                    if(v.addVehicle(v))
                    {
                        Toast.makeText(getContext(),v.getModel()+" is registered successfully ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(),"Error in vehicle registration....",Toast.LENGTH_SHORT).show();
                    }
                }

                System.out.println(uid+" "+vehicleNo+"\n"+vehicleType+"\n"+modelName);
                //v.addVehicle(v);
            }
        });



    }
}