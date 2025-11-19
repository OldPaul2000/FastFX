package com.example.fastfx.src.utility.style;

import com.example.fastfx.src.style.layouts.Margin;
import javafx.scene.layout.Region;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MarginSetter {

    private static Map<Region, Margin> margins = new HashMap<>();
    private static Map<Region, Set<MarginListener>> listeners = new HashMap<>();

    public static Margin getMargin(Region element){
        return margins.get(element);
    }

    public static void setMargin(Region element, double top, double right, double bottom, double left){
        if(element != null){
            if(margins.get(element) == null){
                margins.put(element, new Margin(top, right, bottom, left));
                if(listeners.get(element) != null && !listeners.get(element).isEmpty()){
                    Set<MarginListener> elementListeners = listeners.get(element);
                    elementListeners.forEach(listener -> {
                        listener.apply(margins.get(element));
                    });
                }
            }
            else{
                margins.get(element).setTop(top);
                margins.get(element).setRight(right);
                margins.get(element).setBottom(bottom);
                margins.get(element).setLeft(left);
                if(listeners.get(element) != null && !listeners.get(element).isEmpty()){
                    Set<MarginListener> elementListeners = listeners.get(element);
                    elementListeners.forEach(listener -> {
                        listener.apply(margins.get(element));
                    });
                }
            }
        }
    }

    public static void setMargin(Region element, double topAndBottom, double rightAndLeft){
        setMargin(element, topAndBottom, rightAndLeft, topAndBottom, rightAndLeft);
    }

    public static void setMargin(Region element, double margin){
        setMargin(element, margin, margin, margin, margin);
    }

    public static void removeMargin(Region element){
        if(listeners.get(element) != null && listeners.get(element).size() > 0){
            setMargin(element, 0);
            listeners.remove(element);
        }
        if(margins.get(element) != null){
            margins.remove(element);
        }
    }

    public static void addListener(Region element, MarginListener listener){
        if(listeners.get(element) == null){
            listeners.put(element, new HashSet<>());
            listeners.get(element).add(listener);
        }
        else{
            listeners.get(element).add(listener);
        }
    }

    public static void removeListener(Region element, MarginListener listener){
        if(listeners.get(element) != null && !listeners.get(element).isEmpty()){
            listeners.get(element).remove(listener);
            if(listeners.get(element).size() == 0){
                listeners.remove(element);
            }
        }
    }

}
