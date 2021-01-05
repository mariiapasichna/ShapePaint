package com.mariiapasichna.save;

import java.util.List;

public class BoardSave {
    private double x;
    private double y;
    private List<ShapeSave> shapes;

    public BoardSave(double x, double y, List<ShapeSave> shapes) {
        this.x = x;
        this.y = y;
        this.shapes = shapes;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<ShapeSave> getShapes() {
        return shapes;
    }
}