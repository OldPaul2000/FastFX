package com.example.fastfx.fastfx.style;

public class StyleSplitter {

    public static String[] splitProperties(String inputStyle){
        return inputStyle.split(";\\s*|\\s*\\{|}");
    }

    public static String[] splitPropertyValue(String propertyValue){
        return propertyValue.split("\\s*:\\s*");
    }

}
