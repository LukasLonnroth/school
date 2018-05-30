package com.example.lukas.projekt2traintracker;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Digitraffic extends AsyncTask<String, Integer, ArrayList<Train>> {

    @Override
    protected ArrayList<Train> doInBackground(String... strings) {

        ArrayList<Train> trains = new ArrayList<>();

        try {

            //Hämta datan från api
            URL url = new URL("https://rata.digitraffic.fi/api/v1/live-trains/station/" + strings[0] + "/" +
                    strings[1] + "?departure_date=" + strings[2]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = reader.readLine()) != null) {
                builder.append(inputString);
            }


            //Parsea datan från apin
            JSONArray topLevel = new JSONArray(builder.toString());


            for(int i = 0; i < topLevel.length(); i++) {
                JSONObject train = topLevel.getJSONObject(i);
                String typeNumber = train.getString("trainType") + train.getInt("trainNumber");

                JSONArray timeTableRows = train.getJSONArray("timeTableRows");
                JSONObject firstRow = timeTableRows.getJSONObject(0);
                String departure = firstRow.getString("stationShortCode");
                String depTime = firstRow.getString("scheduledTime");

                JSONObject lastRow = timeTableRows.getJSONObject(timeTableRows.length()-1);
                String destination = lastRow.getString("stationShortCode");
                String arrTime = lastRow.getString("scheduledTime");

                Train thisTrain = new Train(typeNumber,depTime,arrTime,departure,destination);
                trains.add(thisTrain);

                System.out.println("TEST AV API CALL... train = " + typeNumber + ", "+departure+depTime+ ", "+ destination+arrTime);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return trains;
    }
}
