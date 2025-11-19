package com.example.fastfx.src.style.layouts.flex;

import com.example.fastfx.src.utility.ui.UI;
import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.style.layouts.Padding;
import com.example.fastfx.src.utility.style.MarginSetter;
import javafx.scene.layout.Region;

public class FlexJustifyContentManager {

    /**
     * Placeholder variables for margin values. Their purpose is to access these values
     * easies and keep justify algorithm code cleaner
     */
    private double pt = 0;
    private double pr = 0;
    private double pb = 0;
    private double pl = 0;

    /**
     * Placeholder variables for margin values. Their purpose is to access these values
     * easies and keep justify algorithm code cleaner
     */
    private double mt = 0;
    private double mr = 0;
    private double mb = 0;
    private double ml = 0;

    private double prevElX = 0;
    private double prevElY = 0;
    private double prevElWidth = 0;
    private double prevElHeight = 0;
    private double prevElMt = 0;
    private double prevElMr = 0;
    private double prevElMb = 0;
    private double prevElMl = 0;

    private void initPadding(Flex flex){
        pt = 0;
        pr = 0;
        pb = 0;
        pl = 0;
        Padding flexPadding = flex.getPadding();
        if(flexPadding != null){
            pt = flexPadding.getTop();
            pr = flexPadding.getRight();
            pb = flexPadding.getBottom();
            pl = flexPadding.getLeft();
        }
    }

    private void initMargin(Region element){
        mt = 0;
        mr = 0;
        mb = 0;
        ml = 0;
        Margin margin = MarginSetter.getMargin(element);
        if(margin != null){
            mt = margin.getTop();
            mr = margin.getRight();
            mb = margin.getBottom();
            ml = margin.getLeft();
        }
    }

    private void setPrevElementDimensions(double width, double height){
        prevElWidth = width;
        prevElHeight = height;
    }

    private void setPreviousElementMargin(double marginTop, double marginRight,
                                          double marginBottom, double marginLeft){
        prevElMt = marginTop;
        prevElMr = marginRight;
        prevElMb = marginBottom;
        prevElMl = marginLeft;
    }

    /**
     * Order elements starting from the left side of the container if direction is set to row,
     * or from top to bottom if direction is set to column.
     */
    public void justifyStart(int elementIndex, Region element, Flex flex){
        double elWidth = element.getPrefWidth();
        double elHeight = element.getPrefHeight();
        initPadding(flex);
        initMargin(element);

        if(flex.getFlexDirection().equals(Direction.ROW.direction())){
            double x = 0;
            if(elementIndex == 0){
                x = Math.abs(pl + ml);
                element.setLayoutX(x);
            }
            else if(elementIndex > 0){
                x = Math.abs(prevElX + prevElWidth + prevElMr + flex.getGap() + ml);
                element.setLayoutX(x);
            }
            prevElX = x;
        }
        else{
            double y = 0;
            if(elementIndex == 0){
                y = Math.abs(pt + mt);
                element.setLayoutY(y);
            }
            else if(elementIndex > 0){
                y = Math.abs(prevElY + prevElHeight + prevElMb + flex.getGap() + mt);
                element.setLayoutY(y);
            }
            prevElY = y;
        }
        setPrevElementDimensions(elWidth, elHeight);
        setPreviousElementMargin(mt, mr, mb, ml);
    }

    /**
     * Order elements at the center of the container if direction is set to row.
     */
    public void justifyCenter(int elementIndex, Region element, Flex flex){
        double elWidth = element.getPrefWidth();
        double elHeight = element.getPrefHeight();
        initPadding(flex);
        initMargin(element);

        if(flex.getFlexDirection().equals(Direction.ROW.direction())){
            double x = 0;
            if(elementIndex == 0){
                x = Math.abs(flex.getContainer().getPrefWidth() / 2 - flex.getContentWidth() / 2);
                element.setLayoutX(x);
            }
            else if(elementIndex > 0){
                x = Math.abs(prevElX + prevElWidth + prevElMr + flex.getGap() + ml);
                element.setLayoutX(x);
            }
            prevElX = x;
        }
        else{
            double y = 0;
            if(elementIndex == 0){
                y = Math.abs(flex.getContainer().getPrefHeight() / 2 - flex.getContentHeight() / 2);
                element.setLayoutY(y);
            }
            else if(elementIndex > 0){
                y = Math.abs(prevElY + prevElHeight + prevElMb + flex.getGap() + mt);
                element.setLayoutY(y);
            }
            prevElY = y;
        }
        setPrevElementDimensions(elWidth, elHeight);
        setPreviousElementMargin(mt, mr, mb, ml);
    }

