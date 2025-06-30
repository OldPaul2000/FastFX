package com.example.fastfx.fastfx.style;

import com.example.fastfx.fastfx.elements.cache.ElementsCache;
import com.example.fastfx.fastfx.style.cache.StyleCache;
import com.example.fastfx.fastfx.style.parser.ParserFunctions;
import com.example.fastfx.fastfx.style.parser.StyleSplitter;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

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
    private ParserFunctions parserFunctions;

    private StyleCache styleCache;
    private ElementsCache elementsCache;

    public FStyle(AnchorPane mainPageContainer){
        this.mainPageContainer = mainPageContainer;
        styleCache = new StyleCache();
        elementsCache = new ElementsCache(mainPageContainer);
    }


    /**
     * Set the initial style for a certain element
     */
    public void setStyle(Node node, String style){
        currentNode = node;
        String[] splitStyle = StyleSplitter.splitProperties(style);
        List<String> sortedStyle = StyleOrderManager.sortStyle(splitStyle);
        for(int i = 0; i < sortedStyle.size(); i++){
            System.out.println(sortedStyle.get(i));
        }
    };

    /**
     * Add new properties to the existing style
     */
    public void addStyle(Node node, String newProperty){

    }

    /**
     * Remove all style from a certain element and also from style cache
     */
    public void removeStyle(Node node){

    };

    /**
     * Remove a certain property from the element e.g: bc-c
     */
    public void removeProperty(Node node, String property){

    }

    /**
     * Updates existing property if exists.
     * e.g: initial property: bc-c:red
     * -> style parameter value: bc-c:blue
     * -> new property value: bc-c:blue
     */
    public void updateStyle(Node node, String style){

    }

}
