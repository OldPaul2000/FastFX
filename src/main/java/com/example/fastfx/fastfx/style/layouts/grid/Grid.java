package com.example.fastfx.fastfx.style.layouts.grid;

import com.example.fastfx.fastfx.elements.utility.UI;
import com.example.fastfx.fastfx.style.layouts.Alignment;
import com.example.fastfx.fastfx.style.layouts.Justify;
import com.example.fastfx.fastfx.style.layouts.Padding;
import com.example.fastfx.fastfx.style.utility.MarginListener;
import com.example.fastfx.fastfx.style.utility.MarginSetter;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.util.*;

/**
 * Grid layout with similar functionalities like the grid layout from web.
 */
public class Grid {

    private Pane container;
    private double width;
    private double height;
    private int children;
    List<Double> maxWidthChildOnColumns;
    List<Double> maxHeightChildOnRows;
    private double rowGap;
    private double columnGap;
    private Padding padding;
    private String itemsAlignment;
    private String itemsJustify;
    private String gridTemplateRows;
    private String[] rowsTemplate;
    private String gridTemplateColumns;
    private String[] columnsTemplate;
    private int rows;
    private int columns;
    private int elementsCapacity;
    private RowSpaceAllocator rowSpaceAllocator;
    private ColumnSpaceAllocator columnSpaceAllocator;
    private List<Double> allocatedWidths;
    private List<Double> allocatedHeights;
    private double contentWidth;
    private double contentHeight;
    private GridJustifyManager justifyManager;
    private GridAlignmentManager alignmentManager;
    private Map<Region, String> elementsSelfAlignment;
    private Map<Region, String> elementsSelfJustify;
    private Map<Region, Double> absoluteXPositions;
    private Map<Region, Double> absoluteYPositions;
    private Map<Region, ChangeListener<Number>> widthListeners;
    private Map<Region, ChangeListener<Number>> heightListeners;
    private Map<Region, MarginListener> marginListeners;
    private Map<Region, Double> percentageWidthElements;
    private Map<Region, Double> percentageHeightElements;

    public Grid(Pane container) {
        this.container = container;
        this.width = 0;
        this.height = 0;
        this.children = 0;
        this.rowGap = 0;
        this.columnGap = 0;
        this.padding = new Padding(0, 0, 0, 0);
        this.itemsAlignment = Alignment.CENTER.alignment();
        this.itemsJustify = Justify.CENTER.justify();
        this.contentWidth = 0;
        this.contentHeight = 0;
        this.rowSpaceAllocator = new RowSpaceAllocator();
        this.columnSpaceAllocator = new ColumnSpaceAllocator();
        this.justifyManager = new GridJustifyManager();
        this.alignmentManager = new GridAlignmentManager();
        this.gridTemplateRows = "";
        this.gridTemplateColumns = "";
        this.rows = 0;
        this.columns = 0;
        this.elementsCapacity = 0;
        this.elementsSelfAlignment = new HashMap<>();
        this.elementsSelfJustify = new HashMap<>();
        this.absoluteXPositions = new HashMap<>();
        this.absoluteYPositions = new HashMap<>();
        this.widthListeners = new HashMap<>();
        this.heightListeners = new HashMap<>();
        this.marginListeners = new HashMap<>();
        this.percentageWidthElements = new HashMap<>();
        this.percentageHeightElements = new HashMap<>();
    }

    public void add(Region element) {
        if (children < elementsCapacity && element != null) {
            children++;
            UI.add(container, element);
            int elementRow = getElementRowOnAdd();
            int elementColumn = getElementColumnOnAdd();
            calculateMaxWidthChildOnAdd(element, elementColumn);
            calculateMaxHeightChildOnAdd(element, elementRow);
            alignmentManager.setGridAllocatedHeights(allocatedHeights);
            alignmentManager.alignElementOnAdd(element, elementRow);
            justifyManager.setGridAllocatedWidths(allocatedWidths);
            justifyManager.justifyElementOnAdd(element, elementColumn);
            calculateContentWidth();
            calculateContentHeight();
            addMarginListener(element);
            addElementResizeListener(element);
        }
    }

