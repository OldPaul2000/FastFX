package com.example.fastfx.fastfx.style.parser;

/**
 * Backup class for old code
 */

public class OrderManagerOldCode {

//    public void addPropertyInProperPlace(List<String> cacheStyle, String newProperty){
//        if(newProperty.startsWith("::")){
//
//        }
//        else if(newProperty.startsWith(":")){
//
//        }
//        else if(newProperty.startsWith(".")){
//
//        }
//        else{
//            if(newProperty.endsWith("}")){
//                newProperty.replace("}","");
//            }
//        }
//
//        int newPropertyIndex = getPropertyIndex(newProperty);
//
//        if(cacheStyle.size() == 0){
//            cacheStyle.add(newProperty);
//        }
//        else if(cacheStyle.size() == 1){
//            int firstStylePropertyIndex =  getPropertyIndex(cacheStyle.get(0));
//            if(newPropertyIndex > firstStylePropertyIndex){
//                cacheStyle.add(newProperty);
//            }
//            else{
//                cacheStyle.add(0, newProperty);
//            }
//        }
//        else{
//            for(int i = 0; i < cacheStyle.size() - 1; i++){
//                String currStyle = cacheStyle.get(i);
//                int currStyleIndex = getPropertyIndex(currStyle);
//                String nextStyle = cacheStyle.get(i + 1);
//                int nextStyleIndex = getPropertyIndex(nextStyle);
//                if(newPropertyIndex > currStyleIndex && newPropertyIndex < nextStyleIndex){
//                    cacheStyle.add(i + 1, newProperty);
//                    break;
//                }
//                if(i == 0 && newPropertyIndex < currStyleIndex){
//                    cacheStyle.add(i, newProperty);
//                    break;
//                }
//                if(i == cacheStyle.size() - 2 && getPropertyIndex(newProperty) > nextStyleIndex){
//                    cacheStyle.add(newProperty);
//                    break;
//                }
//            }
//        }
//    }



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
