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
}
