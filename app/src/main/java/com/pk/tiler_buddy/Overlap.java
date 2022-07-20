package com.pk.tiler_buddy;

import static com.pk.tiler_buddy.OverlapPosition.BOTTOM_LEFT_CORNER;
import static com.pk.tiler_buddy.OverlapPosition.BOTTOM_RIGHT_CORNER;
import static com.pk.tiler_buddy.OverlapPosition.ONLY_BOTTOM;
import static com.pk.tiler_buddy.OverlapPosition.ONLY_LEFT;
import static com.pk.tiler_buddy.OverlapPosition.ONLY_RIGHT;
import static com.pk.tiler_buddy.OverlapPosition.ONLY_TOP;
import static com.pk.tiler_buddy.OverlapPosition.TOP_LEFT_CORNER;
import static com.pk.tiler_buddy.OverlapPosition.TOP_RIGHT_CORNER;

import java.util.List;

public class Overlap {

    public static OverlapPosition whereOverlap(Tile tile, Obstacle obstacle) {
        if (isOnlyLeftOverlapping(tile, obstacle)) {
            return ONLY_LEFT;
        }
        if (isOnlyTopOverlapping(tile, obstacle)) {
            return ONLY_TOP;
        }
        if (isOnlyRightOverlapping(tile, obstacle)) {
            return ONLY_RIGHT;
        }
        if (isOnlyBottomOverlapping(tile, obstacle)) {
            return ONLY_BOTTOM;
        }
        if (isBottomLeftCornerOverlapping(tile, obstacle)) {
            return BOTTOM_LEFT_CORNER;
        }
        if (isTopLeftCornerOverlapping(tile, obstacle)) {
            return TOP_LEFT_CORNER;
        }
        if (isTopRightCornerOverlapping(tile, obstacle)) {
            return TOP_RIGHT_CORNER;
        }
        if (isBottomRightCornerOverlapping(tile, obstacle)) {
            return BOTTOM_RIGHT_CORNER;
        }
        return null;
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

    public static boolean isFullyOverlapping(Tile tile, List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (isFullyOverlapping(tile, obstacle)) {
                return true;
            }
        }
        return false;
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
