package com.pk.tiler_buddy;

import android.graphics.Canvas;
import android.graphics.Paint;

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

    public Tile(Tile tile){
        this.x1 = tile.getX1();
        this.y1 = tile.getY1();
        this.x2 = tile.getX2();
        this.y2 = tile.getY2();
        this.x3 = tile.getX3();
        this.y3 = tile.getY3();
    }

    public Tile(List<Side> sides) {
        this.sides = sides;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public List<Side> getSides() {
        return sides;
    }

    public void addSide(Side side) {
        this.sides.add(side);
    }

    public void draw(Canvas canvas, Paint paint){
        if (this.sides.isEmpty()) {
            canvas.drawRect(x1, y1, x2, y2, paint);
        } else {
            for (int i = 0; i < sides.size(); i++) {
                canvas.drawLine(sides.get(i).getX1(), sides.get(i).getY1(), sides.get(i).getX2(), sides.get(i).getY2(), paint);
            }
        }
    }
}