    /**
     * Set a margin change listener for the new element to be able to be
     * reordered(re-aligned or re-justified) on changing its margin values.
     */
    private void addMarginListener(Region element) {
        MarginListener marginListener = (margin) -> {
            String selfAlign = getSelfAlignment(element);
            if(selfAlign != null){
                alignSelf(element, selfAlign);
            }
            else{
                alignSelf(element, itemsAlignment);
            }
            String justifySelf = getSelfJustify(element);
            if(justifySelf != null){
                justifySelf(element, justifySelf);
            }
            else{
                justifySelf(element, itemsJustify);
            }
        };
        MarginSetter.addListener(element, marginListener);
        marginListeners.put(element, marginListener);
    }

    /**
     * Returns the sum of all allocated widths
     */
    private double allocatedWidthsSum(){
        double sum = 0;
        for(int i = 0; i < allocatedWidths.size(); i++){
            sum+= allocatedWidths.get(i);
        }
        return sum;
    }

    /**
     * Returns the sum of all allocated heights
     */
    private double allocatedHeightsSum(){
        double sum = 0;
        for(int i = 0; i < allocatedHeights.size(); i++){
            sum+= allocatedHeights.get(i);
        }
        return sum;
    }

    /**
     * Set a width and height change listener for the new element to be able to be
     * reordered(re-aligned or re-justified) on changing its width and height values.
     */
    private void addElementResizeListener(Region element) {
        ChangeListener<Number> widthListener = (property, oldVal, newVal) -> {
            String selfJustify = getSelfJustify(element);
            int elCol = getElementColumn(element) - 1;
            if((double)newVal > maxWidthChildOnColumns.get(elCol)){
                maxWidthChildOnColumns.set(elCol, (double) newVal);
                if((double)newVal > allocatedWidths.get(elCol)){
                    for(int i = 0; i < allocatedWidths.size(); i++){
                        allocatedWidths.set(i, (double) newVal);
                    }
                }
                justifyManager.setGridAllocatedWidths(allocatedWidths);
                calculateContentWidth();
                double allocatedWidthsSum = allocatedWidthsSum();
                if(allocatedWidthsSum > container.getPrefWidth()){
                    container.setPrefWidth(allocatedWidthsSum);
                }
                justifyElements();
            }
            else{
                if(selfJustify != null){
                    justifySelf(element, selfJustify);
                }
                else{
                    justifySelf(element, itemsJustify);
                }
            }
        };
        element.prefWidthProperty().addListener(widthListener);
        widthListeners.put(element, widthListener);

        ChangeListener<Number> heightListener = (property, oldVal, newVal) -> {
            String selfAlign = getSelfAlignment(element);
            int elRow = getElementRow(element) - 1;
            if((double)newVal > maxHeightChildOnRows.get(elRow)){
                maxHeightChildOnRows.set(elRow, (double) newVal);
                if((double)newVal > allocatedHeights.get(elRow)){
                    for(int i = 0; i < allocatedHeights.size(); i++){
                        allocatedHeights.set(i, (double) newVal);
                    }
                }
                alignmentManager.setGridAllocatedHeights(allocatedHeights);
                calculateContentHeight();
                double allocatedHeightsSum = allocatedHeightsSum();
                if(allocatedHeightsSum > container.getPrefHeight()){
                    container.setPrefHeight(allocatedHeightsSum);
                }
                alignElements();
            }
            else{
                if(selfAlign != null){
                    alignSelf(element, selfAlign);
                }
                else{
                    alignSelf(element, itemsAlignment);
                }
            }
        };
        element.prefHeightProperty().addListener(heightListener);
        heightListeners.put(element, heightListener);
    }

    /**
     * Return the container used for this grid object.
     */
    public Pane getContainer() {
        return container;
    }

