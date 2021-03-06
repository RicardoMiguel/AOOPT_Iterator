# Snake - iterator

Compile and run the given code, then analyze it.

Inside - you will find a board being a mesh of tiles. Clicking a tile stats a new thread changing colors of the following tiles.

In the present form, the thread goes through the tiles using a double 'for' loop statement (in method run).

Modify the thread class to use (instead of loops) an iterator object to visit all tiles. Iterator class:

* Should keep the current state of iteration (position of the current tile);
* Should have the code to proceed with iteration:
  * Method next going to the next tile and returning it;
  * Method hasNext to check if there is a next tile;
  * (you can omit the remove method).

Also add to the Board class a new method creating the iterator object. You don't need anymore three methods getRows(), getCols() and getAt().

At the end write few other versions of Iterator class changing the order of "visiting" tiles (eg. random order or reverse order or spiral order) and use them randomly, in succession or give a choice to the user.

```
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Tile {
    // tile state
    private boolean value = false;
    // colors
    private static final Color on = new Color(0xffd700),
            off = new Color(0x1e90ff);
    public Color getColor() {
        return value ? on : off;
    }

    //change color
    public void flip() {
        value = !value;
    }
}

//tile matrix
class Board extends JPanel {

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

    // 3 methods to remove in the final version
    public int getRows() {
        return matrix.length;
    }

    public int getCols() {
        return matrix[0].length;
    }

    public Tile getAt(int row, int col) {
        return matrix[row][col];
    }

    // intead - add the following method to get the iterator
    // public Iterator<Tile> iterator( ...
}

// in this version this thread doesn't use the iterator - change it
class WorkerThread implements Runnable {

    private Board p;
    private int x, y;

    // x, y - initial position of iteration
    public WorkerThread(Board k, int x, int y) {
        this.p = k;
        this.x = x;
        this.y = y;
    }

    public void run() {
        // iteration using 2 'for' loops - to be replaced with the iterator application
        for (int i = y; i < p.getRows(); ++i) {
            int j;
            if (i == y) {
                j = x;
            } else {
                j = 0;
            }
            for (; j < p.getCols(); ++j) {
                p.getAt(i, j).flip(); //change color
                p.repaint();		//refresh the screen
                try {	//wait
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class SnakeIterator {

    static final int TILESIZE = 40;

    public static void main(String[] args) {

        // window construction
        JFrame frame = new JFrame("Iterator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Board Board = new Board(16, 9, TILESIZE);
        frame.getContentPane().add(Board);
        frame.pack();
        frame.setVisible(true);

        // mouse clicking starts the thread with iteration
        Board.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / TILESIZE;
                int y = e.getY() / TILESIZE;
                new Thread(new WorkerThread(Board, x, y)).start();
            }
        });
        // mouse moving - highlight a tile under the cursor
        Board.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX() / TILESIZE;
                int y = e.getY() / TILESIZE;
                Board.highlight(x, y);
            }
        });
    }
}
```