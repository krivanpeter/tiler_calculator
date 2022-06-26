package com.pk.tiler_buddy;

public class Overlap {

    public static String whereOverlap(Tile tile, Obstacle obstacle) {
        if (isOnlyLeftOverlapping(tile, obstacle)) {
            return "onlyLeft";
        }
        if (isOnlyTopOverlapping(tile, obstacle)) {
            return "onlyTop";
        }
        if (isOnlyRightOverlapping(tile, obstacle)) {
            return "onlyRight";
        }
        if (isOnlyBottomOverlapping(tile, obstacle)) {
            return "onlyBottom";
        }
        if (isBottomLeftCornerOverlapping(tile, obstacle)) {
            return "bottomLeftCorner";
        }
        if (isTopLeftCornerOverlapping(tile, obstacle)) {
            return "topLeftCorner";
        }
        if (isTopRightCornerOverlapping(tile, obstacle)) {
            return "topRightCorner";
        }
        if (isBottomRightCornerOverlapping(tile, obstacle)) {
            return "bottomRightCorner";
        }
        return "";
    }

    // Thank you Shubhra Srivastava for the following method
    public static boolean isOverlapping(Tile tile, Obstacle obstacle) {
        if (tile.getY1() >= obstacle.getY2() || tile.getY2() <= obstacle.getY1()) {
            return false;
        }
        if (tile.getX1() >= obstacle.getX2() || tile.getX2() <= obstacle.getX1()) {
            return false;
        }
        return true;
    }

    public static boolean isFullyOverlapping(Tile tile, Obstacle obstacle) {
        if (isOverlapping(tile, obstacle) &&
                tile.getX1() >= obstacle.getX1() && tile.getX2() <= obstacle.getX2() &&
                tile.getY1() >= obstacle.getY1() && tile.getY2() <= obstacle.getY2()) {
            return true;
        }
        return false;
    }

    private static boolean isLeftOverlapping(Tile tile, Obstacle obstacle) {
        if (isOverlapping(tile, obstacle) && tile.getX1() < obstacle.getX2() && tile.getX2() > obstacle.getX2()) {
            return true;
        }
        return false;
    }

    private static boolean isOnlyLeftOverlapping(Tile tile, Obstacle obstacle) {
        if (isLeftOverlapping(tile, obstacle) && !isBottomOverlapping(tile, obstacle) && !isTopOverlapping(tile, obstacle)) {
            return true;
        }
        return false;
    }

    private static boolean isRightOverlapping(Tile tile, Obstacle obstacle) {
        if (isOverlapping(tile, obstacle) && tile.getX1() < obstacle.getX1() && tile.getX2() > obstacle.getX1()) {
            return true;
        }
        return false;
    }

    private static boolean isOnlyRightOverlapping(Tile tile, Obstacle obstacle) {
        if (isRightOverlapping(tile, obstacle) && !isBottomOverlapping(tile, obstacle) && !isTopOverlapping(tile, obstacle)) {
            return true;
        }
        return false;
    }

    private static boolean isBottomOverlapping(Tile tile, Obstacle obstacle) {
        if (isOverlapping(tile, obstacle) && tile.getY1() < obstacle.getY2() && tile.getY2() > obstacle.getY2()) {
            return true;
        }
        return false;
    }

    private static boolean isOnlyBottomOverlapping(Tile tile, Obstacle obstacle) {
        if (isBottomOverlapping(tile, obstacle) && !isLeftOverlapping(tile, obstacle) && !isRightOverlapping(tile, obstacle)) {
            return true;
        }
        return false;
    }

    private static boolean isTopOverlapping(Tile tile, Obstacle obstacle) {
        if (isOverlapping(tile, obstacle) && tile.getY1() < obstacle.getY1() && tile.getY2() > obstacle.getY1()) {
            return true;
        }
        return false;
    }

    private static boolean isOnlyTopOverlapping(Tile tile, Obstacle obstacle) {
        if (isTopOverlapping(tile, obstacle) && !isLeftOverlapping(tile, obstacle) && !isRightOverlapping(tile, obstacle)) {
            return true;
        }
        return false;
    }

    private static boolean isBottomLeftCornerOverlapping(Tile tile, Obstacle obstacle) {
        if (isBottomOverlapping(tile, obstacle) && isLeftOverlapping(tile, obstacle)) {
            return true;
        }
        return false;
    }

    private static boolean isTopLeftCornerOverlapping(Tile tile, Obstacle obstacle) {
        if (isTopOverlapping(tile, obstacle) && isLeftOverlapping(tile, obstacle)) {
            return true;
        }
        return false;
    }

    private static boolean isTopRightCornerOverlapping(Tile tile, Obstacle obstacle) {
        if (isTopOverlapping(tile, obstacle) && isRightOverlapping(tile, obstacle)) {
            return true;
        }
        return false;
    }

    private static boolean isBottomRightCornerOverlapping(Tile tile, Obstacle obstacle) {
        if (isBottomOverlapping(tile, obstacle) && isRightOverlapping(tile, obstacle)) {
            return true;
        }
        return false;
    }
}
