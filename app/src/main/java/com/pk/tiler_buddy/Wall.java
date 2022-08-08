package com.pk.tiler_buddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wall extends Rectangle implements Serializable {
    private final TileDimensions tileDimensions;
    private final List<TileRow> tileRows = new ArrayList<>();
    private final int numberOfRows;
    private final List<Obstacle> obstacles;


    public Wall(WallDimensions wallDimensions, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        this.tileDimensions = tileDimensions;
        length = wallDimensions.getLength();
        height = wallDimensions.getHeight();
        x2 = Calculator.calculatePosX2(this);
        y2 = Calculator.calculatePosY2(this);
        numberOfRows = (int) Calculator.calculateNumberOfRows(wallDimensions, tileDimensions);
        this.obstacles = obstacles;
        setRows();
    }

    public List<TileRow> getTileRows() {
        return tileRows;
    }

    public TileRow getTileRow(int i) {
        return tileRows.get(i);
    }

    public void setRows() {
        for (int i = 0; i < numberOfRows; i++) {
            TileRow tileRow = new TileRow((int) Calculator.calculateNumberOfColumns(length, tileDimensions), tileDimensions, obstacles);
            tileRow.setLength(this.length);
            tileRow.setHeight(Math.min(this.height - i * tileDimensions.getHeight(), tileDimensions.getHeight()));
            tileRow.setY1(i * tileDimensions.getHeight());
            tileRow.setRectXY2(Calculator.calculatePosX2(tileRow), Calculator.calculatePosY2(tileRow));
            tileRow.addTiles();
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

    public void shiftQuarterHorizontally(Boolean quarterShiftButtonClicked) {
        if (!quarterShiftButtonClicked) {
            tileRows.clear();
            setRows();
            int quarterValue = tileDimensions.getLength() / 4;
            int shiftCounter = 0;
            for (int i = 0; i < tileRows.size() - 1; i++) {
                if (i % 4 == 0) {
                    shiftCounter = 0;
                }
                tileRows.get(i).shiftHorizontally(quarterValue * shiftCounter);
                shiftCounter++;
            }
        } else {
            tileRows.clear();
            setRows();
        }
    }

    public void shiftMiddleSymmetry(Boolean symmetryMiddleButtonClicked) {
        if (!symmetryMiddleButtonClicked) {
            tileRows.clear();
            setRows();
            int extent = getTileRow(0).getLastTile().getLength() / 2;
            shiftHorizontally(extent);
        } else {
            tileRows.clear();
            setRows();
        }
    }
}
