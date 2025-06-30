package com.example.fastfx.fastfx.style.cache;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StyleCache {

    private Map<Node, List<String>> sortedProperties;

    public StyleCache(){
        sortedProperties = new HashMap<>();
    }

    public void setStyle(Node node, String[] style){
        sortedProperties.put(node, new ArrayList<>());

    }

    public void addProperty(Node node, String property){
        sortedProperties.get(node).add(property);
    }

}