    /**
     * Calculate the content width by adding the container left and right paddings,
     * the max width child from every column and the column gap between the elements.
     */
    private void calculateContentWidth() {
        contentWidth = 0;
        if (padding != null) {
            contentWidth += padding.getLeft() + padding.getRight();
        }
        for (int i = 0; i < maxWidthChildOnColumns.size(); i++) {
            if(columnsTemplate[i].endsWith("px")){
                contentWidth += Integer.parseInt(columnsTemplate[i].replaceAll("px", ""));
            }
            else{
                contentWidth += maxWidthChildOnColumns.get(i);
            }
            if (i > 0) {
                contentWidth += columnGap;
            }
        }
    }

    /**
     * Calculate the content height by adding the container top and bottom paddings,
     * the max height child from every row and the row gap between the elements.
     */
    private void calculateContentHeight() {
        contentHeight = 0;
        if (padding != null) {
            contentHeight += padding.getTop() + padding.getBottom();
        }
        for (int i = 0; i < maxHeightChildOnRows.size(); i++) {
            if(rowsTemplate[i].endsWith("px")){
                contentHeight += Integer.parseInt(rowsTemplate[i].replaceAll("px", ""));
            }
            else{
                contentHeight += maxHeightChildOnRows.get(i);
            }
            if (i > 0) {
                contentHeight += rowGap;
            }
        }
    }

    /**
     * Stores the highest child's width for the specific column
     */
    private void calculateMaxWidthChildOnAdd(Region element, int column) {
        if (element.getPrefWidth() > maxWidthChildOnColumns.get(column - 1)) {
            maxWidthChildOnColumns.set(column - 1, element.getPrefWidth());
        }
    }

    private void resetMaxWidthChild(){
        for(int i = 0 ; i < maxWidthChildOnColumns.size(); i++){
            maxWidthChildOnColumns.set(i, 0d);
        }
    }

    /**
     * Stores the highest child's height for the specific row
     */
    private void calculateMaxHeightChildOnAdd(Region element, int row) {
        if (element.getPrefHeight() > maxHeightChildOnRows.get(row - 1)) {
            maxHeightChildOnRows.set(row - 1, element.getPrefHeight());
        }
    }

    private void resetMaxHeightChild(){
        for(int i = 0 ; i < maxHeightChildOnRows.size(); i++){
            maxHeightChildOnRows.set(i, 0d);
        }
    }

    /**
     * Return the element row. It's used only when adding a new element.
     */
    private int getElementRowOnAdd() {
        if (children < columns + 1) {
            return 1;
        }
        return ((children - 1) / columns) + 1;
    }

    /**
     * Return the element column. It's used only when adding a new element.
     */
    private int getElementColumnOnAdd() {
        if (children <= columns) {
            return children;
        }
        return ((children - 1) % columns) + 1;
    }

    /**
     * Return the element row by passing the element itself as argument.
     */
    private int getElementRow(Region element) {
        int elementIndex = UI.getChildIndex(container, element);
        if (elementIndex < columns) {
            return 1;
        }
        return (elementIndex / columns) + 1;
    }

    /**
     * Return the element column by passing the element itself as argument.
     */
    private int getElementColumn(Region element) {
        int elementIndex = UI.getChildIndex(container, element);
        if (elementIndex < columns) {
            return elementIndex + 1;
        }
        return ((elementIndex) % columns) + 1;
    }

    public double getWidth() {
        return container.getPrefWidth();
    }

    /**
     * Set the width for the container and this grid object.
     */
    public void width(double width) {
        if (width >= contentWidth) {
            this.width = width;
            this.container.setPrefWidth(width);
            if (columnsTemplate != null) {
                this.allocatedWidths = columnSpaceAllocator.allocateColumnsSpace(this);
                justifyManager.setGridAllocatedWidths(allocatedWidths);
                justifyElements();
            }
        }
    }

    public double getHeight() {
        return container.getPrefHeight();
    }

    /**
     * Set the height for the container and this grid object.
     */
    public void height(double height) {
        if (height >= contentHeight) {
            this.height = height;
            this.container.setPrefHeight(height);
            if (rowsTemplate != null) {
                this.allocatedHeights = rowSpaceAllocator.allocateRowsSpace(this);
                alignmentManager.setGridAllocatedHeights(allocatedHeights);
                alignElements();
            }
        }
    }

