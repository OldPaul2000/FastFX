package com.example.fastfx.fastfx.style.parser;

import com.example.fastfx.fastfx.style.constants.StylesOrderConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to keep a specific order for the introduced style properties.
 * See StylesOrderConstants class.
 */

public class StyleOrderManager {

    /**
     * Sort the initial input style
     */
    public List<String> sortStyle(String[] newStyle, List<String> cacheStyle){
        List<String> sortedStyle = new ArrayList<>();
        for(int i = 0; i < newStyle.length; i++){
//            addPropertyInProperSpace(sortedStyle, newStyle[i]);
        }
        return sortedStyle;
    }

    /**
     * The first argument represents an existing style from style cache
     * and the second one the property or properties to set. It
     * positions the new property in its proper place
     */
    private void addPropertyInProperSpace(List<String> cacheStyle, int indexOffset,
                                         String subNode, String newProperty){
        if(newProperty.startsWith("::")){

        }
        else if(newProperty.startsWith(":")){

        }
        else if(newProperty.startsWith(".")){

        }
        else{
            if(newProperty.endsWith("}")){
                newProperty.replace("}","");
            }
        }

        int newPropertyIndex = getPropertyIndex(newProperty);

        if(cacheStyle.size() == 0){
            cacheStyle.add(newProperty);
        }
        else if(cacheStyle.size() == 1){
            int firstStylePropertyIndex =  getPropertyIndex(cacheStyle.get(0));
            if(newPropertyIndex > firstStylePropertyIndex){
                cacheStyle.add(newProperty);
            }
            else{
                cacheStyle.add(0, newProperty);
            }
        }
        else{
            for(int i = 0; i < cacheStyle.size() - 1; i++){
                String currStyle = cacheStyle.get(i);
                int currStyleIndex = getPropertyIndex(currStyle);
                String nextStyle = cacheStyle.get(i + 1);
                int nextStyleIndex = getPropertyIndex(nextStyle);
                if(newPropertyIndex > currStyleIndex && newPropertyIndex < nextStyleIndex){
                    cacheStyle.add(i + 1, newProperty);
                    break;
                }
                if(i == 0 && newPropertyIndex < currStyleIndex){
                    cacheStyle.add(i, newProperty);
                    break;
                }
                if(i == cacheStyle.size() - 2 && getPropertyIndex(newProperty) > nextStyleIndex){
                    cacheStyle.add(newProperty);
                    break;
                }
            }
        }
    }

    /**
     * The input will be the entire style property (h:100px), the property name will be extracted
     * and passed to StylesOrderConstants.getPropertyIndex() method
     */
    private int getPropertyIndex(String styleProperty){
        String property = splitPropertyValue(styleProperty)[0];
        return StylesOrderConstants.getPropertyIndex(property);
    }

    private String[] splitPropertyValue(String propertyValue){
        return propertyValue.split("\\s*:\\s*");
    }





    // Insertion sort algorithm
//    public void insert(int number){
//        int numsSize = nums.size();
//
//        if(nums.size() == 0){
//            nums.add(number);
//        }
//        else if(numsSize == 1){
//            if(number > nums.get(0)){
//                nums.add(number);
//            }
//            else{
//                nums.add(0, number);
//            }
//        }
//        else{
//            for(int i = 0; i < nums.size() - 1; i++){
//                int currNum = nums.get(i);
//                int nextNum = nums.get(i + 1);
//                if(number > currNum && number < nextNum){
//                    nums.add(i + 1, number);
//                    break;
//                }
//                if(i == 0 && number < currNum){
//                    nums.add(i, number);
//                    break;
//                }
//                if(i == numsSize - 2 && number > nextNum){
//                    nums.add(number);
//                    break;
//                }
//            }
//        }
//    }

}
