package com.mariiapasichna.platform;

import com.mariiapasichna.Const;
import com.mariiapasichna.paint.DisplayDriver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DisplayDriverImpl implements DisplayDriver {
    private final GraphicsContext gc;

    public DisplayDriverImpl(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void setColor(String hex) {
        gc.setFill(Color.web(hex));
        gc.setStroke(Color.web(hex));
    }

    @Override
    public void setColor() {
        gc.setStroke(Const.ACTIVITY_COLOR);
    }

    @Override
    public void setLineWidth() {
        gc.setLineWidth(Const.LINE_WIDTH);
    }

    @Override
    public void drawCircle(double x, double y, double size) {
        gc.fillOval(x, y, size, size);
    }

    @Override
    public void drawSquare(double x, double y, double size) {
        gc.fillRect(x, y, size, size);
    }

    @Override
    public void drawTriangle(double x, double y, double size) {
        gc.fillPolygon(new double[]{x, x + size / 2, x + size}, new double[]{y + size, y, y + size}, 3);
    }

    @Override
    public void drawHexagon(double x, double y, double size) {
        gc.fillPolygon(new double[]{x, x + size / 4, x + 3 * size / 4, x + size, x + 3 * size / 4, x + size / 4},
                new double[]{y + size / 2, y, y, y + size / 2, y + size, y + size}, 6);
    }

    @Override
    public void drawStrokeCircle(double x, double y, double size) {
        gc.strokeOval(x, y, size, size);
    }

    @Override
    public void drawStrokeSquare(double x, double y, double size) {
        gc.strokeRect(x, y, size, size);
    }

    @Override
    public void drawStrokeTriangle(double x, double y, double size) {
        gc.strokePolygon(new double[]{x, x + size / 2, x + size}, new double[]{y + size, y, y + size}, 3);
    }

    @Override
    public void drawStrokeHexagon(double x, double y, double size) {
        gc.strokePolygon(new double[]{x, x + size / 4, x + 3 * size / 4, x + size, x + 3 * size / 4, x + size / 4},
                new double[]{y + size / 2, y, y, y + size / 2, y + size, y + size}, 6);
    }
}