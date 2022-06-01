package com.example.tiler_buddy;

import java.io.Serializable;

public class Obstacle implements Serializable {

    int lengthIn;
    int heightIn;
    int distanceFromLeft;
    int distanceFromBottom;

    public Obstacle() {
        // do something if u want
    }

    public Obstacle(int lengthIn, int heightIn, int distanceFromLeft, int distanceFromBottom) {
        this.lengthIn = lengthIn;
        this.heightIn = heightIn;
        this.distanceFromLeft = distanceFromLeft;
        this.distanceFromBottom = distanceFromBottom;
    }

    public void setLength(int lengthIn) {
        this.lengthIn = lengthIn;
    }

    public int getLength() {
        return lengthIn;
    }

    public void setHeight(int heightIn) {
        this.heightIn = heightIn;
    }

    public int getHeight() {
        return heightIn;
    }

    public void setDisLeft(int distanceFromLeft) {
        this.distanceFromLeft = distanceFromLeft;
    }

    public int getDisLeft() {
        return distanceFromLeft;
    }

    public void setDisBot(int distanceFromBottom) { this.distanceFromBottom = distanceFromBottom; }

    public int getDisBot() {
        return distanceFromBottom;
    }
}