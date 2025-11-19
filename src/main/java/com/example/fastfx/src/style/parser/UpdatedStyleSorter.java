package com.example.fastfx.src.style.parser;

import com.example.fastfx.src.style.constants.StylesOrderConstants;

import java.util.List;

/**
 * Class for sorting the new properties returned from addStyle, updateStyle and removeProperty
 * in ascending or descending order
 */
public class UpdatedStyleSorter {

    private static UpdatedStyleSorter updatedStyleSorter;
    public static UpdatedStyleSorter getInstance(){
        if(updatedStyleSorter == null){
            updatedStyleSorter = new UpdatedStyleSorter();
        }
        return updatedStyleSorter;
    }

    /**
     * Sort the new properties returned from addStyle, updateStyle and removeProperty
     * in ascending or descending order(specified by the descending parameter)
     */
    public void sortStyle(List<String> style, boolean descending){
        if(style.size() > 1){
            int startIndex = 0;
            if(isPseudo(style.get(0))) {
                startIndex = 1;
            }
            int endIndex = calculateEndIndex(style, startIndex);
            while (startIndex < style.size() - 1){
                if(endIndex - startIndex > 0){
                    bubbleSortStyle(style, descending, startIndex, endIndex);
                }
                if(startIndex < style.size() - 1){
                    startIndex = endIndex + 2;
                    endIndex = calculateEndIndex(style, startIndex);
                }
            }
        }
    }

    /**
     * Sort the returned properties using bubble sort algorithm
     * by their index specified in StyleOrderConstants
     */
    private void bubbleSortStyle(List<String> style, boolean descending, int startIndex, int endIndex){
        for(int i = startIndex; i < endIndex; i++){
            for(int j = startIndex; j < endIndex - (i - startIndex); j++){
                int currPropIndex = getPropertyIndex(style.get(j));
                int nextPropIndex = getPropertyIndex(style.get(j + 1));
                if(!descending){
                    if(currPropIndex > nextPropIndex){
                        String temp = style.get(j);
                        style.set(j, style.get(j + 1));
                        style.set(j + 1, temp);
                    }
                }
                else{
                    if(currPropIndex < nextPropIndex){
                        String temp = style.get(j);
                        style.set(j, style.get(j + 1));
                        style.set(j + 1, temp);
                    }
                }
            }
        }
    }

    /**
     * Calculate the index from the specified startIndex to the
     * first occurrence of a pseudo-class/pseudo-element
     */
    private int calculateEndIndex(List<String> cacheStyle, int startIndex){
        for(int i = startIndex; i < cacheStyle.size(); i++){
            String currProperty = cacheStyle.get(i);
            if(isPseudo(currProperty)){
                if(i > 0){
                    return i - 1;
                }
            }
            if(i == cacheStyle.size() - 1){
                return i;
            }
        }
        return 0;
    }

    /**
     * Return the property index of the specified property with its value.
     * This method also splits the property from its value to be able to get the index.
     * */
    private int getPropertyIndex(String property){
        String propertyName = property.split(":")[0];
        return StylesOrderConstants.getPropertyIndex(propertyName);
    }

    /**
     * Check if the current element is pseudo-class/pseudo-element or a regular property
     */
    private boolean isPseudo(String property){
        return Character.isUpperCase(property.charAt(0)) || property.startsWith(":") ||
                property.startsWith("::") || property.startsWith(".");
    }
}
