package com.example.fastfx.src.userinteraction;

import java.util.HashSet;
import java.util.Set;

/**
 * Specifies the resizable axes of the element which will be resized.<br>
 * WIDTH -> Only the element width will be resized.<br>
 * HEIGHT -> Only the element height will be resized.<br>
 * BOTH -> Width and height of the element will be resized.
 */
public class ResizeAxis {

    /**
     * Only the element width will be resized.
     */
    public static final String WIDTH = "width";

    /**
     * Only the element height will be resized.
     */
    public static final String HEIGHT = "height";

    /**
     * Width and height of the element will be resized.
     */
    public static final String BOTH = "both";

    private final static Set<String> RESIZE_AXES = new HashSet<>(Set.of(WIDTH, HEIGHT, BOTH));

    /**
     * Helps to prevent usage of non-existing resize axes by returning
     * null by default if the input is wrong;
     */
    public static String get (String resizeAxis) {
        if (RESIZE_AXES.contains(resizeAxis)) {
            return resizeAxis;
        }
        return null;
    }

}
