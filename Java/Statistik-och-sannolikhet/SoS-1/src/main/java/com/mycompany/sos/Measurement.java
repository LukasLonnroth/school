/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sos;

/**
 *
 * @author Lukas
 */
public class Measurement {
    private int year;
    private int month;
    private float temp;
    private float wind;
    private String timestamp;
    
    public Measurement(int y, int m, float t, float w,String ts){
        year = y;
        month = m;
        temp = t;
        wind = w;
        timestamp = ts;
    }
    
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public float getTemp(){
        return temp;
    }
    public float getWind(){
        return wind;
    }
    public String getTimestamp(){
        return timestamp;
    }
    
   
}
