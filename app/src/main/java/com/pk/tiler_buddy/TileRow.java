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

    public void addTile(Tile tile) {
        this.row.add(tile);
    }

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
            addTile(tile);
            cutTiles(tile, obstacles);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < this.row.size(); i++) {
            Tile tile = this.row.get(i);
            tile.draw(canvas, paint);
        }
    }

    public void cutTiles(Tile tile, List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (Overlap.isFullyOverlapping(tile, obstacle)) {
                removeTile(tile);
            }
            tile.cut(obstacle);
        }
    }

    public void shiftOnX(int extent, List<Obstacle> obstacles, Wall wall, TileDimensions tileDimensions) {
        for (Tile tile : row) {
            tile.shiftOnX(extent, obstacles, wall, tileDimensions);
        }
    }
}
