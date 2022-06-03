package com.example.tiler_buddy;

import java.io.Serializable;
import java.util.List;

// DataWrapper created to pass Obstacle(s) to another Activity
public class CalculatedValuesWrapper implements Serializable {

    private final double toBeTiledArea;
    private final double numOfTiles;
    private final List<Obstacle> obstacle;
    private final List<List<Tile>> tiles;

    public CalculatedValuesWrapper(double toBeTiledArea, double numOfTiles, List<Obstacle> obstacle, List<List<Tile>> tiles) {
        this.toBeTiledArea = toBeTiledArea;
        this.numOfTiles = numOfTiles;
        this.obstacle = obstacle;
        this.tiles = tiles;
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

    public List<List<Tile>> getTiles() {
        return this.tiles;
    }

}
