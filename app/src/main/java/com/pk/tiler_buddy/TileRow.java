package com.pk.tiler_buddy;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TileRow extends Rectangle implements Serializable {
    private final int numberOfColumns;
    private final TileDimensions tileDimensions;
    private final List<Obstacle> obstacles;
    private final List<Tile> tiles = new ArrayList<>();
    private int allShiftExtent;

    public TileRow(int numberOfColumns, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        this.numberOfColumns = numberOfColumns;
        this.tileDimensions = tileDimensions;
        this.obstacles = obstacles;
    }

    public void addTiles() {
        for (int i = 0; i < numberOfColumns; i++) {
            Tile tile = createTile(i);
            cutTiles(tile, obstacles);
            if (!Overlap.isFullyOverlapping(tile, obstacles)) {
                tiles.add(tile);
            }
        }
    }

    @NonNull
    private Tile createTile(int placeInRow) {
        Tile tile = new Tile(obstacles);
        tile.setRectXY1(placeInRow * tileDimensions.getLength(), y1);
        tile.setHeight(height);
        tile.setLength(Math.min(length - placeInRow * tileDimensions.getLength(), tileDimensions.getLength()));
        tile.setRectXY2(Calculator.calculatePosX2(tile), Calculator.calculatePosY2(tile));
        return tile;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getLastTile() {
        return tiles.get(getTiles().size()-1);
    }

    public void shiftHorizontally(int extent) {
        if (Math.abs(allShiftExtent + extent) >= tileDimensions.getLength()) {
            allShiftExtent = 0;
        } else {
            allShiftExtent = allShiftExtent + extent;
        }
        tiles.clear();
        if (allShiftExtent > tileDimensions.getLength()) {
            allShiftExtent = allShiftExtent - tileDimensions.getLength();
        }
        int allLength = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            Tile tile = createTile(i);
            shiftTileHorizontally(allShiftExtent, tile);
            allLength = allLength + tile.getLength();
            if (!Overlap.isFullyOverlapping(tile, obstacles)) {
                tiles.add(tile);
            }
            cutTiles(tile, obstacles);
        }
        fillUp(allLength, allShiftExtent);
    }

    private void cutTiles(Tile tile, List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            tile.cut(obstacle);
        }
    }

    public void drawTiles(Canvas canvas, Paint paint) {
        for (Tile tile : tiles) {
            tile.draw(canvas, paint);
        }
    }

    private void shiftTileHorizontally(int extent, Tile tile) {
        if (extent > tileDimensions.getLength()) {
            extent = tileDimensions.getLength() - extent;
        }
        //If pushing to the right
        if (Calculator.isPositive(extent)) {
            shiftToRight(extent, tile);
            //If pushing to the left
        } else {
            shiftToLeft(extent, tile);
        }
    }

    private void shiftToRight(int extent, Tile tile) {
        tile.setX1(tile.getX1() + extent);
        if (tile.getX2() >= length) {
            tile.setX2(length);
        } else {
            tile.setX2(Calculator.calculatePosX2(tile));
        }
        tile.setLength(Calculator.calculateLength(tile));
    }

    private void shiftToLeft(int extent, Tile tile) {
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

    private void fillUp(int allLength, int extent) {
        if (allLength < length) {
            Tile tile = new Tile(obstacles);
            if (Calculator.isPositive(extent)) {
                tile.setX1(x1);
                tiles.add(0, tile);
            } else {
                tile.setX1(length - (length - allLength));
                tiles.add(tiles.size() - 1, tile);
            }
            tile.setY1(y1);
            tile.setHeight(height);
            tile.setLength(Math.abs(allShiftExtent));
            tile.setX2(Calculator.calculatePosX2(tile));
            tile.setY2(Calculator.calculatePosY2(tile));
        }
    }
}
