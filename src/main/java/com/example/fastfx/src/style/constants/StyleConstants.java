package com.example.fastfx.src.style.constants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StyleConstants {

    // Custom styles need some verifying and updates.

    private static StyleConstants styleConstants;
    public static StyleConstants getInstance(){
        if(styleConstants == null){
            styleConstants = new StyleConstants();
        }
        return styleConstants;
    }

    private final Map<String, String> NATIVE_CSS_PROPERTIES = new HashMap<>(Map.ofEntries(
            Map.entry("w", "-fx-pref-width"),
            Map.entry("h", "-fx-pref-height"),
            Map.entry("tx", "-fx-translate-x"),
            Map.entry("ty", "-fx-translate-y"),
            Map.entry("tz", "-fx-translate-z"),
            Map.entry("rt", "-fx-rotate"),
            Map.entry("sc-x", "-fx-scale-x"),
            Map.entry("sc-y", "-fx-scale-y"),
            Map.entry("pd", "-fx-padding"),
            Map.entry("f-s", "-fx-font-size"),
            Map.entry("f-w", "-fx-font-weight"),
            Map.entry("br-r", "-fx-border-radius"),
            Map.entry("br-w", "-fx-border-width"),
            Map.entry("bc-i", "-fx-background-insets"),
            Map.entry("bc-r", "-fx-background-radius"),
            Map.entry("op", "-fx-opacity"),
            Map.entry("br-s", "-fx-border-style"),
            Map.entry("bc-c", "-fx-background-color"),
            Map.entry("t-f", "-fx-text-fill"),
            Map.entry("br-c", "-fx-border-color"),
            Map.entry("f-c", "-fx-focus-color"),
            Map.entry("ff-c", "-fx-faint-focus-color"),
            Map.entry("ds", "-fx-effect:dropshadow"),
            Map.entry("is", "-fx-effect:innershadow"),
            Map.entry("vo", "(view order)")
    ));
    public Map<String, String> getNATIVE_CSS_PROPERTIES(){
        return new HashMap<>(NATIVE_CSS_PROPERTIES);
    }

    // al -> alignment property
    // d -> display
    // t -> transition
    // o -> orientation (for flex display)
    private final Set<String> CUSTOM_DISPLAY_PROPERTIES = new HashSet<>(Set.of(
            "al", "d", "o", "t"
    ));
    public Set<String> getCUSTOM_DISPLAY_PROPERTIES(){
        return CUSTOM_DISPLAY_PROPERTIES;
    }

    // n -> display none
    // gr -> grid
    // fl -> flex
    private final Set<String> CUSTOM_DISPLAY_VALUES = new HashSet<>(Set.of(
            "n", "gr", "fl"
    ));
    public Set<String> getCUSTOM_DISPLAY_VALUES(){
        return CUSTOM_DISPLAY_VALUES;
    }

    // Overflow
    // property naming: ovf
    // hd -> hidden
    // sc -> scroll
    private final Set<String> OVERFLOW_VALUES = new HashSet<>(Set.of(
            "hd", "sc"
    ));
    public Set<String> getOVERFLOW_VALUES(){
        return OVERFLOW_VALUES;
    }

    // Orientation
    // property naming: or
    // r -> row
    // c -> column
    private final Set<String> CUSTOM_ORIENTATION_VALUES = new HashSet<>(Set.of(
            "row", "col"
    ));
    public Set<String> getCUSTOM_ORIENTATION_VALUES(){
        return CUSTOM_ORIENTATION_VALUES;
    }

    // Alignment
    // property naming: al (see CUSTOM_DISPLAY_PROPERTIES)
    // sp-b -> space between
    // sp-a -> space around
    // l -> left
    // r -> right
    // t -> top
    // b -> bottom
    // If space around is set together with l or r or t or b or a combination of these
    // the space in that direction(between the container border and those elements) will be 0,
    // if padding is set the space will be that padding
    private final Set<String> CUSTOM_ALIGNMENT_VALUES = new HashSet<>(Set.of(
       "sp-b", "sp-a", "l", "r", "t", "b"
    ));
    public Set<String> getCUSTOM_ALIGNMENT_VALUES(){
        return CUSTOM_ALIGNMENT_VALUES;
    }

}
