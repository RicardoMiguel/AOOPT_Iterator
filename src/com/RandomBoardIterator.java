package com;

import java.util.Random;

/**
 * Created by Ricardo on 19/05/2016.
 */
public class RandomBoardIterator extends NormalBoardIterator {

    private static final int NUMBER_OF_RANDOMS = 100;
    private int randomsCounter;

    public RandomBoardIterator(Tile[][] matrix, int y, int x) {
        super(matrix, y, x);
        randomsCounter = 0;
    }

    @Override
    public boolean hasNext() {
        if(first){
            first = false;
            return true;
        }

        if(randomsCounter<=NUMBER_OF_RANDOMS){
            Random rand = new Random();
            y = rand.nextInt(matrix[0].length);
            x = rand.nextInt(matrix.length);
            randomsCounter++;
            return true;
        } else {
            return false;
        }
    }
}
