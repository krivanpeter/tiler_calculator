package com.pk.tiler_buddy;

import java.io.Serializable;

public class TileDimensions implements Serializable {

    private final int length;
    private final int height;

    public TileDimensions(int length, int height) {
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
