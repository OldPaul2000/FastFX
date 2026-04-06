package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.fcomponents.propertymanager.constants.MainProps;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and retrieves the initial values used in PropertyManager. It helps prevent methods for same property<br>
 * to interfere between them or misbehave when calling them in a successive way.<br>
 * e.g. x(20) then x("30 1s") then x(20, "%") or<br>
 *      x(20) then x(30) then x(40)
 *
 * @author Paul Batrin
 */
public class ValuesManager {
    private Map<String, String> fComponentValues;

    public ValuesManager () {
        this.fComponentValues = new HashMap<>();
        initFComponentValues();
    }

    /**
     * Returns the double value for a specific property.<br>
     * e.g. MainProps.X
     *
     * @param property the property name
     */
    public double getDouble(String property){
        String value = fComponentValues.get(property);
        if(value.matches("\\d+|\\d+.\\d+")){
            return Double.parseDouble(value);
        }
        return 0;
    }

    /**
     * Returns the String value for a specific property.<br>
     * It's used for color properties or border style since they<br>
     * are represented as a String value.<br>
     * e.g. MainProps.BG_COLOR
     *
     * @param property the property name
     */
    public String getString(String property){
        return fComponentValues.get(property);
    }

    /**
     * Sets a property with double value.<br>
     * e.g. set(MainProps.X, 20);
     *
     * @param property the property name
     * @param value the property double value
     */
    public void set(String property, double value){
        fComponentValues.put(property, value + "");
    }

    /**
     * Sets a property with String value.<br>
     * e.g. set(MainProps.BG_COLOR, #412212);
     *
     * @param property the property name
     * @param value the property String value
     */
    public void set(String property, String value){
        fComponentValues.put(property, value);
    }

    /**
     * Removes a property from the <strong>fComponentValues</strong>.<br>
     * e.g. MainProps.LAYOUT_X;
     *
     * @param property the property name
     */
    public void remove(String property){
        fComponentValues.remove(property);
    }

    /**
     * Returns true if the <strong>fComponentValues</strong>.<br>
     * e.g. MainProps.LAYOUT_X
     *
     * @param property the property name
     */
    public boolean contains(String property){
        return fComponentValues.containsKey(property);
    }

    /**
     * Insert the initial values for the properties.
     */
    private void initFComponentValues(){
        fComponentValues.put(MainProps.X, "0");
        fComponentValues.put(MainProps.Y, "0");
        fComponentValues.put(MainProps.WIDTH, "0");
        fComponentValues.put(MainProps.HEIGHT, "0");
        fComponentValues.put(MainProps.SCALE_X, "1");
        fComponentValues.put(MainProps.SCALE_Y, "1");
        fComponentValues.put(MainProps.TRANSLATE_X, "0");
        fComponentValues.put(MainProps.TRANSLATE_Y, "0");
        fComponentValues.put(MainProps.ROTATE, "0");
        fComponentValues.put(MainProps.PADDING, "0");
        fComponentValues.put(MainProps.TOP_PADDING, "0");
        fComponentValues.put(MainProps.RIGHT_PADDING, "0");
        fComponentValues.put(MainProps.BOTTOM_PADDING, "0");
        fComponentValues.put(MainProps.LEFT_PADDING, "0");
        fComponentValues.put(MainProps.MARGIN, "0");
        fComponentValues.put(MainProps.TOP_MARGIN, "0");
        fComponentValues.put(MainProps.RIGHT_MARGIN, "0");
        fComponentValues.put(MainProps.BOTTOM_MARGIN, "0");
        fComponentValues.put(MainProps.LEFT_MARGIN, "0");
        fComponentValues.put(MainProps.TOP, "0");
        fComponentValues.put(MainProps.RIGHT, "0");
        fComponentValues.put(MainProps.BOTTOM, "0");
        fComponentValues.put(MainProps.LEFT, "0");
        fComponentValues.put(MainProps.BG_COLOR, "#00000000");
        fComponentValues.put(MainProps.BG_OPACITY, "1");
    }
}
