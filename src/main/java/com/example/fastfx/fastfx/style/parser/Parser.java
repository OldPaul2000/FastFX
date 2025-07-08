package com.example.fastfx.fastfx.style.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private StyleOrderManager orderManager;

    public Parser(){
        orderManager = new StyleOrderManager();
    }

    /** Parse the style to be set to the specific element*/
    public void parseStyle(String style, List<String> cacheStyle){
        String[] splitStyle = splitProperties(style);
        for(int i = 0; i < splitStyle.length; i++){
            String currentProperty = splitStyle[i];
            orderManager.setPropertyInProperPlace(cacheStyle, currentProperty);
        }
    }

    /** Parse the added style. If the new property or properties are valid,
     *  an ArrayList with these properties will be returned in order to call
     *  the JavaFX methods to apply these new styles
     */
    public List<String> parseAddedStyle(String style, List<String> cacheStyle){
        String[] splitStyle = splitProperties(style);
        System.out.println("Split style: " + Arrays.toString(splitStyle));
        List<String> newAddedStyle = new ArrayList<>();
        for(int i = 0; i < splitStyle.length; i++){
            String addedProperty = orderManager.addPropertyInExistingStyle(cacheStyle, splitStyle[i]);
            if(addedProperty != null){
                newAddedStyle.add(addedProperty);
            }
        }
        return newAddedStyle;
    }

    /**
     * Separates property and value from each other.
     * e.g: w:100px -->  [w, 100px]
     */
    private String[] splitPropertyValue(String propertyValue){
        return propertyValue.split("\\s*:\\s*");
    }

    /**
     * Separates every property introduced in the method argument.
     * e.g: w:100px; br-r:10px -->  [w:100px, br-r:10px]
     */
    private  String[] splitProperties(String inputStyle){
        return inputStyle.split(";\\s*|\\{");
    }
}