    /**
     * Order elements to the right side of the container if direction is set to row,
     * or to bottom if direction is set to column.
     */
    public void justifyEnd(int elementIndex, Region element, Flex flex){
        double elWidth = element.getPrefWidth();
        double elHeight = element.getPrefHeight();
        initPadding(flex);
        initMargin(element);

        if(flex.getFlexDirection().equals(Direction.ROW.direction())){
            double x = 0;
            if(elementIndex == 0){
                x = Math.abs(flex.getContainer().getPrefWidth() - flex.getContentWidth() - pr);
                element.setLayoutX(x);
            }
            else if(elementIndex > 0){
                x = Math.abs(prevElX + prevElWidth + prevElMr + flex.getGap() + ml);
                element.setLayoutX(x);
            }
            prevElX = x;
        }
        else{
            double y = 0;
            if(elementIndex == 0){
                y = Math.abs(flex.getContainer().getPrefHeight() - flex.getContentHeight() - pb);
                element.setLayoutY(y);
            }
            else if(elementIndex > 0){
                y = Math.abs(prevElY + prevElHeight + prevElMb + flex.getGap() + mt);
                element.setLayoutY(y);
            }
            prevElY = y;
        }
        setPrevElementDimensions(elWidth, elHeight);
        setPreviousElementMargin(mt, mr, mb, ml);
    }

    /**
     * Order elements at the center and applies an equal gap between them and no gap
     * between left side of the container and the left side of the first element ,
     * and between right side of the last element and right side of the container.
     */
    public void spaceBetween(int elementIndex, Region element, Flex flex){
        double elWidth = element.getPrefWidth();
        double elHeight = element.getPrefHeight();
        initPadding(flex);
        initMargin(element);

        if(flex.getFlexDirection().equals(Direction.ROW.direction())){
            double x;
            double gap;
            int childrenSize = UI.getChildrenSize(flex.getContainer());
            if(childrenSize == 1){
                x = flex.getContainer().getWidth() / 2 - element.getPrefWidth() / 2;
                element.setLayoutX(x);
            }
            else if(elementIndex == 0){
                x = Math.abs(pl + ml);
                element.setLayoutX(x);
            }
            else{
                gap = Math.abs(flex.getContainer().getPrefWidth() -
                        flex.getContentWidth() -
                        pl -
                        pr) /
                        (childrenSize - 1);
                x = Math.abs(prevElX + prevElWidth + prevElMr + gap + ml);
                element.setLayoutX(x);
            }
            prevElX = x;
        }
        else{
            double y;
            double gap;
            int childrenSize = UI.getChildrenSize(flex.getContainer());
            if(childrenSize == 1){
                y = flex.getContainer().getHeight() / 2 - element.getPrefHeight() / 2;
                element.setLayoutY(y);
            }
            else if(elementIndex == 0){
                y = Math.abs(pt + mt);
                element.setLayoutY(y);
            }
            else{
                gap = Math.abs(flex.getContainer().getPrefHeight() -
                        flex.getContentHeight() -
                        pt -
                        pb) /
                        (childrenSize - 1);
                y = Math.abs(prevElY + prevElHeight + prevElMb + gap + mt);
                element.setLayoutY(y);
            }
            prevElY = y;
        }
        setPrevElementDimensions(elWidth, elHeight);
        setPreviousElementMargin(mt, mr, mb, ml);
    }

