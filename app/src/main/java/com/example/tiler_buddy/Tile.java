package com.example.tiler_buddy;

import java.io.Serializable;

public class Tile implements Serializable {

    int length;
    int height;
    int posX;
    int posY;

    public Tile(int length, int height, int posX, int posY){
        this.length = length;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }

    public Tile() {

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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
