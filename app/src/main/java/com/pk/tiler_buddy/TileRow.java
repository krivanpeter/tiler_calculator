package com.pk.tiler_buddy;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TileRow implements Serializable {
    List<Tile> row = new ArrayList<>();

    public TileRow() {
    }

    public TileRow(List<Tile> tileRow) {
        this.row = tileRow;
    }

    public List<Tile> getRow() {
        return row;
    }

    public void setRow(List<Tile> row) {
        this.row = row;
    }

    public void addTile(Tile tile) {
        this.row.add(tile);
    }

    public void removeTile(Tile tile) {
        this.row.remove(tile);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < this.row.size(); i++) {
            Tile tile = this.row.get(i);
            tile.draw(canvas, paint);
        }
    }

    public void shift(int extent) {
        for (Tile tile : row) {
            int newX1 = tile.x1 - extent;
            if (newX1 >= 0) {
                tile.setX1(tile.x1 - extent);
            }
            tile.setX2(tile.x2 - extent);
        }
        Tile newTile = new Tile(row.get(row.size()-1));
        newTile.setX1(newTile.getX1() + newTile.length);
        newTile.setX2(newTile.getX2() + extent);
        addTile(newTile);
    }
}
