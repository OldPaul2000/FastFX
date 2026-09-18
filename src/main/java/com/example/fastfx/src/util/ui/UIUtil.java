package com.example.fastfx.src.util.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility class for simplifying adding and removing children from the UIUtil
 */
public class UIUtil {
    private static final Set<String> POSITIONS = new HashSet<>(Set.of("first", "last"));

    /**
     * Add a new child at the specified location in the specified parent
     */
    public static void add (String position, Pane parent, Node child) {
        if (parent != null && child != null) {
            if (POSITIONS.contains(position)) {
                if (position.equals("first")) {
                    parent.getChildren().add(0, child);
                }
                if (position.equals("last")) {
                    parent.getChildren().add(child);
                }
            }
        }
    }

    /**
     * Add a new child at the specified index in the specified parent
     */
    public static void add (int indexPosition, Pane parent, Node child) {
        if (parent != null && child != null) {
            List<Node> children = parent.getChildren();
            if (indexPosition > children.size() - 1) {
                children.add(child);
            }
            else if (indexPosition < 0) {
                children.add(0, child);
            }
            else {
                children.add(indexPosition, child);
            }
        }
    }

    /**
     * Add a new child at the end of the children list of the specified parent
     */
    public static void add (Pane parent, Node child) {
        if (parent != null && child != null) {
            add("last", parent, child);
        }
    }

    /**
     * Add a new child at the specified location in the specified parent
     */
    public static void add (String position, AnchorPane parent, Node child) {
        if (parent != null && child != null) {
            if (POSITIONS.contains(position)) {
                if (position.equals("first")) {
                    parent.getChildren().add(0, child);
                }
                if (position.equals("last")) {
                    parent.getChildren().add(child);
                }
            }
        }
    }

    /**
     * Add a new child at the specified index in the specified parent
     */
    public static void add (int indexPosition, AnchorPane parent, Node child) {
        if (parent != null && child != null) {
            List<Node> children = parent.getChildren();
            if (indexPosition > children.size() - 1) {
                children.add(child);
            }
            else if (indexPosition < 0) {
                children.add(0, child);
            }
            else {
                children.add(indexPosition, child);
            }
        }
    }

    /**
     * Add a new child at the end of the children list of the specified AnchorPane parent
     */
    public static void add (AnchorPane parent, Node child) {
        if (parent != null && child != null) {
            add("last", parent, child);
        }
    }

    /**
     * Remove child from the specified location from the specified AnchorPane parent
     */
    public static Node remove (String position, AnchorPane parent) {
        Node child = null;
        if (parent != null) {
            if (POSITIONS.contains(position) && !parent.getChildren().isEmpty()) {
                if (position.equals("first")) {
                    child = parent.getChildren().get(0);
                    parent.getChildren().remove(child);
                }
                if (position.equals("last")) {
                    child = parent.getChildren().get(parent.getChildren().size() - 1);
                    parent.getChildren().remove(child);
                }
            }
        }
        return child;
    }

    /**
     * Remove child from the specified index from the specified AnchorPane parent
     * and returns the removed child
     */
    public static Node remove (AnchorPane parent, int nthChild) {
        Node child = null;
        if (parent != null) {
            List<Node> children = parent.getChildren();
            if (children.size() > 0 && nthChild >= 0 && nthChild < children.size()) {
                child = children.get(nthChild);
                children.remove(nthChild);
            }
        }
        return child;
    }

    /**
     * Remove child which has the specified id from the specified AnchorPane parent
     */
    public static Node remove (AnchorPane parent, String id) {
        Node child = null;
        if (parent != null) {
            child = getChildById(parent, id);
            if (child != null) {
                remove(parent, child);
            }
        }
        return child;
    }

    /**
     * Remove specified child from the specified AnchorPane parent
     */
    public static boolean remove (AnchorPane parent, Node child) {
        if (parent != null && child != null) {
            parent.getChildren().remove(child);
            return true;
        }
        return false;
    }

