package com.pk.tiler_buddy.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.pk.tiler_buddy.CalculatedValuesWrapper;
import com.pk.tiler_buddy.R;
import com.pk.tiler_buddy.Wall;

public class DrawingActivity extends AppCompatActivity {
    public static final int IMAGE_CAPTURE_CODE = 123;

    Uri image_uri;
    String backgroundImageUrl;
    Canvas canvas;
    Wall wall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        ImageView toBeCanvasView = findViewById(R.id.fullscreen_view);
        //Grabbing values from main Activity
        CalculatedValuesWrapper calculatedValuesWrapper = (CalculatedValuesWrapper) getIntent().getSerializableExtra("data");
        wall = calculatedValuesWrapper.getWall();
        Bitmap bg = Bitmap.createBitmap(wall.getLength() + 10, wall.getHeight() + 10, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bg);
        toBeCanvasView.setImageBitmap(bg);
        // wall.shiftOnX(20, tileDimensions, obstacles);
        Paint paint = setUpPaint();
        drawTiles(canvas, paint, wall);

        ImageButton takePhotoButton = findViewById(R.id.take_photo_button);
        takePhotoButton.setOnClickListener(v -> openCamera());
    }

    private Paint setUpPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setTextSize(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        return paint;
    }

    public void drawTiles(Canvas canvas, Paint paint, Wall wall) {
        for (int i = 0; i < wall.size(); i++) {
            wall.getRow(i).draw(canvas, paint);
        }
    }

    private void setUpCanvas(Bitmap backgroundBitmap, ImageView toBeCanvasView) {
        Matrix matrix = new Matrix();
        matrix.postRotate(180);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(backgroundBitmap, getDisplaySize().x, getDisplaySize().y, true);

        Bitmap rotatedBackground = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        canvas = new Canvas(rotatedBackground);
        toBeCanvasView.setImageBitmap(rotatedBackground);
        // Bitmap bg = Bitmap.createBitmap(wall.getLength() + 10, wall.getHeight() + 10, Bitmap.Config.ARGB_8888);
    }

    private Point getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "photoName");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        backgroundImageUrl = String.valueOf(image_uri);
        Intent intent = new Intent(DrawingActivity.this, ToBeTiledActivity.class);
        intent.putExtra("data", backgroundImageUrl);
        startActivity(intent);
    }

    /*
    @Override
    protected void onStop() {
        if (backgroundImageUrl != null) {
            getContentResolver().delete(Uri.parse(backgroundImageUrl), null, null);
        }
        if (wall.getPhotoUriString() != null) {
            getContentResolver().delete(Uri.parse(wall.getPhotoUriString()), null, null);
        }
        super.onStop();
    }
     */
}
