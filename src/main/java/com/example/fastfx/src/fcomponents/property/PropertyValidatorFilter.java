package com.example.fastfx.src.fcomponents.property;

import java.util.HashSet;
import java.util.Set;

/**
 * Used to filter input properties and return and array with split<br>
 * values if the input form is valid.<br>
 * e.g: setSY(String y) (sets layout y by accepting a String parameter)<br>
 * input -> 50%<br>
 * it takes the number(50) and the measure unit(%)<br>
 * output -> 50 and %<br>
 * then the algorithm will interpret these values and will fulfill the proper actions
 */
public class PropertyValidatorFilter {
    // **********************************************************
    //                                                          *
    //                  FComponent properties                   *
    //                                                          *
    // **********************************************************
    private static final String DIMENSION_PATTERN = "(-?(\\d+|\\d+\\.\\d+))(%?(px)?)?";
    private static final String DURATION_PATTERN = "(\\d+|\\d+\\.\\d+)(s)";
    private static final Set<String> VALUES_PATTERNS = new HashSet<>(
        Set.of("V", "VD",
               "VV", "VDV", "VVD", "VDVD",
               "VVVV", "VDVVV", "VVDVV", "VVVDV", "VVVVD",
               "VDVDVV", "VDVVDV", "VDVVVD", "VVDVDV", "VVDVVD", "VVVDVD",
               "VDVDVDV", "VVDVDVD", "VDVVDVD", "VDVDVVD",
               "VDVDVDVD")
    );
    private static final String COLOR_PATTERN = "#([0-9a-fA-F]{6}|[0-9a-fA-F]{8})";
    private static final String OPACITY_PATTERN = "\\d|\\d.\\d+";
    private static final String INSETS_DIMENSION_PATTERN = "(-?(\\d+|\\d+\\.\\d+))(px)?";
    private static final String MARGIN_DIMENSION_PATTERN = "(-?(\\d+|\\d+\\.\\d+))(px)?";
    private static final String ROTATE_PATTERN = "-?(\\d+|\\d+\\.\\d+)";
    private static final Set<String> BORDER_STYLES = new HashSet<>(
            Set.of("solid", "dashed", "dotted")
    );
    private static final Set<String> TRANSITION_INTERPOLATORS = new HashSet<>(
            Set.of("discrete", "ease_both", "ease_in", "ease_out", "linear")
    );

    public static double getDoubleValue(String value){
        String numberValue;
        if(value.endsWith("px")){
            numberValue = value.replace("px", "");
        }
        else if(value.endsWith("%")){
            numberValue = value.replace("%", "");
        }
        else{
            numberValue = value;
        }
        return Double.parseDouble(numberValue);
    }

    public static String getUnit(String value){
        if(value.endsWith("px")){
            return "px";
        }
        if(value.endsWith("%")){
            return "%";
        }
        return "";
    }

    public static long getDuration(String value){
        double seconds = Double.parseDouble(value.replaceAll("s", ""));
        return (long)(seconds * 1000);
    }

    public static boolean validateOneDimensionValue(String[] values){
        if(values.length == 1){
            return values[0].matches(DIMENSION_PATTERN);
        }
        if(values.length == 2){
            return values[0].matches(DIMENSION_PATTERN) &&
                    values[1].matches(DURATION_PATTERN);
        }
        return false;
    }

    public static boolean validateMultipleDimensionValue(String[] values){
        String valuesPattern = "";
        for(int i = 0; i < values.length; i++){
            if(values[i].matches(DIMENSION_PATTERN)){
                valuesPattern += "V";
            }
            else if(values[i].matches(DURATION_PATTERN)){
                System.out.println("Matches duration: " + values[i]);
                valuesPattern += "D";
            }
            else{
                return false;
            }
        }
        return VALUES_PATTERNS.contains(valuesPattern);
    }

    public static String[] filterMultipleDimensionValues(String[] values){
        boolean wasDimension = false;
        boolean wasDuration = false;
        String filteredProperties = "";
        for(int i = 0; i < values.length; i++){
            String currEl = values[i];
            if(currEl.matches(DIMENSION_PATTERN)){
                if(wasDuration || wasDimension){
                    filteredProperties += "," + currEl;
                }
                else{
                    filteredProperties += currEl;
                }
                wasDuration = false;
                wasDimension = true;
            }
            else if(currEl.matches(DURATION_PATTERN)){
                filteredProperties += " " + currEl;
                wasDuration = true;
                wasDimension = false;
            }
        }
        return filteredProperties.split(",");
    }

