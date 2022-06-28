package com.pk.tiler_buddy;

import com.pk.tiler_buddy.view.TileDimensions;
import com.pk.tiler_buddy.view.WallDimensions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wall extends Rectangle implements Serializable {
    int numberOfRows;
    List<TileRow> wall = new ArrayList<>();

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public List<TileRow> getWall() {
        return wall;
    }

    public int size() {
        return wall.size();
    }

    public TileRow getRow(int i) {
        return wall.get(i);
    }

    public void set(WallDimensions wallDimensions, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        length = wallDimensions.getLength();
        height = wallDimensions.getHeight();
        x2 = Calculator.calculatePosX2(this);
        y2 = Calculator.calculatePosY2(this);
        numberOfRows = (int) Calculator.calculateNumberOfRows(wallDimensions, tileDimensions);
        setRows(tileDimensions, obstacles);
    }

    public void setRows(TileDimensions tileDimensions, List<Obstacle> obstacles) {
        for (int i = 0; i < numberOfRows; i++) {
            TileRow tileRow = new TileRow();
            tileRow.setLength(this.length);
            tileRow.setHeight(Math.min(this.height - i * tileDimensions.getHeight(), tileDimensions.getHeight()));
            tileRow.setY1(i * tileDimensions.getHeight());
            tileRow.setRectXY2(Calculator.calculatePosX2(tileRow), Calculator.calculatePosY2(tileRow));
            tileRow.setNumberOfColumns((int) Calculator.calculateNumberOfColumns(tileRow.getLength(), tileDimensions));
            tileRow.setTiles(tileDimensions, obstacles);
            wall.add(tileRow);
        }
    }

    public void shiftOnX(int extent, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        if (extent > tileDimensions.getLength()) {
            extent = extent - tileDimensions.getLength();
        }
        for (TileRow tileRow : wall) {
            tileRow.shiftOnX(extent, tileDimensions, obstacles);
        }
    }
}
