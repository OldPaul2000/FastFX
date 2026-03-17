package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.fcomponents.propertymanager.constants.MainProps;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and retrieves the initial values used in PropertyManager. It helps prevent methods for same property<br>
 * to interfere between them or misbehave when calling them in a successive way.<br>
 * e.g. x(20) then x("30 1s") then x(20, "%") or<br>
 *      x(20) then x(30) then x(40)
 */
public class ValuesManager {
    private Map<String, String> fComponentValues;

    public ValuesManager () {
        this.fComponentValues = new HashMap<>();
        initFComponentValues();
    }

    public double getDouble(String property){
        String value = fComponentValues.get(property);
        if(value.matches("\\d+|\\d+.\\d+")){
            return Double.parseDouble(value);
        }
        return 0;
    }

    public String getString(String property){
        return fComponentValues.get(property);
    }

    public void set(String property, double value){
        fComponentValues.put(property, value + "");
    }

    public void set(String property, String value){
        fComponentValues.put(property, value);
    }

    public void remove(String property){
        fComponentValues.remove(property);
    }

    public boolean contains(String property){
        return fComponentValues.containsKey(property);
    }

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
        fComponentValues.put(MainProps.TOP, "0");
        fComponentValues.put(MainProps.RIGHT, "0");
        fComponentValues.put(MainProps.BOTTOM, "0");
        fComponentValues.put(MainProps.LEFT, "0");
    }
}
