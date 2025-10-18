package com.example.fastfx.fastfx.style.layouts.grid;

import com.example.fastfx.fastfx.style.layouts.Padding;

import java.util.ArrayList;
import java.util.List;

public class ColumnSpaceAllocator {

    private String[] colsTemplate;
    private Padding padding;
    private double columnGap;
    private List<Double> allocatedSpaces;

    public void setColsTemplate(String[] colsTemplate) {
        this.colsTemplate = colsTemplate;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public void setColGap(double gap) {
        this.columnGap = gap;
    }

    public void initAllocatedSpaces(int cols){
        allocatedSpaces = new ArrayList<>();
        for(int i = 0; i < cols; i++){
            allocatedSpaces.add(0d);
        }
    }

    /**
     * Allocate the necessary width to each column so that the elements fit properly.
     */
    public List<Double> allocateColumnsSpace(Grid grid){
        double remainingFractionWidth = grid.getWidth();
        if(padding != null){
            remainingFractionWidth -= padding.getLeft();
            remainingFractionWidth -= padding.getRight();
        }
        remainingFractionWidth -= ((colsTemplate.length - 1) * columnGap);
        int summedFractions = 0;
        for(int i = 0; i < colsTemplate.length; i++){
            String currEl = colsTemplate[i];
            if(currEl.endsWith("px")){
                double currWidthInPixels = Integer.parseInt(currEl.replaceAll("px", ""));
                remainingFractionWidth -= currWidthInPixels;
                allocatedSpaces.set(i, currWidthInPixels);
            }
            else if(currEl.endsWith("fr")){
                int currFractionValue =  Integer.parseInt(currEl.replaceAll("fr", ""));
                summedFractions += currFractionValue;
            }
        }
        double fractionSize = remainingFractionWidth / summedFractions;
        for(int i = 0; i < colsTemplate.length; i++){
            String currEl = colsTemplate[i];
            if(currEl.endsWith("fr")){
                double fraction = Integer.parseInt(currEl.replaceAll("fr", ""));
                allocatedSpaces.set(i, fraction * fractionSize);
            }
        }
        return allocatedSpaces;
    }
}
