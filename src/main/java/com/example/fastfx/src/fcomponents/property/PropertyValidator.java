package com.example.fastfx.src.fcomponents.property;

import java.util.HashSet;
import java.util.Set;

public class PropertyValidator {

    public static final String COLOR_PROPERTY = "colorProperty";
    public static final String BORDER_PROPERTY = "borderProperty";
    public static final String SIZE_PROPERTY = "sizeProperty";
    public static final String NUMBER_PROPERTY = "numberProperty";

    public static final String COLOR_REGEX = "#[a-fA-F0-9]{6}|#[a-fA-F0-9]{8}|[a-zA-Z]+";
    public static final String SIZE_REGEX = "(-?(\\d+|\\d+\\.\\d+))(%?(px)?)?";
    public static final String ONLY_POSITIVE_NUMBER_REGEX = "\\d|\\d.\\d+";
    public static final String ONLY_NUMBER_REGEX = "-?(\\d+|\\d+\\.\\d+)";
    public static final String DURATION_REGEX = "(\\d+|\\d+\\.\\d+)(s)";

    private static final Set<String> PROPERTY_PATTERNS = new HashSet<>(
            Set.of("V", "VD",
                    "VV", "VDV", "VVD", "VDVD",
                    "VVVV", "VDVVV", "VVDVV", "VVVDV", "VVVVD",
                    "VDVDVV", "VDVVDV", "VDVVVD", "VVDVDV", "VVDVVD", "VVVDVD",
                    "VDVDVDV", "VVDVDVD", "VDVVDVD", "VDVDVVD",
                    "VDVDVDVD")
    );
    private static final Set<String> BORDER_COLOR_AND_STYLE_PATTERN = new HashSet<>(
            Set.of("C", "CD", "CS", "CDS")
    );
    private static final Set<String> BORDER_STYLES = new HashSet<>(
            Set.of("solid", "dashed", "dotted")
    );

    private int valuesCount;
    private int borderSizes;

    public PropertyValidator(){
        this.valuesCount = 0;
        this.borderSizes = 0;
    }

    public boolean validate(String propertyType, String[] properties){
        resetValues();
        if (propertyType.equals(COLOR_PROPERTY)){
            return validateColorProp(properties);
        }
        if(propertyType.equals(SIZE_PROPERTY)){
            return validateDimensionProp(properties);
        }
        if(propertyType.equals(BORDER_PROPERTY)){
            return validateBorderProp(properties);
        }
        if(propertyType.equals(NUMBER_PROPERTY)){
            return validateNumberProperty(properties);
        }
        return false;
    }

    private boolean validateColorProp(String[] properties){
        String propertyPattern = "";
        for(int i = 0; i < properties.length; i++){
            String currEl = properties[i];
            if(currEl.matches(COLOR_REGEX)){
                propertyPattern += "V";
            }
            if(currEl.matches(DURATION_REGEX)){
                propertyPattern += "D";
            }
        }
        return PROPERTY_PATTERNS.contains(propertyPattern);
    }

    private boolean validateDimensionProp(String[] properties){
        String propertyPattern = "";
        for(int i = 0; i < properties.length; i++){
            String currEl = properties[i];
            if(currEl.matches(SIZE_REGEX)){
                propertyPattern += "V";
                valuesCount++;
            }
            if(currEl.matches(DURATION_REGEX)){
                propertyPattern += "D";
            }
        }
        return PROPERTY_PATTERNS.contains(propertyPattern);
    }

    private boolean validateBorderProp(String[] properties){
        String colorAndStyleProps = "";
        int durationValues = 0;
        int countingStartIndex = 0;

        if(properties[0].matches(COLOR_REGEX)){
            colorAndStyleProps += "C";
        }
        if(properties[1].matches(DURATION_REGEX)){
            colorAndStyleProps += "D";
        }
        if(BORDER_STYLES.contains(properties[2])){
            colorAndStyleProps += "S";
        }

        if(colorAndStyleProps.equals("C")){
            countingStartIndex = 1;
        }
        if(colorAndStyleProps.equals("CD") || colorAndStyleProps.equals("CS")){
            countingStartIndex = 2;
        }
        if(colorAndStyleProps.equals("CDS")){
            countingStartIndex = 3;
        }

        for(int i = countingStartIndex; i < properties.length; i++){
            String currEl = properties[i];
            if(currEl.matches(SIZE_REGEX)){
                borderSizes++;
            }
            else if(currEl.matches(DURATION_REGEX)){
                durationValues++;
            }
            else{
                return false;
            }
        }
        return BORDER_COLOR_AND_STYLE_PATTERN.contains(colorAndStyleProps)
                && (borderSizes == 2 || borderSizes == 8)
                && durationValues <= borderSizes;
    }

    private boolean validateNumberProperty (String[] properties) {
        if(properties.length == 1 && properties[0].matches(ONLY_POSITIVE_NUMBER_REGEX)){
            return true;
        }
        if(properties.length == 2){
            return properties[0].matches(ONLY_POSITIVE_NUMBER_REGEX) && properties[1].matches(DURATION_REGEX);
        }
        return false;
    }

    public int getValuesCount () {
        return valuesCount;
    }

    public int getBorderSizes () {
        return borderSizes;
    }

    private void resetValues(){
        this.valuesCount = 0;
        this.borderSizes = 0;
    }
}