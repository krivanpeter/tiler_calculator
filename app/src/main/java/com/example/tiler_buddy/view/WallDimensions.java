package com.example.tiler_buddy.view;

import java.io.Serializable;

public class WallDimensions implements Serializable {

    private final int length;
    private final int height;

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
}
