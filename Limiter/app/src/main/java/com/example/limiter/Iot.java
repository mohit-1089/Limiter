package com.example.limiter;

public class Iot {
    public static int generate()
    {
        int val = (int)(Math.random()*1000000);
        val=val%2;
        return val;
    }
}
