package com.pk.tiler_buddy;

import java.io.Serializable;

public class Rectangle implements Serializable {
    int x1;
    int y1;
    int x2;
    int y2;
    int length;
    int height;
    String photoUriString;

    public String getPhotoUriString() {
        return photoUriString;
    }

    public void setPhotoUriString(String photoUriString) {
        this.photoUriString = photoUriString;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        // this.x2 = Calculator.calculatePosX2(this);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        // this.y2 = Calculator.calculatePosY2(this);
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setRectXY1(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setRectXY2(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }
}