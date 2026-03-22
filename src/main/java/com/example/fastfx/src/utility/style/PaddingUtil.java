package com.example.fastfx.src.utility.style;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;

public class PaddingUtil {

    /**
     * Sets the padding(insets) for the element
     *
     * @param element the element to set the padding to
     * @param padding the value of the padding
     */
    public static void setPadding(Region element, double padding){
        if(element != null){
            element.setPadding(new Insets(padding, padding, padding, padding));
        }
    }

    /**
     * Sets the top padding(top inset) for the element
     *
     * @param element the element to set the top padding to
     * @param padding the value of the top padding
     */
    public static void setTopPadding(Region element, double padding){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(padding, oldInsets.getRight(), oldInsets.getBottom(), oldInsets.getLeft()));
        }
    }

    /**
     * Sets the right padding(right inset) for the element
     *
     * @param element the element to set the right padding to
     * @param padding the value of the right padding
     */
    public static void setRightPadding(Region element, double padding){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(oldInsets.getTop(), padding, oldInsets.getBottom(), oldInsets.getLeft()));
        }
    }

    /**
     * Sets the bottom padding(bottom inset) for the element
     *
     * @param element the element to set the bottom padding to
     * @param padding the value of the bottom padding
     */
    public static void setBottomPadding(Region element, double padding){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(oldInsets.getTop(), oldInsets.getRight(), padding, oldInsets.getLeft()));
        }
    }

    /**
     * Sets the left padding(left inset) for the element
     *
     * @param element the element to set the left padding to
     * @param padding the value of the left padding
     */
    public static void setLeftPadding(Region element, double padding){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(oldInsets.getTop(), oldInsets.getRight(), oldInsets.getBottom(), padding));
        }
    }
}