    /**
     * Set the row gap and column gap.
     */
    public void gap(double gap) {
        if (gap >= 0) {
            rowGap(gap);
            columnGap(gap);
        }
    }

    /**
     * Return the row gap.
     */
    public double getRowGap() {
        return rowGap;
    }

    /**
     * Set the row gap.
     */
    public void rowGap(double rowGap) {
        if (rowGap >= 0) {
            this.rowGap = rowGap;
            rowSpaceAllocator.setRowGap(rowGap);
            this.allocatedHeights = rowSpaceAllocator.allocateRowsSpace(this);
            calculateContentHeight();
            alignmentManager.setGridAllocatedHeights(allocatedHeights);
            alignmentManager.setRowGap(rowGap);
            alignElements();
        }
    }

    /**
     * Return the column gap.
     */
    public double getColumnGap() {
        return columnGap;
    }

    /**
     * Set the column gap.
     */
    public void columnGap(double columnGap) {
        if (columnGap >= 0) {
            this.columnGap = columnGap;
            columnSpaceAllocator.setColGap(columnGap);
            this.allocatedWidths = columnSpaceAllocator.allocateColumnsSpace(this);
            calculateContentWidth();
            justifyManager.setGridAllocatedWidths(allocatedWidths);
            justifyManager.setColumnGap(columnGap);
            justifyElements();
        }
    }

    /**
     * Get the padding set for this grid object.
     */
    public Padding getPadding() {
        return padding;
    }

    /**
     * Set the padding for this grid object.
     */
    public void padding(double top, double right, double bottom, double left) {
        if (this.padding == null) {
            this.padding = new Padding(top, right, bottom, left);
        } else {
            this.padding.setTop(top);
            this.padding.setRight(right);
            this.padding.setBottom(bottom);
            this.padding.setLeft(left);
        }
        rowSpaceAllocator.setPadding(padding);
        columnSpaceAllocator.setPadding(padding);
        this.allocatedWidths = columnSpaceAllocator.allocateColumnsSpace(this);
        this.allocatedHeights = rowSpaceAllocator.allocateRowsSpace(this);
        calculateContentWidth();
        calculateContentHeight();
        alignmentManager.setGridAllocatedHeights(allocatedHeights);
        alignmentManager.setPadding(padding);
        justifyManager.setGridAllocatedWidths(allocatedWidths);
        justifyManager.setPadding(padding);
        alignElements();
        justifyElements();
    }

    /**
     * Set the padding for this grid object.
     */
    public void padding(double topAndBottom, double rightAndLeft) {
        padding(topAndBottom, rightAndLeft, topAndBottom, rightAndLeft);
    }

    /**
     * Set the padding for this grid object.
     */
    public void padding(double padding) {
        padding(padding, padding, padding, padding);
    }

    /**
     * Return the current items' alignment.
     */
    public String getItemsAlignment() {
        return itemsAlignment;
    }

    /**
     * Align all elements in this grid.
     */
    public void alignItems(String itemsAlignment) {
        if (ALIGNMENT.contains(itemsAlignment)) {
            this.itemsAlignment = itemsAlignment;
            alignmentManager.setGridItemsAlignment(itemsAlignment);
            alignElements();
        }
    }

    /**
     * Align the element in its own allocated space.
     */
    public void alignSelf(Region element, String alignment) {
        if (element != null) {
            int elementIndex = UI.getChildIndex(container, element);
            alignSelf(elementIndex, alignment);
        }
    }

    /**
     * Align the element in its own allocated space.
     */
    public void alignSelf(int elementIndex, String alignment) {
        Region element = (Region) UI.getChildByIndex(container, elementIndex);
        if (element != null && ALIGNMENT.contains(alignment)) {
            int elRow = getElementRow(element);
            elementsSelfAlignment.put(element, alignment);
            alignmentManager.alignSelf(element, elRow, alignment);
        }
    }

