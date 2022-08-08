package com.pk.tiler_buddy;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Rectangle {
    private final List<Side> sides = new ArrayList<>();
    private final List<Obstacle> obstacles;
    private int x3;
    private int y3;

    public Tile(List<Obstacle> obstacles){
        this.obstacles = obstacles;
    }
    public Tile(int x1, int y1, int x2, int y2, int x3, int y3, List<Obstacle> obstacles) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.obstacles = obstacles;
    }

    public Tile(Tile tile, List<Obstacle> obstacles) {
        this.obstacles = obstacles;
        this.x1 = tile.getX1();
        this.y1 = tile.getY1();
        this.x2 = tile.getX2();
        this.y2 = tile.getY2();
        this.x3 = tile.getX3();
        this.y3 = tile.getY3();
    }

    public int getX3() {
        return x3;
    }

    public int getY3() {
        return y3;
    }

    public void addSide(Side side) {
        this.sides.add(side);
    }

    public void adjustLength(int extent) {
        this.length = this.length + extent;
    }

    public void draw(Canvas canvas, Paint paint) {
        if (this.sides.isEmpty()) {
            canvas.drawRect(x1, y1, x2, y2, paint);
        } else {
            for (int i = 0; i < sides.size(); i++) {
                canvas.drawLine(
                        sides.get(i).getFirstPointX1(),
                        sides.get(i).getFirstPointY1(),
                        sides.get(i).getSecondPointX2(),
                        sides.get(i).getSecondPointY2(), paint);
            }
        }
        canvas.save();
        canvas.scale(1, -1);
        canvas.drawText(String.valueOf(this.x1), this.x1 + 10, -this.y2 + 10, paint);
        canvas.drawText(String.valueOf(this.y1), this.x1 + 10, -this.y2 + 20, paint);
        canvas.drawText(String.valueOf(this.x2), this.x1 + 10, -this.y2 + 30, paint);
        canvas.drawText(String.valueOf(this.y2), this.x1 + 10, -this.y2 + 40, paint);
        canvas.restore();
    }

    public void cut(Obstacle obstacle) {
        OverlapPosition overlapAt = Overlap.whereOverlap(this, obstacle);
        if (overlapAt != null) {
            switch (overlapAt) {
                case ONLY_LEFT:
                    this.length = Calculator.cutTileLengthLeft(this, obstacle);
                    this.x1 = Calculator.calculateNewPosX1(this, obstacle);
                    break;
                case ONLY_TOP:
                    this.height = Calculator.cutTileHeightTop(this, obstacle);
                    this.y2 = Calculator.calculatePosY2(this);
                    break;
                case ONLY_RIGHT:
                    this.length = Calculator.cutTileLengthRight(this, obstacle);
                    this.x2 = Calculator.calculatePosX2(this);
                    break;
                case ONLY_BOTTOM:
                    this.height = Calculator.cutTileHeightBottom(this, obstacle);
                    this.y1 = Calculator.calculateNewPosY1(this, obstacle);
                    break;
                case TOP_RIGHT_CORNER:
                    this.x3 = Calculator.calculateX3RightCorner(this, obstacle);
                    this.y3 = Calculator.calculateY3TopCorner(this, obstacle);
                    cutTopRightCorner();
                    break;
                case BOTTOM_RIGHT_CORNER:
                    this.x3 = Calculator.calculateX3RightCorner(this, obstacle);
                    this.y3 = Calculator.calculateY3BottomCorner(this, obstacle);
                    cutBottomRightCorner();
                    break;
                case BOTTOM_LEFT_CORNER:
                    this.x3 = Calculator.calculateX3LeftCorner(this, obstacle);
                    this.y3 = Calculator.calculateY3BottomCorner(this, obstacle);
                    cutBottomLeftCorner();
                    break;
                case TOP_LEFT_CORNER:
                    this.x3 = this.x1 + Calculator.cutTileLengthLeft(this, obstacle);
                    this.y3 = Calculator.calculateY3TopCorner(this, obstacle);
                    cutTopLeftCorner();
                    break;
            }
        }
    }

    private void cutTopRightCorner() {
        this.addSide(new Side(this.x1, this.y1, this.x1, this.y2));
        this.addSide(new Side(this.x1, this.y2, this.x3, this.y2));
        this.addSide(new Side(this.x3, this.y2, this.x3, this.y3));
        this.addSide(new Side(this.x3, this.y3, this.x2, this.y3));
        this.addSide(new Side(this.x2, this.y3, this.x2, this.y1));
        this.addSide(new Side(this.x2, this.y1, this.x1, this.y1));
    }

    private void cutBottomRightCorner() {
        this.addSide(new Side(this.x1, this.y1, this.x1, this.y2));
        this.addSide(new Side(this.x1, this.y2, this.x2, this.y2));
        this.addSide(new Side(this.x2, this.y2, this.x2, this.y3));
        this.addSide(new Side(this.x2, this.y3, this.x3, this.y3));
        this.addSide(new Side(this.x3, this.y3, this.x3, this.y1));
        this.addSide(new Side(this.x3, this.y1, this.x1, this.y1));
    }

    private void cutBottomLeftCorner() {
        this.addSide(new Side(this.x1, this.y3, this.x1, this.y2));
        this.addSide(new Side(this.x1, this.y2, this.x2, this.y2));
        this.addSide(new Side(this.x2, this.y2, this.x2, this.y1));
        this.addSide(new Side(this.x2, this.y1, this.x3, this.y1));
        this.addSide(new Side(this.x3, this.y1, this.x3, this.y3));
        this.addSide(new Side(this.x3, this.y3, this.x1, this.y3));
    }

    private void cutTopLeftCorner() {
        this.addSide(new Side(this.x1, this.y1, this.x1, this.y3));
        this.addSide(new Side(this.x1, this.y3, this.x3, this.y3));
        this.addSide(new Side(this.x3, this.y3, this.x3, this.y2));
        this.addSide(new Side(this.x3, this.y2, this.x2, this.y2));
        this.addSide(new Side(this.x2, this.y2, this.x2, this.y1));
        this.addSide(new Side(this.x2, this.y1, this.x1, this.y1));
    }
}
