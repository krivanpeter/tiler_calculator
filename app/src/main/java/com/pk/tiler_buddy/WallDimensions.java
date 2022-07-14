package com.pk.tiler_buddy;

import java.io.Serializable;

public class WallDimensions implements Serializable {

    private int length;
    private int height;

    public WallDimensions() {
    }

    public WallDimensions(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public void set(int length, int height) {
        this.length = length;
        this.height = height;
    }
}
