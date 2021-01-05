package com.mariiapasichna.save;

import com.mariiapasichna.model.*;
import com.mariiapasichna.paint.*;

import java.util.ArrayList;
import java.util.List;

public class ShapeSave {
    private double x;
    private double y;
    private double size;
    private MyColor color;
    private ShapeType shapeType;
    private boolean fill;
    private List<ShapeSave> list;
    public boolean isFill() {
        return fill;
    }

    public double getSize() {
        return size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public MyColor getColor() {
        return color;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public List<ShapeSave> getList() {
        return list;
    }

    public static ShapeSave createShapeSave(Shape shape) {
        ShapeSave result = new ShapeSave();
        if (shape instanceof BaseShape) {
            BaseShape baseShape = (BaseShape) shape;
            result.x = baseShape.getX();
            result.y = baseShape.getY();
            result.size = baseShape.getSize();
            result.color = baseShape.getColor();
            result.fill = baseShape.isFill();
        }
        if (shape instanceof Circle) {
            result.shapeType = ShapeType.CIRCLE;
        }
        if (shape instanceof Hexagon) {
            result.shapeType = ShapeType.HEXAGON;
        }
        if (shape instanceof Square) {
            result.shapeType = ShapeType.SQUARE;
        }
        if (shape instanceof Triangle) {
            result.shapeType = ShapeType.TRIANGLE;
        }
        if (shape instanceof CombinedShape) {
            CombinedShape combinedShape = (CombinedShape) shape;
            result.list = new ArrayList<>();
            for (Shape groupShape : combinedShape.getGroupShapes()) {
                result.list.add(createShapeSave(groupShape));
            }
            result.shapeType = ShapeType.COMBINED;
        }
        return result;
    }

    public static Shape createShape(ShapeSave shapeSave, Board board, DisplayDriver displayDriver) {
        ShapeType shapeType = shapeSave.getShapeType();
        double x = shapeSave.getX();
        double y = shapeSave.getY();
        switch (shapeType) {
            case CIRCLE:
                return new Circle(x, y, displayDriver, board);
            case HEXAGON:
                return new Hexagon(x, y, displayDriver, board);
            case SQUARE:
                return new Square(x, y, displayDriver, board);
            case TRIANGLE:
                return new Triangle(x, y, displayDriver, board);
            case COMBINED:
                List<Shape> combinedShapes = new ArrayList<>();
                for (ShapeSave saveShape : shapeSave.getList()) {
                    combinedShapes.add(createShape(saveShape, board, displayDriver));
                }
                for (int i = 0; i < shapeSave.getList().size(); i++) {
                    combinedShapes.get(i).setColor(shapeSave.getList().get(i).getColor());
                    combinedShapes.get(i).setFill(shapeSave.getList().get(i).isFill());
                    combinedShapes.get(i).setSize(shapeSave.getList().get(i).getSize());
                }
                return new CombinedShape(combinedShapes);
            default:
                return null;
        }
    }
}