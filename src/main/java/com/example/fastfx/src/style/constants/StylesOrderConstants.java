package com.example.fastfx.src.style.constants;

import java.util.HashMap;
import java.util.Map;

public class StylesOrderConstants {

        /** Style properties and their specific indexes*/
        private static final Map<String, Integer> PROPERTIES_ORDER = new HashMap<>(
                Map.ofEntries(
                        Map.entry("w", 1),
                        Map.entry("h", 2),
                        Map.entry("tx", 3),
                        Map.entry("ty", 4),
                        Map.entry("tz", 5),
                        Map.entry("rt", 6),
                        Map.entry("sc-x", 7),
                        Map.entry("sc-y", 8),
                        Map.entry("pd", 9),
                        Map.entry("f-s", 10),
                        Map.entry("f-w", 11),
                        Map.entry("br-r", 12),
                        Map.entry("br-w", 13),
                        Map.entry("bc-i", 14),
                        Map.entry("bc-r", 15),
                        Map.entry("op", 16),
                        Map.entry("br-s", 17),
                        Map.entry("bc-c", 18),
                        Map.entry("t-f", 19),
                        Map.entry("br-c", 20),
                        Map.entry("f-c", 21),
                        Map.entry("ff-c", 22),
                        Map.entry("ds", 23),
                        Map.entry("is", 24),
                        Map.entry("vo", 25)
                )
        );

        public static int getPropertyIndex(String property){
            return PROPERTIES_ORDER.get(property);
        }

        public static boolean containsProperty(String property){
                return PROPERTIES_ORDER.containsKey(property);
        }
}
