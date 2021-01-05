package com.mariiapasichna.paint;

public interface DisplayDriver {

    void setColor(String hex);

    void setColor();

    void setLineWidth();

    void drawCircle(double x, double y, double size);

    void drawSquare(double x, double y, double size);

    void drawTriangle(double x, double y, double size);

    void drawHexagon(double x, double y, double size);

    void drawStrokeCircle(double x, double y, double size);

    void drawStrokeSquare(double x, double y, double size);

    void drawStrokeTriangle(double x, double y, double size);

    void drawStrokeHexagon(double x, double y, double size);
}