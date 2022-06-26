package com.pk.tiler_buddy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.pk.tiler_buddy.view.TileDimensions;
import com.pk.tiler_buddy.view.WallDimensions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TileRow implements Serializable {
    List<Tile> row = new ArrayList<>();

    public void addTile(Tile tile) {
        this.row.add(tile);
    }

    public void removeTile(Tile tile) {
        this.row.remove(tile);
    }
    
    public void setTiles(WallDimensions wallDimensions, TileDimensions tileDimensions, List<Obstacle> obstacles, int i, int j) {
        Tile tile = new Tile();
        tile.setRectXY1(j * tileDimensions.getLength(), i * tileDimensions.getHeight());
        tile.setHeight(Math.min(wallDimensions.getHeight() - i * tileDimensions.getHeight(), tileDimensions.getHeight()));
        tile.setLength(Math.min(wallDimensions.getLength() - j * tileDimensions.getLength(), tileDimensions.getLength()));
        tile.setRectXY2(Calculator.calculatePosX2(tile), Calculator.calculatePosY2(tile));
        addTile(tile);
        cutTiles(obstacles);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < this.row.size(); i++) {
            Tile tile = this.row.get(i);
            tile.draw(canvas, paint);
        }
    }

    public void cutTiles(List<Obstacle> obstacles) {
        for (Tile tile : row) {
            for (Obstacle obstacle : obstacles) {
                if (Overlap.isFullyOverlapping(tile, obstacle)) {
                    removeTile(tile);
                }
                if (Overlap.isOverlapping(tile, obstacle)) {
                    tile.cut(obstacle);
                }
            }
        }
    }

    public void shiftOnX(int extent, List<Obstacle> obstacles) {
        for (Tile tile : row) {
            tile.shift(extent, obstacles);
        }
    }
}
