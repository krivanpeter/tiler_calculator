package com.example.tiler_buddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Position implements Serializable {
    List<Integer> posXY1 = new ArrayList<>();
    List<Integer> posXY2 = new ArrayList<>();

    public Position(List<Integer> posXY1, List<Integer> posXY2) {
        posXY1 = this.posXY1;
        posXY2 = this.posXY2;
    }

    public Position() {
    }

    public List<Integer> getPosXY1() {
        return this.posXY1;
    }

    public void setPosXY1(Integer posX1, Integer posY1) {
        this.posXY1.add(posX1);
        this.posXY1.add(posY1);
    }

    public List<Integer> getPosXY2() {
        return posXY2;
    }

    public void setPosXY2(Integer posX2, Integer posY2) {
        this.posXY2.add(posX2);
        this.posXY2.add(posY2);
    }

    public int getPosX1() {
        return getPosXY1().get(0);
    }

    public int getPosY1() {
        return getPosXY1().get(1);
    }

    public int getPosX2() {
        return getPosXY2().get(0);
    }

    public int getPosY2() {
        return getPosXY2().get(1);
    }
}