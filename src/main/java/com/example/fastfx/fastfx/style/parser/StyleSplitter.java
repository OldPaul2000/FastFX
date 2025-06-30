package com.example.fastfx.fastfx.style.parser;

/**
 * Helps to separate style properties from the input string
 */
public class StyleSplitter {

    /**
     * Separates every property introduced in the method argument.
     * e.g: w:100px; br-r:10px -->  [w:100px, br-r:10px]
     */
    public static String[] splitProperties(String inputStyle){
        return inputStyle.split(";\\s*|\\s*\\{|};*\\s*");
    }

    /**
     * Separates property and value from each other.
     * e.g: w:100px -->  [w, 100px]
     */
    public static String[] splitPropertyValue(String propertyValue){
        return propertyValue.split("\\s*:\\s*");
    }

}
