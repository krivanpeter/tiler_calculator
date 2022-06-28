package com.pk.tiler_buddy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.pk.tiler_buddy.view.TileDimensions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TileRow extends Rectangle implements Serializable {
    int numberOfColumns;
    List<Tile> row = new ArrayList<>();

    public void removeTile(Tile tile) {
        this.row.remove(tile);
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public void setTiles(TileDimensions tileDimensions, List<Obstacle> obstacles) {
        for (int i = 0; i < numberOfColumns; i++) {
            Tile tile = new Tile();
            tile.setRectXY1(i * tileDimensions.getLength(), y1);
            tile.setHeight(height);
            tile.setLength(Math.min(length - i * tileDimensions.getLength(), tileDimensions.getLength()));
            tile.setRectXY2(Calculator.calculatePosX2(tile), Calculator.calculatePosY2(tile));
            row.add(tile);
            cutTiles(tile, obstacles);
        }
    }

    public void shiftOnX(TileDimensions tileDimensions, List<Obstacle> obstacles, int extent) {
        row.clear();
        int allLength = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            Tile tile = new Tile();
            tile.setRectXY1(i * tileDimensions.getLength(), y1);
            tile.setHeight(height);
            tile.setLength(Math.min(length - i * tileDimensions.getLength(), tileDimensions.getLength()));
            tile.setRectXY2(Calculator.calculatePosX2(tile), Calculator.calculatePosY2(tile));
            shiftTileOnX(extent, tile, tileDimensions);
            allLength = allLength + tile.getLength();
            row.add(tile);
            cutTiles(tile, obstacles);
        }
        fillUp(allLength, extent);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < this.row.size(); i++) {
            Tile tile = this.row.get(i);
            tile.draw(canvas, paint);
        }
    }

    private void cutTiles(Tile tile, List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (Overlap.isFullyOverlapping(tile, obstacle)) {
                removeTile(tile);
            }
            tile.cut(obstacle);
        }
    }

    private void shiftToRight(int extent, Tile tile) {
        tile.setX1(tile.getX1() + extent);
        if (tile.getX2() + extent >= length) {
            tile.setX2(length);
        } else {
            tile.setX2(Calculator.calculatePosX2(tile));
        }
        tile.setLength(Calculator.calculateLength(tile));
    }

    private void shiftToLeft(int extent, Tile tile, TileDimensions tileDimensions) {
        if (tile.getX1() + extent < x1) {
            tile.adjustLength(extent);
            tile.setX2(Calculator.calculatePosX2(tile));
            tile.setX1(Calculator.calculatePosX1(tile));
        } else if (tile.getX2() - extent >= length) {
            if (tile.getLength() - extent > tileDimensions.getLength()) {
                tile.setX1(tile.getX1() + extent);
                tile.setLength(tileDimensions.getLength());
                tile.setX2(Calculator.calculatePosX2(tile));
            } else {
                tile.setX1(tile.getX1() + extent);
                tile.setX2(length);
                tile.setLength(Calculator.calculateLength(tile));
            }
        } else {
            tile.setX1(tile.getX1() + extent);
            tile.setX2(Calculator.calculatePosX2(tile));
            tile.setLength(Calculator.calculateLength(tile));
        }
    }

    private void shiftTileOnX(int extent, Tile tile, TileDimensions tileDimensions) {
        if (extent > tileDimensions.getLength()) {
            extent = extent - (extent / tileDimensions.getLength()) * tileDimensions.getLength();
        }
        //If pushing to the right
        if (Calculator.isPositive(extent)) {
            shiftToRight(extent, tile);
            //If pushing to the left
        } else {
            shiftToLeft(extent, tile, tileDimensions);
        }
    }

    private void fillUp(int allLength, int extent) {
        if (allLength < length) {
            Tile tile = new Tile();
            if (Calculator.isPositive(extent)) {
                tile.setX1(x1);
                row.add(0, tile);
            } else {
                tile.setX1(length - (length - allLength));
                row.add(row.size() - 1, tile);
            }
            tile.setY1(y1);
            tile.setHeight(height);
            tile.setLength(Math.abs(extent));
            tile.setX2(Calculator.calculatePosX2(tile));
            tile.setY2(Calculator.calculatePosY2(tile));
        }
    }
}
