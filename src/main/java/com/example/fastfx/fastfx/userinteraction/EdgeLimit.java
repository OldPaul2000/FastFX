package com.example.fastfx.fastfx.userinteraction;

public class EdgeLimit {

    private double topEdgeLimit;
    private double rightEdgeLimit;
    private double bottomEdgeLimit;
    private double leftEdgeLimit;

    public EdgeLimit(double edgeLimit) {
        this(edgeLimit, edgeLimit, edgeLimit, edgeLimit);
    }

    public EdgeLimit(double topEdgeLimit, double rightEdgeLimit, double bottomEdgeLimit, double leftEdgeLimit) {
        this.topEdgeLimit = topEdgeLimit;
        this.rightEdgeLimit = rightEdgeLimit;
        this.bottomEdgeLimit = bottomEdgeLimit;
        this.leftEdgeLimit = leftEdgeLimit;
    }

    public void setEdgesLimit(double limit){
        setTopEdgeLimit(limit);
        setRightEdgeLimit(limit);
        setBottomEdgeLimit(limit);
        setLeftEdgeLimit(limit);
    }

    public double getTop() {
        return topEdgeLimit;
    }

    public void setTopEdgeLimit(double topEdgeLimit) {
        this.topEdgeLimit = topEdgeLimit;
    }

    public double getRight() {
        return rightEdgeLimit;
    }

    public void setRightEdgeLimit(double rightEdgeLimit) {
        this.rightEdgeLimit = rightEdgeLimit;
    }

    public double getBottom() {
        return bottomEdgeLimit;
    }

    public void setBottomEdgeLimit(double bottomEdgeLimit) {
        this.bottomEdgeLimit = bottomEdgeLimit;
    }

    public double getLeft() {
        return leftEdgeLimit;
    }

    public void setLeftEdgeLimit(double leftEdgeLimit) {
        this.leftEdgeLimit = leftEdgeLimit;
    }
}
