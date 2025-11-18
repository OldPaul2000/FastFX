package com.example.fastfx.fastfx.userinteraction;

public class BoundOffset {
    private double topBoundOffset;
    private double rightBoundOffset;
    private double bottomBoundOffset;
    private double leftBoundOffset;

    public BoundOffset(double boundOffset) {
        this(boundOffset, boundOffset, boundOffset, boundOffset);
    }

    public BoundOffset(double topBoundOffset, double rightBoundOffset, double bottomBoundOffset, double leftBoundOffset) {
        this.topBoundOffset = topBoundOffset;
        this.rightBoundOffset = rightBoundOffset;
        this.bottomBoundOffset = bottomBoundOffset;
        this.leftBoundOffset = leftBoundOffset;
    }

    public void setBoundsOffset(double offset){
        setTop(offset);
        setRight(offset);
        setBottom(offset);
        setLeft(offset);
    }

    public double getTop() {
        return topBoundOffset;
    }

    public void setTop(double topBoundOffset) {
        this.topBoundOffset = topBoundOffset;
    }

    public double getRight() {
        return rightBoundOffset;
    }

    public void setRight(double rightBoundOffset) {
        this.rightBoundOffset = rightBoundOffset;
    }

    public double getBottom() {
        return bottomBoundOffset;
    }

    public void setBottom(double bottomBoundOffset) {
        this.bottomBoundOffset = bottomBoundOffset;
    }

    public double getLeft() {
        return leftBoundOffset;
    }

    public void setLeft(double leftBoundOffset) {
        this.leftBoundOffset = leftBoundOffset;
    }
}
