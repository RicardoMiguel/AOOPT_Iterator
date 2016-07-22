package com;

import javax.swing.*;
import java.util.Iterator;

/**
 * Created by Ricardo on 19/05/2016.
 */
// in this version this thread doesn't use the iterator - change it
class WorkerThread implements Runnable {

    private JPanel p;
    private final Iterator<Tile> iterator;

    public WorkerThread(JPanel p, Iterator<Tile> iterator) {
        this.p = p;
        this.iterator = iterator;
    }

    public void run() {
        // iteration using 2 'for' loops - to be replaced with the iterator application
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            tile.flip(); //change color
            p.repaint();		//refresh the screen
            try {	//wait
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
