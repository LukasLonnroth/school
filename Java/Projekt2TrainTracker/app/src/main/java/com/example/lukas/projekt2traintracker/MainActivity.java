package com.example.lukas.projekt2traintracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGo(View v) throws ExecutionException, InterruptedException {
        EditText et = findViewById(R.id.startCity);
        String startCity = et.getText().toString();
        et = findViewById(R.id.destCity);
        String destCity = et.getText().toString();
        et =findViewById(R.id.date);
        String date = et.getText().toString();

        String[] parameters = {startCity, destCity, date};

        ArrayList<Train> trains = new Digitraffic().execute(parameters).get();
        ArrayList<String> trainData = new ArrayList<>();

        for (int i = 0; i < trains.size(); i++){
            trainData.add(trains.get(i).getDataString());
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, trainData);
        lv = findViewById(R.id.listView);
        lv.setAdapter(itemsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("train", lv.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }
}
