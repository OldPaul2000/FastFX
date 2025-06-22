package com.example.fastfx.tests;

import com.example.fastfx.fastfx.style.StyleSplitter;

public class SplitStyleTest {
    public static void main(String[] args) {

        String style = "bc-c:  #412422;    h  :100px;w  :  70px; br-r:10px; BTNS   {w:70px; h:30px; br-r:10px 3px 3px 3px}";

        String[] parsedStyle = StyleSplitter.splitProperties(style);
        for(int i = 0; i < parsedStyle.length; i++){
            System.out.println("Split property:");
            if(parsedStyle[i].contains(":")){
                System.out.println(parsedStyle[i]);
                String[] propVal = StyleSplitter.splitPropertyValue(parsedStyle[i]);
                System.out.println("property:" + propVal[0] + "---val:" + propVal[1]);
            }
            System.out.println();

        }

    }
}
