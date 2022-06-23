package com.example.tiler_buddy;

import com.example.tiler_buddy.view.TileDimensions;
import com.example.tiler_buddy.view.WallDimensions;

import java.util.List;

public class Calculator {

    private static final double MM_TO_M_RATIO = 1_000_000;
    private static final double PERCENT_OF_WASTAGE = 1.1;

    public static double calculateTiles(double toBeTiledArea, TileDimensions tileDimensions, boolean isWastage) {
        if (isWastage) {
            return Math.ceil(toBeTiledArea / calculateTileArea(tileDimensions) * PERCENT_OF_WASTAGE);
        } else {
            return Math.ceil(toBeTiledArea / calculateTileArea(tileDimensions));
        }
    }

    public static int calculateToBeTiledArea(WallDimensions wallDimensions, int obstacleSize) {
        return calculateWallArea(wallDimensions) - obstacleSize;
    }

    public static double convertToMeter(double value) {
        return value / MM_TO_M_RATIO;
    }

    public static int calculateObstaclesArea(List<Obstacle> obstacles) {
        int allArea = 0;
        for (Obstacle obstacle : obstacles) {
            int area = calculateObstacleArea(obstacle);
            allArea += area;
        }
        return allArea;
    }

    public static double calculateNumberOfColumns(WallDimensions wallDimensions, TileDimensions tileDimensions) {
        return Math.ceil(wallDimensions.getLength() / (double) tileDimensions.getLength());
    }

    public static double calculateNumberOfRows(WallDimensions wallDimensions, TileDimensions tileDimensions) {
        return Math.ceil(wallDimensions.getHeight() / (double) tileDimensions.getHeight());
    }

    public static int calculatePosX2(Rectangle rectangle) {
        return rectangle.getX1() + rectangle.getLength();
    }

    public static int calculatePosY2(Rectangle rectangle) {
        return rectangle.getY1() + rectangle.getHeight();
    }

    public static int cutTileLengthRight(Tile tile, Obstacle obstacle) {
        return tile.getLength() - (tile.getX2() - obstacle.getX1());
    }

    public static int cutTileLengthLeft(Tile tile, Obstacle obstacle) {
        return tile.getLength() - (obstacle.getX2() - tile.getX1());
    }

    public static int cutTileHeightTop(Tile tile, Obstacle obstacle) {
        return tile.getHeight() - (tile.getY2() - obstacle.getY1());
    }

    public static int cutTileHeightBottom(Tile tile, Obstacle obstacle) {
        return tile.getHeight() - (obstacle.getY2() - tile.getY1());
    }

    public static int calculateNewPosX1(Tile tile, Obstacle obstacle){
        return tile.getX1() + (obstacle.getX2() - tile.getX1());
    }

    public static int calculateNewPosY1(Tile tile, Obstacle obstacle){
        return tile.getY1() + (obstacle.getY2() - tile.getY1());
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
