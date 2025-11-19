package com.example.fastfx.fastfx.userinteraction;

/**
 * The limits in which the element can be moved inside its parent.<br>
 * It's used to prevent the element exceeding its parent edges and<br>
 * prevents making it completely hidden if the parent is set to hide<br>
 * the content when overflowing.
 */
public class BoundOffset {

    private double top;
    private double right;
    private double bottom;
    private double left;

    public BoundOffset(double boundOffset) {
        this(boundOffset, boundOffset, boundOffset, boundOffset);
    }

    public BoundOffset(double top, double right, double bottom, double left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double topBoundOffset) {
        this.top = topBoundOffset;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double rightBoundOffset) {
        this.right = rightBoundOffset;
    }

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottomBoundOffset) {
        this.bottom = bottomBoundOffset;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double leftBoundOffset) {
        this.left = leftBoundOffset;
    }
}
