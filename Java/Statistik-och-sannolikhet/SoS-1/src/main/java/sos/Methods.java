/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Lukas
 */
public class Methods {
    
     public float getMaxTemp(ArrayList<Measurement> weatherlist){
        ArrayList<Float> temps = new ArrayList();
        for (int i = 0; i < weatherlist.size(); i++){
            float itemp = weatherlist.get(i).getTemp();
            temps.add(i, itemp);
        }
        
        java.util.Collections.sort(temps);
        float maxTemp = temps.get(temps.size()-1);
        
        return maxTemp;
    }
    
    public float getMinTemp(ArrayList<Measurement> weatherlist){
        float[] temps = new float[weatherlist.size()];
        for (int i = 0; i < temps.length; i++){
            temps[i] = weatherlist.get(i).getTemp();
        }
        
        Arrays.sort(temps);
        float minTemp = temps[0];
        
        return minTemp;
    }
    
    public float getAvgTemp(ArrayList<Measurement> weatherlist){
        float sum = 0;
        
        for(int i = 0; i < weatherlist.size(); i++)
        {
              sum += weatherlist.get(i).getTemp();
        }
        
        float avgTemp = sum / weatherlist.size();
        
        
        return avgTemp;
    }
    
    public String getMaxWind(ArrayList<Measurement> weatherlist){
        int index =0;
        ArrayList<Float> winds = new ArrayList();
        for (int i = 0; i < weatherlist.size(); i++){
            float iwind = weatherlist.get(i).getWind();
            winds.add(i, iwind);
        }
        
        for (int i = 0; i < winds.size(); i++){
            float indexwind = weatherlist.get(i).getWind();
            if (indexwind == Collections.max(winds)){
                index = i;
            }
        }
        
        //java.util.Collections.sort(winds);
        String timestamp = weatherlist.get(index).getTimestamp();
        float maxWind = Collections.max(winds);
        String maxWindTime = maxWind + "m/s measured at "+timestamp;
        
        
        return maxWindTime;
    }
    
    public float getMinWind(ArrayList<Measurement> weatherlist){
        float[] winds = new float[weatherlist.size()];
        for (int i = 0; i < winds.length; i++){
            winds[i] = weatherlist.get(i).getWind();
        }
        
        Arrays.sort(winds);
        float minWind = winds[0];
        
        return minWind;
    }
    
    public float getAvgWind(ArrayList<Measurement> weatherlist){
        float sum = 0;
        
        for(int i = 0; i < weatherlist.size(); i++)
        {
              sum += weatherlist.get(i).getWind();
        }
        
        float avgWind = sum / weatherlist.size();
        
        
        return avgWind;
    }
    
    public String getYearlyAvg(ArrayList<Measurement> weatherlist){
        float year1 = 0;
        float year2 = 0;
        float year3 = 0;
        float year4 = 0;
        float year5 = 0;
        float year6 = 0;
        float year7 = 0;
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        int counter5 = 0;
        int counter6 = 0;
        int counter7 = 0;
        
        
        for (int i = 0; i < weatherlist.size(); i++){
           int thisYear = weatherlist.get(i).getYear();

            switch (thisYear) {
                case 2009:
                    year1 += weatherlist.get(i).getTemp();
                    counter1++;
                    break;
                case 2010:
                    year2 += weatherlist.get(i).getTemp();
                    counter2++;
                    break;
                case 2011:
                    year3 += weatherlist.get(i).getTemp();
                    counter3++;
                    break;
                case 2012:
                    year4 += weatherlist.get(i).getTemp();
                    counter4++;
                    break;
                case 2013:
                    year5 += weatherlist.get(i).getTemp();
                    counter5++;
                    break;
                case 2014:
                    year6 += weatherlist.get(i).getTemp();
                    counter6++;
                    break;
                case 2015:
                    year7 += weatherlist.get(i).getTemp();
                    counter7++;
                    break;
                default:
                    break;
            }
        }
        
        String yearlyAvg = "Yearly average temperature:\n2009: "+year1/counter1+"\u00b0C"+
                "\n2010: "+year2/counter2+"\u00b0C"+ 
                "\n2011: "+year3/counter3+"\u00b0C"+
                "\n2012: "+year4/counter4+"\u00b0C"+
                "\n2013: "+year5/counter5+"\u00b0C"+
                "\n2014: "+year6/counter6+"\u00b0C"+
                "\n2015: "+year7/counter7+"\u00b0C";
        
        return yearlyAvg;
    }
    
    public void getMonthlyStdev(ArrayList<Measurement> weatherlist){
        
        ArrayList<Measurement> yearlyWeatherlist = new ArrayList();
        String monthlyStDev;
        Scanner in = new Scanner(System.in);
        System.out.println("What year would you like to know the monthly standard deviation of?");
        int yearin = in.nextInt();
        
        for (int i = 0; i < weatherlist.size(); i++){
            if(yearin == weatherlist.get(i).getYear()){
                yearlyWeatherlist.add(weatherlist.get(i));
            }
        }
        
        
        for (int i = 0; i < 12; i++){
            ArrayList<Float> intemp = new ArrayList();
            
            for (int j = 0; j < yearlyWeatherlist.size(); j++){ 
                if(yearlyWeatherlist.get(j).getMonth() == i+1){
                    intemp.add(yearlyWeatherlist.get(j).getTemp());
                }
            }
            int sum = 0;
            for (int l = 0; l < intemp.size(); l++){
                sum += intemp.get(l);
            }
            float monthlyAvg = sum/intemp.size();
            
            float sumQuad = 0;
            
            for(int k = 0; k < intemp.size(); k++){
                    sumQuad += ((intemp.get(k) - monthlyAvg)*(intemp.get(k) - monthlyAvg));
                }
        
            float stdev = (float) Math.sqrt(sumQuad/intemp.size());
            int month = i+1;
                    
            System.out.println("Standard deviation for "+month+"/"+yearin+" is: "+stdev+"\u00b0C");
            
        }
        
    }
}
