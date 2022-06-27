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
        return tile.getX1() < obstacle.getX2() && tile.getX2() > obstacle.getX1();
    }

    public static boolean isFullyOverlapping(Tile tile, Obstacle obstacle) {
        return isOverlapping(tile, obstacle) &&
                tile.getX1() >= obstacle.getX1() && tile.getX2() <= obstacle.getX2() &&
                tile.getY1() >= obstacle.getY1() && tile.getY2() <= obstacle.getY2();
    }

    static boolean isXOverlapping(Tile tile, Obstacle obstacle, int point) {
        if (tile.getY1() >= obstacle.getY2() || tile.getY2() <= obstacle.getY1()) {
            return false;
        }
        if (point >= obstacle.getX2() || point <= obstacle.getX1()) {
            return false;
        }
        return !isBottomLeftCornerOverlapping(tile, obstacle) && !isTopLeftCornerOverlapping(tile, obstacle);
    }

    private static boolean isLeftOverlapping(Tile tile, Obstacle obstacle) {
        return isOverlapping(tile, obstacle) && tile.getX1() < obstacle.getX2() && tile.getX2() > obstacle.getX2();
    }

    private static boolean isOnlyLeftOverlapping(Tile tile, Obstacle obstacle) {
        return isLeftOverlapping(tile, obstacle) && !isBottomOverlapping(tile, obstacle) && !isTopOverlapping(tile, obstacle);
    }

    private static boolean isRightOverlapping(Tile tile, Obstacle obstacle) {
        return isOverlapping(tile, obstacle) && tile.getX1() < obstacle.getX1() && tile.getX2() > obstacle.getX1();
    }

    private static boolean isOnlyRightOverlapping(Tile tile, Obstacle obstacle) {
        return isRightOverlapping(tile, obstacle) && !isBottomOverlapping(tile, obstacle) && !isTopOverlapping(tile, obstacle);
    }

    private static boolean isBottomOverlapping(Tile tile, Obstacle obstacle) {
        return isOverlapping(tile, obstacle) && tile.getY1() < obstacle.getY2() && tile.getY2() > obstacle.getY2();
    }

    private static boolean isOnlyBottomOverlapping(Tile tile, Obstacle obstacle) {
        return isBottomOverlapping(tile, obstacle) && !isLeftOverlapping(tile, obstacle) && !isRightOverlapping(tile, obstacle);
    }

    private static boolean isTopOverlapping(Tile tile, Obstacle obstacle) {
        return isOverlapping(tile, obstacle) && tile.getY1() < obstacle.getY1() && tile.getY2() > obstacle.getY1();
    }

    private static boolean isOnlyTopOverlapping(Tile tile, Obstacle obstacle) {
        return isTopOverlapping(tile, obstacle) && !isLeftOverlapping(tile, obstacle) && !isRightOverlapping(tile, obstacle);
    }

    private static boolean isBottomLeftCornerOverlapping(Tile tile, Obstacle obstacle) {
        return isBottomOverlapping(tile, obstacle) && isLeftOverlapping(tile, obstacle);
    }

    static boolean isTopLeftCornerOverlapping(Tile tile, Obstacle obstacle) {
        return isTopOverlapping(tile, obstacle) && isLeftOverlapping(tile, obstacle);
    }

    private static boolean isTopRightCornerOverlapping(Tile tile, Obstacle obstacle) {
        return isTopOverlapping(tile, obstacle) && isRightOverlapping(tile, obstacle);
    }

    private static boolean isBottomRightCornerOverlapping(Tile tile, Obstacle obstacle) {
        return isBottomOverlapping(tile, obstacle) && isRightOverlapping(tile, obstacle);
    }
}