    /**
     * Remove child from the specified location from the specified Pane parent
     */
    public static Node remove (String position, Pane parent) {
        Node child = null;
        if (parent != null) {
            if (POSITIONS.contains(position) && !parent.getChildren().isEmpty()) {
                if (position.equals("first")) {
                    child = parent.getChildren().get(0);
                    parent.getChildren().remove(child);
                }
                if (position.equals("last")) {
                    child = parent.getChildren().get(parent.getChildren().size() - 1);
                    parent.getChildren().remove(child);
                }
            }
        }
        return child;
    }

    /**
     * Remove child from the specified index from the specified parent
     * and returns the removed child
     */
    public static Node remove (Pane parent, int nthChild) {
        Node child = null;
        if (parent != null) {
            List<Node> children = parent.getChildren();
            if (children.size() > 0 && nthChild >= 0 && nthChild < children.size()) {
                child = children.get(nthChild);
                children.remove(nthChild);
            }
        }
        return child;
    }

    /**
     * Remove child which has the specified id from the specified Pane parent
     */
    public static Node remove (Pane parent, String id) {
        Node child = null;
        if (parent != null) {
            child = getChildById(parent, id);
            if (child != null) {
                remove(parent, child);
            }
        }
        return child;
    }

    /**
     * Removes all children from the specified parent
     */
    public static void removeAll(Pane parent){
        if(parent != null){
            parent.getChildren().clear();
        }
    }

    /**
     * Remove specified child from the specified Pane parent
     */
    public static boolean remove (Pane parent, Node child) {
        if (parent != null && child != null) {
            parent.getChildren().remove(child);
            return true;
        }
        return false;
    }

    /**
     * Get the children from the specified parent which has the specified fx id.
     */
    public static Node getChildById (Region parent, String id) {
        if (parent != null) {
            return parent.lookup("#" + id);
        }
        return null;
    }

    /**
     * Get the children from the parent from the specified index position.
     * If the index parameter is less than 0 or higher than children size - 1
     * then null value will be returned.
     */
    public static Node getChildByIndex (Region parent, int index) {
        List<Node> children = parent.getChildrenUnmodifiable();
        if (index >= 0 && index < children.size()) {
            return children.get(index);
        }
        return null;
    }

    /**
     * Return the children from the specified parent.
     */
    public static List<Node> getChildren (Region parent) {
        if (parent != null) {
            return parent.getChildrenUnmodifiable();
        }
        return null;
    }

    /**
     * Return the children size from the specified parent.
     */
    public static int getChildrenSize (Region parent) {
        if (parent != null) {
            return parent.getChildrenUnmodifiable().size();
        }
        return 0;
    }

    /**
     * Return the index of the specified child
     */
    public static int getChildIndex (Pane parent, Node child) {
        if (parent != null && child != null) {
            return UIUtil.getChildren(parent).indexOf(child);
        }
        return -1;
    }

    /**
     * Return the index of the specified child
     */
    public static int getChildIndex (AnchorPane parent, Node child) {
        if (parent != null && child != null) {
            return UIUtil.getChildren(parent).indexOf(child);
        }
        return -1;
    }

    public static Node getParent(Node child){
        return child.getParent();
    }

    public static boolean contains(Pane parent, Node child){
        return parent.getChildren().contains(child);
    }

    /**
     * Switch the elements places in the parent nodes list
     */
    public static void switchElementsIndex (Pane parent, int el1Index, int el2Index) {
        if (parent != null) {
            List<Node> children = parent.getChildren();
            if (children.size() > 1) {
                if (el1Index >= 0 && el1Index < children.size() && el2Index >= 0 && el2Index < children.size() && el1Index != el2Index) {
                    Node tempChild1 = children.get(el1Index);
                    Node tempChild2 = children.get(el2Index);
                    children.remove(tempChild1);
                    children.remove(tempChild2);
                    if (el1Index > children.size() - 1) {
                        children.add(tempChild2);
                    }
                    else {
                        children.add(el1Index, tempChild2);
                    }
                    if (el2Index > children.size() - 1) {
                        children.add(tempChild1);
                    }
                    else {
                        children.add(el2Index, tempChild1);
                    }
                }
            }
        }
    }
}
