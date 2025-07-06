package com.example.fastfx.fastfx.style.parser;

import java.util.List;

public class Parser {

    private StyleOrderManager orderManager;

    public Parser(){
        orderManager = new StyleOrderManager();
    }

    public void parseStyle(String style, List<String> cacheStyle){
        String[] splitStyle = splitProperties(style);
        for(int i = 0; i < splitStyle.length; i++){
            String currentProperty = splitStyle[i];
//            System.out.println(currentProperty);
            orderManager.setPropertyInProperPlace(cacheStyle, currentProperty);
        }
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
