package com.example.fastfx.fastfx.userinteraction;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class lets the user move a specific element by dragging it and under some conditions.
 */
public class ElementMover {

    private Pane parent;
    private Region movableElement;
    private EventHandler<MouseEvent> moveDragEvent;
    private EventHandler<MouseEvent> mousePressedEvent;
    private EventHandler<MouseEvent> mouseReleasedEvent;
    private Set<MouseButton> mouseButtons;
    private double initialX;
    private double initialY;
    private double xMinusBoundOffset;
    private double yMinusBoundOffset;
    private double xPlusBoundOffset;
    private double yPlusBoundOffset;
    private double topEdgeLimit;
    private double rightEdgeLimit;
    private double bottomEdgeLimit;
    private double leftEdgeLimit;
    private double calculatedXPlusBoundOffset;
    private double calculatedYPlusBoundOffset;
    private Cursor cursor;
    private Set<EventTarget> permittedChildren;
    private boolean isPermittedChild;

    public ElementMover(Pane parent, Region movableElement) {
        this.parent = parent;
        this.movableElement = movableElement;
        this.mouseButtons = new HashSet<>();
        this.mouseButtons.add(MouseButton.PRIMARY);
        this.xMinusBoundOffset = 0;
        this.xPlusBoundOffset = 0;
        this.yMinusBoundOffset = 0;
        this.yPlusBoundOffset = 0;
        this.topEdgeLimit = 0;
        this.rightEdgeLimit = 0;
        this.bottomEdgeLimit = 0;
        this.leftEdgeLimit = 0;
        this.cursor = Cursor.DEFAULT;
        this.permittedChildren = new HashSet<>();
        this.isPermittedChild = false;
    }

    /**
     * Represents the left offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void leftOffset(double xMinusBound) {
        this.xMinusBoundOffset = xMinusBound;
    }

    /**
     * Represents the top offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void topOffset(double yMinusBound) {
        this.yMinusBoundOffset = yMinusBound;
    }

    /**
     * Represents the right offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void rightOffset(double xPlusBound) {
        this.xPlusBoundOffset = -xPlusBound;
    }

    /**
     * Represents the bottom offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void bottomOffset(double yPlusBound) {
        this.yPlusBoundOffset = -yPlusBound;
    }

    /**
     * Offset which specifies the inner area starting from the edges which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setEdgeLimits(double limit) {
        this.topEdgeLimit = limit;
        this.rightEdgeLimit = limit;
        this.bottomEdgeLimit = limit;
        this.leftEdgeLimit = limit;
    }

    /**
     * Offset which specifies the inner area starting from the top edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setTopEdgeLimit(double topEdgeLimit) {
        this.topEdgeLimit = topEdgeLimit;
    }

    /**
     * Offset which specifies the inner area starting from the right edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setRightEdgeLimit(double rightEdgeLimit) {
        this.rightEdgeLimit = rightEdgeLimit;
    }

    /**
     * Offset which specifies the inner area starting from the bottom edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setBottomEdgeLimit(double bottomEdgeLimit) {
        this.bottomEdgeLimit = bottomEdgeLimit;
    }

    /**
     * Offset which specifies the inner area starting from the left edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setLeftEdgeLimit(double leftEdgeLimit) {
        this.leftEdgeLimit = leftEdgeLimit;
    }

    public void resetEdgeLimits() {
        setEdgeLimits(0);
    }

    public EventHandler<MouseEvent> getMouseDragEvent() {
        return moveDragEvent;
    }

    public EventHandler<MouseEvent> getMousePressedEvent() {
        return mousePressedEvent;
    }

    public EventHandler<MouseEvent> getMouseReleasedEvent() {
        return mouseReleasedEvent;
    }

    /**
     * Adds a mouse button which can be used to move the element.
     */
    public void addMouseButton(MouseButton mouseButton) {
        mouseButtons.add(mouseButton);
    }

