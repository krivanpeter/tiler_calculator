package com.example.tiler;

import java.io.Serializable;
import java.util.ArrayList;

// DataWrapper created to pass Obstacle(s) to another Activity
public class DataWrapper implements Serializable {

    private ArrayList<Obstacle> obstacles;

    public DataWrapper(ArrayList<Obstacle> data) {
        this.obstacles = data;
    }

    public ArrayList<Obstacle> getObstacles() {
        return this.obstacles;
    }
}
