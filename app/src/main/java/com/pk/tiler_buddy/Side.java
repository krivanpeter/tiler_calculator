package com.pk.tiler_buddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Side implements Serializable {
    int firstPointX1;
    int firstPointY1;
    int secondPointX2;
    int secondPointY2;

    public Side(int firstPointX1, int firstPointY1, int secondPointX2, int secondPointY2) {
        this.firstPointX1 = firstPointX1;
        this.firstPointY1 = firstPointY1;
        this.secondPointX2 = secondPointX2;
        this.secondPointY2 = secondPointY2;
    }

    public int getFirstPointX1() {
        return firstPointX1;
    }

    public void setFirstPointX1(int firstPointX1) {
        this.firstPointX1 = firstPointX1;
    }

    public int getFirstPointY1() {
        return firstPointY1;
    }

    public void setFirstPointY1(int firstPointY1) {
        this.firstPointY1 = firstPointY1;
    }

    public int getSecondPointX2() {
        return secondPointX2;
    }

    public void setSecondPointX2(int secondPointX2) {
        this.secondPointX2 = secondPointX2;
    }

    public int getSecondPointY2() {
        return secondPointY2;
    }

    public void setSecondPointY2(int secondPointY2) {
        this.secondPointY2 = secondPointY2;
    }

    public List<String> getCoords(){
        List<String> coords = new ArrayList<>();
        coords.add(String.valueOf(firstPointX1));
        coords.add(String.valueOf(firstPointY1));
        coords.add(String.valueOf(secondPointX2));
        coords.add(String.valueOf(secondPointY2));
        return coords;
    }
}
