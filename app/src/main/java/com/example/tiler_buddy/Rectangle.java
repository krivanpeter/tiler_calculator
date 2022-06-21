package com.example.tiler_buddy;

import java.io.Serializable;

public class Rectangle implements Serializable {
    int length;
    int height;
    Position position1 = new Position();
    Position position2 = new Position();

    public Rectangle() {
    }

    public Rectangle(int length, int height, Position position1, Position position2) {
        this.length = length;
        this.height = height;
        this.position1 = position1;
        this.position2 = position2;
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

    public Position getPosition1() {
        return position1;
    }

    public Integer getPosX1() {
        return position1.getPosX();
    }

    public Integer getPosY1() {
        return position1.getPosY();
    }

    public Position getPosition2() {
        return position2;
    }

    public Integer getPosX2() {
        return position2.getPosX();
    }

    public Integer getPosY2() {
        return position2.getPosY();
    }

    public void setRectXY1(Integer posX1, Integer posY1) {
        this.position1.posX = posX1;
        this.position1.posY = posY1;
    }

    public void setRectXY2(Integer posX2, Integer posY2) {
        this.position2.posX = posX2;
        this.position2.posY = posY2;
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