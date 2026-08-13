package com.example.fastfx.src.layouts;

public class Margin {

    private double top;
    private double right;
    private double bottom;
    private double left;

    public Margin (double top, double right, double bottom, double left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public Margin (double topBottom, double rightLeft) {
        this(topBottom, rightLeft, topBottom, rightLeft);
    }

    public Margin (double value) {
        this(value, value, value, value);
    }

    public void setMargin (double top, double right, double bottom, double left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public void setMargin (double topBottom, double rightLeft) {
        setMargin(topBottom, rightLeft, topBottom, rightLeft);
    }

    public void setMargin (double value) {
        setMargin(value, value, value, value);
    }

    public double getTop () {
        return top;
    }

    public void setTop (double top) {
        this.top = top;
    }

    public double getRight () {
        return right;
    }

    public void setRight (double right) {
        this.right = right;
    }

    public double getBottom () {
        return bottom;
    }

    public void setBottom (double bottom) {
        this.bottom = bottom;
    }

    public double getLeft () {
        return left;
    }

    public void setLeft (double left) {
        this.left = left;
    }

    @Override
    public String toString () {
        return "Margin{" + "top=" + top + ", right=" + right + ", bottom=" + bottom + ", left=" + left + '}';
    }
}
