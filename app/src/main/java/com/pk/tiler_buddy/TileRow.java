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

    public void shiftOnX(int extent, TileDimensions tileDimensions) {
        int allLength = 0;
        for (Tile tile : row) {
            // 4 sided(rectangle) tiles
            if (tile.getSides().isEmpty()) {
                //If pushing to the right
                if (Calculator.isPositive(extent)) {
                    if (tile.getX2() + extent >= length) {
                        tile.setX1(tile.getX1() + extent);
                        tile.setX2(length);
                        tile.setLength(Calculator.calculateLength(tile));
                    } else {
                        tile.setX1(tile.getX1() + extent);
                        tile.setX2(Calculator.calculatePosX2(tile));
                        tile.setLength(Calculator.calculateLength(tile));
                    }
                    //If pushing to the left
                } else {
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
            }
            allLength = allLength + tile.getLength();
        }
        fillUp(allLength, extent);
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