    /**
     * Returns the self alignment value for the specific element.
     */
    public String getSelfAlignment(Region element) {
        return elementsSelfAlignment.get(element);
    }

    /**
     * Set the specified element's absolute x position calculating the
     * x value as percentage from the grid's container prefWidth.
     * If the element is positioned absolute it won't be taken into
     * account on justify.
     */
    public void absX(Region element, double percentage) {
        if (element != null && UI.getChildren(container).contains(element)) {
            if (!absoluteXPositions.containsKey(element)) {
                absoluteXPositions.put(element, percentage);
                resetMaxWidthChild();
                List<Node> children = UI.getChildren(container);
                for (int i = 0; i < children.size(); i++) {
                    Region currEl = (Region) children.get(i);
                    if (!absoluteXPositions.containsKey(currEl)) {
                        int elCol = getElementColumn(currEl);
                        calculateMaxWidthChildOnAdd(currEl, elCol);
                        justifyManager.justifyElementOnAdd(currEl, elCol);
                        calculateContentWidth();
                    }
                }
                justifyElements();
            }
            absoluteXPositions.put(element, percentage);
            double x = (percentage / 100) * container.getPrefWidth() - element.getPrefWidth() / 2;
            element.setLayoutX(Math.abs(x));
        }
    }

    /**
     * Set the specified element's absolute y position calculating the
     * y value as percentage from the grid's container prefHeight.
     * If the element is positioned absolute it won't be taken into
     * account on alignment.
     */
    public void absY(Region element, double percentage){
        if(element != null && UI.getChildren(container).contains(element)){
            if(!absoluteYPositions.containsKey(element)){
                absoluteYPositions.put(element, percentage);
                resetMaxHeightChild();
                List<Node> children = UI.getChildren(container);
                for(int i = 0; i < children.size(); i++){
                    Region currEl = (Region) children.get(i);
                    if(!absoluteYPositions.containsKey(currEl)){
                        int elRow = getElementRow(currEl);
                        calculateMaxHeightChildOnAdd(currEl, elRow);
                        alignmentManager.alignElementOnAdd(currEl, elRow);
                        calculateContentHeight();
                    }
                }
                alignElements();
            }
            absoluteYPositions.put(element, percentage);
            double y = percentage / 100 * container.getPrefHeight() - element.getPrefHeight() / 2;
            element.setLayoutY(y);
        }
    }

    public void removeAbsPositioning(Region element){
        if(element != null && UI.getChildren(container).contains(element)){
            absoluteXPositions.remove(element);
            absoluteYPositions.remove(element);
            List<Node> children = UI.getChildren(container);
            for(int i = 0; i < children.size(); i++){
                Region currEl = (Region) children.get(i);
                int elRow = getElementRow(currEl);
                int elCol = getElementColumn(currEl);
                calculateMaxWidthChildOnAdd(currEl, elCol);
                calculateMaxHeightChildOnAdd(currEl, elRow);
                alignmentManager.alignElementOnAdd(currEl, elRow);
                justifyManager.justifyElementOnAdd(currEl, elCol);
                calculateContentWidth();
                calculateContentHeight();
            }
            alignElements();
            justifyElements();
        }
    }

    private void alignElements() {
        List<Node> elements = UI.getChildren(container);
        alignmentManager.resetParameters();
        for(int i = 0; i < elements.size(); i++){
            Region currEl = (Region) elements.get(i);
            if(!absoluteYPositions.containsKey(currEl)){
                int elRow = getElementRow(currEl);
                setElPercentageHeight(currEl, elRow);
                alignmentManager.alignElement(this, currEl, elRow);
            }
            else{
                absY(currEl, absoluteYPositions.get(currEl));
            }
        }
    }

