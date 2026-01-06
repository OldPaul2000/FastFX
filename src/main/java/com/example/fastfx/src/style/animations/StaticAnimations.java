package com.example.fastfx.src.style.animations;

import java.util.HashSet;
import java.util.Set;

public class StaticAnimations {

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

    public static String get(String animation) {
        if (ANIMATIONS.contains(animation)) {
            return animation;
        }
        return null;
    }

    private static final Set<String> ANIMATIONS = new HashSet<>(
            Set.of(
                    BR_STYLE,
                    BR_TOP_STYLE,
                    BR_RIGHT_STYLE,
                    BR_BOTTOM_STYLE,
                    BR_LEFT_STYLE
            )
    );

}
