package com;

/**
 * Created by Ricardo on 19/05/2016.
 */
public class ReverseBoardIterator extends NormalBoardIterator {
    public ReverseBoardIterator(Tile[][] matrix, int y, int x) {
        super(matrix, y, x);
    }

    @Override
    public boolean hasNext() {
        if(first){
            first = false;
            return true;
        }

        if(x-1 == -1 && y-1 == -1){
            return false;
        } else if(y-1 == -1){
            y = matrix[0].length-1;
            --x;
            return true;
        } else {
            --y;
            return true;
        }
    }
}
