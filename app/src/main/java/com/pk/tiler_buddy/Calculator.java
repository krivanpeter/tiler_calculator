package com.pk.tiler_buddy;

import com.pk.tiler_buddy.view.TileDimensions;
import com.pk.tiler_buddy.view.WallDimensions;

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

    public static double calculateNumberOfColumns(int rowLength, TileDimensions tileDimensions) {
        return Math.ceil(rowLength / (double) tileDimensions.getLength());
    }

    public static double calculateNumberOfRows(WallDimensions wallDimensions, TileDimensions tileDimensions) {
        return Math.ceil(wallDimensions.getHeight() / (double) tileDimensions.getHeight());
    }

    public static int calculateLength(Rectangle rectangle) {
        return rectangle.getX2() - rectangle.getX1();
    }

    public static int calculatePosX2(Rectangle rectangle) {
        return rectangle.getX1() + rectangle.getLength();
    }

    public static int calculatePosX1(Rectangle rectangle) {
        return rectangle.getX2() - rectangle.getLength();
    }

    public static boolean isPositive(int number) {
        if (number < 0) {
            return false;
        }
        return true;
    }

    public static int calculatePosY2(Rectangle rectangle) {
        return rectangle.getY1() + rectangle.getHeight();
    }

    public static int cutTileLengthLeft(Tile tile, Obstacle obstacle) {
        return tile.getLength() - (tile.getX2() - obstacle.getX2());
    }

    public static int cutTileLengthRight(Tile tile, Obstacle obstacle) {
        return tile.getLength() - (tile.getX2() - obstacle.getX1());
    }

    public static int cutTileHeightTop(Tile tile, Obstacle obstacle) {
        return tile.getHeight() - (tile.getY2() - obstacle.getY1());
    }

    public static int cutTileHeightBottom(Tile tile, Obstacle obstacle) {
        return tile.getHeight() - (obstacle.getY2() - tile.getY1());
    }

    public static int calculateX3RightCorner(Tile tile, Obstacle obstacle) {
        return tile.getX1() + Calculator.cutTileLengthRight(tile, obstacle);
    }

    public static int calculateX3LeftCorner(Tile tile, Obstacle obstacle) {
        return tile.getX1() + Calculator.cutTileLengthLeft(tile, obstacle);
    }

    public static int calculateY3TopCorner(Tile tile, Obstacle obstacle) {
        return tile.getY1() + Calculator.cutTileHeightTop(tile, obstacle);
    }

    public static int calculateY3BottomCorner(Tile tile, Obstacle obstacle) {
        return tile.getY2() - Calculator.cutTileHeightBottom(tile, obstacle);
    }

    public static int calculateNewPosX1(Tile tile, Obstacle obstacle) {
        return tile.getX1() + (obstacle.getX2() - tile.getX1());
    }

    public static int calculateNewPosY1(Tile tile, Obstacle obstacle) {
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
}
