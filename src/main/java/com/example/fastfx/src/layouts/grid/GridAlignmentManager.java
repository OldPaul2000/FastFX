package com.example.fastfx.src.layouts.grid;

import com.example.fastfx.src.layouts.Alignment;
import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.layouts.Padding;
import com.example.fastfx.src.util.style.MarginUtil;
import javafx.scene.layout.Region;

import java.util.List;

public class GridAlignmentManager {

    private List<Double> gridAllocatedHeights;
    private String gridItemsAlignment;
    private Padding padding;
    private double rowGap;

    public void setGridAllocatedHeights (List<Double> gridAllocatedHeights) {
        this.gridAllocatedHeights = gridAllocatedHeights;
    }

    public void setGridItemsAlignment (String gridItemsAlignment) {
        this.gridItemsAlignment = gridItemsAlignment;
    }

    public void setPadding (Padding padding) {
        this.padding = padding;
    }

    public void setRowGap (double rowGap) {
        this.rowGap = rowGap;
    }

    public void resetParameters () {
        y = 0;
        pt = 0;
        pb = 0;
        mt = 0;
        mb = 0;
        lastRow = 1;
        heightSum = 0;
        if (padding != null) {
            pb = padding.getBottom();
            heightSum = padding.getTop();
        }
    }

    private double y;
    private double mt;
    private double mb;
    private double pt = 0;
    private double pb = 0;
    private int lastRow = 1;
    private double heightSum = 0;

    /**
     * Align the element taking into account all properties set for the grid object
     */
    public void alignElement (Grid grid, Region currEl, int elRow) {
        mt = 0;
        mb = 0;
        Margin margin = MarginUtil.getMargin(currEl);
        if (margin != null) {
            mt = margin.getTop();
            mb = margin.getBottom();
        }
        if (elRow > lastRow) {
            heightSum += gridAllocatedHeights.get(elRow - 2) + rowGap;
            lastRow = elRow;
        }
        if (grid.getSelfAlignment(currEl) != null && !grid.getSelfAlignment(currEl).equals(gridItemsAlignment)) {
            alignSelf(currEl, elRow, grid.getSelfAlignment(currEl));
        }
        else {
            if (gridItemsAlignment.equals(Alignment.START.alignment())) {
                y = heightSum + mt - mb;
            }
            if (gridItemsAlignment.equals(Alignment.CENTER.alignment())) {
                if (elRow == 1) {
                    y = heightSum + gridAllocatedHeights.get(0) / 2 - currEl.getPrefHeight() / 2 + mt - mb;
                }
                else {
                    y = heightSum + (gridAllocatedHeights.get(elRow - 1) / 2) - currEl.getPrefHeight() / 2 + mt - mb;
                }
            }
            if (gridItemsAlignment.equals(Alignment.END.alignment())) {
                if (elRow == 1) {
                    y = heightSum + gridAllocatedHeights.get(0) - currEl.getPrefHeight() + mt - mb;
                }
                else {
                    y = heightSum + gridAllocatedHeights.get(lastRow - 1) - currEl.getPrefHeight() + mt - mb;
                }
            }
            currEl.setLayoutY(y);
        }
    }

    /**
     * Align the element into its own allocated space on add operation, taking into account all properties set for the grid object
     */
    private int lastRowOnAdd = 1;
    private double heightSumOnAdd = 0;

    public void alignElementOnAdd (Region currEl, int elRow) {
        double y = 0;
        double mt = 0;
        double mb = 0;
        double pt = 0;
        double pb = 0;
        Margin margin = MarginUtil.getMargin(currEl);
        if (margin != null) {
            mt = margin.getTop();
            mb = margin.getBottom();
        }
        if (padding != null) {
            pt = padding.getTop();
            pb = padding.getBottom();
        }
        if (elRow == 1) {
            heightSumOnAdd = pt;
        }
        else if (elRow > lastRowOnAdd) {
            heightSumOnAdd += gridAllocatedHeights.get(elRow - 2) + rowGap;
            lastRowOnAdd = elRow;
        }
        if (gridItemsAlignment.equals(Alignment.START.alignment())) {
            y = heightSumOnAdd + mt - mb;
        }
        if (gridItemsAlignment.equals(Alignment.CENTER.alignment())) {
            if (elRow == 1) {
                y = heightSumOnAdd + gridAllocatedHeights.get(0) / 2 - currEl.getPrefHeight() / 2 + mt - mb;
            }
            else {
                y = heightSumOnAdd + (gridAllocatedHeights.get(elRow - 1) / 2) - currEl.getPrefHeight() / 2 + mt - mb;
            }
        }
        if (gridItemsAlignment.equals(Alignment.END.alignment())) {
            if (elRow == 1) {
                y = heightSumOnAdd + gridAllocatedHeights.get(0) - currEl.getPrefHeight() + mt;
            }
            else {
                y = heightSumOnAdd + gridAllocatedHeights.get(lastRowOnAdd - 1) - currEl.getPrefHeight() + mt;
            }
        }
        currEl.setLayoutY(y);
    }

    private double getSelfAlignDistance (int row) {
        double heigthSum = 0;
        if (padding != null) {
            heigthSum += padding.getTop();
        }
        for (int i = 0; i < row - 1; i++) {
            heigthSum += gridAllocatedHeights.get(i);
        }
        return heigthSum;
    }

    public void alignSelf (Region element, int elRow, String alignment) {
        double mt = 0;
        double mb = 0;
        Margin margin = MarginUtil.getMargin(element);
        if (margin != null) {
            mt = margin.getTop();
            mb = margin.getBottom();
        }
        double y = getSelfAlignDistance(elRow);
        if (alignment.equals(Alignment.START.alignment())) {
            y += mt - mb;
        }
        if (alignment.equals(Alignment.CENTER.alignment())) {
            y += gridAllocatedHeights.get(elRow - 1) / 2 - element.getPrefHeight() / 2 + mt - mb;
        }
        if (alignment.equals(Alignment.END.alignment())) {
            y += gridAllocatedHeights.get(elRow - 1) - element.getPrefHeight() + mt - mb;
        }
        element.setLayoutY(y);
    }
}