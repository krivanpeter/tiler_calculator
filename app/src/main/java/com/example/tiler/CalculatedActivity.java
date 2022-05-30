package com.example.tiler;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
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
        Intent i = getIntent();
        ArrayList<Integer> calc_values = i.getIntegerArrayListExtra("calculated_values");

        float wall_area_value = (float) (calc_values.get(0) / 1000000.0);
        float tile_area_value = (float) (calc_values.get(1) / 1000000.0);
        int tiles_num_val = calc_values.get(2);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        }
    }

        /*
        int tile_width = calc_values.get(3) / 100;
        int tile_height = calc_values.get(4) / 100;

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#CD5C5C"));
        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        canvas.save();
        canvas.translate(0,canvas.getHeight());
        canvas.scale(1,-1);
        canvas.drawRect(0,0,200,200,paint);
        LinearLayout ll = findViewById(R.id.rect);
        canvas.restore();

        ImageView iV = new ImageView(this);
        iV.setImageBitmap(bg);

        ll.addView(iV);
        */

}


