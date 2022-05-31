package com.example.tiler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class CalculatedActivity extends AppCompatActivity {

    // Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);
        //Grabbing values from main Activity
        Intent intent = getIntent();
        HashMap<String, Integer> inputs = (HashMap) intent.getSerializableExtra("data");
        DataWrapper dw = (DataWrapper)getIntent().getSerializableExtra("obstacles");

        boolean ten_percent = intent.getExtras().getBoolean("ten_percent");
        // Calculating wall and tile area
        int wall_area = inputs.get("wall_length_int") * inputs.get("wall_height_int");
        int tile_area = inputs.get("tile_length_int") * inputs.get("tile_height_int");
        int num_tiles;

        if (ten_percent) {
            num_tiles = (int) Math.ceil((float) wall_area / tile_area * 1.1);
        } else {
            num_tiles = (int) Math.ceil((float) wall_area / tile_area);
        }

        float wall_area_value = (float) (wall_area / 1000000.0);
        float tile_area_value = (float) (tile_area / 1000000.0);
        int tiles_num_val = num_tiles;

        ((TextView) findViewById(R.id.wall_area_value)).setText(String.valueOf(wall_area_value));
        ((TextView) findViewById(R.id.tile_area_value)).setText(String.valueOf(tile_area_value));
        ((TextView) findViewById(R.id.tiles_num_val)).setText(String.valueOf(tiles_num_val));
        /*
        button = findViewById(R.id.button1);
        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(CalculatedActivity.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CalculatedActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
        */
    }
}


