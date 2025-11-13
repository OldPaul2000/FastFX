package com.example.fastfx.fastfx.userinteraction;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ElementResizer {

    private Region parent;
    private Set<Region> resizableElements;
    private Set<Region> dragResizableElements;
    private Map<Region, String> resizeModes;
    private Map<Region, Double> resizeFactors;
    private Map<Region, Double> dimensionsRatios;
    private Map<Region, Boolean> keepPositions;
    private Map<Region, Set<String>> resizeSides;
    private Map<Region, Double> resizeEdgeLimits;
    private Map<Region, String> resizeAxes;
    private EventHandler<MouseEvent> mouseMoveEvent;
    private EventHandler<MouseEvent> mouseDragEvent;
    private Map<Region, EventHandler<ScrollEvent>> mouseWheelEvents;

    private final double DEFAULT_RESIZE_FACTOR = 7;
    private final double DEFAULT_RESIZE_EDGE_LIMIT = 7;

    public ElementResizer(Region parent) {
        this.parent = parent;
        this.resizableElements = new HashSet<>();
        this.dragResizableElements = new HashSet<>();
        this.resizeModes = new HashMap<>();
        this.resizeFactors = new HashMap<>();
        this.dimensionsRatios = new HashMap<>();
        this.keepPositions = new HashMap<>();
        this.resizeSides = new HashMap<>();
        this.resizeEdgeLimits = new HashMap<>();
        this.resizeAxes = new HashMap<>();
        this.mouseWheelEvents = new HashMap<>();
    }

    public void addElement(Region element, String resizeMode) {
        if (!resizableElements.contains(element)) {
            String mode = ResizeMode.get(resizeMode);
            if (mode != null) {
                if (mode.equals(ResizeMode.DRAG_EDGES)) {
                    dragResizableElements.add(element);
                    addMouseMoveEvent();
                    addMouseDragEvent();
                }
                resizableElements.add(element);
                resizeModes.put(element, mode);
                setElementEvents(element);
            }
        }
    }

    public void removeElement(Region element) {
        if (resizableElements.contains(element)) {
            removeMouseWheelEvent(element);
            removeProperties(element);
        }
    }

    public void setResizeMode(Region element, String resizeMode) {
        String mode = ResizeMode.get(resizeMode);
        if (mode != null) {
            resizeModes.put(element, mode);
            setElementEvents(element);
        }
    }

    public void setResizeFactor(Region element, double resizeFactor) {
        resizeFactors.put(element, Math.abs(resizeFactor));
    }

    /**
     * Keep the same position of the resizable element relative
     * to the position of the mouse cursor when resize by mouse wheel.
     * It's true by default.
     */
    public void keepPosition(Region element, boolean keepPosition) {
        this.keepPositions.put(element, keepPosition);
    }

    public void setResizeAxis(Region element, String resizeAxis) {
        String axis = ResizeAxis.get(resizeAxis);
        if (axis != null) {
            if (axis.equals(ResizeAxis.BOTH)) {
                calculateDimensionsRatio(element);
            }
            resizeAxes.put(element, axis);
        }
    }

    private void calculateDimensionsRatio(Region element) {
        double ratio = element.getPrefWidth() / element.getPrefHeight();
        dimensionsRatios.put(element, ratio);
    }

    public EventHandler<ScrollEvent> getMouseWheelEvent(Region element) {
        if (mouseWheelEvents.containsKey(element)) {
            return mouseWheelEvents.get(element);
        }
        return null;
    }

    public EventHandler<MouseEvent> getMouseMoveEvent() {
        return mouseMoveEvent;
    }

    public EventHandler<MouseEvent> getMouseDragEvent() {
        return mouseDragEvent;
    }

    /**
     * Choose the logic for resizing the element.
     */
    private void setElementEvents(Region element) {
        String resizeMode = resizeModes.get(element);
        if (resizableElements.contains(element)) {
            removeMouseWheelEvent(element);
        }
        if (resizeMode.equals(ResizeMode.MOUSE_WHEEL)) {
            resizeFactors.putIfAbsent(element, DEFAULT_RESIZE_FACTOR);
            resizeAxes.putIfAbsent(element, ResizeAxis.BOTH);
            keepPositions.putIfAbsent(element, true);
            dragResizableElements.remove(element);
            calculateDimensionsRatio(element);
            addMouseWheelEvent(element);
        }
        if (resizeMode.equals(ResizeMode.DRAG_EDGES)) {
            resizeEdgeLimits.putIfAbsent(element, DEFAULT_RESIZE_EDGE_LIMIT);
            dragResizableElements.add(element);
            addAllResizeSides(element);
            addMouseMoveEvent();
            addMouseDragEvent();
        }
        if (resizeMode.equals(ResizeMode.BOTH)) {
            resizeFactors.putIfAbsent(element, DEFAULT_RESIZE_FACTOR);
            resizeEdgeLimits.putIfAbsent(element, DEFAULT_RESIZE_EDGE_LIMIT);
            resizeAxes.putIfAbsent(element, ResizeAxis.BOTH);
            keepPositions.putIfAbsent(element, true);
            dragResizableElements.add(element);
            addAllResizeSides(element);
            calculateDimensionsRatio(element);
            addMouseWheelEvent(element);
            addMouseMoveEvent();
            addMouseDragEvent();
        }
    }

    /**
     * Adds the event for resizing by mouse wheel.
     */
    private void addMouseWheelEvent(Region element) {
        EventHandler<ScrollEvent> event = e -> {
            double deltaY = e.getDeltaY();
            double x = e.getX();
            double y = e.getY();
            String resizeAxis = resizeAxes.get(element);
            double resizeFactor = resizeFactors.get(element);
            boolean keepPosition = keepPositions.get(element);
            if (deltaY < 0) {
                if (resizeAxis.equals(ResizeAxis.WIDTH)) {
                    element.setPrefWidth(element.getPrefWidth() - resizeFactor);
                }
                else if (resizeAxis.equals(ResizeAxis.HEIGHT)) {
                    element.setPrefHeight(element.getPrefHeight() - resizeFactor);
                }
                else if (resizeAxis.equals(ResizeAxis.BOTH)) {
                    element.setPrefWidth(element.getPrefWidth() - resizeFactor);
                    element.setPrefHeight(element.getPrefHeight() - resizeFactor);
                }
                if (keepPosition) {
                    if (resizeAxis.equals(ResizeAxis.WIDTH)) {
                        element.setLayoutX(element.getLayoutX() + ((x / element.getPrefWidth()) * resizeFactor));
                    }
                    if (resizeAxis.equals(ResizeAxis.HEIGHT)) {
                        element.setLayoutY(element.getLayoutY() + ((y / element.getPrefHeight()) * (resizeFactor)));
                    }
                    if (resizeAxis.equals(ResizeAxis.BOTH)) {
                        element.setLayoutX(element.getLayoutX() + ((x / element.getPrefWidth()) * resizeFactor));
                        element.setLayoutY(element.getLayoutY() + ((y / element.getPrefHeight()) * (resizeFactor)));
                    }
                }
            }
            else {
                if (resizeAxis.equals(ResizeAxis.WIDTH)) {
                    element.setPrefWidth(element.getPrefWidth() + resizeFactor);
                }
                else if (resizeAxis.equals(ResizeAxis.HEIGHT)) {
                    element.setPrefHeight(element.getPrefHeight() + resizeFactor);
                }
                else if (resizeAxis.equals(ResizeAxis.BOTH)) {
                    element.setPrefWidth(element.getPrefWidth() + resizeFactor);
                    element.setPrefHeight(element.getPrefHeight() + resizeFactor);
                }
                if (keepPosition) {
                    if (resizeAxis.equals(ResizeAxis.WIDTH)) {
                        element.setLayoutX(element.getLayoutX() - ((x / element.getPrefWidth()) * resizeFactor));
                    }
                    if (resizeAxis.equals(ResizeAxis.HEIGHT)) {
                        element.setLayoutY(element.getLayoutY() - ((y / element.getPrefHeight()) * (resizeFactor)));
                    }
                    if (resizeAxis.equals(ResizeAxis.BOTH)) {
                        element.setLayoutX(element.getLayoutX() - ((x / element.getPrefWidth()) * resizeFactor));
                        element.setLayoutY(element.getLayoutY() - ((y / element.getPrefHeight()) * (resizeFactor)));
                    }
                }
            }
        };
        element.addEventHandler(ScrollEvent.SCROLL, event);
        mouseWheelEvents.put(element, event);
    }

    private void removeMouseWheelEvent(Region element) {
        EventHandler<ScrollEvent> event = mouseWheelEvents.get(element);
        if (event != null) {
            element.removeEventHandler(ScrollEvent.SCROLL, event);
            mouseWheelEvents.remove(element);
        }
    }
    //===========================================================================================

    private boolean top = false;
    private boolean right = false;
    private boolean bottom = false;
    private boolean left = false;
    private Region currTarget = null;

    private void addMouseMoveEvent() {
        if (mouseMoveEvent == null) {
            mouseMoveEvent = e -> {
                Region target = (Region) e.getTarget();
                addResizeCursor(target, e);
            };
            parent.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMoveEvent);
        }
    }

    private void addResizeCursor(Region target, MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        currTarget = filterTarget(target);
        boolean validated = validateTarget(currTarget);
        if (validated) {
            double resizeRangeOffset = resizeEdgeLimits.get(currTarget);
            if (x >= currTarget.getLayoutX() + currTarget.getPrefWidth() - resizeRangeOffset && x <= currTarget.getLayoutX() + currTarget.getPrefWidth()) {
                currTarget.setCursor(Cursor.H_RESIZE);
                resetResizeSides();
                right = true;
            }
            else if (x >= currTarget.getLayoutX() && x <= currTarget.getLayoutX() + resizeRangeOffset) {
                currTarget.setCursor(Cursor.H_RESIZE);
                resetResizeSides();
                left = true;
            }
            else if (y >= currTarget.getLayoutY() + currTarget.getPrefHeight() - resizeRangeOffset && y <= currTarget.getLayoutY() + currTarget.getPrefHeight()) {
                currTarget.setCursor(Cursor.V_RESIZE);
                resetResizeSides();
                bottom = true;
            }
            else if (y >= currTarget.getLayoutY() && y <= currTarget.getLayoutY() + resizeRangeOffset) {
                currTarget.setCursor(Cursor.V_RESIZE);
                resetResizeSides();
                top = true;
            }
            else {
                currTarget.setCursor(Cursor.DEFAULT);
                currTarget = null;
                resetResizeSides();
            }
        }
    }

    /**
     * Filter the parent element from the current target
     */
    private Region filterTarget(Region currTarget) {
        if (currTarget != null) {
            Region parent = (Region) currTarget.getParent();
            if (dragResizableElements.contains(parent)) {
                return parent;
            }
            if (dragResizableElements.contains(currTarget)) {
                return currTarget;
            }
        }
        return null;
    }

    /**
     * Is used when a child of the resizableElement is at the starting of and edge
     * and if the resizing logic can be triggered when the current event target
     * is the child element
     */
    private boolean validateTarget(Region currTarget) {
        boolean contains = false;
        if (currTarget != null) {
            contains = dragResizableElements.contains(currTarget);
        }
        return contains;
    }

    private void resetResizeSides() {
        top = false;
        right = false;
        bottom = false;
        left = false;
    }

    public void removeMouseMoveEvent() {
        if (mouseMoveEvent != null) {
            parent.removeEventHandler(MouseEvent.MOUSE_MOVED, mouseMoveEvent);
            mouseMoveEvent = null;
        }
    }

    private void addMouseDragEvent() {
        if (mouseDragEvent == null) {
            mouseDragEvent = e -> {
                if (currTarget != null) {
                    double x = e.getX();
                    double y = e.getY();
                    if (top) {
                        currTarget.setPrefHeight(currTarget.getPrefHeight() + currTarget.getLayoutY() - y);
                        currTarget.setLayoutY(y);
                    }
                    if (right) {
                        currTarget.setPrefWidth(Math.abs(currTarget.getLayoutX() - x));
                    }
                    if (bottom) {
                        currTarget.setPrefHeight(Math.abs(currTarget.getLayoutY() - y));
                    }
                    if (left) {
                        currTarget.setPrefWidth(currTarget.getPrefWidth() + currTarget.getLayoutX() - x);
                        currTarget.setLayoutX(x);
                    }
                    ;
                }
            };
            parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDragEvent);
        }
    }

    private void removeMouseDragEvent() {
        if (mouseDragEvent != null) {
            parent.removeEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDragEvent);
            mouseDragEvent = null;
        }
    }

    private void removeProperties(Region element) {
        resizableElements.remove(element);
        dragResizableElements.remove(element);
        resizeModes.remove(element);
        resizeFactors.remove(element);
        dimensionsRatios.remove(element);
        keepPositions.remove(element);
        resizeSides.remove(element);
        resizeEdgeLimits.remove(element);
        resizeAxes.remove(element);
        if (dragResizableElements.isEmpty()) {
            removeMouseMoveEvent();
            removeMouseDragEvent();
        }
    }

    public void addResizeSide(Region element, String resizeSide) {
        if (resizableElements.contains(element)) {
            String side = ResizeSide.get(resizeSide);
            if (side != null) {
                resizeSides.get(element).add(side);
            }
        }
    }

    public void removeResizeSide(Region element, String resizeSide) {
        if (resizableElements.contains(element)) {
            String side = ResizeSide.get(resizeSide);
            if (side != null) {
                resizeSides.get(element).remove(side);
            }
        }
    }

    public void addAllResizeSides(Region element) {
        if (!resizeSides.containsKey(element)) {
            Set<String> elementResizeSides = new HashSet<>();
            elementResizeSides.add(ResizeSide.TOP);
            elementResizeSides.add(ResizeSide.TR_CORNER);
            elementResizeSides.add(ResizeSide.RIGHT);
            elementResizeSides.add(ResizeSide.BR_CORNER);
            elementResizeSides.add(ResizeSide.BOTTOM);
            elementResizeSides.add(ResizeSide.BL_CORNER);
            elementResizeSides.add(ResizeSide.LEFT);
            elementResizeSides.add(ResizeSide.TL_CORNER);
            resizeSides.put(element, elementResizeSides);
        }
    }

    /**
     * Sets the edge offset by which the resize cursor will appear and make
     * resizable by drag possible.
     */
    public void setResizeEdgeLimit(Region element, double limit) {
        this.resizeEdgeLimits.put(element, Math.abs(limit));
    }

    public void printStatus() {
        System.out.println("resizableElements: " + resizableElements.size());
        System.out.println("resizeFactors: " + resizeFactors.size());
        System.out.println("dimensionsRatios: " + dimensionsRatios.size());
        System.out.println("resizeAxes: " + resizeAxes.size());
        System.out.println("keepPositions: " + keepPositions.size());
        System.out.println("=====================================");
        System.out.println("resizeModes: " + resizeModes.size());
        System.out.println("dragResizableElements: " + dragResizableElements.size());
        System.out.println("resizeSides: " + resizeSides.size());
        System.out.println("resizeEdgeLimits: " + resizeEdgeLimits.size());
        System.out.println("mouseMoveEvent: " + mouseMoveEvent);
        System.out.println("mouseDragEvent: " + mouseDragEvent);
        System.out.println("mouseWheelEvents: " + mouseWheelEvents.size());
        System.out.println();
    }
}
