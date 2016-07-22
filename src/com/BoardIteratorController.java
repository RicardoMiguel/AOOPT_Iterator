package com;

import java.util.Iterator;

/**
 * Created by Ricardo on 19/05/2016.
 */
public class BoardIteratorController {

    public static final int NORMAL = 0;
    public static final int REVERSE = 1;
    public static final int RANDOM = 2;
    private static int iteratorCounter = 0;

    public Iterator<Tile> getIterator(final Tile[][] matrix, final int x, final int y){
        Iterator<Tile> iterator = null;
        if(iteratorCounter > RANDOM){
            iteratorCounter = NORMAL;
        }

        if(iteratorCounter == NORMAL) {
            iterator = new NormalBoardIterator(matrix, x, y);
        } else if(iteratorCounter == REVERSE){
            iterator = new ReverseBoardIterator(matrix, x, y);
        } else if(iteratorCounter == RANDOM){
            iterator = new RandomBoardIterator(matrix,x,y);
        }

        iteratorCounter++;
        return iterator;
    }
}
