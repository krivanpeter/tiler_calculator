package com.example.tiler_buddy;

import com.example.tiler_buddy.view.TileDimensions;
import com.example.tiler_buddy.view.WallDimensions;

import java.util.List;

public class Calculator {

    private static final double MM_TO_M_RATIO = 1_000_000;
    private static final double PERCENT_OF_WASTAGE = 1.1;

    public static double calculateTiles(boolean isWastage, double toBeTiledArea, TileDimensions tileDimensions) {
        if (isWastage) {
            return Math.ceil(toBeTiledArea / calculateTileArea(tileDimensions) * PERCENT_OF_WASTAGE);
        } else {
            return Math.ceil(toBeTiledArea / calculateTileArea(tileDimensions));
        }
    }

    public static int calculateToBeTiledArea(WallDimensions wallDimensions, int obstacleSize) {
        return calculateWallArea(wallDimensions) - obstacleSize;
    }

    public static double convertToMeter(double value){
        return value/MM_TO_M_RATIO;
    }

    public static int calculateObstacleArea(List<Obstacle> obstacles) {
        int allArea = 0;
        for (Obstacle obstacle : obstacles) {
            int area = calculateObstacleArea(obstacle);
            allArea += area;
        }
        return allArea;
    }

    private static int calculateWallArea(WallDimensions wallDimensions) {
        return wallDimensions.getLength() * wallDimensions.getHeight();
    }

    private static int calculateObstacleArea(Obstacle obstacle) {
        return obstacle.getLength() * obstacle.getHeight();
    }

    private static int calculateTileArea(TileDimensions tileDimensions) {
        return tileDimensions.getLength() * tileDimensions.getHeight();
    }
}
