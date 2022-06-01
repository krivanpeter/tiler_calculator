package com.example.tiler_buddy.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiler.R;
import com.example.tiler_buddy.Calculator;
import com.example.tiler_buddy.DataWrapper;

public class CalculatedActivity extends AppCompatActivity {

    // Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);
        //Grabbing values from main Activity
        Intent intent = getIntent();
        WallDimensions wallDimensions = (WallDimensions) intent.getSerializableExtra("wallDimensions");
        TileDimensions tileDimensions = (TileDimensions) intent.getSerializableExtra("tileDimensions");
        DataWrapper obstacleWrapper = (DataWrapper) getIntent().getSerializableExtra("obstacles");
        boolean ten_percent = intent.getExtras().getBoolean("ten_percent");
        // Calculating wall and tile area
        int wall_area = Calculator.calculateWallArea(wallDimensions, Calculator.calculateObstacleArea(obstacleWrapper.getObstacles()));
        int tile_area = Calculator.calculateTileArea(tileDimensions);
        double num_tiles = Calculator.calculateTiles(ten_percent, wall_area, tile_area);

        double wall_area_value = Calculator.convertToMeter(wall_area);
        double tile_area_value = Calculator.convertToMeter(tile_area);

        ((TextView) findViewById(R.id.wall_area_value)).setText(String.valueOf(wall_area_value));
        ((TextView) findViewById(R.id.tile_area_value)).setText(String.valueOf(tile_area_value));
        ((TextView) findViewById(R.id.tiles_num_val)).setText(String.valueOf(num_tiles));
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


