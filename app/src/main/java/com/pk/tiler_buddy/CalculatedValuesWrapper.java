package com.pk.tiler_buddy;

import com.pk.tiler_buddy.view.TileDimensions;

import java.io.Serializable;
import java.util.List;

// DataWrapper created to pass Obstacle(s) to another Activity
public class CalculatedValuesWrapper implements Serializable {

    private final double toBeTiledArea;
    private final double numOfTiles;
    private final List<Obstacle> obstacle;
    private final Wall wall;
    private final TileDimensions tileDimensions;

    public CalculatedValuesWrapper(double toBeTiledArea, double numOfTiles, List<Obstacle> obstacle, Wall wall, TileDimensions tileDimensions) {
        this.toBeTiledArea = toBeTiledArea;
        this.numOfTiles = numOfTiles;
        this.obstacle = obstacle;
        this.wall = wall;
        this.tileDimensions = tileDimensions;
    }

    public double getToBeTiledArea() {
        return this.toBeTiledArea;
    }

    public double getNumOfTiles() {
        return this.numOfTiles;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacle;
    }

    public Wall getWall() {
        return this.wall;
    }

    public TileDimensions getTileDimensions() {
        return tileDimensions;
    }
}
