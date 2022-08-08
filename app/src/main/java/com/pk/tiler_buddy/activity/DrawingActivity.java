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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private boolean tileLayoutMenuButtonClicked = false;
    private boolean symmetryMiddleButtonClicked = false;
    private boolean quarterShiftButtonClicked = false;
    private boolean symmetrySideButtonClicked = false;
    private Wall wall;
    private WallView wallView;
    private float canvasScaleValue;
    private Uri image_uri;
    private int whichRow;

    @SuppressLint({"ClickableViewAccessibility", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        drawingLayout = findViewById(R.id.drawing_layout);
        InputValuesWrapper calculatedValuesWrapper = (InputValuesWrapper) getIntent().getSerializableExtra("data");
        tileDimensions = calculatedValuesWrapper.getTileDimensions();

        setWall(calculatedValuesWrapper);
        canvasScaleValue = getCanvasScaleValue(wall);
        setScreenOrientation();
        setWallView();
        setupButtons();
    }

    private void setupButtons() {
        FloatingActionButton tileMenuLayoutButton = findViewById(R.id.tile_menu_button);
        ImageButton symmetryMiddleButton = findViewById(R.id.symmetry_middle_button);
        ImageButton symmetrySideButton = findViewById(R.id.symmetry_side_button);
        ImageButton quarterShiftButton = findViewById(R.id.quarter_shift_button);
        ImageButton takePhotoButton = findViewById(R.id.take_photo_button);

        setAllTouchListener(tileMenuLayoutButton, symmetryMiddleButton, symmetrySideButton, quarterShiftButton, takePhotoButton);
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setAllTouchListener(
            FloatingActionButton tileMenuLayoutButton,
            ImageButton symmetryMiddleButton,
            ImageButton symmetrySideButton,
            ImageButton quarterShiftButton,
            ImageButton takePhotoButton) {
        wallView.setOnTouchListener((v, event) -> dragging(event));

        tileMenuLayoutButton.setOnClickListener(v -> {
            toggleTileMenuButton(tileMenuLayoutButton);
        });

        symmetryMiddleButton.setOnClickListener(v -> {
            if (symmetryMiddleButtonClicked) {
                wall.shiftMiddleSymmetry(true);
                symmetryMiddleButtonClicked = false;
            } else {
                wall.shiftMiddleSymmetry(false);
                symmetryMiddleButtonClicked = true;
                symmetrySideButtonClicked = false;
                quarterShiftButtonClicked = false;
            }
        });

        symmetrySideButton.setOnClickListener(v -> {
            if (symmetrySideButtonClicked) {
                wall.shiftSideSymmetry(true);
                symmetrySideButtonClicked = false;
            } else {
                wall.shiftSideSymmetry(false);
                symmetrySideButtonClicked = true;
                symmetryMiddleButtonClicked = false;
                quarterShiftButtonClicked = false;
            }
        });

        quarterShiftButton.setOnClickListener(v -> {
            if (quarterShiftButtonClicked) {
                wall.shiftQuarterHorizontally(true);
                quarterShiftButtonClicked = false;
            } else {
                wall.shiftQuarterHorizontally(false);
                quarterShiftButtonClicked = true;
                symmetryMiddleButtonClicked = false;
                symmetrySideButtonClicked = false;
            }
        });

        takePhotoButton.setOnClickListener(v -> {
            checkCameraPermission();
            openCamera();
        });
    }

    private void toggleTileMenuButton(FloatingActionButton tileMenuLayoutButton) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(drawingLayout);
        if (tileLayoutMenuButtonClicked) {
            constraintSet.clear(R.id.tile_menu_layout, ConstraintSet.BOTTOM);
            constraintSet.connect(R.id.tile_menu_layout, ConstraintSet.TOP, R.id.drawing_layout, ConstraintSet.BOTTOM, 0);
            tileMenuLayoutButton.setImageResource(R.drawable.arrow_up_float);
            tileLayoutMenuButtonClicked = false;
        } else {
            constraintSet.clear(R.id.tile_menu_layout, ConstraintSet.TOP);
            constraintSet.connect(R.id.tile_menu_layout, ConstraintSet.BOTTOM, R.id.drawing_layout, ConstraintSet.BOTTOM, 0);
            tileMenuLayoutButton.setImageResource(R.drawable.arrow_down_float);
            tileLayoutMenuButtonClicked = true;
        }
        constraintSet.applyTo(drawingLayout);
    }

    private void setWall(InputValuesWrapper calculatedValuesWrapper) {
        wall = new Wall(
                calculatedValuesWrapper.getWallDimensions(),
                calculatedValuesWrapper.getTileDimensions(),
                calculatedValuesWrapper.getObstacles());
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private void setScreenOrientation() {
        if (wall.getHeight() > wall.getLength()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
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
                dragValue = (int) (Math.abs(dragX2 - dragFirstPointX) / canvasScaleValue) / tileDimensions.getLength();
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
        wallView.setRotationX(180);
        drawingLayout.addView(wallView);
        wallView.setId(View.generateViewId());
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
