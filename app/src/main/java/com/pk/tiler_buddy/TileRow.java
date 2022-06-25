package com.example.tiler_buddy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TileRow implements Serializable {
    List<Tile> row = new ArrayList<>();

    public TileRow(){}

    public TileRow(List<Tile> tileRow){
        this.row = tileRow;
    }

    public List<Tile> getRow() {
        return row;
    }

    public void setRow(List<Tile> row) {
        this.row = row;
    }

    public void addTile(Tile tile){
        this.row.add(tile);
    }

    public void removeTile(Tile tile){
        this.row.remove(tile);
    }
}
