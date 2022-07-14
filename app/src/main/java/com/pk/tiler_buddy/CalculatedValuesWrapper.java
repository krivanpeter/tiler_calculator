package com.pk.tiler_buddy;

import java.io.Serializable;
import java.util.List;

// DataWrapper created to pass Obstacle(s) to another Activity
public class CalculatedValuesWrapper implements Serializable {

    private double toBeTiledArea;
    private double numOfTiles;
    private TileDimensions tileDimensions;
    private WallDimensions wallDimensions;
    private List<Obstacle> obstacles;
    private Wall wall;
    private String backgroundImageUrl;

    public CalculatedValuesWrapper(double toBeTiledArea, double numOfTiles, TileDimensions tileDimensions, WallDimensions wallDimensions, List<Obstacle> obstacles, Wall wall) {
        this.toBeTiledArea = toBeTiledArea;
        this.numOfTiles = numOfTiles;
        this.tileDimensions = tileDimensions;
        this.wallDimensions = wallDimensions;
        this.obstacles = obstacles;
        this.wall = wall;
    }

    public CalculatedValuesWrapper(List<Obstacle> obstacles, Wall wall) {
        this.obstacles = obstacles;
        this.wall = wall;
    }

    public CalculatedValuesWrapper(List<Obstacle> obstacles, Wall wall, String backgroundImageUrl) {
        this.obstacles = obstacles;
        this.wall = wall;
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public CalculatedValuesWrapper(WallDimensions wallDimensions) {
        this.wallDimensions = wallDimensions;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public WallDimensions getWallDimensions() {
        return wallDimensions;
    }

    public double getToBeTiledArea() {
        return this.toBeTiledArea;
    }

    public double getNumOfTiles() {
        return this.numOfTiles;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public Wall getWall() {
        return this.wall;
    }

    public TileDimensions getTileDimensions() {
        return tileDimensions;
    }
}