    public static boolean validateInsetsValues(String[] values){
        if(values.length == 1 || values.length == 2 || values.length == 4){
            for(int i = 0; i < values.length; i++){
                if(!values[i].matches(INSETS_DIMENSION_PATTERN)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean validateMarginValues(String[] values){
        if(values.length == 1 || values.length == 2 || values.length == 4){
            for(int i = 0; i < values.length; i++){
                if(!values[i].matches(MARGIN_DIMENSION_PATTERN)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean validateColorValue(String[] values){
        if(values.length == 1){
            return values[0].matches(COLOR_PATTERN);
        }
        if(values.length == 2){
            return values[0].matches(COLOR_PATTERN) &&
                    values[1].matches(DURATION_PATTERN);
        }
        return false;
    }

    public static boolean validateOpacityValue(String[] values){
        if(values.length == 1){
            return values[0].matches(OPACITY_PATTERN);
        }
        if(values.length == 2){
            return values[0].matches(OPACITY_PATTERN) &&
                    values[1].matches(DURATION_PATTERN);
        }
        return false;
    }

    public static boolean validateRotateValue(String[] values){
        if(values.length == 1){
            return values[0].matches(ROTATE_PATTERN);
        }
        else if(values.length == 2){
            return values[0].matches(ROTATE_PATTERN) &&
                    values[1].matches(DURATION_PATTERN);
        }
        return false;
    }

    public static boolean validateBorderValue(String[] values){
        if(values.length == 2){
            return values[0].matches(COLOR_PATTERN) && BORDER_STYLES.contains(values[1]);
        }
        return true;
    }

    public static String[] filterBorderValue(String[] values){
        String[] filteredValues = null;
        if(values.length == 2){
            return values;
        }
        if(values.length >= 3){
            filteredValues = new String[4];
            String currValue = "";
            boolean wasProperty = false;
            int currIndex = 0;
            for(int i = 0; i < values.length; i++){
                String currEl = values[i].trim();
                if(currEl.matches(DURATION_PATTERN)){
                    if(wasProperty){
                        currValue += currEl;
                        filteredValues[currIndex] = currValue.trim();
                        currIndex++;
                        currValue = "";
                    }
                    wasProperty = false;
                }
                else{
                    if(wasProperty){
                        filteredValues[currIndex] = values[i - 1].trim();
                        currIndex++;
                    }
                    currValue = currEl + " ";
                    if(i == values.length - 1){
                        filteredValues[currIndex] = currEl;
                    }
                    wasProperty = true;
                }
            }
        }
        return filteredValues;
    }

    public static boolean validateOneSideBorderValue(String[] values){
        if(values.length == 2){
            return values[0].matches(COLOR_PATTERN) && BORDER_STYLES.contains(values[1]);
        }
        return true;
    }

    public static String[] filterOneSideBorderValue(String[] values){
        String[] filteredValues = new String[3];
        String currValue = "";
        boolean wasProperty = false;
        int currIndex = 0;
        for(int i = 0; i < values.length; i++){
            String currEl = values[i].trim();
            if(currEl.matches(DURATION_PATTERN)){
                if(wasProperty) {
                    currValue += currEl;
                    filteredValues[currIndex] = currValue.trim();
                    currIndex++;
                    currValue = "";
                }
                wasProperty = false;
            }
            else{
                if(wasProperty){
                    filteredValues[currIndex] = values[i - 1].trim();
                    currIndex++;
                }
                if(i == 1 && (!BORDER_STYLES.contains(currEl))){
                    currIndex++;
                }
                currValue = currEl + " ";
                if(i == values.length - 1){
                    filteredValues[currIndex] = currEl;
                }
                wasProperty = true;
            }
        }
        if(!BORDER_STYLES.contains(filteredValues[1]) && filteredValues[2] == null){
            filteredValues[2] = filteredValues[1];
            filteredValues[1] = null;
        }
        return filteredValues;
    }
    // **********************************************************
    //                                                          *
    //                  FComponent properties                   *
    //                                                          *
    // **********************************************************
    // *********************************************************************************************
    // **********************************************************
    //                                                          *
    //                   FLabeled properties                    *
    //                                                          *
    // **********************************************************

    // **********************************************************
    //                                                          *
    //                   FLabeled properties                    *
    //                                                          *
    // **********************************************************

}
