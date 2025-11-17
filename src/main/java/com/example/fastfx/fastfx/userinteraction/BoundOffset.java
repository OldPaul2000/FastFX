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
        setTopBoundOffset(offset);
        setRightBoundOffset(offset);
        setBottomBoundOffset(offset);
        setLeftBoundOffset(offset);
    }

    public double getTop() {
        return topBoundOffset;
    }

    public void setTopBoundOffset(double topBoundOffset) {
        this.topBoundOffset = topBoundOffset;
    }

    public double getRight() {
        return rightBoundOffset;
    }

    public void setRightBoundOffset(double rightBoundOffset) {
        this.rightBoundOffset = rightBoundOffset;
    }

    public double getBottom() {
        return bottomBoundOffset;
    }

    public void setBottomBoundOffset(double bottomBoundOffset) {
        this.bottomBoundOffset = bottomBoundOffset;
    }

    public double getLeft() {
        return leftBoundOffset;
    }

    public void setLeftBoundOffset(double leftBoundOffset) {
        this.leftBoundOffset = leftBoundOffset;
    }
}
