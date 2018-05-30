package com.example.lukas.projekt2traintracker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Train {
    public String typeNumber;
    public String depTime;
    public String arrTime;
    public String departure;
    public String destination;

    Train(String typeNumber, String depTime, String arrTime, String departure, String destination){
        this.typeNumber = typeNumber;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.departure = departure;
        this.destination = destination;
    }

    public String getDepTime() {
        return depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getTypeNumber() {
        return typeNumber;
    }

    public String getFormattedDep(){
        String formattedDate;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            outputFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
            Date date = inputFormat.parse(getDepTime());
            formattedDate = outputFormat.format(date);

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return formattedDate;
    }

    public String getFormattedArr(){
        String formattedDate;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            outputFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
            Date date = inputFormat.parse(getArrTime());
            formattedDate = outputFormat.format(date);

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return formattedDate;
    }

    public String getDataString(){
        String data = this.typeNumber + " \n" + this.departure + " " + getFormattedDep() + " \n"+destination+" "+getFormattedArr();
        return data;
    }
}
