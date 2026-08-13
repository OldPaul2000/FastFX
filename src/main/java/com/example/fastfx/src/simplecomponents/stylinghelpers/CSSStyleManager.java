package com.example.fastfx.src.simplecomponents.stylinghelpers;

import java.util.HashMap;
import java.util.Map;

/**
 * User for styles which cannot be applied by calling methods with Java code
 * but only through CSS properties.
 */
public class CSSStyleManager {
    private StringBuilder builtStyle;
    private Map<String, String> cssProperties;
    private final String NUMBER_TYPE = "number";
    private final String COLOR_TYPE = "color";
    private final String BORDER_STYLE = "borderStyle";
    private Map<String, String> PARAMETER_TYPES_DEFAULT_VALUES;

    public CSSStyleManager () {
        this.builtStyle = new StringBuilder();
        this.cssProperties = new HashMap<>();
        PARAMETER_TYPES_DEFAULT_VALUES = new HashMap<>();
        PARAMETER_TYPES_DEFAULT_VALUES.put(NUMBER_TYPE, "0");
        PARAMETER_TYPES_DEFAULT_VALUES.put(COLOR_TYPE, "transparent");
        PARAMETER_TYPES_DEFAULT_VALUES.put(BORDER_STYLE, "solid");
    }

    public void removeProperty (String property) {
        cssProperties.remove(property);
    }

    public void setNthParameter (String property, String propertyType, int parameterIndex, double value) {
        setParameter(property, propertyType, parameterIndex, value + "");
    }

    public void setNthParameter (String property, String propertyType, int parameterIndex, String value) {
        setParameter(property, propertyType, parameterIndex, value);
    }

    private void setParameter (String property, String paramType, int parameterIndex, String value) {
        String newValue = "";
        if (PARAMETER_TYPES_DEFAULT_VALUES.containsKey(paramType) && parameterIndex >= 0 && parameterIndex < 4) {
            if (cssProperties.containsKey(property)) {
                String[] currParams = getParameters(cssProperties.get(property));
                String[] newParams = new String[4];
                if (currParams.length == 1) {
                    newParams[0] = currParams[0];
                    newParams[1] = currParams[0];
                    newParams[2] = currParams[0];
                    newParams[3] = currParams[0];
                } else if (currParams.length == 2) {
                    newParams[0] = currParams[0];
                    newParams[1] = currParams[1];
                    newParams[2] = currParams[0];
                    newParams[3] = currParams[1];
                } else if (currParams.length == 4) {
                    newParams[0] = currParams[0];
                    newParams[1] = currParams[1];
                    newParams[2] = currParams[2];
                    newParams[3] = currParams[3];
                }
                newParams[parameterIndex] = value;
                newValue += newParams[0] + " " + newParams[1] + " " + newParams[2] + " " + newParams[3];
                cssProperties.put(property, newValue);
            }
            else {
                for (int i = 0; i < 4; i++) {
                    if (i != parameterIndex) {
                        newValue += PARAMETER_TYPES_DEFAULT_VALUES.get(paramType) + " ";
                    } else {
                        newValue += value + " ";
                    }
                }
                cssProperties.put(property, newValue.trim());
            }
        }
    }

    private String[] getParameters (String value) {
        return value.split("\\s+");
    }

    public void setProperty (String property, String value) {
        cssProperties.put(property, value);
    }

    public void setProperty (String property, String value1, String value2) {
        cssProperties.put(property, value1 + " " + value2);
    }

    public void setProperty (String property, String value1, String value2, String value3, String value4) {
        cssProperties.put(property, value1 + " " + value2 + " " + value3 + " " + value4);
    }

    public void setProperty (String property, int value) {
        cssProperties.put(property, value + "");
    }

    public void setProperty (String property, double value) {
        cssProperties.put(property, value + "");
    }

    public void setProperty (String property, double value1, double value2) {
        cssProperties.put(property, value1 + " " + value2);
    }

    public void setProperty (String property, double value1, double value2, double value3, double value4) {
        cssProperties.put(property, value1 + " " + value2 + " " + value3 + " " + value4);
    }

    public void setProperty (String property, int value, String additionalValue) {
        cssProperties.put(property, value + additionalValue);
    }

    public void setProperty (String property, double value, String additionalValue) {
        cssProperties.put(property, value + additionalValue);
    }

    public void buildStyle () {
        builtStyle = new StringBuilder();
        cssProperties.forEach((key, value) -> {
            builtStyle.append(key);
            builtStyle.append(":");
            builtStyle.append(value);
            builtStyle.append(";\n");
        });
    }

    public String getStyle () {
        return builtStyle.toString();
    }

    public String getProperty(String property){
        return cssProperties.get(property);
    }

    public Map<String, String> getCssProperties () {
        return cssProperties;
    }
}
