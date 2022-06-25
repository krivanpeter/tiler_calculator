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
import com.pk.tiler_buddy.Tile;
import com.pk.tiler_buddy.TileRow;

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
        List<TileRow> tiles = calculatedValuesWrapper.getAllRows();
        WallDimensions wallDimensions = calculatedValuesWrapper.getWallDimensions();
        ImageView imgview = findViewById(R.id.imageView1);

        if (imgview != null) {
            Bitmap bg = Bitmap.createBitmap(wallDimensions.getLength() + 10, wallDimensions.getHeight() + 10, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bg);
            Paint paint = setUpPaint();
            drawTiles(canvas, paint, tiles);
            imgview.setImageBitmap(bg);
        }
    }

    public void drawTiles(Canvas canvas, Paint paint, List<TileRow> tiles) {
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).getRow().size(); j++) {
                Tile tile = tiles.get(i).getRow().get(j);
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
        paint.setStrokeWidth(5);
        return paint;
    }
}
