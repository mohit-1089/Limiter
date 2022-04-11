package com.example.limiter;



public class SharedData {

    public static User globalUser;

    static void setUser()
    {
        globalUser =new User();
    }

}
