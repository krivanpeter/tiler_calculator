package com.pk.tiler_buddy.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageButton;

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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        // ImageView canvasBackground = findViewById(R.id.fullscreen_view);
        ConstraintLayout drawingLayout = findViewById(R.id.drawing_layout);
        //Grabbing values from main Activity
        InputValuesWrapper calculatedValuesWrapper = (InputValuesWrapper) getIntent().getSerializableExtra("data");
        Wall wall = new Wall(
                calculatedValuesWrapper.getWallDimensions(),
                calculatedValuesWrapper.getTileDimensions(),
                calculatedValuesWrapper.getObstacles());

        WallView wallView = new WallView(this);
        wallView.setWall(wall);
        wallView.setScaleValue(getCanvasScaleValue(wall));

        wallView.setLayoutParams(new ViewGroup.LayoutParams(
                (int) Math.round(getCanvasScaleValue(wall) * wall.getLength()),
                (int) Math.round(getCanvasScaleValue(wall) * wall.getHeight())));
        drawingLayout.addView(wallView);

        wallView.setOnTouchListener(new OnSwipeTouchListener(DrawingActivity.this) {
            public void onSwipeRight() {
                wall.shiftHorizontally(30);
            }

            public void onSwipeLeft() {
                wall.shiftHorizontally(-30);
            }
        });

        ImageButton takePhotoButton = findViewById(R.id.take_photo_button);
        takePhotoButton.setOnClickListener(v -> {
            checkCameraPermission();
            openCamera();
        });
    }

    private float getCanvasScaleValue(Wall wall) {
        float scaleValueX = (float) getDisplaySize().x / wall.getLength();
        float scaleValueY = (float) getDisplaySize().y / wall.getHeight();
        return Math.min(scaleValueX, scaleValueY);
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
