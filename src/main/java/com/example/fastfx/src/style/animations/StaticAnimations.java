package com.example.fastfx.src.style.animations;

import java.util.HashSet;
import java.util.Set;

public class StaticAnimations {
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

    /**
     * Animation name for <strong>padding</strong> property
     */
    public static final String PADDING = "padding";

    /**
     * Animation name for <strong>top padding</strong> property
     */
    public static final String TOP_PADDING = "topPadding";

    /**
     * Animation name for <strong>right padding</strong> property
     */
    public static final String RIGHT_PADDING = "rightPadding";

    /**
     * Animation name for <strong>bottom padding</strong> property
     */
    public static final String BOTTOM_PADDING = "bottomPadding";

    /**
     * Animation name for <strong>left padding</strong> property
     */
    public static final String LEFT_PADDING = "leftPadding";

    /**
     * Animation name for <strong>margin</strong> property
     */
    public static final String MARGIN = "margin";

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

    /**
     * Animation name for <strong>background insets</strong>
     */
    public static final String BG_INSETS = "bgInsets";

    /**
     * Animation name for <strong>background insets</strong>
     */
    public static final String BG_TOP_INSET = "bgTopInset";

    /**
     * Animation name for <strong>background insets</strong>
     */
    public static final String BG_RIGHT_INSET = "bgRightInset";

    /**
     * Animation name for <strong>background insets</strong>
     */
    public static final String BG_BOTTOM_INSET = "bgBottomInset";

    /**
     * Animation name for <strong>background insets</strong>
     */
    public static final String BG_LEFT_INSET = "bgLeftInset";

    //*************
    // Background *
    //*************
    //*****************************************************************************
    //*************
    //   Border   *
    //*************
    /**
     * Animation name for <strong>border</strong>
     */
    public static final String BORDER = "border";

    /**
     * Animation name for <strong>top border</strong>
     */
    public static final String TOP_BORDER = "topBorder";

    /**
     * Animation name for <strong>right border</strong>
     */
    public static final String RIGHT_BORDER = "rightBorder";

    /**
     * Animation name for <strong>bottom border</strong>
     */
    public static final String BOTTOM_BORDER = "bottomBorder";

    /**
     * Animation name for <strong>left border</strong>
     */
    public static final String LEFT_BORDER = "leftBorder";

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
     * Animation name for <strong>border insets</strong>
     */
    public static final String BR_INSETS = "brInsets";

    /**
     * Animation name for <strong>top border inset</strong> property
     */
    public static final String BR_TOP_INSET = "brTopInset";

    /**
     * Animation name for <strong>right border inset</strong> property
     */
    public static final String BR_RIGHT_INSET = "brRightInset";

    /**
     * Animation name for <strong>bottom border inset</strong> property
     */
    public static final String BR_BOTTOM_INSET = "brBottomInset";

    /**
     * Animation name for <strong>left border inset</strong> property
     */
    public static final String BR_LEFT_INSET = "brLeftInset";

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

    /**
     * Animation name for <strong>border style</strong>
     */
    public static final String BR_STYLE = "brStyle";

    /**
     * Animation name for <strong>border top style</strong>
     */
    public static final String BR_TOP_STYLE = "brTopStyle";

    /**
     * Animation name for <strong>border right style</strong>
     */
    public static final String BR_RIGHT_STYLE = "brRightStyle";

    /**
     * Animation name for <strong>border bottom style</strong>
     */
    public static final String BR_BOTTOM_STYLE = "brBottomStyle";

    /**
     * Animation name for <strong>border left style</strong>
     */
    public static final String BR_LEFT_STYLE = "brLeftStyle";
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
                    PADDING,
                    TOP_PADDING,
                    RIGHT_PADDING,
                    BOTTOM_PADDING,
                    LEFT_PADDING,
                    MARGIN,
                    BG_COLOR,
                    BG_OPACITY,
                    BG_RADIUS,
                    BG_TL_RADIUS,
                    BG_TR_RADIUS,
                    BG_BR_RADIUS,
                    BG_BL_RADIUS,
                    BG_INSETS,
                    BG_TOP_INSET,
                    BG_RIGHT_INSET,
                    BG_BOTTOM_INSET,
                    BG_LEFT_INSET,
                    BORDER,
                    TOP_BORDER,
                    RIGHT_BORDER,
                    BOTTOM_BORDER,
                    LEFT_BORDER,
                    BR_OPACITY,
                    BR_WIDTH,
                    BR_TOP_WIDTH,
                    BR_RIGHT_WIDTH,
                    BR_BOTTOM_WIDTH,
                    BR_LEFT_WIDTH,
                    BR_INSETS,
                    BR_TOP_INSET,
                    BR_RIGHT_INSET,
                    BR_BOTTOM_INSET,
                    BR_LEFT_INSET,
                    BR_RADIUS,
                    BR_TL_RADIUS,
                    BR_TR_RADIUS,
                    BR_BR_RADIUS,
                    BR_BL_RADIUS,
                    BR_STYLE,
                    BR_TOP_STYLE,
                    BR_RIGHT_STYLE,
                    BR_BOTTOM_STYLE,
                    BR_LEFT_STYLE,
                    SHADOW,
                    BLUR
            )
    );

}
