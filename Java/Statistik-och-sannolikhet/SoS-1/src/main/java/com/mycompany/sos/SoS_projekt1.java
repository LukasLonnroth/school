/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Lukas
 */
public class SoS_projekt1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Skapar arraylist
        ArrayList<Measurement> weatherlist = new ArrayList();

        //filen weather.json, scannar in den till en string
        File weather = new File("weather.json");
        try {
            Scanner in = new Scanner(weather);
            StringBuilder jsonIn = new StringBuilder();

            while (in.hasNextLine()) {
                jsonIn.append(in.nextLine());
            }

            JSONParser parser = new JSONParser();

            JSONObject objWeather = (JSONObject) parser.parse(jsonIn.toString());

            //Gör en jsonarray och plockar ut de olika mätvärden
            JSONArray measurementsIn = (JSONArray) objWeather.get("measurements");

            for (int i = 0; i < measurementsIn.size(); i++) {
                JSONObject measurementIn = (JSONObject) measurementsIn.get(i);
                String timeIn = (String) measurementIn.get("time");
                String tempIn = (String) measurementIn.get("temp");
                String windIn = (String) measurementIn.get("wind");
                String humidIn = (String) measurementIn.get("humid");

                //System.out.print("Time of measurement: " + timeIn + " Temp: " + tempIn + " Windspeed: " + windIn + " Humidity: " + humidIn + "\n");
                
                String timeInTrim = timeIn.trim();
                String[] date = timeInTrim.split("-");
                String yearString = date[0];
                String monthString = date[1];
                float temp = Float.parseFloat(tempIn);
                float wind = Float.parseFloat(windIn);
                int year = Integer.parseInt(yearString);
                int month = Integer.parseInt(monthString);
                
                Measurement weatherIn = new Measurement(year, month, temp, wind, timeIn);
                
                weatherlist.add(weatherIn);
                
            }
            
            Methods met = new Methods();
            
            float maxTemp = met.getMaxTemp(weatherlist);
            float minTemp = met.getMinTemp(weatherlist);
            float avgTemp = met.getAvgTemp(weatherlist);
            String maxWind = met.getMaxWind(weatherlist);
            float minWind = met.getMinWind(weatherlist);
            float avgWind = met.getAvgWind(weatherlist);
            String yearlyAvg = met.getYearlyAvg(weatherlist);
            
            System.out.println("Max temp: "+maxTemp+"\u00b0C Min temp: "+minTemp+"\u00b0C Average temp: "+avgTemp+"\u00b0C\nMax wind: "+maxWind+"\n"
            +yearlyAvg);
            
            met.getMonthlyStdev(weatherlist);

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (ParseException ex) {
            System.out.println(ex);
        }

    }

}
