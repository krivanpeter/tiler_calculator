package com.pk.tiler_buddy.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.pk.tiler_buddy.R;
import com.pk.tiler_buddy.view.DragRectView;

import java.io.FileDescriptor;
import java.io.IOException;

public class ToBeTiledActivity extends AppCompatActivity {

    ImageView fullScreenBackground;
    int x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobe_tiled_area);

        String backgroundImageUrl = (String) getIntent().getSerializableExtra("data");
        Bitmap originalBitmap = uriToBitmap(Uri.parse(backgroundImageUrl));
        Bitmap fullscreenBitmap = originalBitmap.createScaledBitmap(originalBitmap, getDisplaySize().x, getDisplaySize().y, true);

        final DragRectView dragRectangleView = findViewById(R.id.dragRect);
        fullScreenBackground = findViewById(R.id.fullscreen_view);
        fullScreenBackground.setImageBitmap(fullscreenBitmap);

        if (dragRectangleView != null) {
            dragRectangleView.setOnUpCallback(rect -> {
                x1 = rect.left;
                y1 = rect.top;
                x2 = rect.right;
                y2 = rect.bottom;
                Log.d("Coordinates: ", x1 + ", " + y1 + ", " + x2 + ", " + y2 + ")");
            });
        }

        Button selectionOkButton = findViewById(R.id.save_selection_button);
        selectionOkButton.setOnClickListener(v -> saveSelectionCoordinates());
    }

    public Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Point getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    private void saveSelectionCoordinates(){
        //TODO
    }
}
