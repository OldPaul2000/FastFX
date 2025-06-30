package com.example.fastfx.fastfx.style;

import com.example.fastfx.fastfx.style.constants.StylesOrderConstants;
import com.example.fastfx.fastfx.style.parser.StyleSplitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to keep a specific order for the introduced style properties.
 * See StylesOrderConstants class.
 */

public class StyleOrderManager {


    public static List<String> sortStyle(String[] style){
        List<String> sortedStyle = new ArrayList<>();
        for(int i = 0; i < style.length; i++){
            addPropertyInProperSpace(sortedStyle, style[i]);
        }
        return sortedStyle;
    }

    public static void addPropertyInProperSpace(List<String> existingStyle, String newProperty){

        int newPropertyIndex = getPropertyIndex(newProperty);

        if(existingStyle.size() == 0){
            existingStyle.add(newProperty);
        }
        else if(existingStyle.size() == 1){
            int firstStylePropertyIndex =  getPropertyIndex(existingStyle.get(0));
            if(newPropertyIndex > firstStylePropertyIndex){
                existingStyle.add(newProperty);
            }
            else{
                existingStyle.add(0, newProperty);
            }
        }
        else{
            for(int i = 0; i < existingStyle.size() - 1; i++){
                String currStyle = existingStyle.get(i);
                int currStyleIndex = getPropertyIndex(currStyle);
                String nextStyle = existingStyle.get(i + 1);
                int nextStyleIndex = getPropertyIndex(nextStyle);
                if(newPropertyIndex > currStyleIndex && newPropertyIndex < nextStyleIndex){
                    existingStyle.add(i + 1, newProperty);
                    break;
                }
                if(i == 0 && newPropertyIndex < currStyleIndex){
                    existingStyle.add(i, newProperty);
                    break;
                }
                if(i == existingStyle.size() - 2 && getPropertyIndex(newProperty) > nextStyleIndex){
                    existingStyle.add(newProperty);
                    break;
                }
            }
        }
    }

    /**
     * The input will be the entire style property (h:100px), the property name will be extracted
     * and passed to StylesOrderConstants.getPropertyIndex() method
     */

    private static int getPropertyIndex(String styleProperty){
        String property = StyleSplitter.splitPropertyValue(styleProperty)[0];
        return StylesOrderConstants.getPropertyIndex(property);
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
