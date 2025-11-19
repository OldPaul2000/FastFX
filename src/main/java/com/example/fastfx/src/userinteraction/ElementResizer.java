package com.example.fastfx.src.userinteraction;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class lets the user resize a specific element by dragging its edges<br>
 * or by using the mouse wheel.
 */
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

    public void removeMouseWheelEvent(Region element) {
        EventHandler<ScrollEvent> event = mouseWheelEvents.get(element);
        if (event != null) {
            element.removeEventHandler(ScrollEvent.SCROLL, event);
            mouseWheelEvents.remove(element);
        }
    }

    private boolean top = false;
    private boolean right = false;
    private boolean bottom = false;
    private boolean left = false;
    private boolean topLeft = false;
    private boolean topRight = false;
    private boolean bottomRight = false;
    private boolean bottomLeft = false;
    private Region currTarget = null;

    private void addMouseMoveEvent() {
        if (mouseMoveEvent == null) {
            mouseMoveEvent = e -> {
                addResizeCursor(e);
            };
            parent.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMoveEvent);
        }
    }

    private void addResizeCursor(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        currTarget = filterTarget(e);
        boolean validated = validateTarget(currTarget);
        if (validated) {
            double resizeRangeOffset = resizeEdgeLimits.get(currTarget);
            if (x >= currTarget.getLayoutX() + currTarget.getPrefWidth() - resizeRangeOffset && x <= currTarget.getLayoutX() + currTarget.getPrefWidth()
                    && y >= currTarget.getLayoutY() && y <= currTarget.getLayoutY() + resizeRangeOffset
                    && resizeSides.get(currTarget).contains(ResizeSide.TR_CORNER)) {
                currTarget.setCursor(Cursor.NE_RESIZE);
                resetResizeSides();
                topRight = true;
            }
            else if (x >= currTarget.getLayoutX() + currTarget.getPrefWidth() - resizeRangeOffset && x <= currTarget.getLayoutX() + currTarget.getPrefWidth()
                    && y >= currTarget.getLayoutY() + currTarget.getPrefHeight() - resizeRangeOffset && y <= currTarget.getLayoutY() + currTarget.getPrefHeight()
                    && resizeSides.get(currTarget).contains(ResizeSide.BR_CORNER)) {
                currTarget.setCursor(Cursor.SE_RESIZE);
                resetResizeSides();
                bottomRight = true;
            }
            else if (x >= currTarget.getLayoutX() && x <= currTarget.getLayoutX() + resizeRangeOffset
                    && y >= currTarget.getLayoutY() + currTarget.getPrefHeight() - resizeRangeOffset && y <= currTarget.getLayoutY() + currTarget.getPrefHeight()
                    && resizeSides.get(currTarget).contains(ResizeSide.BL_CORNER)) {
                currTarget.setCursor(Cursor.SW_RESIZE);
                resetResizeSides();
                bottomLeft = true;
            }
            else if (x >= currTarget.getLayoutX() && x <= currTarget.getLayoutX() + resizeRangeOffset
                    && y >= currTarget.getLayoutY() && y <= currTarget.getLayoutY() + resizeRangeOffset
                    && resizeSides.get(currTarget).contains(ResizeSide.TL_CORNER)) {
                currTarget.setCursor(Cursor.NW_RESIZE);
                resetResizeSides();
                topLeft = true;
            }
            else if (x >= currTarget.getLayoutX() + currTarget.getPrefWidth() - resizeRangeOffset && x <= currTarget.getLayoutX() + currTarget.getPrefWidth()
                    && resizeSides.get(currTarget).contains(ResizeSide.RIGHT)) {
                currTarget.setCursor(Cursor.H_RESIZE);
                resetResizeSides();
                right = true;
            }
            else if (x >= currTarget.getLayoutX() && x <= currTarget.getLayoutX() + resizeRangeOffset
                    && resizeSides.get(currTarget).contains(ResizeSide.LEFT)) {
                currTarget.setCursor(Cursor.H_RESIZE);
                resetResizeSides();
                left = true;
            }
            else if (y >= currTarget.getLayoutY() + currTarget.getPrefHeight() - resizeRangeOffset && y <= currTarget.getLayoutY() + currTarget.getPrefHeight()
                    && resizeSides.get(currTarget).contains(ResizeSide.BOTTOM)) {
                currTarget.setCursor(Cursor.V_RESIZE);
                resetResizeSides();
                bottom = true;
            }
            else if (y >= currTarget.getLayoutY() && y <= currTarget.getLayoutY() + resizeRangeOffset
                    && resizeSides.get(currTarget).contains(ResizeSide.TOP)) {
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

    public void removeMouseMoveEvent() {
        if (mouseMoveEvent != null) {
            parent.removeEventHandler(MouseEvent.MOUSE_MOVED, mouseMoveEvent);
            mouseMoveEvent = null;
        }
    }

    /**
     * Filter the parent element from the current target
     */
    private Region filterTarget(MouseEvent e) {
        Node currTarget = (Node) e.getTarget();
        Region currEl;
        if (currTarget instanceof Text) {
            currEl = (Region) currTarget.getParent();
        }
        else {
            currEl = (Region) currTarget;
        }
        while (currEl != null) {
            if (resizableElements.contains(currEl)) {
                return currEl;
            }
            currEl = (Region) currEl.getParent();
            if (currEl == null || currEl.equals(parent)) {
                return null;
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
        topLeft = false;
        topRight = false;
        bottomRight = false;
        bottomLeft = false;
    }

    private void addMouseDragEvent() {
        if (mouseDragEvent == null) {
            mouseDragEvent = e -> {
                if (currTarget != null) {
                    double x = e.getX();
                    double y = e.getY();
                    if (topLeft) {
                        currTarget.setPrefHeight(currTarget.getPrefHeight() + currTarget.getLayoutY() - y);
                        currTarget.setLayoutY(y);
                        currTarget.setPrefWidth(currTarget.getPrefWidth() + currTarget.getLayoutX() - x);
                        currTarget.setLayoutX(x);
                    }
                    else if (top) {
                        currTarget.setPrefHeight(currTarget.getPrefHeight() + currTarget.getLayoutY() - y);
                        currTarget.setLayoutY(y);
                    }
                    if (topRight) {
                        currTarget.setPrefHeight(currTarget.getPrefHeight() + currTarget.getLayoutY() - y);
                        currTarget.setLayoutY(y);
                        currTarget.setPrefWidth(Math.abs(currTarget.getLayoutX() - x));
                    }
                    else if (right) {
                        currTarget.setPrefWidth(Math.abs(currTarget.getLayoutX() - x));
                    }
                    if (bottomRight) {
                        currTarget.setPrefHeight(Math.abs(currTarget.getLayoutY() - y));
                        currTarget.setPrefWidth(Math.abs(currTarget.getLayoutX() - x));
                    }
                    else if (bottom) {
                        currTarget.setPrefHeight(Math.abs(currTarget.getLayoutY() - y));
                    }
                    if (bottomLeft) {
                        currTarget.setPrefHeight(Math.abs(currTarget.getLayoutY() - y));
                        currTarget.setPrefWidth(currTarget.getPrefWidth() + currTarget.getLayoutX() - x);
                        currTarget.setLayoutX(x);
                    }
                    else if (left) {
                        currTarget.setPrefWidth(currTarget.getPrefWidth() + currTarget.getLayoutX() - x);
                        currTarget.setLayoutX(x);
                    }
                }
            };
            parent.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDragEvent);
        }
    }

    public void removeMouseDragEvent() {
        if (mouseDragEvent != null) {
            parent.removeEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDragEvent);
            mouseDragEvent = null;
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
}
