package com.example.tiler_buddy;

import com.example.tiler_buddy.view.TileDimensions;
import com.example.tiler_buddy.view.WallDimensions;

import java.util.List;

public class Calculator {

    private static final double MM_TO_M_RATIO = 1_000_000;
    private static final double PERCENT_OF_WASTAGE = 1.1;

    public static double calculateTiles(boolean isWastage, float wall_area, int tile_area) {
        if (isWastage) {
            return Math.ceil(wall_area / tile_area * PERCENT_OF_WASTAGE);
        } else {
            return Math.ceil(wall_area / tile_area);
        }
    }

    public static int calculateWallArea(WallDimensions wallDimensions, int obstacleSize) {
        return wallDimensions.getLength() * wallDimensions.getHeight() - obstacleSize;
    }

    public static int calculateTileArea(TileDimensions tileDimensions) {
        return tileDimensions.getLength() * tileDimensions.getHeight();
    }

    public static double convertToMeter(int value){
        return value/MM_TO_M_RATIO;
    }

    public static int calculateObstacleArea(List<Obstacle> obstacles) {
        int all_area = 0;
        for (Obstacle obstacle : obstacles) {
            int area = calculateObstacleArea(obstacle);
            all_area += area;
        }
        return all_area;
    }

    private static int calculateObstacleArea(Obstacle obstacle) {
        return obstacle.getLength() * obstacle.getHeight();
    }
}
