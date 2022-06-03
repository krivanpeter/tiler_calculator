package com.example.tiler_buddy;

import java.io.Serializable;

public class Position implements Serializable {
    int posX1;
    int posX2;
    int posY1;
    int posY2;

    public Position(int posX1, int posY1, int posX2, int posY2) {
        posX1 = this.posX1;
        posY1 = this.posY1;
        posX2 = this.posX2;
        posY2 = this.posY2;
    }

    public Position() {
    }

    public int getPosX1() {
        return posX1;
    }

    public void setPosX1(int posX1) {
        this.posX1 = posX1;
    }

    public int getPosY1() {
        return posY1;
    }

    public void setPosY1(int posY1) {
        this.posY1 = posY1;
    }

    public int getPosX2() {
        return posX2;
    }

    public void setPosX2(int posX2) {
        this.posX2 = posX2;
    }

    public int getPosY2() {
        return posY2;
    }

    public void setPosY2(int posY2) {
        this.posY2 = posY2;
    }


}