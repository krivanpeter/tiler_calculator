package com.pk.tiler_buddy.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pk.tiler_buddy.CalculatedValuesWrapper;
import com.pk.tiler_buddy.Obstacle;
import com.pk.tiler_buddy.R;
import com.pk.tiler_buddy.Wall;

import java.util.List;

public class CalculatedActivity extends AppCompatActivity {

    // Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);
        //Grabbing values from main Activity
        CalculatedValuesWrapper calculatedValuesWrapper = (CalculatedValuesWrapper) getIntent().getSerializableExtra("data");

        ((TextView) findViewById(R.id.to_be_tiled_are_value)).setText(String.valueOf(calculatedValuesWrapper.getToBeTiledArea()));
        ((TextView) findViewById(R.id.tiles_num_val)).setText(String.valueOf(calculatedValuesWrapper.getNumOfTiles()));
        List<Obstacle> obstacles = calculatedValuesWrapper.getObstacles();
        Wall wall = calculatedValuesWrapper.getWall();
        WallDimensions wallDimensions = calculatedValuesWrapper.getWallDimensions();
    }
}
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


