package com;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by Ricardo on 19/05/2016.
 */
public class Board extends JPanel implements Iterable<Tile>{

    private Tile[][] matrix;
    private int tilesize;
    // higlighted (with mouse) tile
    private int hx = -1, hy = -1;

    // matrix initialization
    public Board(int cols, int rows, int tilesize) {
        this.setPreferredSize(new Dimension(cols * tilesize, rows * tilesize));
        this.tilesize = tilesize;
        matrix = new Tile[rows][cols];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                matrix[i][j] = new Tile();
            }
        }
    }

    // matrix drawing (including the highlighted tile)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (i == hy && j == hx) {
                    g.setColor(matrix[i][j].getColor().brighter());
                } else {
                    g.setColor(matrix[i][j].getColor());
                }
                g.fillRect(j * tilesize, i * tilesize + 1, tilesize - 1, tilesize - 1);
            }
        }
    }

    public void highlight(int x, int y) {
        hx = x;
        hy = y;
        repaint();
    }

    @Override
    public Iterator<Tile> iterator() {
        return new NormalBoardIterator(matrix, 0, 0);
    }

    public Iterator<Tile> iterator(final int x, final int y){
        return new BoardIteratorController().getIterator(matrix, x, y);
    }
}

