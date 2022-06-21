package com.example.tiler_buddy;

import java.util.ArrayList;
import java.util.List;

public class Tile extends Rectangle {

    List<Position> sides = new ArrayList<>();

    public Tile() {
    }

    public Tile(int length, int height, Position position, List<Position> sides) {
        this.length = length;
        this.height = height;
        this.position1 = position;
        this.sides = sides;
    }

    public List<Position> getSides() {
        return sides;
    }

    public void setSides(List<Position> sides) {
        this.sides = sides;
    }

    public void addSide(Position position) {
        this.sides.add(position);
    }
}
