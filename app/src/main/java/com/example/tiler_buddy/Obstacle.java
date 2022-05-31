package com.example.tiler_buddy;

import java.io.Serializable;

public class Obstacle implements Serializable {

    int length_in;
    int height_in;
    int dis_left;
    int dis_bottom;

    public Obstacle() {
        // do something if u want
    }

    public Obstacle(int length_in, int height_in, int dis_left, int dis_bottom) {
        this.length_in = length_in;
        this.height_in = height_in;
        this.dis_left = dis_left;
        this.dis_bottom = dis_bottom;
    }

    public void setLength(int length_in) {
        this.length_in = length_in;
    }

    public int getLength() {
        return length_in;
    }

    public void setHeight(int height_in) {
        this.height_in = height_in;
    }

    public int getHeight() {
        return height_in;
    }

    public void setDisLeft(int dis_left) {
        this.dis_left = dis_left;
    }

    public int getDisLeft() {
        return dis_left;
    }

    public void setDisBot(int dis_bottom) { this.dis_bottom = dis_bottom; }

    public int getDisBot() {
        return dis_bottom;
    }
}