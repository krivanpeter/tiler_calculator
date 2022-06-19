package com.example.tiler_buddy;

import java.io.Serializable;

public class Rectangle implements Serializable {
    int length;
    int height;
    Position position = new Position();

    public Rectangle() {
    }

    public Rectangle(int length, int height, Position position) {
        this.length = length;
        this.height = height;
        this.position = position;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosXY1(Integer posX1, Integer posY1) {
        this.position.posXY1.set(0, posX1);
        this.position.posXY1.set(1, posY1);
    }

    public void setPosXY2(Integer posX2, Integer posY2) {
        this.position.posXY2.set(0, posX2);
        this.position.posXY2.set(1, posY2);
    }

    // Thank you Shubhra Srivastava for the following method
    public boolean isOverlapping(Rectangle other) {
        if (this.position.getPosY1() >= other.getPosition().getPosY2() || this.position.getPosY2() <= other.getPosition().getPosY1()) {
            return false;
        }
        if (this.position.getPosX1() >= other.getPosition().getPosX2() || this.position.getPosX2() <= other.getPosition().getPosX1()) {
            return false;
        }
        return true;
    }

    public boolean isFullyOverlapping(Rectangle other) {
        if (this.position.getPosX1() <= other.getPosition().getPosX1() && this.position.getPosX2() >= other.getPosition().getPosX2() &&
                this.position.getPosY1() <= other.getPosition().getPosY1() && this.position.getPosY2() >= other.getPosition().getPosY2()) {
            return true;
        }
        return false;
    }

    public boolean isVerticallyOverlapping(Rectangle other) {
        if (this.position.getPosX1() > other.getPosition().getPosX1() && this.position.getPosX2() > other.getPosition().getPosX2()) {
            return true;
        }
        return false;
    }

    public boolean isHorizontallyOverlapping(Rectangle other) {
        if (this.position.getPosY1() > other.getPosition().getPosY1() && this.position.getPosY2() > other.getPosition().getPosY2()) {
            return true;
        }
        return false;
    }
}