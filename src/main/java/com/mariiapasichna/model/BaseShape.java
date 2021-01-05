package com.mariiapasichna.model;

import com.mariiapasichna.Const;
import com.mariiapasichna.paint.*;

import java.util.Random;

public abstract class BaseShape implements Shape {
    protected double x;
    protected double y;
    protected double size;
    protected DisplayDriver displayDriver;
    protected Board board;
    protected MyColor color;
    protected boolean fill;

    public BaseShape(double x, double y, DisplayDriver displayDriver, Board board) {
        this.x = x;
        this.y = y;
        this.size = Const.SHAPE_SIZE;
        this.displayDriver = displayDriver;
        this.board = board;
        Random random = new Random();
        color = MyColor.values()[random.nextInt(MyColor.values().length)];
        this.fill = false;
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    @Override
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    @Override
    public boolean isFill() {
        return fill;
    }

    @Override
    public void setColor(MyColor color) {
        this.color = color;
    }

    public MyColor getColor() {
        return this.color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean isActive(double x, double y) {
        if (x >= this.x && x <= this.x + size && y >= this.y && y <= this.y + size) {
            return true;
        }
        return false;
    }

    @Override
    public void shrink() {
        this.size -= 5;
    }

    @Override
    public void grow() {
        if (x + size >= Const.BOARD_WIDTH) {
            x -= 5;
        } else if (y + size >= Const.BOARD_HEIGHT) {
            y -= 5;
        } else if (x <= 0) {
            x += 5;
        } else if (y <= 0) {
            y += 5;
        } else {
            this.size += 5;
        }
    }

    @Override
    public void move(Direction direction) {
        if (x + size >= Const.BOARD_WIDTH - 5) {
            x -= 1;
        } else if (y + size >= Const.BOARD_HEIGHT - 5) {
            y -= 1;
        } else if (x <= 5) {
            x += 1;
        } else if (y <= 5) {
            y += 1;
        } else {
            if (direction == Direction.RIGHT) {
                x += 5;
                board.setX(this.x);
            } else if (direction == Direction.LEFT) {
                x -= 5;
                board.setX(this.x);
            } else if (direction == Direction.UP) {
                y -= 5;
                board.setY(this.y);
            } else if (direction == Direction.DOWN) {
                y += 5;
                board.setY(this.y);
            }
        }
    }

    @Override
    public Shape clone() {
        try {
            return (BaseShape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}