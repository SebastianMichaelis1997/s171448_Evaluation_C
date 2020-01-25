package com.sebisoftworks.s171448_evaluation_c;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity implements Runnable, SensorEventListener {
    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mData;
    Handler mHandler;
    ListView listView;
    String name;
    ArrayList<Float> sensorData;
    int sek = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        name = intent.getStringExtra("sensorName");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        listView = findViewById(R.id.list_detail);
        mData = new ArrayList<>();
        sensorData = new ArrayList<>();
        sensorData.add(0.0F);
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < sensors.size(); i++) {
            if (sensors.get(i).getName().equals(name)) {
                sm.registerListener(this, sensors.get(i), SensorManager.SENSOR_DELAY_NORMAL);
                break;
            }
        }
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(mArrayAdapter);
        mHandler = new Handler();
        mHandler.postDelayed(this, 1000);
    }

    @Override
    public void run() {
        Float average = 0.0F;
        ArrayList<Float> vals = sensorData;
        sensorData = new ArrayList<>();
        if (vals.size() > 0) {
            for (int i = 0; i < vals.size(); i++) {
                average += vals.get(i);
            }
            Float Onaverage = average / ((0.0F + vals.size()));
            mData.add("Sec " + sek++ + ": " + Onaverage);
        } else {
            mData.add("Sec " + sek + ": No new Values in Second " + sek++);
        }
        mArrayAdapter.notifyDataSetChanged();
        mHandler.postDelayed(this, 1000);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorData.add(event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
