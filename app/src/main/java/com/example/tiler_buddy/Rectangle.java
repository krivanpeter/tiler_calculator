package com.example.tiler_buddy;

import java.io.Serializable;

public class Rectangle implements Serializable {
    int x1;
    int y1;
    int x2;
    int y2;
    int length;
    int height;

    public Rectangle() {
    }

    public Rectangle(int length, int height, int x1, int y1, int x2, int y2) {
        this.length = length;
        this.height = height;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setRectXY1(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setRectXY2(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    // Thank you Shubhra Srivastava for the following method
    public boolean isOverlapping(Rectangle other) {
        if (this.y1 >= other.getY2() || this.y2 <= other.getY1()) {
            return false;
        }
        if (this.x1 >= other.getX2() || this.x2 <= other.getX1()) {
            return false;
        }
        return true;
    }

    public boolean isFullyOverlapping(Rectangle other) {
        if (this.isOverlapping(other) &&
                this.x1 <= other.getX1() && this.x2 >= other.getX2() &&
                this.y1 <= other.getY1() && this.y2 >= other.getY2()) {
            return true;
        }
        return false;
    }

    public boolean isLeftOverlapping(Rectangle other) {
        if (this.isOverlapping(other) && this.x1 > other.getX1() && this.x2 > other.getX2()) {
            return true;
        }
        return false;
    }

    public boolean isRightOverlapping(Rectangle other) {
        if (this.isOverlapping(other) && this.x1 < other.getX1() && this.x2 < other.getX2()) {
            return true;
        }
        return false;
    }

    public boolean isBottomOverlapping(Rectangle other) {
        if (this.isOverlapping(other) && this.y1 > other.getY1() && this.y2 > other.getY2()) {
            return true;
        }
        return false;
    }

    public boolean isTopOverlapping(Rectangle other) {
        if (this.isOverlapping(other) && this.y1 < other.getY1() && this.y2 < other.getY2()) {
            return true;
        }
        return false;
    }

    public boolean isBottomLeftCornerOverlapping(Rectangle other) {
        if (this.isBottomOverlapping(other) && this.isLeftOverlapping(other)) {
            return true;
        }
        return false;
    }

    public boolean isBottomRightCornerOverlapping(Rectangle other) {
        if (this.isBottomOverlapping(other) && this.isRightOverlapping(other)) {
            return true;
        }
        return false;
    }
    public boolean isTopLeftCornerOverlapping(Rectangle other) {
        if (this.isTopOverlapping(other) && this.isLeftOverlapping(other)) {
            return true;
        }
        return false;
    }
    public boolean isTopRightCornerOverlapping(Rectangle other) {
        if (this.isTopOverlapping(other) && this.isRightOverlapping(other)) {
            return true;
        }
        return false;
    }

}