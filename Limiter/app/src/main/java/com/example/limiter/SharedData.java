package com.example.limiter;



public class SharedData {

    public static User globalUser;
    public static Vehicle currentVehicle;

    public SharedData()
    {
        globalUser=new User();
        currentVehicle=new Vehicle();
    }

    static void setUser()
    {
        globalUser =new User();
    }
    static void setCurrentVehicle(){currentVehicle=new Vehicle();}

}
