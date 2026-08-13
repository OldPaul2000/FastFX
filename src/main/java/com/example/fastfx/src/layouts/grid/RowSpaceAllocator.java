package com.example.fastfx.src.layouts.grid;

import com.example.fastfx.src.layouts.Padding;

import java.util.ArrayList;
import java.util.List;

public class RowSpaceAllocator {

    private String[] rowsTemplate;
    private Padding padding;
    private double rowGap;
    private List<Double> allocatedSpaces;

    public void setRowsTemplate (String[] rowsTemplate) {
        this.rowsTemplate = rowsTemplate;
    }

    public void setPadding (Padding padding) {
        this.padding = padding;
    }

    public void setRowGap (double gap) {
        this.rowGap = gap;
    }

    public void initAllocatedSpaces (int rows) {
        allocatedSpaces = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            allocatedSpaces.add(0d);
        }
    }

    /**
     * Allocate the necessary height to each row so that the elements fit properly.
     */
    public List<Double> allocateRowsSpace (Grid grid) {
        double remainingFractionHeight = grid.getHeight();
        if (padding != null) {
            remainingFractionHeight -= padding.getTop();
            remainingFractionHeight -= padding.getBottom();
        }
        remainingFractionHeight -= ((rowsTemplate.length - 1)) * rowGap;
        int summedFractions = 0;
        for (int i = 0; i < rowsTemplate.length; i++) {
            String currEl = rowsTemplate[i];
            if (currEl.endsWith("px")) {
                double currHeightInPixels = Integer.parseInt(currEl.replaceAll("px", ""));
                remainingFractionHeight -= currHeightInPixels;
                allocatedSpaces.set(i, currHeightInPixels);
            }
            else if (currEl.endsWith("fr")) {
                int currFractionValue = Integer.parseInt(currEl.replaceAll("fr", ""));
                summedFractions += currFractionValue;
            }
        }
        double fractionSize = remainingFractionHeight / summedFractions;
        for (int i = 0; i < rowsTemplate.length; i++) {
            String currEl = rowsTemplate[i];
            if (currEl.endsWith("fr")) {
                double fraction = Integer.parseInt(currEl.replaceAll("fr", ""));
                allocatedSpaces.set(i, fraction * fractionSize);
            }
        }
        return allocatedSpaces;
    }
}
