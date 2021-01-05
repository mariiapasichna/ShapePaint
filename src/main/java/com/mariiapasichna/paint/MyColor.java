package com.mariiapasichna.paint;

public enum MyColor {
    PINK("#f50298"), GREEN("#81f505"), BLUE("#05eff7"), ORANGE("#ff951c"), PURPLE("#d905f5"), YELLOW("#ebff12");
    private String color;

    MyColor(String color) {
        this.color = color;
    }

    public String toHex() {
        return color;
    }
}