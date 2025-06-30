package com.example.fastfx.fastfx.elements.constants;

import java.util.HashMap;
import java.util.Map;

public class ElementsConstants {

    private static ElementsConstants elementsConstants;
    public static ElementsConstants getInstance(){
        if(elementsConstants == null){
            elementsConstants = new ElementsConstants();
        }
        return elementsConstants;
    }

    private final Map<String, String> ELEMENTS_SHORT_NAMES = new HashMap<>(Map.ofEntries(
                    Map.entry("",""))
    );

}
