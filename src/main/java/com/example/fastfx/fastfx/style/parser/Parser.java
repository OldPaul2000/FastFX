package com.example.fastfx.fastfx.style.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private StyleOrderManager orderManager;

    public Parser(){
        orderManager = new StyleOrderManager();
    }

    /**
     * Parse the style to be set to the specific element
     */
    public void parseStyle(String style, List<String> cacheStyle){
        String[] splitStyle = splitProperties(style);
        for(int i = 0; i < splitStyle.length; i++){
            String currentProperty = splitStyle[i];
            orderManager.setPropertyInProperPlace(cacheStyle, currentProperty);
        }
        orderManager.resetLimitIndexesValues();
    }

    /**
     *  Parse the added style. If the new property or properties are valid,
     *  an ArrayList with these properties will be returned in order to call
     *  the JavaFX methods to apply these new styles
     */
    public List<String> parseStyleToAdd(String style, List<String> cacheStyle){
        orderManager.resetLimitIndexesValues();
        String[] splitStyle = splitProperties(style);
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

    /**
     * Logic for replacing old values of existing properties with new values
     */
    public List<String> parseStyleToUpdate(String style, List<String> cacheStyle){
        List<String> updatedProperties = new ArrayList<>();
        String[] splitStyle = splitProperties(style);
        int startCountingIndex = 0;
        int endCountingIndex = calculateEndCountingIndex(0, cacheStyle);
        for(int i = 0; i < splitStyle.length; i++){
            String currElement = splitStyle[i];
            if(isPseudoForUpdateMethod(currElement)){
                startCountingIndex = cacheStyle.indexOf(currElement) + 1;
                endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle) + 1;
                updatedProperties.add(currElement);
                continue;
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

    // Works. Need more testing

    /**
     * Logic for removing existing properties
     */
    public List<String> parseStyleToRemove(String style, List<String> cacheStyle){
        List<String> removedProperties = new ArrayList<>();
        String[] splitStyle = splitProperties(style);
        int startCountingIndex = 0;
        int endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle);
        for(int i = 0; i < splitStyle.length; i++){
            String currElement = replaceClosingBracket(splitStyle[i]);
            if(splitStyle.length > 1){
                if(i < splitStyle.length - 1){
                    if(currentAndNextPropertyArePseudo(currElement, splitStyle[i + 1])){
                        startCountingIndex = cacheStyle.indexOf(currElement);
                        endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle);
                        for(int j = startCountingIndex; j < endCountingIndex; j++){
                            removedProperties.add(cacheStyle.get(j));
                            cacheStyle.remove(j);
                            j--;
                            endCountingIndex--;
                            if(j == cacheStyle.size() - 2){
                                removedProperties.add(cacheStyle.get(cacheStyle.size() - 1));
                                cacheStyle.remove(cacheStyle.size() - 1);
                            }
                        }
                    }
                    else if(isPseudoForDeleteMethod(currElement)){
                        startCountingIndex = cacheStyle.indexOf(currElement);
                        endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle);
                        removedProperties.add(cacheStyle.get(startCountingIndex));
                    }
                    else{
                        for(int j = startCountingIndex ; j <= endCountingIndex; j++){
                            String propertyToRemove = splitPropertyValue(cacheStyle.get(j))[0];
                            if(currElement.equals(propertyToRemove)){
                                removedProperties.add(cacheStyle.get(j));
                                cacheStyle.remove(j);
                                endCountingIndex--;
                                if(j > 1 && j < cacheStyle.size() - 1 &&
                                        currentAndNextPropertyArePseudo(cacheStyle.get(j - 1), cacheStyle.get(j + 1))){
                                    cacheStyle.remove(j - 1);
                                    endCountingIndex--;
                                }
                                break;
                            }
                            else if(j > 0 && j < cacheStyle.size() - 1){
                                if(currentAndNextPropertyArePseudo(cacheStyle.get(j - 1), cacheStyle.get(j))){
                                    cacheStyle.remove(j - 1);
                                    j--;
                                }
                            }
                        }
                    }
                }
                if(i == splitStyle.length - 1){
                    if(isPseudoForDeleteMethod(currElement)){
                        startCountingIndex = cacheStyle.indexOf(currElement);
                        endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle);
                        for(int j = startCountingIndex; j < endCountingIndex; j++){
                            removedProperties.add(cacheStyle.get(j));
                            cacheStyle.remove(j);
                            j--;
                            endCountingIndex--;
                            if(j == cacheStyle.size() - 2){
                                removedProperties.add(cacheStyle.get(j + 1));
                                cacheStyle.remove(j + 1);
                            }
                        }
                    }
                    else{
                        if(cacheStyle.size() == 1 && currElement.equals(splitPropertyValue(cacheStyle.get(0))[0])){
                            removedProperties.add(cacheStyle.get(0));
                            cacheStyle.remove(0);
                        }
                        endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle);
                        for(int j = startCountingIndex; j < endCountingIndex; j++){
                            if(j < cacheStyle.size() - 1){
                                String propertyToRemove = splitPropertyValue(cacheStyle.get(j))[0];
                                if(currElement.equals(propertyToRemove)){
                                    removedProperties.add(cacheStyle.get(j));
                                    cacheStyle.remove(j);
                                    j--;
                                    endCountingIndex--;
                                }
                                if(isPseudoForDeleteMethod(cacheStyle.get(cacheStyle.size() - 1))){
                                    cacheStyle.remove(cacheStyle.size() - 1);
                                }
                                else if(j > 1 && isPseudoForDeleteMethod(cacheStyle.get(j)) &&
                                        isPseudoForDeleteMethod(cacheStyle.get(j + 1))){
                                    cacheStyle.remove(j);
                                    j--;
                                    endCountingIndex--;
                                }
                                if(j == cacheStyle.size() - 2){
                                    propertyToRemove = splitPropertyValue(cacheStyle.get(j + 1))[0];
                                    if(propertyToRemove.equals(currElement)){
                                        removedProperties.add(cacheStyle.get(j + 1));
                                        cacheStyle.remove(j + 1);
                                        j--;
                                        startCountingIndex++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{
                if(isPseudoForDeleteMethod(currElement)){
                    startCountingIndex = cacheStyle.indexOf(currElement);
                    endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle);
                    for(int j = startCountingIndex; j < endCountingIndex; j++){
                        removedProperties.add(cacheStyle.get(j));
                        cacheStyle.remove(j);
                        j--;
                        endCountingIndex--;
                        if(j == cacheStyle.size() - 2){
                            removedProperties.add(cacheStyle.get(cacheStyle.size() - 1));
                            cacheStyle.remove(cacheStyle.size() - 1);
                        }
                    }
                }
                else{
                    endCountingIndex = calculateEndCountingIndex(startCountingIndex, cacheStyle);
                    for(int j = startCountingIndex; j <= endCountingIndex; j++){
                        String propertyToRemove = splitPropertyValue(cacheStyle.get(j))[0];
                        if(propertyToRemove.equals(currElement)){
                            removedProperties.add(cacheStyle.get(j));
                            cacheStyle.remove(j);
                            endCountingIndex--;
                            break;
                        }
                    }
                }
            }
            if(cacheStyle.size() > 0 && isPseudoForDeleteMethod(cacheStyle.get(cacheStyle.size() - 1))){
                cacheStyle.remove(cacheStyle.size() - 1);
            }
        }
        return removedProperties;
    }

    /**
     *  Checks if the cache style contains the passed property to update
     *  comparing their property names(e.g: w:100px -> w)
     */
    private boolean containsProperty(String updatedProperty, String styleCacheProperty){
        String updatedPropertyName = splitPropertyValue(updatedProperty)[0];
        String styleCachePropertyName = splitPropertyValue(styleCacheProperty)[0];
        return updatedPropertyName.equals(styleCachePropertyName);
    }

    /**
     * Calculates the next pseudo-element or pseudo-class index
     */
    private int calculateEndCountingIndex(int startIndex, List<String> cacheStyle) {
        for(int i = startIndex; i < cacheStyle.size(); i++){
            if((isPseudoForDeleteMethod(cacheStyle.get(i)) && i != startIndex) ||
                    i == cacheStyle.size() - 1){
                return i;
            }
        }
        return 0;
    }

    /**
     *  Check if the current and next element are both pseudo-elements/classes
     *  in order to remove the current pseudo-element/class(which has no more styles)
     */
    private boolean currentAndNextPropertyArePseudo(String current, String next){
        boolean currentIsPseudo = isPseudoForDeleteMethod(current);
        boolean nextIsPseudo = isPseudoForDeleteMethod(next);
        return currentIsPseudo && nextIsPseudo;
    }

    /**
     *  Check if the current element is a pseudo-element/class.
     *  Used in parseStyleToRemove method
     */
    private boolean isPseudoForDeleteMethod(String property){
        return Character.isUpperCase(property.charAt(0)) || property.startsWith(":") ||
                property.startsWith("::") || property.startsWith(".");
    }

    /**
     *  Check if the current element is a pseudo-element/class.
     *  Used in parseStyleToUpdate method
     */
    private boolean isPseudoForUpdateMethod(String property){
        return Character.isUpperCase(property.charAt(0)) || property.startsWith(":") ||
                property.startsWith("::") || property.startsWith(".");
    }

    /**
     * Remove the closing bracket from the last style property of the element
     */
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
