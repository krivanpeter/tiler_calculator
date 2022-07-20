package com.pk.tiler_buddy.activity;

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

import com.pk.tiler_buddy.Calculator;
import com.pk.tiler_buddy.InputValuesWrapper;
import com.pk.tiler_buddy.Obstacle;
import com.pk.tiler_buddy.ObstacleInputException;
import com.pk.tiler_buddy.R;
import com.pk.tiler_buddy.TileDimensions;
import com.pk.tiler_buddy.WallDimensions;

import java.util.ArrayList;
import java.util.List;

public class InputActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupObstacleButton();
        setupCalculateButton();
    }

    private void setupCalculateButton() {
        final Button calculateButton = findViewById(R.id.button_calc);
        calculateButton.setOnClickListener(v -> {
            try {
                // Wall Inputs
                int wallLength = getEditTextInputAsInt("wall_length_in");
                int wallHeight = getEditTextInputAsInt("wall_height_in");
                // Tile Inputs
                int tileLength = getEditTextInputAsInt("tile_length_in");
                int tileHeight = getEditTextInputAsInt("tile_height_in");

                WallDimensions wallDimensions = new WallDimensions(wallLength, wallHeight);
                TileDimensions tileDimensions = new TileDimensions(tileLength, tileHeight);

                startDrawingActivity(getObstacles(), wallDimensions, tileDimensions);

            } catch (ObstacleInputException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startDrawingActivity(List<Obstacle> obstacles, WallDimensions wallDimensions, TileDimensions tileDimensions) {
        Intent intent = new Intent(InputActivity.this, DrawingActivity.class);
        intent.putExtra("data", new InputValuesWrapper(wallDimensions, tileDimensions, obstacles));
        startActivity(intent);
    }

    private void setupObstacleButton() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout container = InputActivity.this.findViewById(R.id.container);

        TextView obstacleButton = findViewById(R.id.button_obs);
        obstacleButton.setOnClickListener(v -> addNewObstacleInputLayout(inflater, container));
    }

    private int getEditTextInputAsInt(String xmlId) throws ObstacleInputException {
        int inputId = getResources().getIdentifier(xmlId, "id", getPackageName());
        EditText editText = findViewById(inputId);
        String value = editText.getText().toString();
        if (editText.getText() == null || value.isEmpty()) {
            throw new ObstacleInputException();
        }
        return Integer.parseInt(value);
    }

    private void addNewObstacleInputLayout(LayoutInflater inflater, LinearLayout container) {
        View obstacle = inflater.inflate(R.layout.obstacle_layout, container, false);
        container.addView(obstacle);

        ImageButton deleteObs = obstacle.findViewById(R.id.delete_obs);
        deleteObs.setOnClickListener(v -> ((ViewManager) obstacle.getParent()).removeView(obstacle));
    }

    private List<Obstacle> getObstacles() throws ObstacleInputException {
        List<Obstacle> obstacles = new ArrayList<>();
        LinearLayout obstaclesContainer = findViewById(R.id.container);

        for (int i = 0; i < obstaclesContainer.getChildCount(); i++) {
            View child = obstaclesContainer.getChildAt(i);

            EditText obsLengthIn = child.findViewById(R.id.obs_length_in);
            EditText obsHeightIn = child.findViewById(R.id.obs_height_in);
            EditText obsDisFromLeft = child.findViewById(R.id.obs_from_left);
            EditText obsDisFromBottom = child.findViewById(R.id.obs_from_bot);

            if (isObstaclesInputValid(child)) {
                Obstacle obstacle = new Obstacle();

                obstacle.setLength(getEditTextNumber(obsLengthIn));
                obstacle.setHeight(getEditTextNumber(obsHeightIn));

                obstacle.setRectXY1(getEditTextNumber(obsDisFromLeft), getEditTextNumber(obsDisFromBottom));
                obstacle.setRectXY2(Calculator.calculatePosX2(obstacle), Calculator.calculatePosY2(obstacle));

                obstacles.add(obstacle);
            } else {
                throw new ObstacleInputException();
            }
        }
        return obstacles;
    }

    private int getEditTextNumber(EditText editText) {
        return Integer.parseInt(editText.getText().toString());
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

