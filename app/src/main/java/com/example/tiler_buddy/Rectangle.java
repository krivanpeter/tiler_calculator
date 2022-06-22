package com.example.tiler_buddy;

import java.io.Serializable;

public class Rectangle implements Serializable {
    int length;
    int height;
    Position leftBottomPosition = new Position();
    Position topRightPosition = new Position();

    public Rectangle() {
    }

    public Rectangle(int length, int height, Position leftBottomPosition, Position topRightPosition) {
        this.length = length;
        this.height = height;
        this.leftBottomPosition = leftBottomPosition;
        this.topRightPosition = topRightPosition;
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

    public Position getLeftBottomPosition() {
        return leftBottomPosition;
    }

    public Integer getPosX1() {
        return leftBottomPosition.getPosX();
    }

    public Integer getPosY1() {
        return leftBottomPosition.getPosY();
    }

    public Position getTopRightPosition() {
        return topRightPosition;
    }

    public Integer getPosX2() {
        return topRightPosition.getPosX();
    }

    public Integer getPosY2() {
        return topRightPosition.getPosY();
    }

    public void setRectXY1(Integer posX1, Integer posY1) {
        this.leftBottomPosition.posX = posX1;
        this.leftBottomPosition.posY = posY1;
    }

    public void setRectXY2(Integer posX2, Integer posY2) {
        this.topRightPosition.posX = posX2;
        this.topRightPosition.posY = posY2;
    }

    // Thank you Shubhra Srivastava for the following method
    public boolean isOverlapping(Rectangle other) {
        if (this.getPosY1() >= other.getPosY2() || this.getPosY2() <= other.getPosY1()) {
            return false;
        }
        if (this.getPosX1() >= other.getPosX2() || this.getPosX2() <= other.getPosX1()) {
            return false;
        }
        return true;
    }

    public boolean isFullyOverlapping(Rectangle other) {
        if (this.getPosX1() <= other.getPosX1() && this.getPosX2() >= other.getPosX2() &&
                this.getPosY1() <= other.getPosY1() && this.getPosY2() >= other.getPosY2()) {
            return true;
        }
        return false;
    }

    public boolean isLeftOverlapping(Rectangle other) {
        if (this.getPosX1() > other.getPosX1() && this.getPosX2() > other.getPosX2()) {
            return true;
        }
        return false;
    }

    public boolean isRightOverlapping(Rectangle other) {
        if (this.getPosX1() < other.getPosX1() && this.getPosX2() < other.getPosX2()) {
            return true;
        }
        return false;
    }

    public boolean isBottomOverlapping(Rectangle other) {
        if (this.getPosY1() > other.getPosY1() && this.getPosY2() > other.getPosY2()) {
            return true;
        }
        return false;
    }

    public boolean isTopOverlapping(Rectangle other) {
        if (this.getPosY1() < other.getPosY1() && this.getPosY2() < other.getPosY2()) {
            return true;
        }
        return false;
    }
}