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

import java.util.*;

/**
 * This class lets the user move a specific element by dragging it and under some conditions.
 */
public class ElementMover {

    private Pane parent;
    private Map<Region, Set<EventTarget>> movableElements;
    private double initialX;
    private double initialY;
    private Map<Region, BoundOffset> boundOffsets;
    private Map<Region, EdgeLimit> edgeLimits;
    private Map<Region, Cursor> cursors;
    private Map<Region, Set<MouseButton>> mouseButtons;
    private EventHandler<MouseEvent> moveDragEvent;
    private EventHandler<MouseEvent> mousePressedEvent;
    private EventHandler<MouseEvent> mouseReleasedEvent;

    public ElementMover(Pane parent) {
        this.parent = parent;
        this.movableElements = new HashMap<>();
        this.initialX = 0;
        this.initialY = 0;
        this.boundOffsets = new HashMap<>();
        this.edgeLimits = new HashMap<>();
        this.cursors = new HashMap<>();
        this.mouseButtons = new HashMap<>();
        this.moveDragEvent = null;
        this.mousePressedEvent = null;
        this.mouseReleasedEvent = null;
        initialize();
    }

    public void addElement(Region element){
        addElement(element, null);
    }

    public void addElement(Region element, Region permittedChild){
        if(!movableElements.containsKey(element)){
            Set<EventTarget> permittedChildren = new HashSet<>();
            if(permittedChild != null){
                permittedChildren.add(permittedChild);
            }
            movableElements.put(element, permittedChildren);
            boundOffsets.put(element, new BoundOffset(0));
            edgeLimits.put(element, new EdgeLimit(0));
            cursors.put(element, Cursor.DEFAULT);
            Set<MouseButton> allowedMouseButtons = new HashSet<>();
            allowedMouseButtons.add(MouseButton.PRIMARY);
            mouseButtons.put(element, allowedMouseButtons);
        }
    }

    public void removeElement(Region element){
        movableElements.remove(element);
        boundOffsets.remove(element);
        edgeLimits.remove(element);
        cursors.remove(element);
        mouseButtons.remove(element);
    }


    /**
     * Adds a child on which the movable action of the movableElement can happen
     * if it's interacted with.
     */
    public void addPermittedChild(Region element, Node node) {
        if(movableElements.containsKey(element)){
            movableElements.get(element).add(node);
        }
    }

    /**
     * Removes a permitted child.
     */
    public void removePermittedChild(Region element, Node node) {
        if(movableElements.containsKey(element)){
            movableElements.get(element).remove(node);
        }
    }

    /**
     * Permits all children contained by the movableElement.
     */
    public void permitAllChildren(Region element) {
        if(movableElements.containsKey(element)){
            Set<EventTarget> permittedChildren = movableElements.get(element);
            permittedChildren.add(element);
            List<Node> children = element.getChildrenUnmodifiable();
            children.forEach((child) -> {
                permittedChildren.add(child);
                findChildren(element, child);
            });
        }
    }

    /**
     * Helper recursive method which helps to find all children from every child from the movableElement.
     */
    private void findChildren(Region movableElement, Node node) {
        if (node instanceof Pane) {
            List<Node> children = ((Pane) node).getChildren();
            children.forEach((child) -> {
                movableElements.get(movableElement).add(child);
                findChildren(movableElement, child);
            });
        }
    }

    public void setBoundsOffset(Region element, double offset){
        BoundOffset boundOffset = boundOffsets.get(element);
        if(boundOffset != null){
            boundOffset.setTop(offset);
            boundOffset.setRight(-offset);
            boundOffset.setBottom(-offset);
            boundOffset.setLeft(offset);
        }
    }

    /**
     * Represents the top offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void setTopOffset(Region element, double top) {
        if(boundOffsets.containsKey(element)){
            boundOffsets.get(element).setTop(top);
        }
    }

    /**
     * Represents the right offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void setRightOffset(Region element, double right) {
        if(boundOffsets.containsKey(element)){
            boundOffsets.get(element).setRight(-right);
        }
    }

    /**
     * Represents the bottom offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void setBottomOffset(Region element, double bottom) {
        if(boundOffsets.containsKey(element)){
            boundOffsets.get(element).setBottom(-bottom);
        }
    }

    /**
     * Represents the left offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void setLeftOffset(Region element, double left) {
        if(boundOffsets.containsKey(element)){
            boundOffsets.get(element).setLeft(left);
        }
    }

    /**
     * Offset which specifies the inner area starting from the edges which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setEdgeLimits(Region element, double limit){
        if(edgeLimits.containsKey(element)){
            EdgeLimit edgeLimit = edgeLimits.get(element);
            edgeLimit.setTop(limit);
            edgeLimit.setRight(limit);
            edgeLimit.setBottom(limit);
            edgeLimit.setRight(limit);
        }
    }

    /**
     * Offset which specifies the inner area starting from the top edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setTopEdgeLimit(Region element, double top) {
        if(edgeLimits.containsKey(element)){
            edgeLimits.get(element).setTop(top);
        }
    }

    /**
     * Offset which specifies the inner area starting from the right edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setRightEdgeLimit(Region element, double right) {
        if(edgeLimits.containsKey(element)){
            edgeLimits.get(element).setRight(right);
        }
    }

    /**
     * Offset which specifies the inner area starting from the bottom edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setBottomEdgeLimit(Region element, double bottom) {
        if(edgeLimits.containsKey(element)){
            edgeLimits.get(element).setBottom(bottom);
        }
    }

    /**
     * Offset which specifies the inner area starting from the left edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setLeftEdgeLimit(Region element, double left) {
        if(edgeLimits.containsKey(element)){
            edgeLimits.get(element).setLeft(left);
        }
    }

    public void resetEdgeLimits(Region element) {
        setEdgeLimits(element, 0);
    }

    public EventHandler<MouseEvent> getMousePressedEvent() {
        return mousePressedEvent;
    }

    public EventHandler<MouseEvent> getMouseDragEvent() {
        return moveDragEvent;
    }

    public EventHandler<MouseEvent> getMouseReleasedEvent() {
        return mouseReleasedEvent;
    }

    /**
     * The cursor to appear on mouse press.
     */
    public void setCursor(Region element, Cursor cursor) {
        if(cursors.containsKey(element)){
            cursors.put(element, cursor);
        }
    }

