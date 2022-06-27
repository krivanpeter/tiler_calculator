package com.pk.tiler_buddy.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.pk.tiler_buddy.CalculatedValuesWrapper;
import com.pk.tiler_buddy.Obstacle;
import com.pk.tiler_buddy.R;
import com.pk.tiler_buddy.Wall;

import java.util.List;

public class DrawingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove notifications bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Remove app title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_drawing);
        //Grabbing values from main Activity
        CalculatedValuesWrapper calculatedValuesWrapper = (CalculatedValuesWrapper) getIntent().getSerializableExtra("data");
        List<Obstacle> obstacles = calculatedValuesWrapper.getObstacles();
        Wall wall = calculatedValuesWrapper.getWall();
        WallDimensions wallDimensions = calculatedValuesWrapper.getWallDimensions();
        TileDimensions tileDimensions = calculatedValuesWrapper.getTileDimensions();

        ImageView imgview = findViewById(R.id.imageView1);
        wall.shiftOnX(20, obstacles, tileDimensions);
        Bitmap bg = Bitmap.createBitmap(wallDimensions.getLength() + 10, wallDimensions.getHeight() + 10, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        Paint paint = setUpPaint();
        drawTiles(canvas, paint, wall);
        imgview.setImageBitmap(bg);
    }

    public void drawTiles(Canvas canvas, Paint paint, Wall wall) {
        for (int i = 0; i < wall.size(); i++) {
            wall.get(i).draw(canvas, paint);
        }
    }

    private Paint setUpPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setTextSize(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        return paint;
    }
}
