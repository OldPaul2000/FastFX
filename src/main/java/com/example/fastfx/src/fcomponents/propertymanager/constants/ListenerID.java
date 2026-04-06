package com.example.fastfx.src.fcomponents.propertymanager.constants;

/**
 * Listener IDs used in PropertyManager and PropertyHelper
 */
public class ListenerID {
    public static final String SEPARATOR = "_";
    public static final String PARENT = "PARENT";
    public static final String COMPONENT = "COMPONENT";
    public static final String CHILD = "CHILD";

    public static final String WIDTH_X = "WIDTH_X";
    public static final String HEIGHT_Y = "HEIGHT_Y";
    public static final String WIDTH_WIDTH = "WIDTH_WIDTH";
    public static final String HEIGHT_HEIGHT = "HEIGHT_HEIGHT";
    public static final String TRANSLATE_X = "TRANSLATE_X";
    public static final String TRANSLATE_Y = "TRANSLATE_Y";
    public static final String HEIGHT_VERTICAL_PADDING = "HEIGHT_VERTICAL_PADDING";
    public static final String WIDTH_HORIZONTAL_PADDING = "WIDTH_HORIZONTAL_PADDING";
    public static final String HEIGHT_TOP_PADDING = "HEIGHT_TOP_PADDING";
    public static final String WIDTH_RIGHT_PADDING = "WIDTH_RIGHT_PADDING";
    public static final String HEIGHT_BOTTOM_PADDING = "HEIGHT_BOTTOM_PADDING";
    public static final String WIDTH_LEFT_PADDING = "WIDTH_LEFT_PADDING";
    public static final String HEIGHT_TOP = "HEIGHT_TOP";
    public static final String WIDTH_RIGHT = "WIDTH_RIGHT";
    public static final String HEIGHT_BOTTOM = "HEIGHT_BOTTOM";
    public static final String WIDTH_LEFT = "WIDTH_LEFT";

    public static final String HEIGHT_BOTTOM_PARENT = HEIGHT_BOTTOM + SEPARATOR + COMPONENT;
    public static final String HEIGHT_BOTTOM_COMPONENT = HEIGHT_BOTTOM + SEPARATOR + PARENT;
    public static final String WIDTH_RIGHT_PARENT = WIDTH_RIGHT + SEPARATOR + PARENT;
    public static final String WIDTH_RIGHT_COMPONENT = WIDTH_RIGHT + SEPARATOR + COMPONENT;

    public static final String BG_COLOR = "BG_COLOR";
    public static final String BG_OPACITY = "BG_OPACITY";
}
