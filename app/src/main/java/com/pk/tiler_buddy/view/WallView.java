package com.pk.tiler_buddy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.pk.tiler_buddy.TileRow;
import com.pk.tiler_buddy.Wall;

public class WallView extends View {
    private Paint drawPaint;
    private Wall wall;
    private float canvasScaleValue;

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

    public void setCanvasScaleValue(float canvasScaleValue) {
        this.canvasScaleValue = canvasScaleValue;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.scale(canvasScaleValue, canvasScaleValue);
        drawTileRows(canvas);
        invalidate();
        canvas.restore();
    }

    public void drawTileRows(Canvas canvas) {
        for (TileRow tileRow : wall.getTileRows()) {
            tileRow.drawTiles(canvas, drawPaint);
        }
    }
}
