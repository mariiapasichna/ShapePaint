package com.mariiapasichna.paint;

import com.mariiapasichna.model.*;
import com.mariiapasichna.save.BoardSave;
import com.mariiapasichna.save.ShapeSave;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private double x;
    private double y;
    private final DisplayDriver displayDriver;
    public List<Shape> shapes = new ArrayList<>();
    private List<Shape> groupShapes = new ArrayList<>();

    public Board(DisplayDriver displayDriver) {
        this.x = 0;
        this.y = 0;
        this.displayDriver = displayDriver;
    }

    public BoardSave makeBoardSave() {
        List<ShapeSave> saveShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            ShapeSave shapeSave = ShapeSave.createShapeSave(shape);
            saveShapes.add(shapeSave);
        }
        return new BoardSave(x, y, saveShapes);
    }

    public void downloadBoardSave(BoardSave boardSave) {
        shapes.clear();
        List<ShapeSave> saveShapes = boardSave.getShapes();
        x = boardSave.getX();
        y = boardSave.getY();
        for (ShapeSave shapeSave : saveShapes) {
            Shape shape = ShapeSave.createShape(shapeSave, this, displayDriver);
            shape.setColor(shapeSave.getColor());
            shape.setFill(shapeSave.isFill());
            shape.setSize(shapeSave.getSize());
            shapes.add(shape);
        }
        drawShape();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addCircle() {
        shapes.add(new Circle(10, 10, displayDriver, this));
    }

    public void addSquare() {
        shapes.add(new Square(10, 10, displayDriver, this));
    }

    public void addTriangle() {
        shapes.add(new Triangle(10, 10, displayDriver, this));
    }

    public void addHexagon() {
        shapes.add(new Hexagon(10, 10, displayDriver, this));
    }

    public void drawShape() {
        for (Shape shape : shapes) {
            if (shape.isFill()) {
                shape.drawFill();
            } else {
                shape.drawStroke();
            }
            if (shape.isActive(x, y)) {
                shape.drawActive();
            }
        }
        for (Shape shape : groupShapes) {
            if (shape.isFill()) {
                shape.drawFill();
            } else {
                shape.drawStroke();
            }
            if (shape.isActive(x, y)) {
                shape.drawActive();
            }
        }
    }

    public void move(Direction direction) {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.move(direction);
                break;
            }
        }
    }

    public void fill() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.setFill(true);
                break;
            }
        }
    }

    public void grow() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.grow();
                break;
            }
        }
    }

    public void shrink() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.shrink();
                break;
            }
        }
    }

    public void delete() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shapes.remove(shape);
                break;
            }
        }
    }

    public void combineShapesToList() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                groupShapes.add(shape);
                shapes.remove(shape);
                break;
            }
        }
    }

    public void combineShapes() {
        shapes.add(new CombinedShape(groupShapes));
        groupShapes.clear();
    }

    public void Active() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.drawActive();
                break;
            }
        }
    }

    public void cloneShape() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shapes.add(shape.clone());
                break;
            }
        }
    }

    public void clear() {
        shapes.clear();
        groupShapes.clear();
    }
}