package com.pk.tiler_buddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wall extends Rectangle implements Serializable {
    private final TileDimensions tileDimensions;
    private final List<Obstacle> obstacles;
    private final List<TileRow> tileRows = new ArrayList<>();
    private final int numberOfRows;


    public Wall(WallDimensions wallDimensions, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        this.tileDimensions = tileDimensions;
        this.obstacles = obstacles;
        length = wallDimensions.getLength();
        height = wallDimensions.getHeight();
        x2 = Calculator.calculatePosX2(this);
        y2 = Calculator.calculatePosY2(this);
        numberOfRows = (int) Calculator.calculateNumberOfRows(wallDimensions, tileDimensions);
        setRows(tileDimensions, obstacles);
    }

    public TileDimensions getTileDimensions() {
        return tileDimensions;
    }

    public int getNumberOfTileRows() {
        return tileRows.size();
    }

    public List<TileRow> getTileRows() {
        return tileRows;
    }

    public TileRow getTileRow(int i) {
        return tileRows.get(i);
    }

    public void setRows(TileDimensions tileDimensions, List<Obstacle> obstacles) {
        for (int i = 0; i < numberOfRows; i++) {
            TileRow tileRow = new TileRow((int) Calculator.calculateNumberOfColumns(length, tileDimensions), tileDimensions, obstacles);
            tileRow.setLength(this.length);
            tileRow.setHeight(Math.min(this.height - i * tileDimensions.getHeight(), tileDimensions.getHeight()));
            tileRow.setY1(i * tileDimensions.getHeight());
            tileRow.setRectXY2(Calculator.calculatePosX2(tileRow), Calculator.calculatePosY2(tileRow));
            tileRow.addTiles(obstacles);
            tileRows.add(tileRow);
        }
    }

    public void shiftHorizontally(int extent) {
        if (extent > tileDimensions.getLength()) {
            extent = extent - tileDimensions.getLength();
        }
        for (TileRow tileRow : tileRows) {
            tileRow.shiftHorizontally(extent);
        }
    }
}
