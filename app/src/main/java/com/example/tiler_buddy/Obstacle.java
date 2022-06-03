package com.example.tiler_buddy;

import java.io.Serializable;

public class Obstacle implements Serializable {

    int length;
    int height;
    int posX;
    int posY;

    public Obstacle() {
        // do something if u want
    }

    public Obstacle(int length, int height, int posX, int posY) {
        this.length = length;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) { this.posY = posY; }

    public int getPosY() {
        return posY;
    }
}