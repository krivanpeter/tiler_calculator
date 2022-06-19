package com.example.tiler_buddy.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiler.R;
import com.example.tiler_buddy.CalculatedValuesWrapper;
import com.example.tiler_buddy.Obstacle;
import com.example.tiler_buddy.Tile;

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
        List<List<Tile>> tiles = calculatedValuesWrapper.getTiles();
        WallDimensions wallDimensions = calculatedValuesWrapper.getWallDimensions();

        int num0 = 1;
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            String posXY1 = String.valueOf(obstacle.getPosition().getPosXY1());
            String posXY2 = String.valueOf(obstacle.getPosition().getPosXY2());
            String length = String.valueOf(obstacle.getLength());
            String height = String.valueOf(obstacle.getHeight());
            num0++;
        }

        int num = 1;
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                Tile tile = tiles.get(i).get(j);
                String posXY1 = String.valueOf(tile.getPosition().getPosXY1());
                String posXY2 = String.valueOf(tile.getPosition().getPosXY2());
                String length = String.valueOf(tile.getLength());
                String height = String.valueOf(tile.getHeight());
                Log.v("csempe", num + ": " + "pos1: " + posXY1 + ", " + "pos2: " + posXY2 + ", " + "length: " + length + "mm, " + "height: " + height + "mm");
                num++;
            }
        }

        ImageView imgview = findViewById(R.id.imageView1);

        if (imgview != null) {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#000000"));
            paint.setStyle(Paint.Style.STROKE);

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;


            Bitmap bg = Bitmap.createBitmap(wallDimensions.getLength() + 10, wallDimensions.getHeight() + 10, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bg);
            for (int i = 0; i < tiles.size(); i++) {
                for (int j = 0; j < tiles.get(i).size(); j++) {
                    Tile tile = tiles.get(i).get(j);
                    int posx1 = tile.getPosition().getPosX1();
                    int posy1 = tile.getPosition().getPosY1();
                    int posx2 = tile.getPosition().getPosX2();
                    int posy2 = tile.getPosition().getPosY2();
                    canvas.drawRect(posx1, posy1, posx2, posy2, paint);
                }
            }
            imgview.setBackground(new BitmapDrawable(bg));
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
    }
}


