package com.example.fastfx.fastfx.userinteraction;

import java.util.HashSet;
import java.util.Set;

/**
 * Modes for <strong>ElementResizer</strong>.(Resize by mouse wheel or by dragging edges).
 */
public class ResizeMode {

    /**
     * When the cursor is on the element to be resized, it can be zoomed-in
     * by rotating the mouse wheel up or zoomed-out by rotating the mouse wheel down.
     */
    public static final String MOUSE_WHEEL = "mouseWheel";

    /**
     * Is works the same way as Windows window behavior. The user can drag
     * the edges of the element to resize it.
     */
    public static final String DRAG_EDGES = "dragEdges";

    /**
     * Apply both modalities for resizing the element.
     */
    public static final String BOTH = "both";

    private static final Set<String> RESIZE_MODES = new HashSet<>(
            Set.of(
                    MOUSE_WHEEL,
                    DRAG_EDGES,
                    BOTH
            )
    );

    /**
     * Helps to prevent usage of non-existing resize modes by returning
     * MOUSE_WHEEL by default if the input is wrong;
     */
    public static String get(String resizeMode) {
        if (RESIZE_MODES.contains(resizeMode)) {
            return resizeMode;
        }
        return null;
    }
}
