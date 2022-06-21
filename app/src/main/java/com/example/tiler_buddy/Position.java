package com.example.tiler_buddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Position implements Serializable {
    List<Integer> posXY = new ArrayList<>(Arrays.asList(0, 0));

    public Position(List<Integer> posXY) {
        this.posXY = posXY;
    }

    public Position() {
    }

    public List<Integer> getPosXY() {
        return this.posXY;
    }

    public void setPosXY(Integer posX, Integer posY) {
        this.posXY.set(0, posX);
        this.posXY.set(1, posY);
    }

    public int getPosX() {
        return this.posXY.get(0);
    }

    public int getPosY() {
        return this.posXY.get(1);
    }
}