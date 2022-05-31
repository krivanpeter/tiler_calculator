package com.example.tiler_buddy;

import java.io.Serializable;
import java.util.List;

// DataWrapper created to pass Obstacle(s) to another Activity
public class DataWrapper implements Serializable {

    private final List<Obstacle> obstacles;

    public DataWrapper(List<Obstacle> data) {
        this.obstacles = data;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }
}
