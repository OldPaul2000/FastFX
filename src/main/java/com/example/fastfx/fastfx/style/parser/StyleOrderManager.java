package com.example.fastfx.fastfx.style.parser;

import com.example.fastfx.fastfx.style.constants.StylesOrderConstants;

import java.util.List;

/**
 * Used to keep a specific order for the introduced style properties.
 * See StylesOrderConstants class.
 */
public class StyleOrderManager {

    private int startCountingIndex = 0;
    private int endCountingIndex = 0;
    private boolean wasPseudoName = false;
    private boolean wasProperty = false;
    private boolean countingLimitsSet = false;

    // Works. Need more testing
    /** Method to call for adding the new property or properties in the existing style*/
    public String addPropertyInExistingStyle(List<String> cacheStyle, String newProperty){
        boolean isPseudoName = isPseudoName(newProperty);
        String currProperty = replaceClosingBracket(newProperty);

        if(isPseudoName){
            if(cacheStyle.contains(newProperty)){
                startCountingIndex = cacheStyle.indexOf(currProperty) + 1;
                endCountingIndex = getEndCountingIndex(startCountingIndex, cacheStyle);
            }
            else{
                startCountingIndex = cacheStyle.size() + 1;
                endCountingIndex = startCountingIndex;
                cacheStyle.add(currProperty);
            }
            wasPseudoName = true;
            wasProperty = false;
            countingLimitsSet = true;
        }
        else{
            if(!countingLimitsSet){
                startCountingIndex = 0;
                endCountingIndex = getEndCountingIndex(startCountingIndex, cacheStyle);
                addNewProperty(currProperty, cacheStyle);
                countingLimitsSet = true;
            }
            else{
                addNewProperty(currProperty, cacheStyle);
            }
        }
        return currProperty;
    }

    /** Finds the last index where to stop searching for the proper
     *  place of the new property in the existing style for the specific element
     */
    private int getEndCountingIndex(int addedPropertyIndexOffset, List<String> cacheStyle){
        for(int i = addedPropertyIndexOffset; i < cacheStyle.size(); i++){
            String currElement = cacheStyle.get(i);
            if(!currElement.matches(".+:.+") || i == cacheStyle.size() - 1){
                return i;
            }
        }
        return 0;
    }

    /** Adds new property in the existing style in the proper place*/
    private void addNewProperty(String newProperty, List<String> cacheStyle){
        int newPropertyIndex = getPropertyIndex(newProperty);
        if(endCountingIndex - startCountingIndex == 0){
            cacheStyle.add(newProperty);
        }
        for(int i = startCountingIndex; i < endCountingIndex; i++){
            if(isPseudoName(newProperty)){
                cacheStyle.add(newProperty);
                break;
            }
            else{
                String currStyle = cacheStyle.get(i);
                int currStyleIndex = getPropertyIndex(currStyle);
                String nextStyle = cacheStyle.get(i + 1);
                int nextStyleIndex = getPropertyIndex(nextStyle);
                if(newPropertyIndex > currStyleIndex && newPropertyIndex < nextStyleIndex){
                    cacheStyle.add(i + 1, newProperty);
                    endCountingIndex++;
                    break;
                }
                if(i == startCountingIndex && newPropertyIndex < currStyleIndex){
                    cacheStyle.add(i, newProperty);
                    endCountingIndex++;
                    break;
                }
                if(i == cacheStyle.size() - 2 && getPropertyIndex(newProperty) > nextStyleIndex){
                    cacheStyle.add(newProperty);
                    endCountingIndex++;
                    break;
                }
            }
        }
    }

    //======================================================================================================
    /**
     * The first argument represents an existing style from style cache
     * and the second one the property or properties to set. It
     * positions the new property in its proper place
     */
    private int propertyIndexOffset = 0;
    public void setPropertyInProperPlace(List<String> cacheStyle, String newProperty){
        boolean isPseudoName = isPseudoName(newProperty);
        String currProperty = replaceClosingBracket(newProperty);

        int newPropertyIndex = getPropertyIndex(currProperty);
        if(isPseudoName){
            cacheStyle.add(currProperty);
            propertyIndexOffset = cacheStyle.size();
        }

        if(cacheStyle.size() == 0){
            cacheStyle.add(currProperty);
        }
        else if(cacheStyle.size() == 1 && !isPseudoName){
            if(isPseudoName(currProperty)){
                cacheStyle.add(currProperty);
            }
            else{
                int firstStylePropertyIndex =  getPropertyIndex(cacheStyle.get(0));
                if(newPropertyIndex > firstStylePropertyIndex){
                    cacheStyle.add(currProperty);
                }
                else{
                    cacheStyle.add(0, currProperty);
                }
            }
        }
        else{
            if(cacheStyle.size() == propertyIndexOffset && !isPseudoName){
                cacheStyle.add(currProperty);
            }
            else if(cacheStyle.size() == propertyIndexOffset + 1){
                if(newPropertyIndex > getPropertyIndex(cacheStyle.get(cacheStyle.size() - 1))){
                    cacheStyle.add(currProperty);
                }
                else{
                    cacheStyle.add(propertyIndexOffset, currProperty);
                }
            }
            for(int i = propertyIndexOffset; i < cacheStyle.size() - 1; i++){
                if(isPseudoName(currProperty)){
                    cacheStyle.add(currProperty);
                    break;
                }
                else{
                    String currStyle = cacheStyle.get(i);
                    int currStyleIndex = getPropertyIndex(currStyle);
                    String nextStyle = cacheStyle.get(i + 1);
                    int nextStyleIndex = getPropertyIndex(nextStyle);
                    if(newPropertyIndex > currStyleIndex && newPropertyIndex < nextStyleIndex){
                        cacheStyle.add(i + 1, currProperty);
                        break;
                    }
                    if(i == propertyIndexOffset && newPropertyIndex < currStyleIndex){
                        cacheStyle.add(i, currProperty);
                        break;
                    }
                    if(i == cacheStyle.size() - 2 && getPropertyIndex(currProperty) > nextStyleIndex){
                        cacheStyle.add(currProperty);
                        break;
                    }
                }
            }
        }
    }

    /** Checks if the property is pseudo class or pseudo element or a child and
     *  not a regular property(w, h, tx etc)
     */
    private boolean isPseudoName(String property){
        return (property.startsWith("::") || property.startsWith(":") ||
                property.startsWith(".") || property.matches("[a-zA-Z0-9]+"));
    }

    /** Replace the closing bracket from the end of the property*/
    private String replaceClosingBracket(String property){
        if(property.endsWith("}")){
            return property.replace("}","");
        }
        return property;
    }

    /**
     * The input will be the entire style property (h:100px), the property name will be extracted
     * and passed to StylesOrderConstants.getPropertyIndex() method
     */
    private int getPropertyIndex(String styleProperty){
        String property = splitPropertyValue(styleProperty)[0];
        if(StylesOrderConstants.containsProperty(property)){
            return StylesOrderConstants.getPropertyIndex(property);
        }
        return 0;
    }

    /** Splits property and its value in an array with 2 elements:
     *  e.g:
     *  arr[0] = w
     *  arr[1] = 100px
     */
    private String[] splitPropertyValue(String propertyValue){
        return propertyValue.split("\\s*:\\s*");
    }
}
