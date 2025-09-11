package com.example.fastfx.fastfx.style.layouts.grid;

import com.example.fastfx.fastfx.elements.utility.UI;
import com.example.fastfx.fastfx.style.layouts.Alignment;
import com.example.fastfx.fastfx.style.layouts.Justify;
import com.example.fastfx.fastfx.style.layouts.Margin;
import com.example.fastfx.fastfx.style.layouts.Padding;
import com.example.fastfx.fastfx.style.utility.MarginListener;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Grid layout with similar functionalities like the grid layout from web.
 */
public class Grid {

    private AnchorPane container;
    private double width;
    private double height;
    private int children;
    List<Double> maxWidthChildOnColumns;
    List<Double> maxHeightChildOnRows;
    Map<Region, Margin> maxChildMarginOnRows;
    private double contentWidth;
    private double contentHeight;
    private double rowGap;
    private double columnGap;
    private Padding padding;
    private String itemsAlignment;
    private String itemsJustify;
    private String gridTemplateRows;
    private String gridTemplateColumns;
    private int rows;
    private int columns;
    private int elementsCapacity;
    private GridJustifyManager justifyManager;
    private GridAlignmentManager alignmentManager;
    private Map<Region, ChangeListener<Number>> widthListeners;
    private Map<Region, ChangeListener<Number>> heightListeners;
    private Map<Region, MarginListener> marginListeners;

    public Grid(AnchorPane container) {
        this.container = container;
        this.width = 0;
        this.height = 0;
        this.children = 0;
        this.maxChildMarginOnRows = new HashMap<>();
        this.contentWidth = 0;
        this.contentHeight = 0;
        this.rowGap =  0;
        this.columnGap = 0;
        this.padding = null;
        this.itemsAlignment = Alignment.CENTER.alignment();
        this.itemsJustify = Justify.CENTER.justify();
        this.justifyManager = new GridJustifyManager();
        this.alignmentManager = new GridAlignmentManager();
        this.gridTemplateRows = "";
        this.gridTemplateColumns = "";
        this.rows = 0;
        this.columns = 0;
        this.elementsCapacity = 0;
    }

    public void addElement(Region element){
        columns = 2;
        rows = 7;
        if(children < elementsCapacity){
            children++;
            UI.add(container, element);
        }
    }

    /**
     * Calculate the max width child for the specific row
     */
    private void calculateMaxWidthChild(int row){
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){

            }
        }
    }
 
    /**
     * Calculate the max height child for the specific column
     */
    private void calculateMaxHeightChild(int column){
        for(int i = 0; i < rows; i++){

        }
    }

    //Finished
    private int getElementRowOnAdd(){
        if(children < columns + 1){
            return 1;
        }
        return ((children - 1) / columns) + 1;
    }

    //Finished
    public int getElementRow(Region element){
        int elementIndex = UI.getChildIndex(container, element);
        if(elementIndex < columns){
            return 1;
        }
        return (elementIndex / columns) + 1;
    }

    //Finished
    private int getElementColumnOnAdd(){
        if(children <= columns){
            return children;
        }
        return ((children - 1) % columns) + 1;
    }

    //Finished
    public int getElementColumn(Region element){
        int elementIndex = UI.getChildIndex(container, element);
        if(elementIndex < columns){
            return elementIndex + 1;
        }
        return ((elementIndex) % columns) + 1;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        this.container.setPrefWidth(width);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        this.container.setPrefHeight(height);
    }

    public void setGap(double gap) {
        setRowGap(gap);
        setColumnGap(gap);
        alignElements();
        justifyElements();
    }

    public double getRowGap() {
        return rowGap;
    }

    public void setRowGap(double rowGap) {
        this.rowGap = rowGap;
    }

    public double getColumnGap() {
        return columnGap;
    }

    public void setColumnGap(double columnGap) {
        this.columnGap = columnGap;
    }

    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public String getItemsAlignment() {
        return itemsAlignment;
    }

    public void alignItems(String itemsAlignment) {
        this.itemsAlignment = itemsAlignment;
        alignElements();
    }

    private void alignElements(){

    }

    public String getItemsJustify() {
        return itemsJustify;
    }

    public void justifyItems(String itemsJustify) {
        this.itemsJustify = itemsJustify;
        justifyElements();
    }

    private void justifyElements(){

    }

    public String getGridTemplateRows() {
        return gridTemplateRows;
    }

    public int getRows() {
        return rows;
    }

    private void initMaxWidthChildOnColumns(){
        maxWidthChildOnColumns = new ArrayList<>();
        for(int i = 0; i < columns; i++){
            maxWidthChildOnColumns.add(0d);
        }
    }

    public void setGridTemplateRows(String gridTemplateRows) {
        this.gridTemplateRows = gridTemplateRows;
        String[] rowsTemplate = gridTemplateRows.split(" ");
        this.rows = rowsTemplate.length;
        this.elementsCapacity = rows * columns;
        initMaxHeightChildOnRows();
        alignElements();
    }

    public String getGridTemplateColumns() {
        return gridTemplateColumns;
    }

    public int getColumns() {
        return columns;
    }

    private void initMaxHeightChildOnRows(){
        maxHeightChildOnRows = new ArrayList<>();
        for(int i = 0; i < rows; i++){
            maxHeightChildOnRows.add(0d);
        }
    }

    public void setGridTemplateColumns(String gridTemplateColumns) {
        this.gridTemplateColumns = gridTemplateColumns;
        String[] columnsTemplate = gridTemplateColumns.split(" ");
        this.columns = columnsTemplate.length;
        this.elementsCapacity = rows * columns;
        initMaxWidthChildOnColumns();
        justifyElements();
    }
}
