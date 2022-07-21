package com.pk.tiler_buddy.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.pk.tiler_buddy.InputValuesWrapper;
import com.pk.tiler_buddy.OnSwipeTouchListener;
import com.pk.tiler_buddy.R;
import com.pk.tiler_buddy.Wall;
import com.pk.tiler_buddy.view.WallView;

public class DrawingActivity extends AppCompatActivity {
    public static final int IMAGE_CAPTURE_CODE = 123;

    private Uri image_uri;
    private Canvas canvas;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        // ImageView canvasBackground = findViewById(R.id.fullscreen_view);
        ConstraintLayout drawingLayout = findViewById(R.id.drawing_layout);
        //Grabbing values from main Activity
        InputValuesWrapper calculatedValuesWrapper = (InputValuesWrapper) getIntent().getSerializableExtra("data");
        Wall wall = new Wall(calculatedValuesWrapper.getWallDimensions(), calculatedValuesWrapper.getTileDimensions(), calculatedValuesWrapper.getObstacles());
        Log.d("runs", "how many times?");
        // canvasBackground.setImageBitmap(bg);
        // wall.shiftOnX(20, tileDimensions, obstacles);
        Paint paint = setUpPaint();

        WallView wallView = new WallView(this);
        wallView.setWall(wall);
        wallView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT));
        drawingLayout.addView(wallView);
        wallView.setOnTouchListener(new OnSwipeTouchListener(DrawingActivity.this) {
            public void onSwipeRight() {
                wall.shiftHorizontally(30);
            }

            public void onSwipeLeft() {
                wall.shiftHorizontally(-30);
            }
        });

        // drawTiles(canvas, paint, wall);
        /*
        wall.setOnTouchListener(new OnSwipeTouchListener(context){
           @Override
           public void onSwipeLeft(){

           }
        });

         */
        ImageButton takePhotoButton = findViewById(R.id.take_photo_button);
        takePhotoButton.setOnClickListener(v -> {
            checkCameraPermission();
            openCamera();
        });
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
        String backgroundImageUrl = String.valueOf(image_uri);
        Intent intent = new Intent(DrawingActivity.this, ToBeTiledActivity.class);
        intent.putExtra("data", backgroundImageUrl);
        startActivity(intent);
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(DrawingActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DrawingActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
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
