package com.example.tiler_buddy;

import com.example.tiler_buddy.view.TileDimensions;
import com.example.tiler_buddy.view.WallDimensions;

import java.util.List;

public class Calculator {

    private static final double MM_TO_M_RATIO = 1_000_000;
    private static final double PERCENT_OF_WASTAGE = 1.1;

    //Calculate all the tiles needed
    public static double calculateTiles(boolean isWastage, WallDimensions wallDimensions, TileDimensions tileDimensions) {
        if (isWastage) {
            return Math.ceil(calculateTilesPerRow(wallDimensions, tileDimensions) * calculateTilesPerColumn(wallDimensions, tileDimensions) * PERCENT_OF_WASTAGE);
        } else {
            return Math.ceil(calculateTilesPerRow(wallDimensions, tileDimensions) * calculateTilesPerColumn(wallDimensions, tileDimensions));
        }
    }

    public static double calculateTilesPerRow(WallDimensions wallDimensions, TileDimensions tileDimensions) {
        return calculateRowArea(wallDimensions, tileDimensions) / calculateTileArea(tileDimensions);
    }

    public static double calculateTilesPerColumn(WallDimensions wallDimensions, TileDimensions tileDimensions) {
        return calculateColumnArea(wallDimensions, tileDimensions) / calculateTileArea(tileDimensions);
    }

    public static int calculateToBeTiledArea(WallDimensions wallDimensions, int obstacleSize) {
        return calculateWallArea(wallDimensions) - obstacleSize;
    }

    public static double convertToMeter(double value) {
        return value / MM_TO_M_RATIO;
    }

    public static int calculateObstacleArea(List<Obstacle> obstacles) {
        int allArea = 0;
        for (Obstacle obstacle : obstacles) {
            int area = calculateObstacleArea(obstacle);
            allArea += area;
        }
        return allArea;
    }

    public static int calculateNumberOfRows(WallDimensions wallDimensions, TileDimensions tileDimensions){
        return wallDimensions.getLength() / tileDimensions.getLength();
    }

    public static int calculateNumberOfColumns(WallDimensions wallDimensions, TileDimensions tileDimensions){
        return wallDimensions.getHeight() / tileDimensions.getHeight();
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

    private static double calculateRowArea(WallDimensions wallDimensions, TileDimensions tileDimensions) {
        return wallDimensions.getLength() * tileDimensions.getHeight();
    }

    private static double calculateColumnArea(WallDimensions wallDimensions, TileDimensions tileDimensions) {
        return wallDimensions.getHeight() * tileDimensions.getLength();
    }

}
