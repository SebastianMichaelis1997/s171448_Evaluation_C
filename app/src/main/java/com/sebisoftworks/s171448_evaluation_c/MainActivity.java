package com.sebisoftworks.s171448_evaluation_c;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mData;
    List<Sensor> sensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.list_name);
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensors = sm.getSensorList(Sensor.TYPE_ALL);
        mData = new ArrayList<String>();
        for (int i = 0; i < sensors.size(); i++) {
            mData.add(sensors.get(i).getName());
        }

        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(mArrayAdapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Sensor sensor = sensors.get(position);
        Toast.makeText(this, "Detailed Activity für Sensor " + sensor.getName(), Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, DetailedActivity.class);
        i.putExtra("sensorName", sensor.getName());
        this.startActivity(i);
    }
}
