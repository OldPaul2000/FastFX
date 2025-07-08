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

    // Works. Need more testing

    /** Logic for replacing old values for existing properties with new values*/
    public List<String> parseStyleToUpdate(String style, List<String> cacheStyle){
        List<String> updatedProperties = new ArrayList<>();
        String[] splitStyle = splitProperties(style);
        int startCountingIndex = 0;
        int endCountingIndex = calculateEndCountingIndex(0, cacheStyle);
        for(int i = 0; i < splitStyle.length; i++){
            String currElement = splitStyle[i];
            if(isPseudoElement(currElement)){
                startCountingIndex = cacheStyle.indexOf(currElement) + 1;
                endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle) + 1;
                updatedProperties.add(currElement);
            }
            for(int j = startCountingIndex; j < endCountingIndex; j++){
                String currCacheProperty = cacheStyle.get(j);
                if(containsProperty(currElement, currCacheProperty)){
                    cacheStyle.set(j, replaceClosingBracket(currElement));
                    updatedProperties.add(replaceClosingBracket(currElement));
                }
            }
        }
        return updatedProperties;
    }

    /** Checks if the cache style contains the passed property to update
     *  comparing their property names(e.g: w:100px -> w)
     */
    private boolean containsProperty(String updatedProperty, String styleCacheProperty){
        String updatedPropertyName = splitPropertyValue(updatedProperty)[0];
        String styleCachePropertyName = splitPropertyValue(styleCacheProperty)[0];
        return updatedPropertyName.equals(styleCachePropertyName);
    }

    private int calculateEndCountingIndex(int startIndex, List<String> cacheStyle) {
        for(int i = startIndex; i < cacheStyle.size(); i++){
            if(isPseudoElement(cacheStyle.get(i)) || i == cacheStyle.size() - 1){
                return i;
            }
        }
        return 0;
    }

    private boolean isPseudoElement(String property){
        return !property.matches(".+:.+");
    }

    private String replaceClosingBracket(String property){
        return property.replaceAll("}", "");
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
