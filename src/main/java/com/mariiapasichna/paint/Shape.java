package com.mariiapasichna.paint;

public interface Shape extends Cloneable {
    void drawStroke();

    void drawFill();

    void drawActive();

    void grow();

    void shrink();

    void move(Direction direction);

    Shape clone();

    boolean isActive(double x, double y);

    boolean isFill();

    void setFill(boolean fill);

    void setColor(MyColor color);

    void setSize(double size);
}