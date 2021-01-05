package com.mariiapasichna.model;

import com.mariiapasichna.paint.Board;
import com.mariiapasichna.paint.DisplayDriver;

public class Hexagon extends BaseShape {
    public Hexagon(double x, double y, DisplayDriver displayDriver, Board board) {
        super(x, y, displayDriver, board);
    }

    @Override
    public void drawStroke() {
        displayDriver.setColor(color.toHex());
        displayDriver.setLineWidth();
        displayDriver.drawStrokeHexagon(x, y, size);
    }

    @Override
    public void drawFill() {
        displayDriver.setColor(color.toHex());
        displayDriver.drawHexagon(x, y, size);
    }

    @Override
    public void drawActive() {
        displayDriver.setColor();
        displayDriver.setLineWidth();
        displayDriver.drawStrokeHexagon(x, y, size);
    }
}