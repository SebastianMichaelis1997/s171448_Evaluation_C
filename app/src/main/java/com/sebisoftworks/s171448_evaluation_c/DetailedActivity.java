package com.sebisoftworks.s171448_evaluation_c;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailedActivity extends AppCompatActivity implements Runnable {
    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mData;
    Handler mHandler;
    ListView listView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        name = i.getStringExtra("sensorName");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        listView = findViewById(R.id.list_detail);
        mData = new ArrayList<>();
        mData.add("Sensor Data for "+name);
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(mArrayAdapter);
        mHandler = new Handler();
        mHandler.postDelayed(this, 1000);
    }

    @Override
    public void run() {
        mData.add("Sensor Data for "+name);
        mArrayAdapter.notifyDataSetChanged();
        mHandler.postDelayed(this, 1000);
    }
}
