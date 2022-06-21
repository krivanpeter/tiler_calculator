package com.example.tiler_buddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Position implements Serializable {
    int posX;
    int posY;

    public Position() {
    }

    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public List<Integer> getPosXY(){
        List<Integer> posXY = new ArrayList<>();
        posXY.add(this.posX);
        posXY.add(this.posY);
        return posXY;
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