    /**
     * Order elements at the center and applies an equal gap between them and half of that gap
     * between left side of the container and the left side of the first element ,
     * and between right side of the last element and right side of the container.
     */
    public void spaceAround(int elementIndex, Region element, Flex flex){
        double elWidth = element.getPrefWidth();
        double elHeight = element.getPrefHeight();
        initPadding(flex);
        initMargin(element);

        if(flex.getFlexDirection().equals(Direction.ROW.direction())){
            double x;
            double gap;
            int childrenSize = UI.getChildrenSize(flex.getContainer());
            if(childrenSize == 1){
                x = flex.getContainer().getPrefWidth() / 2 - element.getPrefWidth() / 2;
                element.setLayoutX(x);
            }
            else{
                gap = Math.abs(flex.getContainer().getPrefWidth() -
                        flex.getContentWidth() -
                        pl -
                        pr) /
                        childrenSize;
                if(elementIndex == 0){
                    x = Math.abs(pl + ml + gap / 2);
                    element.setLayoutX(x);
                }
                else{
                    x = Math.abs(prevElX + prevElWidth + prevElMr + gap + ml);
                    element.setLayoutX(x);
                }
            }
            prevElX = x;
        }
        else{
            double y;
            double gap;
            int childrenSize = UI.getChildrenSize(flex.getContainer());
            if(childrenSize == 1){
                y = flex.getContainer().getPrefHeight() / 2 - element.getPrefHeight() / 2;
                element.setLayoutY(y);
            }
            else{
                gap = Math.abs(flex.getContainer().getPrefHeight() -
                        flex.getContentHeight() -
                        pt -
                        pb) /
                        childrenSize;
                if(elementIndex == 0){
                    y = Math.abs(pt + mt + gap / 2);
                    element.setLayoutY(y);
                }
                else{
                    y = Math.abs(prevElY + prevElHeight + prevElMb + gap + mt);
                    element.setLayoutY(y);
                }
            }
            prevElY = y;
        }
        setPrevElementDimensions(elWidth, elHeight);
        setPreviousElementMargin(mt, mr, mb, ml);
    }

    /**
     * Order elements at the center and applies an equal gap between them and
     * between left side of the container and the left side of the first element ,
     * and between right side of the last element and right side of the container.
     */
    public void spaceEvenly(int elementIndex, Region element, Flex flex){
        double elWidth = element.getPrefWidth();
        double elHeight = element.getPrefHeight();
        initPadding(flex);
        initMargin(element);

        if(flex.getFlexDirection().equals(Direction.ROW.direction())){
            double x;
            double gap;
            int childrenSize = UI.getChildrenSize(flex.getContainer());
            if(childrenSize == 1){
                x = flex.getContainer().getPrefWidth() / 2 - element.getPrefWidth() / 2;
                element.setLayoutX(x);
            }
            else{
                gap = Math.abs(flex.getContainer().getPrefWidth() -
                        flex.getContentWidth() -
                        pl -
                        pr) /
                        (childrenSize + 1);
                if(elementIndex == 0){
                    x = Math.abs(pl + ml + gap);
                    element.setLayoutX(x);
                }
                else{
                    x = Math.abs(prevElX + prevElWidth + prevElMr + gap + ml);
                    element.setLayoutX(x);
                }
            }
            prevElX = x;
        }
        else{
            double y;
            double gap;
            int childrenSize = UI.getChildrenSize(flex.getContainer());
            if(childrenSize == 1){
                y = flex.getContainer().getPrefHeight() / 2 - element.getPrefHeight() / 2;
                element.setLayoutY(y);
            }
            else{
                gap = Math.abs(flex.getContainer().getPrefHeight() -
                        flex.getContentHeight() -
                        pt -
                        pb) /
                        (childrenSize + 1);
                if(elementIndex == 0){
                    y = Math.abs(pt + mt + gap);
                    element.setLayoutY(y);
                }
                else{
                    y = Math.abs(prevElY + prevElHeight + prevElMb + gap + mt);
                    element.setLayoutY(y);
                }
            }
            prevElY = y;
        }
        setPrevElementDimensions(elWidth, elHeight);
        setPreviousElementMargin(mt, mr, mb, ml);
    }

}
