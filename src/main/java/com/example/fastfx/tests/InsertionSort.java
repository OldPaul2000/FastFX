package com.example.fastfx.tests;

import java.util.*;

public class InsertionSort {

    private List<Integer> sortedNumbers;

    public void sort(int[] numbers){
        sortedNumbers = new ArrayList<>();
        for(int i = 0; i < numbers.length; i++){
            int currNumber = numbers[i];
            if(sortedNumbers.size() == 0){
                sortedNumbers.add(currNumber);
            }
            else if(sortedNumbers.size() == 1){

            }
            else{
                for(int j = 0; j < sortedNumbers.size(); j++){
                    int currSortedNum = sortedNumbers.get(j);
                    if(currNumber > currSortedNum && currNumber < sortedNumbers.get(j + 1)){
                        sortedNumbers.add(currNumber);
                    }
                }
            }
        }
    }

    public List<Integer> getSortedNumbers(){
        return sortedNumbers;
    }

    private final Map<Integer, Integer> numbersWithIndexes = new HashMap<>(Map.ofEntries(
            Map.entry(3, 2),
            Map.entry(5, 4),
            Map.entry(1, 0),
            Map.entry(4, 3),
            Map.entry(2, 1)
    )
    );



}
