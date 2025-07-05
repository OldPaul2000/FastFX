package com.example.fastfx.fastfx.style.cache;

import javafx.scene.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StyleCache {

    private Map<Node, List<String>> sortedStyle;

    public StyleCache(){
        sortedStyle = new HashMap<>();
    }

    public void setStyle(Node node, List<String> style){
        sortedStyle.put(node, style);
    }

    public List<String> getStyle(Node node){
        return sortedStyle.get(node);
    }

    public void removeStyle(Node node){
        sortedStyle.remove(node);
    }

}
