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
    private final List<Tile> tiles = new ArrayList<>();

    public TileRow(int numberOfColumns, TileDimensions tileDimensions) {
        this.numberOfColumns = numberOfColumns;
        this.tileDimensions = tileDimensions;
    }

    public void addTiles(List<Obstacle> obstacles) {
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
        Tile tile = new Tile();
        tile.setRectXY1(placeInRow * tileDimensions.getLength(), y1);
        tile.setHeight(height);
        tile.setLength(Math.min(length - placeInRow * tileDimensions.getLength(), tileDimensions.getLength()));
        tile.setRectXY2(Calculator.calculatePosX2(tile), Calculator.calculatePosY2(tile));
        return tile;
    }

    public void shiftHorizontally(int extent, List<Obstacle> obstacles) {
        tiles.clear();
        if (extent > tileDimensions.getLength()) {
            extent = extent - tileDimensions.getLength();
        }
        int allLength = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            Tile tile = createTile(i);
            shiftTileHorizontally(extent, tile);
            allLength = allLength + tile.getLength();
            if (!Overlap.isFullyOverlapping(tile, obstacles)) {
                tiles.add(tile);
            }
            cutTiles(tile, obstacles);
        }
        fillUp(allLength, extent);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < this.tiles.size(); i++) {
            Tile tile = this.tiles.get(i);
            tile.draw(canvas, paint);
        }
    }

    private void cutTiles(Tile tile, List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            tile.cut(obstacle);
        }
    }

    private void shiftTileHorizontally(int extent, Tile tile) {
        if (extent > tileDimensions.getLength()) {
            extent = extent - (extent / tileDimensions.getLength()) * tileDimensions.getLength();
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
        if (tile.getX2() + extent >= length) {
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
            Tile tile = new Tile();
            if (Calculator.isPositive(extent)) {
                tile.setX1(x1);
                tiles.add(0, tile);
            } else {
                tile.setX1(length - (length - allLength));
                tiles.add(tiles.size() - 1, tile);
            }
            tile.setY1(y1);
            tile.setHeight(height);
            tile.setLength(Math.abs(extent));
            tile.setX2(Calculator.calculatePosX2(tile));
            tile.setY2(Calculator.calculatePosY2(tile));
        }
    }
}
