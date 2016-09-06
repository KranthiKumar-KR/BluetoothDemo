package com.example.kranthi.bluetoothdemo;





import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter BA;
    BroadcastReceiver broadcastReceiver;
    Button turnOn, turnOff, startVisibility, showPaired;
    ListView deviceList;
    ArrayAdapter arrayAdapter;

    public void turnBtOn(View view) {
        BA.enable();
        Toast.makeText(getApplicationContext(), "Bluetooth Turned On", Toast.LENGTH_LONG).show();
    }

    public void turnBtOff(View view) {

        if (BA.isEnabled()) {
            BA.disable();
            Toast.makeText(getApplicationContext(), "Bluetooth turned Off", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth is not Turned On", Toast.LENGTH_LONG).show();
        }
    }

    public void searchForDevices(View view) {
        if (BA.isEnabled()) {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth is not Turned On", Toast.LENGTH_LONG).show();
        }

    }

    public void showPairedDevices(View view) {
        if (BA.isEnabled()) {
            Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
            ArrayList pairedDevicelist = new ArrayList();

            for (BluetoothDevice bluetoothDevice : pairedDevices) {
                pairedDevicelist.add(bluetoothDevice.getName());
            }

            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pairedDevicelist);
            deviceList.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth is not Turned On", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turnOn = (Button) findViewById(R.id.on);
        turnOff = (Button) findViewById(R.id.off);
        startVisibility = (Button) findViewById(R.id.enable);
        showPaired = (Button) findViewById(R.id.paired);
        deviceList = (ListView) findViewById(R.id.listView);
        BA = BluetoothAdapter.getDefaultAdapter();


    }
}
