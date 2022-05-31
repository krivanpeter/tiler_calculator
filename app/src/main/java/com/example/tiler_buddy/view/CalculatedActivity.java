package com.example.tiler_buddy.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiler.R;
import com.example.tiler_buddy.DataWrapper;
import com.example.tiler_buddy.Obstacle;

public class CalculatedActivity extends AppCompatActivity {

    // Button button;
    private static final double MM_TO_M_RATIO = 1_000_000;
    private static final double PERCENT_OF_WASTAGE = 1.1;

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
        int wall_area = wallDimensions.getLength() * wallDimensions.getHeight() - obstacleArea(obstacleWrapper);
        int tile_area = tileDimensions.getLength() * tileDimensions.getHeight();
        double num_tiles = calculateTiles(ten_percent, wall_area, tile_area);

        double wall_area_value = wall_area / MM_TO_M_RATIO;
        double tile_area_value = tile_area / MM_TO_M_RATIO;

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

    private double calculateTiles(boolean isWastage, float wall_area, int tile_area) {
        if (isWastage) {
            return Math.ceil(wall_area / tile_area * PERCENT_OF_WASTAGE);
        } else {
            return Math.ceil(wall_area / tile_area);
        }
    }

    public int obstacleArea(DataWrapper dw) {
        int all_area = 0;
        for (Obstacle obstacle : dw.getObstacles()) {
            int area = calculateObstacleArea(obstacle);
            all_area += area;
        }
        return all_area;
    }

    private int calculateObstacleArea(Obstacle obstacle) {
        return obstacle.getLength() * obstacle.getHeight();
    }
}


