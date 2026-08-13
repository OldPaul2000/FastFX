package com.example.fastfx.src.simplecomponents.stylinghelpers;

import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Makes choosing the component to style easier, also removes the necessity for ifs<br>
 * from the components classes. Also, it makes possible to check if the current<br>
 * property to style is allowed for the specific component;
 */
public class ComponentStyleTracker {
    private Region currComponentToStyle;
    private Map<Region, Set<String>> componentsToStyle;

    public ComponentStyleTracker (){
        this.componentsToStyle = new HashMap<>();
    }

    public void setCurrentComponent(Region currComponent){
        if(componentsToStyle.containsKey(currComponent)){
            currComponentToStyle = currComponent;
        }
    }

    public void addComponent(Region component, Set<String> notAllowedProperties){
        componentsToStyle.put(component, notAllowedProperties);
    }
    
    public void addDeniedProperty(Region component, String propertyName){
        if(componentsToStyle.containsKey(component)){
            componentsToStyle.get(component).add(propertyName);
        }
    }

    public boolean isAllowedProperty(String propertyName){
        return componentsToStyle.containsKey(currComponentToStyle) && !componentsToStyle.get(currComponentToStyle).contains(propertyName);
    }

    public Region getCurrentComponent(){
        return currComponentToStyle;
    }

    public Map<Region, Set<String>> getNotAllowedProperties(){
        return componentsToStyle;
    }
}