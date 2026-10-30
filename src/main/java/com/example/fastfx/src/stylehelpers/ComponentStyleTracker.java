package com.example.fastfx.src.stylehelpers;

import javafx.scene.layout.Region;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Makes choosing the component to style easier, also removes the necessity for ifs<br>
 * from the components classes. Also, it makes possible to check if the current<br>
 * property to style is allowed for the specific component;
 */
public class ComponentStyleTracker {
    public static final String THIS_COMPONENT_TO_STYLE_ID = "this";
    private Region currComponentToStyle;
    private Map<Region, Set<String>> componentsToStyle;
    private Set<String> componentsToStyleIDs;
    private String currentComponentID;

    public ComponentStyleTracker () {
        currComponentToStyle = null;
        this.componentsToStyle = new LinkedHashMap<>();
        this.componentsToStyleIDs = new HashSet<>();
        componentsToStyleIDs.add(THIS_COMPONENT_TO_STYLE_ID);
        currentComponentID = THIS_COMPONENT_TO_STYLE_ID;
    }

    public void setCurrentComponent (Region currComponent) {
        if (componentsToStyle.containsKey(currComponent)) {
            currComponentToStyle = currComponent;
        }
    }

    public void addComponent (Region component, Set<String> notAllowedProperties) {
        componentsToStyle.put(component, notAllowedProperties);
    }

    public void addDeniedProperty (Region component, String propertyName) {
        if (componentsToStyle.containsKey(component)) {
            componentsToStyle.get(component).add(propertyName);
        }
    }

    public boolean isAllowedProperty (String propertyName) {
        return componentsToStyle.containsKey(currComponentToStyle) && !componentsToStyle.get(currComponentToStyle).contains(propertyName);
    }

    public void removeComponent (Region component) {
        componentsToStyle.remove(component);
    }

    public Region getCurrentComponent () {
        return currComponentToStyle;
    }

    public Map<Region, Set<String>> getNotAllowedProperties () {
        return componentsToStyle;
    }

    public void addComponentToStyleID (String id) {
        componentsToStyleIDs.add(id);
    }

    public void setCurrentComponentID (String id) {
        if (componentsToStyleIDs.contains(id)) {
            currentComponentID = id;
        }
    }

    public String getCurrentComponentID () {
        return currentComponentID;
    }
}