    /**
     * Switch elements places in the grid
     */
    public void switchElements(Region el1, Region el2){
        List<Node> children = UI.getChildren(container);
        if(children.contains(el1) && children.contains(el2)){
            int el1Index = children.indexOf(el1);
            int el2Index = children.indexOf(el2);
            if(el1Index != el2Index){
                UI.switchElementsIndex(container, el1Index, el2Index);
                resetMaxHeightChild();
                resetMaxWidthChild();
                for (int i = 0; i < children.size(); i++) {
                    Region currEl = (Region) children.get(i);
                    if (!absoluteXPositions.containsKey(currEl) ||
                            !absoluteYPositions.containsKey(currEl)) {
                        int elRow = getElementRow(currEl);
                        int elCol = getElementColumn(currEl);
                        calculateMaxWidthChildOnAdd(currEl, elCol);
                        calculateMaxHeightChildOnAdd(currEl, elRow);
                        alignmentManager.alignElementOnAdd(currEl, elRow);
                        justifyManager.justifyElementOnAdd(currEl, elCol);
                        calculateContentWidth();
                        calculateContentHeight();
                    }
                }
                alignElements();
                justifyElements();
            }
        }
    }

    /**
     * Return the current items' justify.
     */
    public String getItemsJustify() {
        return itemsJustify;
    }

    /**
     * Justify all elements in this grid.
     */
    public void justifyItems(String itemsJustify) {
        if (JUSTIFY.contains(itemsJustify)) {
            this.itemsJustify = itemsJustify;
            justifyManager.setGridItemsJustify(itemsJustify);
            justifyElements();
        }
    }

    /**
     * Justify the element in its own allocated space.
     */
    public void justifySelf(Region element, String justify) {
        if (element != null) {
            int index = UI.getChildIndex(container, element);
            justifySelf(index, justify);
        }
    }

    /**
     * Justify the element in its own allocated space.
     */
    public void justifySelf(int elementIndex, String justify) {
        Region element = (Region) UI.getChildByIndex(container, elementIndex);
        if (element != null && JUSTIFY.contains(justify)) {
            int elCol = getElementColumn(element);
            elementsSelfJustify.put(element, justify);
            justifyManager.justifySelf(element, elCol, justify);
        }
    }

    /**
     * Returns the self justify value for the specific element.
     */
    public String getSelfJustify(Region element) {
        return elementsSelfJustify.get(element);
    }

    private void justifyElements() {
        List<Node> elements = UI.getChildren(container);
        justifyManager.resetParameters();
        for(int i = 0; i < elements.size(); i++){
            Region currEl = (Region) elements.get(i);
            if(!absoluteXPositions.containsKey(currEl)){
                int elCol = getElementColumn(currEl);
                setElPercentageWidth(currEl, elCol);
                justifyManager.justifyElement(this, currEl, elCol);
            }
            else{
                absX(currEl, absoluteXPositions.get(currEl));
            }
        }
    }

