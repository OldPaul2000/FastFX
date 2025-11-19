package com.example.fastfx.src.style.animations;

import javafx.animation.Interpolator;

import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the JavaFX Interpolator values and helps retrieve those values.
 */
public class FInterpolator {

    public static final String EASE_IN = "ease_in";
    public static final String EASE_OUT = "ease_out";
    public static final String EASE_BOTH = "ease_both";
    public static final String LINEAR = "linear";
    public static final String DISCRETE = "discrete";

    /**
     * If the passed interpolator value exists then the corresponding
     * interpolator will be return, otherwise Interpolator.LINEAR will be returned.
     */
    public static Interpolator get(String interpolator) {
        if (!INTERPOLATORS.containsKey(interpolator)) {
            return Interpolator.LINEAR;
        }
        return INTERPOLATORS.get(interpolator);
    }

    private static final Map<String, Interpolator> INTERPOLATORS = new HashMap<>(
            Map.ofEntries(
                    Map.entry("ease_in", Interpolator.EASE_IN),
                    Map.entry("ease_out", Interpolator.EASE_OUT),
                    Map.entry("ease_both", Interpolator.EASE_BOTH),
                    Map.entry("linear", Interpolator.LINEAR),
                    Map.entry("discrete", Interpolator.DISCRETE)
            )
    );

}
