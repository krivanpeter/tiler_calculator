package com.example.tiler_buddy;

import java.io.Serializable;

public class Rectangle implements Serializable {
    int length;
    int height;
    Position position;

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
}
