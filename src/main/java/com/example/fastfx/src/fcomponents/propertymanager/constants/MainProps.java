package com.example.fastfx.src.fcomponents.propertymanager.constants;

import java.util.HashSet;
import java.util.Set;

/**
 * Constant properties names used for storing current values in PropertyManager
 */
public class MainProps {

    // Basic properties
    public static final String X = "x";
    public static final String Y = "y";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String TRANSLATE_X = "translateX";
    public static final String TRANSLATE_Y = "translateY";
    public static final String ROTATE = "rotate";
    public static final String TOP = "top";
    public static final String RIGHT = "right";
    public static final String BOTTOM = "bottom";
    public static final String LEFT = "left";

    // Background properties
    public static final String BG_COLOR = "bgColor";
    public static final String BG_OPACITY = "bgOpacity";
    public static final String BG_RADIUS = "bgRadius";
    public static final String BG_TL_RADIUS = "tlBgRadius";
    public static final String BG_TR_RADIUS = "trBgRadius";
    public static final String BG_BR_RADIUS = "brBgRadius";
    public static final String BG_BL_RADIUS = "blBgRadius";

    // Border properties
    public static final String BR_COLOR = "brColor";
    public static final String BR_OPACITY = "brOpacity";
    public static final String BR_STYLE = "brStyle";
    public static final String BR_RADIUS = "brRadius";
    public static final String BR_TL_RADIUS = "tlBrRadius";
    public static final String BR_TR_RADIUS = "trBrRadius";
    public static final String BR_BR_RADIUS = "brBrRadius";
    public static final String BR_BL_RADIUS = "blBrRadius";
    public static final String BR_WIDTH = "brWidth";
    public static final String BR_TOP_COLOR = "brTopColor";
    public static final String BR_RIGHT_COLOR = "brRightColor";
    public static final String BR_BOTTOM_COLOR = "brBottomColor";
    public static final String BR_LEFT_COLOR = "brLeftColor";
    public static final String BR_TOP_STYLE = "brTopStyle";
    public static final String BR_RIGHT_STYLE = "brRightStyle";
    public static final String BR_BOTTOM_STYLE = "brBottomStyle";
    public static final String BR_LEFT_STYLE = "brLeftStyle";
    public static final String BR_TOP_WIDTH = "brTopWidth";
    public static final String BR_RIGHT_WIDTH = "brRightWidth";
    public static final String BR_BOTTOM_WIDTH = "brBottomWidth";
    public static final String BR_LEFT_WIDTH = "brLeftWidth";


    public static boolean contains(String property){
        return MAIN_PROPERTIES.contains(property);
    }

    private static final Set<String> MAIN_PROPERTIES = new HashSet(
            Set.of(
                    X,
                    Y,
                    WIDTH,
                    HEIGHT,
                    SCALE_X,
                    SCALE_Y,
                    TRANSLATE_X,
                    TRANSLATE_Y,
                    ROTATE,
                    TOP,
                    RIGHT,
                    BOTTOM,
                    LEFT,
                    BG_COLOR,
                    BG_OPACITY,
                    BG_RADIUS,
                    BG_TL_RADIUS,
                    BG_TR_RADIUS,
                    BG_BR_RADIUS,
                    BG_BL_RADIUS,
                    BR_COLOR,
                    BR_OPACITY,
                    BR_STYLE,
                    BR_RADIUS,
                    BR_TL_RADIUS,
                    BR_TR_RADIUS,
                    BR_BR_RADIUS,
                    BR_BL_RADIUS,
                    BR_WIDTH,
                    BR_TOP_COLOR,
                    BR_RIGHT_COLOR,
                    BR_BOTTOM_COLOR,
                    BR_LEFT_COLOR,
                    BR_TOP_STYLE,
                    BR_RIGHT_STYLE,
                    BR_BOTTOM_STYLE,
                    BR_LEFT_STYLE,
                    BR_TOP_WIDTH,
                    BR_RIGHT_WIDTH,
                    BR_BOTTOM_WIDTH,
                    BR_LEFT_WIDTH
                  )
    );
}
