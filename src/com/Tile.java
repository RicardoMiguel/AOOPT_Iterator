package com;

import java.awt.*;

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
