package com.example.lukas.projekt2traintracker;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LiveTrack extends AsyncTask<String, Integer, LatLng> {
    @Override
    protected LatLng doInBackground(String... strings) {
        String train = strings[0].replace("IC", "");
        LatLng position = null;
        try {

            //Hämta datan från api
            URL url = new URL("https://rata.digitraffic.fi/api/v1/train-locations/latest/"+train);

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
            JSONObject nextLevel = topLevel.getJSONObject(0);
            JSONObject location = nextLevel.getJSONObject("location");
            JSONArray coordinates = location.getJSONArray("coordinates");
            Double lat = coordinates.getDouble(0);
            Double lng = coordinates.getDouble(1);

            System.out.println("TESTAR LIVETRACK: " + lat + lng);

            position = new LatLng(lng, lat);



        }catch (Exception e){
            e.printStackTrace();
        }
        return position;
    }
}
