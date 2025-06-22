package com.example.fastfx.fastfx.elements;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElementsCache {


    private AnchorPane mainContainer;

    public ElementsCache(AnchorPane mainContainer) {
        this.mainContainer = mainContainer;
    }

    private Set<Node> allNodes = new HashSet<>();

    public Set<Node> getAllChildren() {
        return allNodes;
    }

    // WORKS
    public void searchAllElements(){
        allNodes = new HashSet<>();
        List<Node> nodes = mainContainer.getChildren();
        for(int i = 0; i < nodes.size(); i++){
            Node currNode = nodes.get(i);
            allNodes.add(currNode);
            searchSubNodes(currNode);
        }
    }
    // WORKS
    private void searchSubNodes(Node node){
        if(node.getClass().getSimpleName().equals("AnchorPane")){
            List<Node> children = ((AnchorPane)node).getChildren();
            if(children.size() > 0){
                children.forEach(child -> {
                    allNodes.add(child);
                    searchSubNodes(child);
                });
            }
        }
    }

}
