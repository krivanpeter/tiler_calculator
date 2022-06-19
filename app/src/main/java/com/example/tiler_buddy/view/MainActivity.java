package com.example.tiler_buddy.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiler.R;
import com.example.tiler_buddy.CalculatedValuesWrapper;
import com.example.tiler_buddy.Calculator;
import com.example.tiler_buddy.Obstacle;
import com.example.tiler_buddy.ObstacleInputException;
import com.example.tiler_buddy.Tile;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout container = MainActivity.this.findViewById(R.id.container);

        TextView buttonObs = findViewById(R.id.button_obs);
        buttonObs.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                View obstacle = inflater.inflate(R.layout.obstacle_layout, container, false);
                inflater.inflate(R.layout.obstacle_layout, container, false);
                container.addView(obstacle);

                ImageButton deleteObs = obstacle.findViewById(R.id.delete_obs);
                deleteObs.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ViewManager) obstacle.getParent()).removeView(obstacle);
                    }
                });
            }
        });

        final Button buttonCalculate = findViewById(R.id.button_calc);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Wall Inputs
                EditText wallLengthIn = findViewById(R.id.wall_length_in);
                EditText wallHeightIn = findViewById(R.id.wall_height_in);
                // Tile Inputs
                EditText tileLengthIn = findViewById(R.id.tile_length_in);
                EditText tileHeightIn = findViewById(R.id.tile_height_in);
                // 10% wastage
                SwitchMaterial tenPercentIn = findViewById(R.id.ten_percent);

                try {
                    List<Obstacle> obstacles = parseInputsAsObstacles();
                    //Getting inputs as strings for checks
                    String wallLengthStr = wallLengthIn.getText().toString();
                    String wallHeightStr = wallHeightIn.getText().toString();
                    String tileLengthStr = tileLengthIn.getText().toString();
                    String tileHeightStr = tileHeightIn.getText().toString();
                    //Checking if inputs are empty
                    if (wallLengthStr.isEmpty() || wallHeightStr.isEmpty() || tileLengthStr.isEmpty() || tileHeightStr.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "You did not enter all numbers", Toast.LENGTH_SHORT).show();
                    } else {
                        // Getting All User Inputs
                        boolean tenPercent = tenPercentIn.isChecked();
                        // Converting strings to integers
                        WallDimensions wallDimensions = new WallDimensions(Integer.parseInt(wallLengthStr), Integer.parseInt(wallHeightStr));
                        TileDimensions tileDimensions = new TileDimensions(Integer.parseInt(tileLengthStr), Integer.parseInt(tileHeightStr));
                        // Calculating values
                        double toBeTiledArea = Calculator.calculateToBeTiledArea(wallDimensions, Calculator.calculateObstaclesArea(obstacles));
                        double numTiles = Calculator.calculateTiles(toBeTiledArea, tileDimensions, tenPercent);
                        double wallAreaMeter = Calculator.convertToMeter(toBeTiledArea);
                        //Creating Tile Objects in a List
                        List<List<Tile>> tiles = setTilesSize(wallDimensions, tileDimensions, obstacles);
                        // Start New Activity
                        Intent intent = new Intent(MainActivity.this, CalculatedActivity.class);
                        intent.putExtra("data", new CalculatedValuesWrapper(wallAreaMeter, numTiles, obstacles, tiles, wallDimensions));
                        startActivity(intent);
                    }
                } catch (ObstacleInputException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Setting up Obstacles
    public List<Obstacle> parseInputsAsObstacles() throws ObstacleInputException {
        List<Obstacle> obstacleIns = new ArrayList<>();
        LinearLayout obstaclesContainer = findViewById(R.id.container);

        for (int i = 0; i < obstaclesContainer.getChildCount(); i++) {
            View child = obstaclesContainer.getChildAt(i);

            EditText obsLengthIn = child.findViewById(R.id.obs_length_in);
            EditText obsHeightIn = child.findViewById(R.id.obs_height_in);
            EditText obsDisFromLeft = child.findViewById(R.id.obs_from_left);
            EditText obsDisFromBottom = child.findViewById(R.id.obs_from_bot);

            if (isObstaclesInputValid(child)) {
                Obstacle obstacle = new Obstacle();

                obstacle.setLength(getEditTextNumbers(obsLengthIn));
                obstacle.setHeight(getEditTextNumbers(obsHeightIn));

                obstacle.setPosXY1(getEditTextNumbers(obsDisFromLeft), getEditTextNumbers(obsDisFromBottom));
                obstacle.setPosXY2(Calculator.calculatePosX2(obstacle), Calculator.calculatePosY2(obstacle));

                obstacleIns.add(obstacle);
            } else {
                throw new ObstacleInputException();
            }
        }
        return obstacleIns;
    }

    private int getEditTextNumbers(EditText editText) {
        return Integer.parseInt(editText.getText().toString());
    }

    private List<List<Tile>> setTilesSize(WallDimensions wallDimensions, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        List<List<Tile>> tiles = new ArrayList<>();
        double numberOfRows = Calculator.calculateNumberOfRows(wallDimensions, tileDimensions);
        double numberOfColumns = Calculator.calculateNumberOfColumns(wallDimensions, tileDimensions);
        for (int i = 0; i < numberOfRows; i++) {
            List<Tile> newRow = new ArrayList<>();
            for (int j = 0; j < numberOfColumns; j++) {
                Tile tile = new Tile();

                tile.setPosXY1(j * tileDimensions.getLength(), i * tileDimensions.getHeight());

                tile.setHeight(Math.min(wallDimensions.getHeight() - i * tileDimensions.getHeight(), tileDimensions.getHeight()));
                tile.setLength(Math.min(wallDimensions.getLength() - j * tileDimensions.getLength(), tileDimensions.getLength()));

                tile.setPosXY2(Calculator.calculatePosX2(tile), Calculator.calculatePosY2(tile));

                newRow.add(tile);
                num++;
                for (Obstacle obstacle : obstacles) {
                    if (obstacle.isOverlapping(tile)) {
                        // Removing Tiles that are fully overlap obstacle(s)
                        if (obstacle.isFullyOverlapping(tile)) {
                            newRow.remove(tile);
                        }
                        if (obstacle.isLeftOverlapping(tile)) {
                            tile.setLength(Calculator.cutTileLength(tile, obstacle));
                        }
                        if (obstacle.isBottomOverlapping(tile)) {
                            tile.setHeight(Calculator.cutTileHeight(tile, obstacle));
                        }
                        tile.setPosXY2(Calculator.calculatePosX2(tile), Calculator.calculatePosY2(tile));
                    }
                }
            }
            tiles.add(newRow);
        }
        return tiles;
    }

    private boolean isObstaclesInputValid(View obstacleInput) {
        EditText obsLengthIn = obstacleInput.findViewById(R.id.obs_length_in);
        EditText obsHeightIn = obstacleInput.findViewById(R.id.obs_height_in);
        EditText obsDisFromLeft = obstacleInput.findViewById(R.id.obs_from_left);
        EditText obsDisFromBottom = obstacleInput.findViewById(R.id.obs_from_bot);

        String obsLengthInTxt = obsLengthIn.getText().toString();
        String obsHeightInTxt = obsHeightIn.getText().toString();
        String obsDisFromLeftTxt = obsDisFromLeft.getText().toString();
        String obsDisFromBottomTxt = obsDisFromBottom.getText().toString();

        return !obsLengthInTxt.isEmpty() && !obsHeightInTxt.isEmpty() && !obsDisFromLeftTxt.isEmpty() && !obsDisFromBottomTxt.isEmpty();
    }
}

