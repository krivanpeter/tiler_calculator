package com.example.tiler_buddy;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Rectangle {

    List<Side> sides = new ArrayList<>();

    public Tile() {
    }

    public Tile(List<Side> sides) {
        this.sides = sides;
    }

    public List<Side> getSides() {
        return sides;
    }

    public void addSide(Side side) {
        this.sides.add(side);
    }

}
