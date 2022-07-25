package com.pk.tiler_buddy.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.pk.tiler_buddy.InputValuesWrapper;
import com.pk.tiler_buddy.R;
import com.pk.tiler_buddy.TileDimensions;
import com.pk.tiler_buddy.Wall;
import com.pk.tiler_buddy.view.WallView;

public class DrawingActivity extends AppCompatActivity {
    public static final int IMAGE_CAPTURE_CODE = 123;
    TileDimensions tileDimensions;
    int dragFirstPointX;
    private ConstraintLayout drawingLayout;
    private Wall wall;
    private WallView wallView;
    private float canvasScaleValue;
    private Uri image_uri;
    private int whichRow;

    @SuppressLint({"ClickableViewAccessibility", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InputValuesWrapper calculatedValuesWrapper = (InputValuesWrapper) getIntent().getSerializableExtra("data");
        tileDimensions = calculatedValuesWrapper.getTileDimensions();
        wall = new Wall(
                calculatedValuesWrapper.getWallDimensions(),
                calculatedValuesWrapper.getTileDimensions(),
                calculatedValuesWrapper.getObstacles());
        if (wall.getHeight() > wall.getLength()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.activity_drawing);

        // ImageView canvasBackground = findViewById(R.id.fullscreen_view);
        drawingLayout = findViewById(R.id.drawing_layout);
        canvasScaleValue = getCanvasScaleValue(wall);

        setWallView();
        wallView.setOnTouchListener((v, event) -> dragging(event));

        ImageButton takePhotoButton = findViewById(R.id.take_photo_button);
        takePhotoButton.setOnClickListener(v -> {
            checkCameraPermission();
            openCamera();
        });
    }

    private boolean dragging(MotionEvent event) {
        int dragX2;
        int dragValue;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dragFirstPointX = (int) event.getX();
                return true;
            case MotionEvent.ACTION_MOVE:
                dragX2 = (int) event.getX();
                dragValue = (int) (Math.abs(dragX2 - dragFirstPointX) / canvasScaleValue / tileDimensions.getLength());
                if (event.getPointerCount() > 1) {
                    dragWall(dragX2, dragValue);
                } else {
                    dragRow(event, dragX2, dragValue);
                }
                break;
        }
        return true;
    }

    private void dragWall(int dragX2, int dragValue) {
        if (dragX2 > dragFirstPointX) {
            wall.shiftHorizontally(dragValue);
        } else {
            wall.shiftHorizontally(dragValue * -1);
        }
    }

    private void dragRow(MotionEvent event, int dragX2, int dragValue) {
        if (dragX2 > dragFirstPointX) {
            wall.getTileRow(getWhichRowTouched(event)).shiftHorizontally(dragValue);
        } else {
            wall.getTileRow(getWhichRowTouched(event)).shiftHorizontally(dragValue * -1);
        }
    }

    private int getWhichRowTouched(MotionEvent event) {
        for (int i = 0; i < wall.getTileRows().size(); i++) {
            if (wall.getTileRow(i).getY2() * canvasScaleValue >= event.getY()
                    && wall.getTileRow(i).getY1() * canvasScaleValue <= event.getY()) {
                whichRow = i;
            }
        }
        return whichRow;
    }

    private void setWallView() {
        wallView = new WallView(this);
        wallView.setWall(wall);
        wallView.setCanvasScaleValue(canvasScaleValue);
        wallView.setLayoutParams(new ViewGroup.LayoutParams(
                Math.round(canvasScaleValue * wall.getLength()),
                Math.round(canvasScaleValue * wall.getHeight())));
        drawingLayout.addView(wallView);
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
