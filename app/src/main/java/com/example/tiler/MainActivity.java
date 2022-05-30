package com.example.tiler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

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
                        ((ViewManager)obstacle.getParent()).removeView(obstacle);
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
                // Obstacle
                ArrayList<Obstacle> obs = setObsIn();
                for(int i = 0; i < obs.size(); ++i) {
                    Log.d("obs", String.valueOf(obs.get(i).getLength()));
                    Log.d("obs", String.valueOf(obs.get(i).getHeight()));
                    Log.d("obs", String.valueOf(obs.get(i).getDisLeft()));
                    Log.d("obs", String.valueOf(obs.get(i).getDisBot()));
                }
                // Getting All User Input
                boolean ten_percent_boo = ten_percent_in.isChecked();
                String wall_length_str = wall_length_in.getText().toString();
                String wall_height_str = wall_height_in.getText().toString();
                String tile_length_str = tile_length_in.getText().toString();
                String tile_height_str = tile_height_in.getText().toString();

                if (wall_length_str.isEmpty() || wall_height_str.isEmpty() || tile_length_str.isEmpty() || tile_height_str.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "You did not enter all numbers.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    int wall_length_int = Integer.parseInt(wall_length_str);
                    int wall_height_int = Integer.parseInt(wall_height_str);

                    int tile_length_int = Integer.parseInt(tile_length_str);
                    int tile_height_int = Integer.parseInt(tile_height_str);

                    int wall_area = wall_length_int * wall_height_int;
                    int tile_area = tile_length_int * tile_height_int;
                    int num_tiles;
                    if (ten_percent_boo) {
                        num_tiles = (int) Math.ceil((float) wall_area / tile_area * 1.1);
                    } else {
                        num_tiles = (int) Math.ceil((float) wall_area / tile_area);
                    }
                    ArrayList<Integer> calc_values = new ArrayList<>();
                    calc_values.add(wall_area);
                    calc_values.add(tile_area);
                    calc_values.add(num_tiles);
                    calc_values.add(tile_length_int);
                    calc_values.add(tile_height_int);

                    //New Activity
                    Intent calculated = new Intent(MainActivity.this, CalculatedActivity.class);
                    calculated.putIntegerArrayListExtra("calculated_values", calc_values);
                    startActivity(calculated);
                }
            }
        });
    }
    public ArrayList<Obstacle> setObsIn(){
        ArrayList<Obstacle> obs_ins = new ArrayList<>();
        LinearLayout obs_all = findViewById(R.id.container);

        for(int i = 0; i < obs_all.getChildCount(); ++i) {
            View child = obs_all.getChildAt(i);
            Obstacle obstacle = new Obstacle();

            EditText obs_length_in = (EditText)child.findViewById(R.id.obs_length_in);
            EditText obs_height_in = (EditText)child.findViewById(R.id.obs_height_in);
            EditText obs_from_left = (EditText)child.findViewById(R.id.obs_from_left);
            EditText obs_from_bot = (EditText)child.findViewById(R.id.obs_from_bot);

            obstacle.setLength(Integer.parseInt(obs_length_in.getText().toString()));
            obstacle.setHeight(Integer.parseInt(obs_height_in.getText().toString()));
            obstacle.setDisLeft(Integer.parseInt(obs_from_left.getText().toString()));
            obstacle.setDisBot(Integer.parseInt(obs_from_bot.getText().toString()));

            obs_ins.add(obstacle);
        }
        return obs_ins;
    }
    // TextView obs_name = findViewById(R.id.obs_name);

}

