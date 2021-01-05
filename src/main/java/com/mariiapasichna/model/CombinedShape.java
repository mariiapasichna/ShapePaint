package com.mariiapasichna.model;

import com.mariiapasichna.paint.Direction;
import com.mariiapasichna.paint.MyColor;
import com.mariiapasichna.paint.Shape;

import java.util.ArrayList;
import java.util.List;

public class CombinedShape implements Shape {
    private List<Shape> groupShapes;

    public CombinedShape(List<Shape> groupShapes) {
        this.groupShapes = new ArrayList<>(groupShapes);
    }

    public List<Shape> getGroupShapes() {
        return groupShapes;
    }

    @Override
    public boolean isActive(double x, double y) {
        for (Shape shape : groupShapes) {
            if (shape.isActive(x,y)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFill() {
        return false;
    }

    @Override
    public void setFill(boolean fill) {
    }

    @Override
    public void setColor(MyColor color) {
    }

    @Override
    public Shape clone() {
        List<Shape> cloneShapes = new ArrayList<>();
        for (Shape shape : groupShapes) {
            cloneShapes.add(shape.clone());
        }
        return new CombinedShape(cloneShapes);
    }

    @Override
    public void setSize(double size) {
    }

    @Override
    public void drawStroke() {
        for (Shape shape : groupShapes) {
            if (shape.isFill()) {
                shape.drawFill();
            } else {
                shape.drawStroke();
            }
        }
    }

    @Override
    public void drawFill() {
        for (Shape shape : groupShapes) {
            if (shape.isFill()) {
                shape.drawFill();
            } else {
                shape.drawStroke();
            }
        }
    }

    @Override
    public void drawActive() {
        for (Shape shape : groupShapes) {
            shape.drawActive();
        }
    }

    @Override
    public void move(Direction direction) {
        for (Shape shape : groupShapes) {
            shape.move(direction);
        }
    }

    @Override
    public void grow() {
        for (Shape shape : groupShapes) {
            shape.grow();
        }
    }

    @Override
    public void shrink() {
        for (Shape shape : groupShapes) {
            shape.shrink();
        }
    }
}