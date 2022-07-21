package com.pk.tiler_buddy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.pk.tiler_buddy.Wall;

public class WallView extends View {
    private Paint drawPaint;
    private Wall wall;
    private float scaleValue;

    public WallView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(1);
        drawPaint.setStyle(Paint.Style.STROKE);
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public void setScaleValue(float scaleValue) {
        this.scaleValue = scaleValue;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.scale(scaleValue, scaleValue);
        drawTiles(canvas);
        invalidate();
        canvas.restore();
    }

    public void drawTiles(Canvas canvas) {
        for (int i = 0; i < wall.size(); i++) {
            wall.getRow(i).draw(canvas, drawPaint);
        }
    }
}
