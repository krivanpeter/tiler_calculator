package com.pk.tiler_buddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wall extends Rectangle implements Serializable {
    private final List<TileRow> tileRows = new ArrayList<>();
    private int numberOfRows;

    public Wall(WallDimensions wallDimensions, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        length = wallDimensions.getLength();
        height = wallDimensions.getHeight();
        x2 = Calculator.calculatePosX2(this);
        y2 = Calculator.calculatePosY2(this);
        numberOfRows = (int) Calculator.calculateNumberOfRows(wallDimensions, tileDimensions);
        setRows(tileDimensions, obstacles);
    }

    public Wall() {
    }

    public int size() {
        return tileRows.size();
    }

    public TileRow getRow(int i) {
        return tileRows.get(i);
    }

    public void setRows(TileDimensions tileDimensions, List<Obstacle> obstacles) {
        for (int i = 0; i < numberOfRows; i++) {
            TileRow tileRow = new TileRow((int) Calculator.calculateNumberOfColumns(length, tileDimensions), tileDimensions);
            tileRow.setLength(this.length);
            tileRow.setHeight(Math.min(this.height - i * tileDimensions.getHeight(), tileDimensions.getHeight()));
            tileRow.setY1(i * tileDimensions.getHeight());
            tileRow.setRectXY2(Calculator.calculatePosX2(tileRow), Calculator.calculatePosY2(tileRow));
            tileRow.addTiles(obstacles);
            tileRows.add(tileRow);
        }
    }

    public void shiftHorizontally(int extent, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        if (extent > tileDimensions.getLength()) {
            extent = extent - tileDimensions.getLength();
        }
        for (TileRow tileRow : tileRows) {
            tileRow.shiftHorizontally(extent, obstacles);
        }
    }
}
