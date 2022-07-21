package com.pk.tiler_buddy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import com.pk.tiler_buddy.Wall;

public class WallView extends View {
    private Paint drawPaint;
    private Wall wall;
    private Point displaySize;

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

    public void setDisplaySize(Point displaySize) {
        this.displaySize = displaySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        scaleCanvas(canvas);
        drawTiles(canvas);
        invalidate();
        canvas.restore();
    }

    private void scaleCanvas(Canvas canvas) {
        float scaleValueX = (float) displaySize.x / wall.getLength();
        float scaleValueY = (float) displaySize.y / wall.getHeight();
        if (scaleValueX > scaleValueY) {
            canvas.scale(scaleValueY, scaleValueY);
        } else {
            canvas.scale(scaleValueX, scaleValueX);
        }
    }


    public void drawTiles(Canvas canvas) {
        for (int i = 0; i < wall.size(); i++) {
            wall.getRow(i).draw(canvas, drawPaint);
        }
    }
}
