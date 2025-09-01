package com.example.fastfx.fastfx.style.layouts;

import com.example.fastfx.fastfx.style.utility.MarginSetter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class FlexAlignmentManager {

    /**
     * Align the new element taking into account all properties set for the flex object
     */
    public void alignElement(String flexDirection, Region element, AnchorPane container,
                              Padding padding, String contentAlignment){
        double x = 0;
        double y = 0;
        double mt = 0;
        double mr = 0;
        double mb = 0;
        double ml = 0;
        Margin margin = MarginSetter.getMargin(element);
        if(margin != null){
            mt = margin.getTop();
            mr = margin.getRight();
            mb = margin.getBottom();
            ml = margin.getLeft();
        }
        double pt = 0;
        double pr = 0;
        double pb = 0;
        double pl = 0;
        if(padding != null){
            pt = padding.getTop();
            pr = padding.getRight();
            pb = padding.getBottom();
            pl = padding.getLeft();
        }
        if(flexDirection.equals(Direction.ROW.direction())){
            if(contentAlignment.equals(Alignment.START.alignment())){
                y = Math.abs(pt + mt);
            }
            if(contentAlignment.equals(Alignment.CENTER.alignment())){
                y = Math.abs(element.getPrefHeight() / 2 - container.getPrefHeight() / 2);
                y += (pt + mt) - (mb + pb);
            }
            if(contentAlignment.equals(Alignment.END.alignment())){
                y = Math.abs(element.getPrefHeight() - container.getPrefHeight() + mb + pb);
            }
            // Implement a new check and create a new function for overflow cases
            element.setLayoutY(y);
        }
        else{
            if(contentAlignment.equals(Alignment.START.alignment())){
                x = Math.abs(pl + ml);
            }
            if(contentAlignment.equals(Alignment.CENTER.alignment())){
                x = Math.abs(element.getPrefWidth() / 2 - container.getPrefWidth() / 2);
                x += (pl + ml) - (mr + pr);
            }
            if(contentAlignment.equals(Alignment.END.alignment())){
                x = Math.abs(element.getPrefWidth() - container.getPrefWidth() + mr + pr);
            }
            // Implement a new check and create a new function for overflow cases
            element.setLayoutX(x);
        }
    }

}
