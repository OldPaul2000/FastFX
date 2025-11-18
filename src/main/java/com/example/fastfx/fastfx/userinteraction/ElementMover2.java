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
public class ElementMover2 {

    private Pane parent;
    private Map<Region, Set<EventTarget>> movableElements;
    private double initialX;
    private double initialY;
    private boolean isPermittedChild;
    private Map<Region, BoundOffset> boundOffsets;
    private Map<Region, EdgeLimit> edgeLimits;
    private Map<Region, Cursor> cursors;
    private Map<Region, Set<MouseButton>> mouseButtons;
    private EventHandler<MouseEvent> moveDragEvent;
    private EventHandler<MouseEvent> mousePressedEvent;
    private EventHandler<MouseEvent> mouseReleasedEvent;

    public ElementMover2(Pane parent) {
        this.parent = parent;
        this.movableElements = new HashMap<>();
        this.initialX = 0;
        this.initialY = 0;
        this.isPermittedChild = false;
        this.boundOffsets = new HashMap<>();
        this.edgeLimits = new HashMap<>();
        this.cursors = new HashMap<>();
        this.mouseButtons = new HashMap<>();
        this.moveDragEvent = null;
        this.mousePressedEvent = null;
        this.mouseReleasedEvent = null;
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
            addMousePressedEvent(element);
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
            boundOffset.setBoundsOffset(offset);
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
            boundOffsets.get(element).setRight(right);
        }
    }

    /**
     * Represents the bottom offset limit in pixels by which the movable element can be moved inside its parent.
     */
    public void setBottomOffset(Region element, double bottom) {
        if(boundOffsets.containsKey(element)){
            boundOffsets.get(element).setBottom(bottom);
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
            edgeLimits.get(element).setEdgesLimit(limit);
        }
    }

    /**
     * Offset which specifies the inner area starting from the top edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setTopEdgeLimit(Region element, double top) {
        if(edgeLimits.containsKey(element)){
            edgeLimits.get(element).setTopEdgeLimit(top);
        }
    }

    /**
     * Offset which specifies the inner area starting from the right edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setRightEdgeLimit(Region element, double right) {
        if(edgeLimits.containsKey(element)){
            edgeLimits.get(element).setRightEdgeLimit(right);
        }
    }

    /**
     * Offset which specifies the inner area starting from the bottom edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setBottomEdgeLimit(Region element, double bottom) {
        if(edgeLimits.containsKey(element)){
            edgeLimits.get(element).setBottomEdgeLimit(bottom);
        }
    }

    /**
     * Offset which specifies the inner area starting from the left edge which will
     * be unresponsive for movement. Is used if this element is set to be
     * resizable by dragging edges and to not interfere with that event.
     */
    public void setLeftEdgeLimit(Region element, double left) {
        if(edgeLimits.containsKey(element)){
            edgeLimits.get(element).setLeftEdgeLimit(left);
        }
    }

    public void resetEdgeLimits(Region element) {
        if (edgeLimits.containsKey(element)) {
            edgeLimits.get(element).setEdgesLimit(0);
        }
    }

    private Region currElement = null;
    double calculatedXPlusBoundOffset;
    double calculatedYPlusBoundOffset;

    private void addMousePressedEvent(Region element){
        mousePressedEvent = e -> {
            if (mouseButtons.get(currElement).contains(e.getButton())) {
                double x = e.getX();
                double y = e.getY();
                double topEdgeLimit = edgeLimits.get(element).getTop();
                double rightEdgeLimit = edgeLimits.get(element).getRight();
                double bottomEdgeLimit = edgeLimits.get(element).getBottom();
                double leftEdgeLimit = edgeLimits.get(element).getLeft();
                double rightBoundOffset = boundOffsets.get(element).getRight();
                double bottomBoundOffset = boundOffsets.get(element).getBottom();
                if (x > leftEdgeLimit && x < element.getPrefWidth() - rightEdgeLimit
                        && y > topEdgeLimit && y < element.getPrefHeight() - bottomEdgeLimit) {
                    calculatedXPlusBoundOffset = parent.getPrefWidth() - element.getPrefWidth() + rightBoundOffset;
                    calculatedYPlusBoundOffset = parent.getPrefHeight() - element.getPrefHeight() + bottomBoundOffset;
//                    isPermittedChild = validateTarget(e);
                    if (isPermittedChild) {
                        initialX = x;
                        initialY = y;
                        element.setCursor(cursors.get(element));
                    }
                }
                else {
                    isPermittedChild = false;
                }
            }
        };
        element.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEvent);
    }

    public EventHandler<MouseEvent> getMousePressedEvent() {
        return mousePressedEvent;
    }

    public EventHandler<MouseEvent> getMouseDragEvent() {
        return moveDragEvent;
    }

    private void addMouseDragEvent(Region element){
//        moveDragEvent = e -> {
//            if (mouseButtons.get(element).contains(e.getButton())) {
//                if (isPermittedChild) {
//                    if (initialX > leftEdgeLimit && initialX < movableElement.getPrefWidth() - rightEdgeLimit
//                            && initialY > topEdgeLimit && initialY < movableElement.getPrefHeight() - bottomEdgeLimit) {
//                        double x = e.getSceneX() - parent.getLayoutX() - initialX;
//                        double y = e.getSceneY() - parent.getLayoutY() - initialY;
//                        movableElement.setLayoutX(Math.max(leftBoundOffset, Math.min(x, calculatedXPlusBoundOffset)));
//                        movableElement.setLayoutY(Math.max(topBoundOffset, Math.min(y, calculatedYPlusBoundOffset)));
//                    }
//                }
//            }
//        };
//        movableElement.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveDragEvent);
    }

    public EventHandler<MouseEvent> getMouseReleasedEvent() {
        return mouseReleasedEvent;
    }

    private void addMouseReleasedEvent(Region element){

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

    public void initialize() {
//        mousePressedEvent = e -> {
//            Region movableElement = findCurrentElement(e);
//            if (mouseButtons.get(currElement).contains(e.getButton())) {
//                double x = e.getX();
//                double y = e.getY();
//                if (x > leftEdgeLimit && x < movableElement.getPrefWidth() - rightEdgeLimit
//                        && y > topEdgeLimit && y < movableElement.getPrefHeight() - bottomEdgeLimit) {
//                    calculatedXPlusBoundOffset = parent.getPrefWidth() - movableElement.getPrefWidth() + rightBoundOffset;
//                    calculatedYPlusBoundOffset = parent.getPrefHeight() - movableElement.getPrefHeight() + bottomBoundOffset;
//                    isPermittedChild = validateTarget(e);
//                    if (isPermittedChild) {
//                        initialX = x;
//                        initialY = y;
//                        movableElement.setCursor(cursor);
//                    }
//                }
//                else {
//                    isPermittedChild = false;
//                }
//            }
//        };
//        movableElement.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEvent);

//        moveDragEvent = e -> {
//            if (mouseButtons.contains(e.getButton())) {
//                if (isPermittedChild) {
//                    if (initialX > leftEdgeLimit && initialX < movableElement.getPrefWidth() - rightEdgeLimit
//                            && initialY > topEdgeLimit && initialY < movableElement.getPrefHeight() - bottomEdgeLimit) {
//                        double x = e.getSceneX() - parent.getLayoutX() - initialX;
//                        double y = e.getSceneY() - parent.getLayoutY() - initialY;
//                        movableElement.setLayoutX(Math.max(leftBoundOffset, Math.min(x, calculatedXPlusBoundOffset)));
//                        movableElement.setLayoutY(Math.max(topBoundOffset, Math.min(y, calculatedYPlusBoundOffset)));
//                    }
//                }
//            }
//        };
//        movableElement.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveDragEvent);

//        mouseReleasedEvent = e -> {
//            if (mouseButtons.contains(e.getButton())) {
//                if (validateTarget(e)) {
//                    movableElement.setCursor(Cursor.DEFAULT);
//                }
//            }
//        };
//        movableElement.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedEvent);
    }

    private Region findCurrentElement(Event event){
        Region currEl = (Region) event.getTarget();
        while (!movableElements.containsKey(currEl)){
            currEl = (Region) currEl.getParent();
            if(currEl == null){
                break;
            }
            if(movableElements.containsKey(currEl)){
                break;
            }
        }
        if(!movableElements.containsKey(currEl)){
            currEl = null;
        }
        return currEl;
    }

    /**
     * Returns true if the current target is present in the permittedChildren.
     */
//    private boolean validateTarget(Event e) {
//        boolean valid = false;
//        Node target = (Node) e.getTarget();
//        Node targetParent = ((Node) e.getTarget()).getParent();
//        if (permittedChildren.contains(target)) {
//            valid = true;
//        }
//        if (target instanceof Text) {
//            valid = permittedChildren.contains(targetParent);
//        }
//        return valid;
//    }

    /**
     * Initializes the movement logic.
     */
//    public void initialize() {
//        mousePressedEvent = e -> {
//            if (mouseButtons.contains(e.getButton())) {
//                double x = e.getX();
//                double y = e.getY();
//                if (x > leftEdgeLimit && x < movableElement.getPrefWidth() - rightEdgeLimit
//                        && y > topEdgeLimit && y < movableElement.getPrefHeight() - bottomEdgeLimit) {
//                    calculatedXPlusBoundOffset = parent.getPrefWidth() - movableElement.getPrefWidth() + rightBoundOffset;
//                    calculatedYPlusBoundOffset = parent.getPrefHeight() - movableElement.getPrefHeight() + bottomBoundOffset;
//                    isPermittedChild = validateTarget(e);
//                    if (isPermittedChild) {
//                        initialX = x;
//                        initialY = y;
//                        movableElement.setCursor(cursor);
//                    }
//                }
//                else {
//                    isPermittedChild = false;
//                }
//            }
//        };
//        movableElement.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedEvent);
//
//        moveDragEvent = e -> {
//            if (mouseButtons.contains(e.getButton())) {
//                if (isPermittedChild) {
//                    if (initialX > leftEdgeLimit && initialX < movableElement.getPrefWidth() - rightEdgeLimit
//                            && initialY > topEdgeLimit && initialY < movableElement.getPrefHeight() - bottomEdgeLimit) {
//                        double x = e.getSceneX() - parent.getLayoutX() - initialX;
//                        double y = e.getSceneY() - parent.getLayoutY() - initialY;
//                        movableElement.setLayoutX(Math.max(leftBoundOffset, Math.min(x, calculatedXPlusBoundOffset)));
//                        movableElement.setLayoutY(Math.max(topBoundOffset, Math.min(y, calculatedYPlusBoundOffset)));
//                    }
//                }
//            }
//        };
//        movableElement.addEventHandler(MouseEvent.MOUSE_DRAGGED, moveDragEvent);
//
//        mouseReleasedEvent = e -> {
//            if (mouseButtons.contains(e.getButton())) {
//                if (validateTarget(e)) {
//                    movableElement.setCursor(Cursor.DEFAULT);
//                }
//            }
//        };
//        movableElement.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedEvent);
//    }


//    /**
//     * Returns true if the current target is present in the permittedChildren.
//     */
    private boolean validateTarget(Region element, Event e) {
        boolean valid = false;
        Node target = (Node) e.getTarget();
        Node targetParent = ((Node) e.getTarget()).getParent();
        if (movableElements.get(element).contains(target)) {
            valid = true;
        }
        if (target instanceof Text) {
            valid = movableElements.get(e).contains(targetParent);
        }
        return valid;
    }

}
