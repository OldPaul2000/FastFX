package com.example.fastfx.fastfx.style;

import com.example.fastfx.fastfx.style.cache.StyleCache;
import com.example.fastfx.fastfx.style.parser.Parser;
import com.example.fastfx.fastfx.style.parser.UpdatedStyleSorter;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the entry point for setting, adding, removing and updating the styles
 */
public class FStyle {

    /**
     *   The main container which holds all the elements from the page
     */
    private AnchorPane mainPageContainer;
    private Node currentNode;

    private Parser parser;
    private StyleCache styleCache;
    private UpdatedStyleSorter propertiesSorter = UpdatedStyleSorter.getInstance();

    public FStyle(AnchorPane mainPageContainer){
        this.mainPageContainer = mainPageContainer;
        parser = new Parser();
        styleCache = new StyleCache();
    }

    /**
     * Set the initial style for a certain element
     * and removes the old one if exists
     */
    public void setStyle(Node node, String style){
        currentNode = node;
        styleCache.setStyle(node, new ArrayList<>());
        parser.parseStyle(style, styleCache.getStyle(node));
        List<String> cacheStyle = styleCache.getStyle(node);
        for(int i = 0; i < cacheStyle.size(); i++){
            // PropertySetter methods will be called here

        }
    };

    /**
     * Add one or more properties to the existing style
     */
    public void addStyle(Node node, String newProperty){
        if(styleCache.getStyle(node) == null){
            styleCache.setStyle(node, new ArrayList<>());
        }
        List<String> addedProperties = parser.parseStyleToAdd(newProperty, styleCache.getStyle(node));
        propertiesSorter.sortStyle(addedProperties, false); // SORT PROPERTIES


    }

    /**
     * Updates existing property if exists.
     * e.g: initial property: bc-c:red
     * -> style parameter value: bc-c:blue
     * -> new property value: bc-c:blue
     * accepts more properties bc-r; br-r
     */
    public void updateStyle(Node node, String style){
        List<String> updatedProperties = parser.parseStyleToUpdate(style, styleCache.getStyle(node));
        propertiesSorter.sortStyle(updatedProperties, false); // SORT PROPERTIES
    }

    /**
     * Remove all style from a certain element and also from style cache
     */
    public void removeStyle(Node node){
        List<String> currStyle = styleCache.getStyle(node);
        for(int i = 0; i < currStyle.size(); i++){
            //Perform all necessary remove methods calls
        }
        styleCache.removeStyle(node);
    }

    /**
     * Remove one or more properties from the element e.g: bc-c; br-r
     */
    public void removeProperty(Node node, String property){
        List<String> removedProperties = parser.parseStyleToRemove(property, styleCache.getStyle(node));
        propertiesSorter.sortStyle(removedProperties, true);
    }

    /**
     * Check if the current element is pseudo-class/pseudo-element or a regular property
     */
    private boolean isPseudo(String property){
        return Character.isUpperCase(property.charAt(0)) || property.startsWith(":") ||
                property.startsWith("::") || property.startsWith(".");
    }

}