    /**
     * Adds a mouse button which can be used to move the element.
     */
    public void addMouseButton(Region element, MouseButton mouseButton) {
        if(mouseButtons.containsKey(element)){
            mouseButtons.get(element).add(mouseButton);
        }
    }

    /**
     * Removes a mouse button.
     */
    public void removeMouseButton(Region element, MouseButton mouseButton) {
        if (mouseButtons.containsKey(element)) {
            mouseButtons.get(element).remove(mouseButton);
        }
    }

    private Region currElement;
    private boolean validTarget;
    private double currLeftEdgeLimit;
    private double currRightEdgeLimit;
    private double currTopEdgeLimit;
    private double currBottomEdgeLimit;
    private double currTopBoundOffset;
    private double currRightBoundOffset;
    private double currBottomBoundOffset;
    private double currLeftBoundOffset;
    double calculatedRightBoundOffset;
    double calculatedBottomBoundOffset;

    public void initialize() {
        mousePressedEvent = e -> {
            currElement = findCurrentElement(e);
            validTarget = validateTarget(currElement, e);
            if (mouseButtons.containsKey(currElement) && mouseButtons.get(currElement).contains(e.getButton()) && validTarget) {
                double x = Math.abs(currElement.getLayoutX() - e.getX());
                double y = Math.abs(currElement.getLayoutY() - e.getY());
                EdgeLimit edgeLimit = edgeLimits.get(currElement);
                currTopEdgeLimit = edgeLimit.getTop();
                currRightEdgeLimit = edgeLimit.getRight();
                currBottomEdgeLimit = edgeLimit.getBottom();
                currLeftEdgeLimit = edgeLimit.getLeft();
                BoundOffset boundOffset = boundOffsets.get(currElement);
                currTopBoundOffset = boundOffset.getTop();
                currRightBoundOffset = boundOffset.getRight();
                currBottomBoundOffset = boundOffset.getBottom();
                currLeftBoundOffset = boundOffset.getLeft();
                if (x > currLeftEdgeLimit && x < currElement.getPrefWidth() - currRightEdgeLimit
                        && y > currTopEdgeLimit && y < currElement.getPrefHeight() - currBottomEdgeLimit) {
                    calculatedRightBoundOffset = parent.getPrefWidth() - currElement.getPrefWidth() + currRightBoundOffset;
                    calculatedBottomBoundOffset = parent.getPrefHeight() - currElement.getPrefHeight() + currBottomBoundOffset;
                    initialX = x;
                    initialY = y;
                    currElement.setCursor(cursors.get(currElement));
                }
                else {
                    validTarget = false;
                }
            }
        };
        parent.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEvent);

        moveDragEvent = e -> {
            if (mouseButtons.containsKey(currElement) && mouseButtons.get(currElement).contains(e.getButton()) && validTarget) {
                if (initialX > currLeftEdgeLimit && initialX < currElement.getPrefWidth() - currRightEdgeLimit
                        && initialY > currTopEdgeLimit && initialY < currElement.getPrefHeight() - currBottomEdgeLimit) {
                    double x = e.getSceneX() - parent.getLayoutX() - initialX;
                    double y = e.getSceneY() - parent.getLayoutY() - initialY;
                    currElement.setLayoutX(Math.max(currLeftBoundOffset, Math.min(x, calculatedRightBoundOffset)));
                    currElement.setLayoutY(Math.max(currTopBoundOffset, Math.min(y, calculatedBottomBoundOffset)));
                }
            }
        };
        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveDragEvent);

        mouseReleasedEvent = e -> {
            if (mouseButtons.containsKey(currElement) && mouseButtons.get(currElement).contains(e.getButton())) {
                currElement.setCursor(Cursor.DEFAULT);
                currElement = null;
                validTarget = false;
            }
        };
        parent.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedEvent);
    }

    /**
     * Finds the current clicked element and checks if it's present in movableElements.
     * If it's true then the algorithm will return the element
     * else it will return null.
     */
    private Region findCurrentElement(Event event){
        Node currTarget = (Node) event.getTarget();
        Region currEl;
        if(currTarget instanceof Text){
            currEl = (Region) currTarget.getParent();
        }
        else{
            currEl = (Region) currTarget;
        }
        while (currEl != null){
            if(movableElements.containsKey(currEl)){
                return currEl;
            }
            currEl = (Region) currEl.getParent();
            if(currEl == null || currEl.equals(parent)){
                return null;
            }
        }
        return null;
    }

    /**
     * Returns true if the current target is present in the permittedChildren(movableElements element hashset).
     */
    private boolean validateTarget(Region element, Event e) {
        if(element == null){
            return false;
        }
        boolean valid = false;
        Node nodeTarget = (Node) e.getTarget();
        Set<EventTarget> permittedChildren = movableElements.get(element);
        if (permittedChildren.contains(nodeTarget)) {
            valid = true;
        }
        if(nodeTarget instanceof Text){
            valid = true;
        }
        return valid;
    }
}
