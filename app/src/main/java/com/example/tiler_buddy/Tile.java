package com.example.tiler_buddy;

import java.io.Serializable;

public class Tile implements Serializable {

    int lengthIn;
    int heightIn;
    int posX;
    int posY;

    public Tile(int lengthIn, int heightIn, int posX, int posY){
        this.lengthIn = lengthIn;
        this.heightIn = heightIn;
        this.posX = posX;
        this.posY = posY;
    }

    public int getLengthIn() {
        return lengthIn;
    }

    public void setLengthIn(int lengthIn) {
        this.lengthIn = lengthIn;
    }

    public int getHeightIn() {
        return heightIn;
    }

    public void setHeightIn(int heightIn) {
        this.heightIn = heightIn;
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
