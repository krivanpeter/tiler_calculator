package com.example.tiler;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class CalculatedActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);

        //Grabbing values from main Activity
        Intent intent = getIntent();
        ArrayList<Integer> inputs = intent.getIntegerArrayListExtra("data");
        DataWrapper dw = (DataWrapper)getIntent().getSerializableExtra("obstacles");

        boolean ten_percent = intent.getExtras().getBoolean("ten_percent");
        Log.d("all_data", String.valueOf(ten_percent));

        // Calculating wall and tile area
        int wall_area = inputs.get(0) * inputs.get(1);
        int tile_area = inputs.get(2) * inputs.get(3);
        int num_tiles;

        if (ten_percent) {
            num_tiles = (int) Math.ceil((float) wall_area / tile_area * 1.1);
        } else {
            num_tiles = (int) Math.ceil((float) wall_area / tile_area);
        }

        float wall_area_value = (float) (inputs.get(0) / 1000000.0);
        float tile_area_value = (float) (inputs.get(1) / 1000000.0);
        int tiles_num_val = inputs.get(2);

        ((TextView) findViewById(R.id.wall_area_value)).setText(String.valueOf(wall_area_value));
        ((TextView) findViewById(R.id.tile_area_value)).setText(String.valueOf(tile_area_value));
        ((TextView) findViewById(R.id.tiles_num_val)).setText(String.valueOf(tiles_num_val));

        button = findViewById(R.id.button1);
        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(CalculatedActivity.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CalculatedActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
    }
}


