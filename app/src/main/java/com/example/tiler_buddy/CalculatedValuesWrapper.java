package com.example.tiler_buddy;

import java.io.Serializable;
import java.util.List;

// DataWrapper created to pass Obstacle(s) to another Activity
public class CalculatedValuesWrapper implements Serializable {

    private final List<Obstacle> obstacle;
    private final List<List<Tile>> tiles;

    public CalculatedValuesWrapper(List<Obstacle> obstacle, List<List<Tile>> tiles) {
        this.obstacle = obstacle;
        this.tiles = tiles;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacle;
    }

    public List<List<Tile>> getTiles(){
        return this.tiles;
    }

}
