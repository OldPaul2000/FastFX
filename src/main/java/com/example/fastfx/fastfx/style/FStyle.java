package com.example.fastfx.fastfx.style;

import com.example.fastfx.fastfx.elements.cache.ElementsCache;
import com.example.fastfx.fastfx.style.cache.StyleCache;
import com.example.fastfx.fastfx.style.parser.Parser;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the entry point for applying, removing, updating the styles
 */
public class FStyle {

    /**
     *   The main container which holds all the elements from the page
     */
    private AnchorPane mainPageContainer;
    private Node currentNode;

    private Parser parser;
    private StyleCache styleCache;
    private ElementsCache elementsCache;

    public FStyle(AnchorPane mainPageContainer){
        this.mainPageContainer = mainPageContainer;
        parser = new Parser();
        styleCache = new StyleCache();
        elementsCache = new ElementsCache(mainPageContainer);
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
            // JavaFX methods will be called here
//            System.out.println(cacheStyle.get(i));
        }
    };

    /**
     * Add one or more properties to the existing style
     */
    public void addStyle(Node node, String newProperty){
        parser.parseStyleToAdd(newProperty, styleCache.getStyle(node));
        System.out.println(styleCache.getStyle(node));
    }

    /**
     * Updates existing property if exists.
     * e.g: initial property: bc-c:red
     * -> style parameter value: bc-c:blue
     * -> new property value: bc-c:blue
     * accepts more properties bc-r; br-r
     */
    public void updateStyle(Node node, String style){
        List<String> newVals = parser.parseStyleToUpdate(style, styleCache.getStyle(node));
        System.out.println("newVals: " + newVals);
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
        System.out.println(styleCache.getStyle(node));
    }

    /**
     * Remove one or more properties from the element e.g: bc-c; br-r
     */
    public void removeProperty(Node node, String property){
        System.out.println("Old style: " + styleCache.getStyle(node));
        long start = System.currentTimeMillis();
        List<String> removedVals = parser.parseStyleToRemove(property, styleCache.getStyle(node));
        System.out.println("New style: " + styleCache.getStyle(node));

    }

}
