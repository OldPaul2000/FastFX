package com.example.fastfx.fastfx.style.parser;

import com.example.fastfx.fastfx.style.constants.StylesOrderConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to keep a specific order for the introduced style properties.
 * See StylesOrderConstants class.
 */

public class StyleOrderManager {

    private List<String> currentStyleList;

    public StyleOrderManager(){
        currentStyleList = new ArrayList<>();
    }

    /**
     * The first argument represents an existing style from style cache
     * and the second one the property or properties to set. It
     * positions the new property in its proper place
     */
    private int propertyIndexOffset = 0;
    public void setPropertyInProperPlace(List<String> cacheStyle, String newProperty){
        boolean isElementName = isElementName(newProperty);
        String currProperty = replaceClosingBracket(newProperty);

        int newPropertyIndex = getPropertyIndex(currProperty);
        if(isElementName){
            cacheStyle.add(currProperty);
            propertyIndexOffset = cacheStyle.size();
        }

        if(cacheStyle.size() == 0){
            cacheStyle.add(currProperty);
        }
        else if(cacheStyle.size() == 1 && !isElementName){
            if(isElementName(currProperty)){
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
            if(cacheStyle.size() == propertyIndexOffset && !isElementName){
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
                if(isElementName(currProperty)){
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

    private boolean isElementName(String property){
        return (property.startsWith("::") || property.startsWith(":") ||
                property.startsWith(".") || property.matches("[a-zA-Z0-9]+"));
    }

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

    private String[] splitPropertyValue(String propertyValue){
        return propertyValue.split("\\s*:\\s*");
    }
}
