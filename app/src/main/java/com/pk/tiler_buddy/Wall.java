package com.pk.tiler_buddy;

import com.pk.tiler_buddy.view.TileDimensions;
import com.pk.tiler_buddy.view.WallDimensions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wall extends Rectangle implements Serializable {
    List<TileRow> wall = new ArrayList<>();

    public int size() {
        return wall.size();
    }

    public TileRow get(int i) {
        return wall.get(i);
    }

    public void setWall(WallDimensions wallDimensions, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        int numberOfRows = (int) Calculator.calculateNumberOfRows(wallDimensions, tileDimensions);
        int numberOfColumns = (int) Calculator.calculateNumberOfColumns(wallDimensions, tileDimensions);
        for (int i = 0; i < numberOfRows; i++) {
            TileRow tileRow = new TileRow();
            for (int j = 0; j < numberOfColumns; j++) {
                tileRow.setTiles(wallDimensions, tileDimensions, obstacles, i, j);
            }
            wall.add(tileRow);
        }
    }

    public void shiftOnX(int extent, List<Obstacle> obstacles, TileDimensions tileDimensions){
        for (TileRow tileRow : wall){
            tileRow.shiftOnX(extent, obstacles, tileDimensions);
        }
    }
}