    /**
     * Removes a mouse button.
     */
    public void removeMouseButton(MouseButton mouseButton) {
        mouseButtons.remove(mouseButton);
    }

    /**
     * The cursor to appear on mouse press.
     */
    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    /**
     * Adds a child on which the movable action of the movableElement can happen
     * if it's interacted with.
     */
    public void addPermittedChild(Node node) {
        permittedChildren.add(node);
    }

    /**
     * Removes a permitted child.
     */
    public void removePermittedChild(Node node) {
        permittedChildren.remove(node);
    }

    /**
     * Permits all children contained by the movableElement.
     */
    public void permitAllChildren() {
        permittedChildren.clear();
        permittedChildren.add(movableElement);
        List<Node> children = movableElement.getChildrenUnmodifiable();
        children.forEach((child) -> {
            permittedChildren.add(child);
            findChildren(child);
        });
    }

    /**
     * Helper recursive method which helps to find all children from every child from the movableElement.
     */
    private void findChildren(Node node) {
        if (node instanceof Pane) {
            List<Node> children = ((Pane) node).getChildren();
            children.forEach((child) -> {
                permittedChildren.add(child);
                findChildren(child);
            });
        }
    }

    /**
     * Initializes the movement logic.
     */
    public void initialize() {
        mousePressedEvent = e -> {
            System.out.println("target: " + e.getTarget());
            if (mouseButtons.contains(e.getButton())) {
                double x = e.getX();
                double y = e.getY();
                System.out.println("x: " + x);
                System.out.println("y: " + y);
                if (x > leftEdgeLimit && x < movableElement.getPrefWidth() - rightEdgeLimit
                        && y > topEdgeLimit && y < movableElement.getPrefHeight() - bottomEdgeLimit) {
                    calculatedXPlusBoundOffset = parent.getPrefWidth() - movableElement.getPrefWidth() + xPlusBoundOffset;
                    calculatedYPlusBoundOffset = parent.getPrefHeight() - movableElement.getPrefHeight() + yPlusBoundOffset;
                    isPermittedChild = validateTarget(e);
                    if (isPermittedChild) {
                        initialX = x;
                        initialY = y;
                        movableElement.setCursor(cursor);
                    }
                }
                else {
                    isPermittedChild = false;
                }
            }
        };
        movableElement.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEvent);

        moveDragEvent = e -> {
            if (mouseButtons.contains(e.getButton())) {
                if (isPermittedChild) {
                    if (initialX > leftEdgeLimit && initialX < movableElement.getPrefWidth() - rightEdgeLimit
                            && initialY > topEdgeLimit && initialY < movableElement.getPrefHeight() - bottomEdgeLimit) {
                        double x = e.getSceneX() - parent.getLayoutX() - initialX;
                        double y = e.getSceneY() - parent.getLayoutY() - initialY;
                        movableElement.setLayoutX(Math.max(xMinusBoundOffset, Math.min(x, calculatedXPlusBoundOffset)));
                        movableElement.setLayoutY(Math.max(yMinusBoundOffset, Math.min(y, calculatedYPlusBoundOffset)));
                    }
                }
            }
        };
        movableElement.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveDragEvent);

        mouseReleasedEvent = e -> {
            if (mouseButtons.contains(e.getButton())) {
                if (validateTarget(e)) {
                    movableElement.setCursor(Cursor.DEFAULT);
                    System.out.println();
                }
            }
        };
        movableElement.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedEvent);
    }

    /**
     * Returns true if the current target is present in the permittedChildren.
     */
    private boolean validateTarget(Event e) {
        boolean valid = false;
        Node target = (Node) e.getTarget();
        Node targetParent = ((Node) e.getTarget()).getParent();
        System.out.println("targetParent: " + targetParent);
        if (permittedChildren.contains(target)) {
            valid = true;
        }
        if (target instanceof Text) {
            valid = permittedChildren.contains(targetParent);
        }
        return valid;
    }
}
