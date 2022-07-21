package com.pk.tiler_buddy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.pk.tiler_buddy.Wall;

public class WallView extends View {
    private final int paintColor = Color.BLACK;
    private Paint drawPaint;
    private Wall wall;

    public WallView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(1);
        drawPaint.setStyle(Paint.Style.STROKE);
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawTiles(canvas);
        invalidate();
    }

    public void drawTiles(Canvas canvas) {
        for (int i = 0; i < wall.size(); i++) {
            wall.getRow(i).draw(canvas, drawPaint);
        }
    }
}
