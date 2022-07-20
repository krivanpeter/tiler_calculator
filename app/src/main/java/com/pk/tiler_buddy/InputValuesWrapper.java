package com.pk.tiler_buddy;

import java.io.Serializable;
import java.util.List;

// DataWrapper created to pass Obstacle(s) to another Activity
public class InputValuesWrapper implements Serializable {

    private TileDimensions tileDimensions;
    private WallDimensions wallDimensions;
    final private List<Obstacle> obstacles;

    public InputValuesWrapper(WallDimensions wallDimensions, TileDimensions tileDimensions, List<Obstacle> obstacles) {
        this.tileDimensions = tileDimensions;
        this.wallDimensions = wallDimensions;
        this.obstacles = obstacles;
    }

    public InputValuesWrapper(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public WallDimensions getWallDimensions() {
        return wallDimensions;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public TileDimensions getTileDimensions() {
        return tileDimensions;
    }
}