    private void initMaxWidthChildOnColumns() {
        maxWidthChildOnColumns = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            maxWidthChildOnColumns.add(0d);
        }
    }

    private void initMaxHeightChildOnRows() {
        maxHeightChildOnRows = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            maxHeightChildOnRows.add(0d);
        }
    }

    private void initAllocatedWidths() {
        allocatedWidths = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            allocatedWidths.add(0d);
        }
        columnSpaceAllocator.initAllocatedSpaces(columns);
    }

    private void initAllocatedHeights() {
        allocatedHeights = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            allocatedHeights.add(0d);
        }
        rowSpaceAllocator.initAllocatedSpaces(rows);
    }

    public int getRows() {
        return rows;
    }

    public String getGridTemplateRows() {
        return gridTemplateRows;
    }

    public void templateRows(String gridTemplateRows) {
        this.gridTemplateRows = gridTemplateRows;
        this.rowsTemplate = gridTemplateRows.split("\\s+");
        rowSpaceAllocator.setRowsTemplate(rowsTemplate);
        this.rows = rowsTemplate.length;
        this.elementsCapacity = rows * columns;
        if (elementsCapacity > 0) {
            initMaxWidthChildOnColumns();
            initMaxHeightChildOnRows();
            initAllocatedWidths();
            initAllocatedHeights();
            this.allocatedWidths = columnSpaceAllocator.allocateColumnsSpace(this);
            this.allocatedHeights = rowSpaceAllocator.allocateRowsSpace(this);
            alignmentManager.setGridAllocatedHeights(allocatedHeights);
            justifyManager.setGridAllocatedWidths(allocatedWidths);
            alignElements();
            justifyElements();
        }
    }

    public int getColumns() {
        return columns;
    }

    public String getGridTemplateColumns(){
        return gridTemplateColumns;
    }

    /**
     * The element width will be calculated as percentage from the allocated width
     * corresponding to that element. The element will be resized at the same time
     * its allocated width grows.
     */
    public void setElWidthPercent(Region element, double percent) {
        if (UI.getChildren(container).contains(element)) {
            percentageWidthElements.put(element, percent);
            setElPercentageWidth(element, getElementColumn(element));
            String selfJustify = getSelfJustify(element);
            int elCol = getElementColumn(element);
            if (selfJustify == null) {
                justifyManager.justifySelf(element, elCol, itemsJustify);
            } else {
                justifyManager.justifySelf(element, elCol, selfJustify);
            }
        }
    }

    /**
     * The element height will be calculated as percentage from the allocated height
     * corresponding to that element. The element will be resized at the same time
     * its allocated height grows.
     */
    public void setElHeightPercent(Region element, double percent) {
        if (UI.getChildren(container).contains(element)) {
            percentageHeightElements.put(element, percent);
            setElPercentageHeight(element, getElementRow(element));
            String selfAlignment = getSelfAlignment(element);
            int elRow = getElementRow(element);
            if (selfAlignment == null) {
                alignmentManager.alignSelf(element, elRow, itemsAlignment);
            } else {
                alignmentManager.alignSelf(element, elRow, selfAlignment);
            }
        }
    }

    /**
     * The element width will no longer be calculated as percentage from its allocated width.
     */
    public void removeWidthPercent(Region element) {
        percentageWidthElements.remove(element);
    }

    /**
     * The element height will no longer be calculated as percentage from its allocated height.
     */
    public void removeHeightPercent(Region element) {
        percentageHeightElements.remove(element);
    }

    private void setElPercentageWidth(Region element, int elCol) {
        if (percentageWidthElements.containsKey(element)) {
            double width = percentageWidthElements.get(element) / 100 * allocatedWidths.get(elCol - 1);
            element.setPrefWidth(width);
        }
    }

    private void setElPercentageHeight(Region element, int elRow) {
        if (percentageHeightElements.containsKey(element)) {
            double height = percentageHeightElements.get(element) / 100 * allocatedHeights.get(elRow - 1);
            element.setPrefHeight(height);
        }
    }

    public void templateColumns(String gridTemplateColumns) {
        this.gridTemplateColumns = gridTemplateColumns;
        this.columnsTemplate = gridTemplateColumns.split("\\s+");
        columnSpaceAllocator.setColsTemplate(columnsTemplate);
        this.columns = columnsTemplate.length;
        this.elementsCapacity = rows * columns;
        if (elementsCapacity > 0) {
            initMaxWidthChildOnColumns();
            initMaxHeightChildOnRows();
            initAllocatedWidths();
            initAllocatedHeights();
            this.allocatedWidths = columnSpaceAllocator.allocateColumnsSpace(this);
            this.allocatedHeights = rowSpaceAllocator.allocateRowsSpace(this);
            alignmentManager.setGridAllocatedHeights(allocatedHeights);
            justifyManager.setGridAllocatedWidths(allocatedWidths);
            alignElements();
            justifyElements();
        }
    }

    private final Set<String> ALIGNMENT = new HashSet<>(
            Set.of(
                    Alignment.START.alignment(),
                    Alignment.CENTER.alignment(),
                    Alignment.END.alignment()));


    private final Set<String> JUSTIFY = new HashSet<>(
            Set.of(
                    Justify.START.justify(),
                    Justify.CENTER.justify(),
                    Justify.END.justify()));
}
