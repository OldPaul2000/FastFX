package com.example.fastfx.src.userinteraction;

/**
 * Used in <strong>ElementResizer</strong> and <strong>ElementMover</strong>.<br>
 * Sets the edge offset in which the resize cursor will appear when moving the mouse<br>
 * inside the resizable element and make resizing by drag possible.<br>
 * Also sets the area in which the drag event for moving the element will have<br>
 * effect therefore the element can be moved.<br>
 * This prevents interference between the moving and resizing events.
 */
public class EdgeLimit {

    private double top;
    private double right;
    private double bottom;
    private double left;

    public EdgeLimit(double edgeLimit) {
        this(edgeLimit, edgeLimit, edgeLimit, edgeLimit);
    }

    public EdgeLimit(double top, double right, double bottom, double left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double rightEdgeLimit) {
        this.right = rightEdgeLimit;
    }

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottomEdgeLimit) {
        this.bottom = bottomEdgeLimit;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double leftEdgeLimit) {
        this.left = leftEdgeLimit;
    }
}
