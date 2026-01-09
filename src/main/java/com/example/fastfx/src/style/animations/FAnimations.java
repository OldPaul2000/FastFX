package com.example.fastfx.src.style.animations;

import java.util.HashSet;
import java.util.Set;

/**
 * Class with constants names for every kind of animation.
 * These names will be used as argument when creating a new <strong>FAnimation</strong> object.
 */
public class FAnimations {

    //*********************************************************
    //*                                                       *
    //*       Basic properties from Node class                *
    //*                                                       *
    //*********************************************************

    /**
     * Animation name for <strong>prefWidth</strong> property
     */
    public static final String WIDTH = "width";

    /**
     * Animation name for <strong>prefHeight</strong> property
     */
    public static final String HEIGHT = "height";

    /**
     * Animation name for <strong>layoutX</strong> property
     */
    public static final String LAYOUT_X = "layoutX";

    /**
     * Animation name for <strong>layoutY</strong> property
     */
    public static final String LAYOUT_Y = "layoutY";

    /**
     * Animation name for <strong>rotate</strong> property
     */
    public static final String ROTATE = "rotate";

    /**
     * Animation name for <strong>scaleX</strong> property
     */
    public static final String SCALE_X = "scaleX";

    /**
     * Animation name for <strong>scaleY</strong> property
     */
    public static final String SCALE_Y = "scaleY";

    /**
     * Animation name for <strong>translateX</strong> property
     */
    public static final String TRANSLATE_X = "translateX";

    /**
     * Animation name for <strong>translateY</strong> property
     */
    public static final String TRANSLATE_Y = "translateY";

    /**
     * Animation name for <strong>opacity</strong> property
     */
    public static final String OPACITY = "opacity";

    //*********************************************************
    //*                                                       *
    //*       Basic properties from Node class                *
    //*                                                       *
    //*********************************************************
    //************************************************************************
    //*********************************************************
    //*                                                       *
    //*       Custom properties and effects                   *
    //*                                                       *
    //*********************************************************
    //*************
    // Background *
    //*************

    /**
     * Animation name for <strong>background color</strong>
     */
    public static final String BG_COLOR = "bgColor";

    /**
     * Animation name for <strong>background opacity</strong>
     */
    public static final String BG_OPACITY = "bgOpacity";

    /**
     * Animation name for <strong>background radius</strong>
     */
    public static final String BG_RADIUS = "bgRadius";

    /**
     * Animation name for <strong>background top left radius</strong>
     */
    public static final String BG_TL_RADIUS = "bgTLRadius";

    /**
     * Animation name for <strong>background top right radius</strong>
     */
    public static final String BG_TR_RADIUS = "bgTRRadius";

    /**
     * Animation name for <strong>background bottom right radius</strong>
     */
    public static final String BG_BR_RADIUS = "bgBRRadius";

    /**
     * Animation name for <strong>background bottom left radius</strong>
     */
    public static final String BG_BL_RADIUS = "bgBLRadius";

    //*************
    // Background *
    //*************
    //*****************************************************************************
    //*************
    //   Border   *
    //*************

    /**
     * Animation name for <strong>border color</strong>
     */
    public static final String BR_COLOR = "brColor";

    /**
     * Animation name for <strong>border top color</strong>
     */
    public static final String BR_TOP_COLOR = "brTopColor";

    /**
     * Animation name for <strong>border right color</strong>
     */
    public static final String BR_RIGHT_COLOR = "brRightColor";

    /**
     * Animation name for <strong>border bottom color</strong>
     */
    public static final String BR_BOTTOM_COLOR = "brBottomColor";

    /**
     * Animation name for <strong>border left color</strong>
     */
    public static final String BR_LEFT_COLOR = "brLeftColor";

    /**
     * Animation name for <strong>border opacity</strong>
     */
    public static final String BR_OPACITY = "brOpacity";

    /**
     * Animation name for <strong>border width</strong>
     */
    public static final String BR_WIDTH = "brWidth";

    /**
     * Animation name for <strong>border top width</strong>
     */
    public static final String BR_TOP_WIDTH = "brTopWidth";

    /**
     * Animation name for <strong>border right width</strong>
     */
    public static final String BR_RIGHT_WIDTH = "brRightWidth";

    /**
     * Animation name for <strong>border bottom width</strong>
     */
    public static final String BR_BOTTOM_WIDTH = "brBottomWidth";

    /**
     * Animation name for <strong>border left width</strong>
     */
    public static final String BR_LEFT_WIDTH = "brLeftWidth";

    /**
     * Animation name for <strong>border radius</strong>
     */
    public static final String BR_RADIUS = "brRadius";

    /**
     * Animation name for <strong>border top left radius</strong>
     */
    public static final String BR_TL_RADIUS = "brTLRadius";

    /**
     * Animation name for <strong>border top right radius</strong>
     */
    public static final String BR_TR_RADIUS = "brTRRadius";

    /**
     * Animation name for <strong>border bottom right radius</strong>
     */
    public static final String BR_BR_RADIUS = "brBRRadius";

    /**
     * Animation name for <strong>border bottom left radius</strong>
     */
    public static final String BR_BL_RADIUS = "brBLRadius";

    //*************
    //   Border   *
    //*************
    //**************************************************************************
    //*************
    //   Effects  *
    //*************

    /**
     * Animation name for <strong>DropShadow</strong>
     */
    public static final String SHADOW = "shadow";

    /**
     * Animation name for <strong>GaussianBlur</strong>
     */
    public static final String BLUR = "blur";

    //**************
    //   Effects   *
    //**************
    //*********************************************************
    //*                                                       *
    //*       Custom properties and effects                   *
    //*                                                       *
    //*********************************************************

    /**
     * If the passed animation value exists then the corresponding
     * animation will be return, otherwise null will be returned.
     */
    public static String get(String animation) {
        if (ANIMATIONS.contains(animation)) {
            return animation;
        }
        return null;
    }

    private static final Set<String> ANIMATIONS = new HashSet<>(
            Set.of(
                    WIDTH,
                    HEIGHT,
                    LAYOUT_X,
                    LAYOUT_Y,
                    ROTATE,
                    SCALE_X,
                    SCALE_Y,
                    TRANSLATE_X,
                    TRANSLATE_Y,
                    OPACITY,
                    BG_COLOR,
                    BG_OPACITY,
                    BG_RADIUS,
                    BG_TL_RADIUS,
                    BG_TR_RADIUS,
                    BG_BR_RADIUS,
                    BG_BL_RADIUS,
                    BR_COLOR,
                    BR_TOP_COLOR,
                    BR_RIGHT_COLOR,
                    BR_BOTTOM_COLOR,
                    BR_LEFT_COLOR,
                    BR_OPACITY,
                    BR_WIDTH,
                    BR_TOP_WIDTH,
                    BR_RIGHT_WIDTH,
                    BR_BOTTOM_WIDTH,
                    BR_LEFT_WIDTH,
                    BR_RADIUS,
                    BR_TL_RADIUS,
                    BR_TR_RADIUS,
                    BR_BR_RADIUS,
                    BR_BL_RADIUS,
                    SHADOW,
                    BLUR
            )
    );
}
