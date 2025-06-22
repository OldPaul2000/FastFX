package com.example.fastfx.fastfx.style;

import com.example.fastfx.fastfx.elements.ElementsCache;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class StyleSetter {

    private AnchorPane mainPageContainer;
    private Node currentNode;
    private StyleSplitter styleSplitter;
    private ParserFunctions parserFunctions;

    private StyleCache styleCache;
    private ElementsCache elementsCache;

    public void StyleSetter(AnchorPane mainPageContainer){
        this.mainPageContainer = mainPageContainer;
        styleSplitter = new StyleSplitter();
        styleCache = new StyleCache();
        elementsCache = new ElementsCache(mainPageContainer);
    }

    public void setStyle(Node node, String style){
        currentNode = node;
    };

    public void addStyle(Node node, String newProperty){

    }

    public void removeStyle(Node node){

    };

    public void removeProperty(Node node, String property){

    }

    public void updateStyle(Node node, String style){

    }

}
