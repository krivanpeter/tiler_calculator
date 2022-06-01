package com.example.tiler_buddy;

import java.io.Serializable;

// DataWrapper created to pass Obstacle(s) to another Activity
public class CalculatedValuesWrapper implements Serializable {

    private final double toBeTiledArea;
    private final double numTiles;

    public CalculatedValuesWrapper(double toBeTiledArea, double numTiles) {
        this.toBeTiledArea = toBeTiledArea;
        this.numTiles = numTiles;
    }

    public double getToBeTiledArea() {
        return this.toBeTiledArea;
    }

    public double getNumTiles() {
        return this.numTiles;
    }

}
