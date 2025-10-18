package com.example.fastfx.fastfx.style.layouts.grid;

import com.example.fastfx.fastfx.style.layouts.Justify;
import com.example.fastfx.fastfx.style.layouts.Margin;
import com.example.fastfx.fastfx.style.layouts.Padding;
import com.example.fastfx.fastfx.style.utility.MarginSetter;
import javafx.scene.layout.Region;

import java.util.List;

public class GridJustifyManager {

    private List<Double> gridAllocatedWidths;
    private String gridItemsJustify;
    private Padding padding;
    private double columnGap;

    public void setGridAllocatedWidths(List<Double> gridAllocatedWidths) {
        this.gridAllocatedWidths = gridAllocatedWidths;
    }

    public void setGridItemsJustify(String gridItemsJustify) {
        this.gridItemsJustify = gridItemsJustify;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public void setColumnGap(double columnGap) {
        this.columnGap = columnGap;
    }

    public void resetParameters() {
        x = 0;
        pl = 0;
        pr = 0;
        ml = 0;
        mr = 0;
        lastCol = 0;
        widthSum = 0;
        if (padding != null) {
            pl = padding.getLeft();
            widthSum = pl;
        }
    }

    private double x;
    private double ml;
    private double mr;
    private double pl = 0;
    private double pr = 0;
    private double widthSum = 0;

    private int lastCol = 0;
    /**
     * Justify the element taking into account all properties set for the grid object
     */
    public void justifyElement(Grid grid, Region currEl, int elCol) {
        Margin margin = MarginSetter.getMargin(currEl);
        if (margin != null) {
            ml = margin.getLeft();
            mr = margin.getRight();
        }
        if (elCol == 1) {
            widthSum = pl;
        }
        if (elCol > lastCol && elCol > 1) {
            widthSum += gridAllocatedWidths.get(elCol - 2) + grid.getColumnGap();
        }
        if (grid.getSelfJustify(currEl) != null &&
                !grid.getSelfJustify(currEl).equals(gridItemsJustify)) {
            justifySelf(currEl, elCol, grid.getSelfJustify(currEl));
        }
        else {
            if (gridItemsJustify.equals(Justify.START.justify())) {
                x = widthSum + ml - mr;
            }
            if (gridItemsJustify.equals(Justify.CENTER.justify())) {
                if (elCol == 1) {
                    x = widthSum + gridAllocatedWidths.get(0) / 2 - currEl.getPrefWidth() / 2 + ml - mr;
                }
                else {
                    x = widthSum +
                            (gridAllocatedWidths.get(elCol - 1) / 2) -
                            currEl.getPrefWidth() / 2 + ml - mr;
                }
            }
            if (gridItemsJustify.equals(Justify.END.justify())) {
                if (elCol == 1) {
                    x = widthSum + gridAllocatedWidths.get(0) - currEl.getPrefWidth() + ml - mr;
                }
                else {
                    x = ml - mr + widthSum + gridAllocatedWidths.get(elCol - 1) - currEl.getPrefWidth();
                }
            }
            currEl.setLayoutX(x);
        }
        lastCol = elCol;
    }

    /**
     * Justify the element into its own allocated space on add operation, taking into account all properties set for the grid object
     */
    private double widthSumOnAdd = 0;

    public void justifyElementOnAdd(Region currEl, int elCol) {
        double x = 0;
        double ml = 0;
        double mr = 0;
        double pl = 0;
        double pr = 0;
        Margin margin = MarginSetter.getMargin(currEl);
        if (margin != null) {
            ml = margin.getLeft();
            mr = margin.getRight();
        }
        if (padding != null) {
            pl = padding.getLeft();
            pr = padding.getRight();
        }
        if (elCol == 1) {
            widthSumOnAdd = pl;
        }
        if (elCol > 1) {
            widthSumOnAdd += gridAllocatedWidths.get(elCol - 2) + columnGap;
        }
        if (gridItemsJustify.equals(Justify.START.justify())) {
            x = widthSumOnAdd + ml - mr;
        }
        if (gridItemsJustify.equals(Justify.CENTER.justify())) {
            if (elCol == 1) {
                x = widthSumOnAdd + gridAllocatedWidths.get(0) / 2 - currEl.getPrefWidth() / 2;
            }
            else {
                x = widthSumOnAdd +
                        (gridAllocatedWidths.get(elCol - 1) / 2) -
                        currEl.getPrefWidth() / 2 + ml - mr;
            }
        }
        if (gridItemsJustify.equals(Justify.END.justify())) {
            if (elCol == 1) {
                x = widthSumOnAdd + gridAllocatedWidths.get(0) - currEl.getPrefWidth() + ml - mr;
            }
            else {
                x = widthSumOnAdd + gridAllocatedWidths.get(elCol - 1) - currEl.getPrefWidth() + ml - mr;
            }
        }
        currEl.setLayoutX(x);
    }

    private double getSelfJustifyDistance(int column) {
        double widthSum = 0;
        if (padding != null) {
            widthSum += padding.getLeft();
        }
        for (int i = 0; i < column - 1; i++) {
            widthSum += gridAllocatedWidths.get(i);
        }
        return widthSum;
    }

    public void justifySelf(Region element, int elCol, String justify) {
        double ml = 0;
        double mr = 0;
        Margin margin = MarginSetter.getMargin(element);
        if (margin != null) {
            ml = margin.getLeft();
            mr = margin.getRight();
        }
        double x = getSelfJustifyDistance(elCol);
        if (justify.equals(Justify.START.justify())) {
            x += ml - mr;
        }
        if (justify.equals(Justify.CENTER.justify())) {
            x += gridAllocatedWidths.get(elCol - 1) / 2 - element.getPrefWidth() / 2 + ml - mr;
        }
        if (justify.equals(Justify.END.justify())) {
            x += gridAllocatedWidths.get(elCol - 1) - element.getPrefWidth() + ml - mr;
        }
        element.setLayoutX(x);
    }
}
