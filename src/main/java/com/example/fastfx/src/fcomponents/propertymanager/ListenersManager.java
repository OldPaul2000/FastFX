package com.example.fastfx.src.fcomponents.propertymanager;

import com.example.fastfx.src.fcomponents.propertymanager.constants.MainProps;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the listeners by adding or removing them using ids.
 */
public class ListenersManager {
    private Map<String, Region>  components;
    private Map<String, ChangeListener<Number>> changeListeners;
    private Map<String, String> listenersTypes;

    public ListenersManager(){
        this.components = new HashMap<>();
        this.changeListeners = new HashMap<>();
        this.listenersTypes = new HashMap<>();
    }

    /**
     * Returns the ChangeListener based on an id.
     *
     * @param id for identifying the listener
     */
    public ChangeListener<Number> get(String id){
        return changeListeners.get(id);
    }

    /**
     * Returns the ChangeListener type based on an id(e.g. WIDTH, HEIGHT).<br>
     * WIDTH -> listeners for prefWidth property<br>
     * HEIGHT -> listeners for prefHeight property
     *
     * @param id for identifying the listener
     */
    public String getType(String id){
        return listenersTypes.get(id);
    }

    /**
     * Adds a new ChangeListener along with its id and type
     *
     * @param id the id the listener will be identified by
     * @param type the type of the listener(e.g. WIDTH, HEIGHT)
     * @param listener the listener to be added
     */
    public void add(Region component, String id, String type, ChangeListener<Number> listener){
        if(!changeListeners.containsKey(id)){
            if(type.equals(MainProps.WIDTH)){
                component.prefWidthProperty().addListener(listener);
            }
            else if(type.equals(MainProps.HEIGHT)){
                component.prefHeightProperty().addListener(listener);
            }
            components.put(id, component);
            changeListeners.put(id, listener);
            listenersTypes.put(id, type);
        }
    }

    /**
     * Removes the listener
     *
     * @param id id of the listener
     */
    public void remove(String id){
        if(changeListeners.containsKey(id)){
            Region component = components.get(id);
            ChangeListener<Number> listener = changeListeners.get(id);
            String type = listenersTypes.get(id);
            if(type.equals(MainProps.WIDTH)){
                component.prefWidthProperty().removeListener(listener);
            }
            else if(type.equals(MainProps.HEIGHT)){
                component.prefHeightProperty().removeListener(listener);
            }
            changeListeners.remove(id);
            listenersTypes.remove(id);
        }
    }
}
