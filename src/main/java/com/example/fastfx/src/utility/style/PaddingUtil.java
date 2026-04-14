package com.example.fastfx.src.utility.style;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;

public class PaddingUtil {
    /**
     * Sets the padding(insets) for the element
     *
     * @param element the element to set the padding for
     * @param padding the value of the padding
     */
    public static void setPadding(Region element, double padding){
        if(element != null){
            element.setPadding(new Insets(padding, padding, padding, padding));
        }
    }

    /**
     * Sets the padding(insets) for the element
     *
     * @param element the element to set the padding for
     * @param topBottom the value of the top and bottom padding
     * @param leftRight the value of the left and right padding
     */
    public static void setPadding(Region element, double topBottom, double leftRight){
        if(element != null){
            element.setPadding(new Insets(topBottom, leftRight, topBottom, leftRight));
        }
    }

    /**
     * Sets the padding(insets) for the element
     *
     * @param element the element to set the padding for
     * @param top the value of the top padding
     * @param right the value of the right padding
     * @param bottom the value of the bottom padding
     * @param left the value of the left padding
     */
    public static void setPadding(Region element, double top, double right, double bottom, double left){
        if(element != null){
            element.setPadding(new Insets(top, right, bottom, left));
        }
    }

    /**
     * Sets the top padding(top inset) for the element
     *
     * @param element the element to set the top padding for
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
     * @param element the element to set the right padding for
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
     * @param element the element to set the bottom padding for
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
     * @param element the element to set the left padding for
     * @param padding the value of the left padding
     */
    public static void setLeftPadding(Region element, double padding){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(oldInsets.getTop(), oldInsets.getRight(), oldInsets.getBottom(), padding));
        }
    }

    /**
     * Sets the top and bottom padding(top and bottom inset) for the element
     *
     * @param element the element to set the top and bottom padding for
     * @param padding the value of the top and bottom padding
     */
    public static void setTopBottomPadding(Region element, double padding){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(padding, oldInsets.getRight(), padding, oldInsets.getLeft()));
        }
    }

    /**
     * Sets the top and bottom padding(top and bottom inset) for the element
     *
     * @param element the element to set the top and bottom padding for
     * @param top the value of the top padding
     * @param bottom the value of the bottom padding
     */
    public static void setTopBottomPadding(Region element, double top, double bottom){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(top, oldInsets.getRight(), bottom, oldInsets.getLeft()));
        }
    }

    /**
     * Sets the right and left padding(right and left inset) for the element
     *
     * @param element the element to set the right and left padding for
     * @param padding the value of the right and left padding
     */
    public static void setRightLeftPadding(Region element, double padding){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(oldInsets.getTop(), padding, oldInsets.getBottom(), padding));
        }
    }

    /**
     * Sets the right and left padding(right and left inset) for the element
     *
     * @param element the element to set the right and left padding for
     * @param right the value of the right padding
     * @param left the value of the left padding
     */
    public static void setRightLeftPadding(Region element, double right, double left){
        if(element != null){
            Insets oldInsets = element.getInsets();
            element.setPadding(new Insets(oldInsets.getTop(), right, oldInsets.getBottom(), left));
        }
    }

    public static double getTopPadding(Region element){
        if(element != null){
            return element.getPadding().getTop();
        }
        return 0;
    }

    public static double getRightPadding(Region element){
        if(element != null){
            return element.getPadding().getRight();
        }
        return 0;
    }

    public static double getBottomPadding(Region element){
        if(element != null){
            return element.getPadding().getBottom();
        }
        return 0;
    }

    public static double getLeftPadding(Region element){
        if(element != null){
            return element.getPadding().getLeft();
        }
        return 0;
    }
}
