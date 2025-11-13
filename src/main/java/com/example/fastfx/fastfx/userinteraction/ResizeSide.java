package com.example.fastfx.fastfx.userinteraction;

import java.util.HashSet;
import java.util.Set;

/**
 * The sides which can be dragged to resize the element.
 */
public class ResizeSide {

    /**
     * Drag by <strong>top side</strong> to resize the element.
     */
    public static final String TOP = "top";

    /**
     * Drag by <strong>top-right corner</strong> to resize the element.
     */
    public static final String TR_CORNER = "trCorner";

    /**
     * Drag by <strong>right side</strong> to resize the element.
     */
    public static final String RIGHT = "right";

    /**
     * Drag by <strong>bottom-right corner</strong> to resize the element.
     */
    public static final String BR_CORNER = "brCorner";

    /**
     * Drag by <strong>bottom side</strong> to resize the element.
     */
    public static final String BOTTOM = "bottom";

    /**
     * Drag by <strong>bottom-left corner</strong> to resize the element.
     */
    public static final String BL_CORNER = "blCorner";

    /**
     * Drag by <strong>left side</strong> to resize the element.
     */
    public static final String LEFT = "left";

    /**
     * Drag by <strong>top left cornet</strong> to resize the element.
     */
    public static final String TL_CORNER = "tlCorner";

    private static final Set<String> RESIZE_SIDES = new HashSet<>(
            Set.of(
                    TOP,
                    TR_CORNER,
                    RIGHT,
                    BR_CORNER,
                    BOTTOM,
                    BL_CORNER,
                    LEFT,
                    TL_CORNER
            )
    );

    /**
     * Helps to prevent usage of non-existing resize modes by returning
     * null by default if the input is wrong;
     */
    public static String get(String resizeSide) {
        if (RESIZE_SIDES.contains(resizeSide)) {
            return resizeSide;
        }
        return null;
    }
}
