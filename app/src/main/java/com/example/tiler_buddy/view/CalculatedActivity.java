package com.example.tiler_buddy.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

        ImageView imgview = findViewById(R.id.imageView1);
        if (imgview != null) {
            Bitmap bg = Bitmap.createBitmap(wallDimensions.getLength() + 10, wallDimensions.getHeight() - 10, Bitmap.Config.ARGB_8888);
            Canvas canvas = createCanvas(bg);
            Paint paint = setUpPaint();
            drawTiles(canvas, paint, tiles);
            imgview.setBackground(new BitmapDrawable(bg));
        }
    }

    public void drawTiles(Canvas canvas, Paint paint, List<List<Tile>> tiles) {
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                Tile tile = tiles.get(i).get(j);
                int posx1 = tile.getX1();
                int posy1 = tile.getY1();
                int posx2 = tile.getX2();
                int posy2 = tile.getY2();

                if (tile.getSides().isEmpty()) {
                    canvas.drawRect(posx1, posy1, posx2, posy2, paint);
                } else {
                    for (int x = 0; x < tile.getSides().size(); x++) {
                        canvas.drawLine(tile.getSides().get(x).getX1(), tile.getSides().get(x).getY1(), tile.getSides().get(x).getX2(), tile.getSides().get(x).getY2(), paint);
                    }
                }
            }
        }
    }

    private Paint setUpPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    private Canvas createCanvas(Bitmap bg) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        /*
        int displayWidth = size.x;
        int displayHeight = size.y;
         */

        Canvas canvas = new Canvas(bg);
        return canvas;
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


