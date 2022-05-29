package com.example.tiler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView obs = findViewById(R.id.button_obs);
        ArrayList<TextView> buttons_extra = new ArrayList<>();
        buttons_extra.add(obs);
        buttons_extra.get(buttons_extra.size()-1).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout canvas = MainActivity.this.findViewById(R.id.layout_obs);
                obs.setTextSize(24);
                obs.setText("Obstacle 1");
                obs.setFocusable(false);
                obs.setOnClickListener(null);
                ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT);
                canvas.setLayoutParams(layoutParam);
                ConstraintSet set = new ConstraintSet();
                TextView button1 = createObstacle(buttons_extra.size(),buttons_extra);
                canvas.addView(button1);
                set.connect(button1.getId(), ConstraintSet.TOP,
                        obs.getId(), ConstraintSet.BOTTOM, 60);
            }
        });


        final Button button_calc = findViewById(R.id.button_calc);
        button_calc.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v) {
            // Wall Inputs
            EditText wall_length_in = findViewById(R.id.wall_length_in);
            EditText wall_height_in = findViewById(R.id.wall_height_in);
            // Tile Inputs
            EditText tile_length_in = findViewById(R.id.tile_length_in);
            EditText tile_height_in = findViewById(R.id.tile_height_in);
            // 10% wastage
            SwitchMaterial ten_percent_in = findViewById(R.id.ten_percent);
            // Getting All User Input
            boolean ten_percent_boo = ten_percent_in.isChecked();
            String wall_length_str = wall_length_in.getText().toString();
            String wall_height_str = wall_height_in.getText().toString();
            String tile_length_str = tile_length_in.getText().toString();
            String tile_height_str = tile_height_in.getText().toString();

            if (    wall_length_str.isEmpty() || wall_height_str.isEmpty()
                ||  tile_length_str.isEmpty() || tile_height_str.isEmpty()){
                Context context = getApplicationContext();
                CharSequence text = "You did not enter all numbers.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else{
                int wall_length_int = Integer.parseInt(wall_length_str);
                int wall_height_int = Integer.parseInt(wall_height_str);

                int tile_length_int = Integer.parseInt(tile_length_str);
                int tile_height_int = Integer.parseInt(tile_height_str);

                int wall_area = wall_length_int * wall_height_int ;
                int tile_area = tile_length_int * tile_height_int ;
                int num_tiles;
                if (ten_percent_boo){
                    num_tiles = (int) Math.ceil((float) wall_area / tile_area * 1.1);
                }
                else {
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
                calculated.putIntegerArrayListExtra("calculated_values",calc_values);
                startActivity(calculated);
                }
            }
        });
    }

    public TextView createObstacle(int button_number, ArrayList buttons_extra) {
        TextView name = new TextView(this);
        name.setText("btn_obs"+button_number);
        Log.d("btn_obs", name.getText().toString());
        name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        name.setTextColor(Color.parseColor("#000000"));
        name.setFocusable(true);
        name.setClickable(true);
        buttons_extra.add("btn_obs"+button_number);
        return name;
    }
}
