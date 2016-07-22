package com;

import java.util.Iterator;

/**
 * Created by Ricardo on 19/05/2016.
 */
public class NormalBoardIterator implements Iterator<Tile>{
    protected Tile[][] matrix;
    protected int x;
    protected int y;
    protected boolean first;

    public NormalBoardIterator(Tile[][] matrix, int y, int x) {
        this.matrix = matrix;
        this.x = x;
        this.y = y;
        first = true;
    }


    @Override
    public boolean hasNext() {
        if(first){
            first = false;
            return true;
        }

        if(x+1 == matrix.length && y+1 == matrix[0].length){
            return false;
        } else if(y+1 == matrix[0].length){
            y = 0;
            ++x;
            return true;
        } else {
            ++y;
            return true;
        }
    }

    @Override
    public Tile next() {
        return matrix[x][y];
    }
}
