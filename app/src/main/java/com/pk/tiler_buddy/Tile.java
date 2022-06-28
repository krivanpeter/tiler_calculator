package com.pk.tiler_buddy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.pk.tiler_buddy.view.TileDimensions;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Rectangle {
    int x3;
    int y3;
    List<Side> sides = new ArrayList<>();

    public Tile() {
    }

    public Tile(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public Tile(Tile tile) {
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

    public void draw(Canvas canvas, Paint paint) {
        if (this.sides.isEmpty()) {
            canvas.drawRect(x1, y1, x2, y2, paint);
        } else {
            for (int i = 0; i < sides.size(); i++) {
                canvas.drawLine(sides.get(i).getFirstPointX1(), sides.get(i).getFirstPointY1(), sides.get(i).getSecondPointX2(), sides.get(i).getSecondPointY2(), paint);
            }
        }
        canvas.save();
        canvas.scale(1, -1);
        canvas.drawText(String.valueOf(this.x1), this.x1 + 10, -this.y2 + 10, paint);
        canvas.drawText(String.valueOf(this.y1), this.x1 + 10, -this.y2 + 20, paint);
        canvas.drawText(String.valueOf(this.x2), this.x1 + 10, -this.y2 + 30, paint);
        canvas.drawText(String.valueOf(this.y2), this.x1 + 10, -this.y2 + 40, paint);
        canvas.drawText(String.valueOf(this.x3), this.x1 + 10, -this.y2 + 50, paint);
        canvas.drawText(String.valueOf(this.y3), this.x1 + 10, -this.y2 + 60, paint);
        canvas.restore();
    }

    public void shiftOnX(int extent, List<Obstacle> obstacles, Wall wall, TileDimensions tileDimensions) {
        for (Obstacle obstacle : obstacles) {
            if (x1 - extent < 0) {
                length = length - extent;
            } else {
                if (!Overlap.isXOverlapping(this, obstacle, x1 - extent)) {
                    if (length >= tileDimensions.getLength() - extent) {
                        x1 = x1 - extent;
                        length = tileDimensions.getLength();
                    }
                    else{
                        x1 = x1 - extent;
                    }
                } else {
                    length = length - extent;
                }
            }
            x2 = Calculator.calculatePosX2(this);
            sides.clear();
            cut(obstacle);
        }
    }


    public void cut(Obstacle obstacle) {
        String overlapAt = Overlap.whereOverlap(this, obstacle);
        if (overlapAt != "") {
            switch (overlapAt) {
                case "onlyLeft":
                    this.length = Calculator.cutTileLengthLeft(this, obstacle);
                    this.x1 = Calculator.calculateNewPosX1(this, obstacle);
                    break;
                case "onlyTop":
                    this.height = Calculator.cutTileHeightTop(this, obstacle);
                    this.y2 = Calculator.calculatePosY2(this);
                    break;
                case "onlyRight":
                    this.length = Calculator.cutTileLengthRight(this, obstacle);
                    this.x2 = Calculator.calculatePosX2(this);
                    break;
                case "onlyBottom":
                    this.height = Calculator.cutTileHeightBottom(this, obstacle);
                    this.y1 = Calculator.calculateNewPosY1(this, obstacle);
                    break;
                case "topRightCorner":
                    this.x3 = Calculator.calculateX3RightCorner(this, obstacle);
                    this.y3 = Calculator.calculateY3TopCorner(this, obstacle);
                    cutTopRightCorner();
                    break;
                case "bottomRightCorner":
                    this.x3 = Calculator.calculateX3RightCorner(this, obstacle);
                    this.y3 = Calculator.calculateY3BottomCorner(this, obstacle);
                    cutBottomRightCorner();
                    break;
                case "bottomLeftCorner":
                    this.x3 = Calculator.calculateX3LeftCorner(this, obstacle);
                    this.y3 = Calculator.calculateY3BottomCorner(this, obstacle);
                    cutBottomLeftCorner();
                    break;
                case "topLeftCorner":
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
