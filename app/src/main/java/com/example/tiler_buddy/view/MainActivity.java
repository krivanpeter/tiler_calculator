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
import com.example.tiler_buddy.DataWrapper;
import com.example.tiler_buddy.Obstacle;
import com.example.tiler_buddy.ObstacleInputException;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout container = MainActivity.this.findViewById(R.id.container);

        TextView button_obs = findViewById(R.id.button_obs);
        button_obs.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                View obstacle = inflater.inflate(R.layout.obstacle_layout, container, false);
                inflater.inflate(R.layout.obstacle_layout, container, false);
                container.addView(obstacle);

                ImageButton delete_obs = obstacle.findViewById(R.id.delete_obs);
                delete_obs.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ViewManager) obstacle.getParent()).removeView(obstacle);
                    }
                });
            }
        });

        final Button button_calc = findViewById(R.id.button_calc);
        button_calc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Wall Inputs
                EditText wall_length_in = findViewById(R.id.wall_length_in);
                EditText wall_height_in = findViewById(R.id.wall_height_in);
                // Tile Inputs
                EditText tile_length_in = findViewById(R.id.tile_length_in);
                EditText tile_height_in = findViewById(R.id.tile_height_in);
                // Spacer width
                EditText spacer_width_in = findViewById(R.id.spacer_width_in);
                // 10% wastage
                SwitchMaterial ten_percent_in = findViewById(R.id.ten_percent);

                try {
                    List<Obstacle> obs = parseInputsAsObstacles();
                    //Getting inputs as strings for checks
                    String wall_length_str = wall_length_in.getText().toString();
                    String wall_height_str = wall_height_in.getText().toString();
                    String tile_length_str = tile_length_in.getText().toString();
                    String tile_height_str = tile_height_in.getText().toString();
                    String spacer_width_str = spacer_width_in.getText().toString();
                    //Checking if inputs are empty
                    if (wall_length_str.isEmpty() || wall_height_str.isEmpty() || tile_length_str.isEmpty() || tile_height_str.isEmpty() || spacer_width_str.isEmpty()) {
                        Toast.makeText(getApplicationContext(),"You did not enter all numbers", Toast.LENGTH_SHORT).show();
                    } else {
                        // Getting All User Inputs
                        boolean ten_percent = ten_percent_in.isChecked();
                        // Converting strings to integers
                        WallDimensions wallDimensions = new WallDimensions(Integer.parseInt(wall_length_str), Integer.parseInt(wall_height_str));
                        TileDimensions tileDimensions = new TileDimensions(Integer.parseInt(tile_length_str), Integer.parseInt(tile_height_str));
                        // int spacer_width_int = Integer.parseInt(spacer_width_str);

                        //Start New Activity
                        Intent intent = new Intent(MainActivity.this, CalculatedActivity.class);
                        intent.putExtra("obstacles", new DataWrapper(obs));
                        intent.putExtra("wallDimensions", wallDimensions);
                        intent.putExtra("tileDimensions", tileDimensions);
                        intent.putExtra("ten_percent", ten_percent);
                        startActivity(intent);
                    }
                } catch (ObstacleInputException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Setting up Obstacles
    public List<Obstacle> parseInputsAsObstacles() throws ObstacleInputException {
        List<Obstacle> obs_ins = new ArrayList<>();
        LinearLayout obstaclesContainer = findViewById(R.id.container);

        for (int i = 0; i < obstaclesContainer.getChildCount(); i++) {
            View child = obstaclesContainer.getChildAt(i);

            EditText obs_length_in = child.findViewById(R.id.obs_length_in);
            EditText obs_height_in = child.findViewById(R.id.obs_height_in);
            EditText obs_from_left = child.findViewById(R.id.obs_from_left);
            EditText obs_from_bot = child.findViewById(R.id.obs_from_bot);

            if (isObstaclesInputValid(child)) {
                Obstacle obstacle = new Obstacle();
                obstacle.setLength(getEditTextNumbers(obs_length_in));
                obstacle.setHeight(getEditTextNumbers(obs_height_in));
                obstacle.setDisLeft(getEditTextNumbers(obs_from_left));
                obstacle.setDisBot(getEditTextNumbers(obs_from_bot));
                obs_ins.add(obstacle);
            }
            else{
                throw new ObstacleInputException();
            }
        }
        return obs_ins;
    }

    private boolean isObstaclesInputValid(View obstacleInput){
        EditText obs_length_in = obstacleInput.findViewById(R.id.obs_length_in);
        EditText obs_height_in = obstacleInput.findViewById(R.id.obs_height_in);
        EditText obs_from_left = obstacleInput.findViewById(R.id.obs_from_left);
        EditText obs_from_bot = obstacleInput.findViewById(R.id.obs_from_bot);

        String obs_length_in_txt = obs_length_in.getText().toString();
        String obs_height_in_txt = obs_height_in.getText().toString();
        String obs_from_left_txt = obs_from_left.getText().toString();
        String obs_from_bot_txt = obs_from_bot.getText().toString();

        return !obs_length_in_txt.isEmpty() && !obs_height_in_txt.isEmpty() && !obs_from_left_txt.isEmpty() &&!obs_from_bot_txt.isEmpty();
    }

    private int getEditTextNumbers(EditText editText){
        return Integer.parseInt(editText.getText().toString());
    }
}

