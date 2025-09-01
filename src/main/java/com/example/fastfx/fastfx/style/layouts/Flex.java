package com.example.fastfx.fastfx.style.layouts;

import com.example.fastfx.fastfx.elements.utility.UI;
import com.example.fastfx.fastfx.style.utility.MarginListener;
import com.example.fastfx.fastfx.style.utility.MarginSetter;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.util.*;

/**
 * Flex layout with similar functionalities like the flex layout from web.
 */
public class Flex {

    private AnchorPane container;
    private double width;
    private double height;
    private double maxWidthChild;
    private double maxHeightChild;
    private Margin maxWidthChildMargin;
    private Margin maxHeightChildMargin;
    private double contentWidth;
    private double contentHeight;
    private double gap;
    private Padding padding;
    private String flexDirection;
    private String contentAlignment;
    private String contentJustify;
    private FlexJustifyContentManager justifyManager;
    private FlexAlignmentManager alignmentManager;
    private Map<Region, ChangeListener<Number>> widthListeners;
    private Map<Region, ChangeListener<Number>> heightListeners;
    private Map<Region, MarginListener> marginListeners;

    public Flex(AnchorPane container) {
        this.container = container;
        this.width = 0;
        this.height = 0;
        this.maxHeightChild = 0;
        this.maxWidthChild = 0;
        this.contentWidth = 0;
        this.contentHeight = 0;
        this.gap = 0;
        this.padding = new Padding(0);
        this.flexDirection = Direction.ROW.direction();
        this.contentAlignment = Alignment.CENTER.alignment();
        this.contentJustify = Justify.CENTER.justify();
        this.justifyManager = new FlexJustifyContentManager();
        this.alignmentManager = new FlexAlignmentManager();
        this.widthListeners = new HashMap<>();
        this.heightListeners = new HashMap<>();
        this.marginListeners = new HashMap<>();
    }

    /**
     * Return the container of this flex.
     */
    public AnchorPane getContainer(){
        return container;
    }

    public double getWidth() {
        return width;
    }

    /**
     * Set the width of the container.
     */
    public void setWidth(double width){
        this.width = width;
        this.container.setPrefWidth(width);
        this.contentWidth = 0;
        this.contentHeight = 0;
        UI.getChildren(container).forEach((el) -> {
            calculateContentHeightOnAdd((Region)el);
            calculateContentWidthOnAdd((Region)el);
        });
        setContainerWidth();
        setContainerHeight();
        alignElements();
        justifyElements();
    }

    public double getHeight() {
        return height;
    }

    /**
     * Set the height of the container.
     */
    public void setHeight(double height){
        this.height = height;
        this.container.setPrefHeight(height);
        this.contentWidth = 0;
        this.contentHeight = 0;
        UI.getChildren(container).forEach((el) -> {
            calculateContentHeightOnAdd((Region)el);
            calculateContentWidthOnAdd((Region)el);
        });
        setContainerWidth();
        setContainerHeight();
        alignElements();
        justifyElements();
    }

    public String getFlexDirection(){
        return flexDirection;
    }

    /**
     * Set the flex direction(row or column where row is for horizontal orientation
     * and column is for vertical orientation). It takes a String as the direction
     * value.
     */
    public void setFlexDirection(String direction){
        if(DIRECTION.contains(direction)){
            this.flexDirection = direction;
        }
        this.contentWidth = 0;
        this.contentHeight = 0;
        UI.getChildren(container).forEach((el) -> {
            calculateContentHeightOnAdd((Region)el);
            calculateContentWidthOnAdd((Region)el);
        });
        setContainerWidth();
        setContainerHeight();
        alignElements();
        justifyElements();
    }

    public double getGap(){
        return gap;
    }

    /**
     * Set the gap between elements. Is ignored when contentJustify is set to
     * space-between, space-around or space-evenly.
     */
    public void setGap(double gap){
        if(gap >= 0){
            this.gap = gap;
            this.contentWidth = 0;
            this.contentHeight = 0;
            UI.getChildren(container).forEach((el) -> {
                calculateContentHeightOnAdd((Region)el);
                calculateContentWidthOnAdd((Region)el);
            });
            setContainerWidth();
            setContainerHeight();
            alignElements();
            justifyElements();
        }
    }

    public Padding getPadding(){
        return padding;
    }

    /**
     * Set the padding for this flex container.
     */
    public void setPadding(double top, double right, double bottom, double left){
        if(this.padding == null){
            this.padding = new Padding(top, right, bottom, left);
        }
        else{
            this.padding.setTop(top);
            this.padding.setRight(right);
            this.padding.setBottom(bottom);
            this.padding.setLeft(left);
        }
        this.contentWidth = 0;
        this.contentHeight = 0;
        UI.getChildren(container).forEach((el) -> {
            calculateContentHeightOnAdd((Region)el);
            calculateContentWidthOnAdd((Region)el);
        });
        setContainerWidth();
        setContainerHeight();
        alignElements();
        justifyElements();
    }

    public void setPadding(double topAndBottom, double rightAndLeft){
        setPadding(topAndBottom, rightAndLeft, topAndBottom, rightAndLeft);
    }

    public void setPadding(double padding){
        setPadding(padding, padding, padding, padding);
    }

    public String getAlignment(){
        return this.contentAlignment;
    }

    /**
     * Align the content on y-axis (vertically) if the flexDirection is set to 'column'
     * else will align the content on x-axis (horizontally). It takes a String as the
     * alignment value.
     */
    public void alignContent(String contentAlignment){
        if(ALIGNMENT.contains(contentAlignment)){
            this.contentAlignment = contentAlignment;
            alignElements();
        }
    }

    /**
     * Align the content on y-axis (vertically) if the flexDirection is set to 'column'
     * else will align the content on x-axis (horizontally). It takes an Alignment enum as the
     * alignment value.
     */
    public void alignContent(Alignment alignment){
        this.contentAlignment = alignment.alignment();
    }

    public String getJustify(){
        return this.contentJustify;
    }

    /**
     * Justify the content on x-axis (horizontally) if the flexDirection is set to 'row'
     * else will justify the content on y-axis (vertically). It takes
     * a String as the justify value.
     */
    public void justifyContent(String contentJustify){
        if(JUSTIFY.contains(contentJustify)){
            this.contentJustify = contentJustify;
            justifyElements();
        }
    }

    /**
     * Justify the content on x-axis (horizontally) if the flexDirection is set to 'row'
     * else will justify the content on y-axis (vertically). Is takes a Justify enum
     * as the justify value.
     */
    public void justifyContent(Justify justify){
        this.contentJustify = justify.justify();
        justifyElements();
    }

    /**
     * Add a new element at the end of the container.
     */
    public void addElement(Region element){
        calculateMaxDimensionsChild(element);
        calculateContentWidthOnAdd(element);
        calculateContentHeightOnAdd(element);
        setContainerWidth();
        setContainerHeight();
        UI.add(container, element);
        alignElements();
        justifyElements();
        addElementResizeListener(element);
        addElementMarginListener(element);
    }

    /**
     * Add a new element at a specified index in the container.
     */
    public void addElement(int location, Region element){
        calculateMaxDimensionsChild(element);
        calculateContentWidthOnAdd(element);
        calculateContentHeightOnAdd(element);
        setContainerWidth();
        setContainerHeight();
        UI.add(location, container, element);
        alignElements();
        justifyElements();
        addElementResizeListener(element);
        addElementMarginListener(element);
    }

    /**
     * Add a new element at a specified position(first or last) in the container.
     */
    public void addElement(String position, Region element){
        calculateMaxDimensionsChild(element);
        calculateContentWidthOnAdd(element);
        calculateContentHeightOnAdd(element);
        setContainerWidth();
        setContainerHeight();
        UI.add(position, container, element);
        alignElements();
        justifyElements();
        addElementResizeListener(element);
        addElementMarginListener(element);
    }

    /**
     * Add prefWidthProperty listener and prefHeightProperty listener for the
     * new added element to make the flex to recalculate the dimensions of the content
     * and of the container and reorder the elements when one of the elements'
     * width or height are changed.
     */
    private void addElementResizeListener(Region element){
        ChangeListener<Number> widthListener = (property, oldVal , newVal) ->{
            this.contentWidth = 0;
            this.contentHeight = 0;
            UI.getChildren(container).forEach((el) -> {
                calculateContentWidthOnAdd((Region)el);
            });
            setContainerWidth();
            alignElements();
            justifyElements();
        };
        element.prefWidthProperty().addListener(widthListener);
        widthListeners.put(element, widthListener);

        ChangeListener<Number> heightListener = (property, oldVal , newVal) ->{
            this.contentWidth = 0;
            this.contentHeight = 0;
            UI.getChildren(container).forEach((el) -> {
                calculateContentHeightOnAdd((Region)el);
            });
            setContainerHeight();
            alignElements();
            justifyElements();
        };
        element.prefHeightProperty().addListener(heightListener);
        heightListeners.put(element, heightListener);
    }

    private void removeElementResizeListener(Region element){
        ChangeListener<Number> widthListener = widthListeners.get(element);
        element.prefWidthProperty().removeListener(widthListener);
        ChangeListener<Number> heightListener = heightListeners.get(element);
        element.prefHeightProperty().removeListener(heightListener);

        widthListeners.remove(element);
        heightListeners.remove(element);
    }

    /**
     * Add a margin listener for the new added element to make the flex
     * to recalculate the dimensions of the content and of the container
     * and reorder the elements when one of the elements' margin values are changed.
     */
    private void addElementMarginListener(Region element){
        MarginListener marginListener = margin -> {
            this.contentWidth = 0;
            this.contentHeight = 0;
            UI.getChildren(container).forEach((el) -> {
                calculateContentWidthOnAdd((Region)el);
                calculateContentHeightOnAdd((Region)el);
            });
            setContainerHeight();
            alignElements();
            justifyElements();
        };
        MarginSetter.addListener(element, marginListener);
        marginListeners.put(element, marginListener);
    }

    /**
     * Remove the last element from the container.
     */
    public void removeElement(Region element){
        boolean wasRemoved = UI.remove(container, element);
        if(wasRemoved){
            remove(element);
        }
    }

    /**
     * Remove the element from the container from the specified index.
     */
    public void removeElement(int index){
        Region child = (Region)UI.remove(container, index);
        if(child != null){
            remove(child);;
        }
    }

    /**
     * Remove the element from the container from the specified position(first or last).
     */
    public void removeElement(String position){
        Region child = (Region)UI.remove(position, container);
        if(child != null){
            remove(child);
        }
    }

    /**
     * Remove the element from the container by its fx id.
     */
    public void removeElementById(String id){
        Region child = (Region)UI.getChildById(container, id);
        if(child != null){
            remove(child);
        }
    }

    /**
     * Remove the last element from the container.
     */
    private void remove(Region child){
        calculateMaxDimensionsChildOnRemove(child);
        calculateContentWidthOnRemove(child);
        calculateContentHeightOnRemove(child);
        setContainerWidth();
        setContainerHeight();
        alignElements();
        justifyElements();
        MarginSetter.removeMargin(child);
        removeElementResizeListener(child);
    }

    /**
     * Align all elements by the specified align value.
     */
    private void alignElements(){
        List<Node> children = UI.getChildren(this.container);
        for(int i = 0; i < children.size(); i++){
            alignmentManager.alignElement(flexDirection, (Region) children.get(i), container, padding, contentAlignment);
        }
    }

    /**
     * Justify all elements by the specified justify value.
     */
    private void justifyElements(){
        List<Node> elements = UI.getChildren(this.container);
        for(int i = 0; i < elements.size(); i++){
            Node element = elements.get(i);
            if(contentJustify.equals(Justify.START.justify())){
                justifyManager.justifyStart(i, (Region)element, this);
            }
            if(contentJustify.equals(Justify.CENTER.justify())){
                justifyManager.justifyCenter(i, (Region)element, this);
            }
            if(contentJustify.equals(Justify.END.justify())){
                justifyManager.justifyEnd(i, (Region)element, this);
            }
            if(contentJustify.equals(Justify.SPACE_BETWEEN.justify())){
                justifyManager.spaceBetween(i, (Region)element, this);
            }
            if(contentJustify.equals(Justify.SPACE_AROUND.justify())){
                justifyManager.spaceAround(i, (Region)element, this);
            }
            if(contentJustify.equals(Justify.SPACE_EVENLY.justify())){
                justifyManager.spaceEvenly(i, (Region)element, this);
            }
        }
    }

    public double getContentWidth(){
        return contentWidth;
    }

    /**
     * Recalculate the content width and container width when an element is removed.
     */
    private void calculateContentWidthOnRemove(Region element){
        double mr = 0;
        double ml = 0;
        Margin margin = MarginSetter.getMargin(element);
        if(margin != null){
            mr = margin.getRight();
            ml = margin.getLeft();
        }
        if(flexDirection.equals(Direction.ROW.direction())){
            if(container.getChildren().size() == 0){
                this.contentWidth -= Math.abs(ml + element.getPrefWidth() + mr);
            }
            else if(container.getChildren().size() > 0){
                if(this.contentJustify.equals(Justify.SPACE_BETWEEN.justify()) ||
                        this.contentJustify.equals(Justify.SPACE_AROUND.justify()) ||
                        this.contentJustify.equals(Justify.SPACE_EVENLY.justify())){
                    this.contentWidth -= Math.abs(ml + element.getPrefWidth() + mr);
                }
                else{
                    this.contentWidth -= Math.abs(this.gap + ml + element.getPrefWidth() + mr);
                }
            }
        }
        else{
            this.contentWidth = maxWidthChild;
            if(maxWidthChildMargin != null){
                this.contentWidth += maxWidthChildMargin.getLeft();
                this.contentWidth += maxWidthChildMargin.getRight();
            }
        }
    }

    public double getContentHeight(){
        return contentHeight;
    }

    /**
     * Recalculate the content height and container height when an element is removed.
     */
    private void calculateContentHeightOnRemove(Region element){
        double mt = 0;
        double mb = 0;
        Margin margin = MarginSetter.getMargin(element);
        if(margin != null){
            mt = margin.getTop();
            mb = margin.getBottom();
        }
        if(flexDirection.equals(Direction.COLUMN.direction())){
            if(container.getChildren().size() == 0){
                this.contentHeight -= Math.abs(mt + element.getPrefHeight() + mb);
            }
            else if(container.getChildren().size() > 0){
                if(this.contentJustify.equals(Justify.SPACE_BETWEEN.justify()) ||
                        this.contentJustify.equals(Justify.SPACE_AROUND.justify()) ||
                        this.contentJustify.equals(Justify.SPACE_EVENLY.justify())){
                    this.contentHeight -= Math.abs(mt + element.getPrefHeight() + mb);
                }
                else{
                    this.contentHeight -= Math.abs(this.gap + mt + element.getPrefHeight() + mb);
                }
            }
        }
        else{
            this.contentHeight = maxHeightChild;
            if(maxHeightChildMargin != null){
                this.contentHeight += maxHeightChildMargin.getTop();
                this.contentHeight += maxHeightChildMargin.getBottom();
            }
        }
    }

    /**
     * Calculate the content width when a new element is added.
     * Is also used in other methods to recalculate the dimensions of the
     * content and of the container when changing the alignment, justify, orientation etc.
     */
    private void calculateContentWidthOnAdd(Region element){
        double mr = 0;
        double ml = 0;
        Margin margin = MarginSetter.getMargin(element);
        if(margin != null){
            mr = margin.getRight();
            ml = margin.getLeft();
        }
        if(flexDirection.equals(Direction.ROW.direction())){
            if(container.getChildren().size() == 0 || container.getChildren().indexOf(element) == 0){
                this.contentWidth += Math.abs(ml + element.getPrefWidth() + mr);
            }
            else{
                if(this.contentJustify.equals(Justify.SPACE_BETWEEN.justify()) ||
                        this.contentJustify.equals(Justify.SPACE_AROUND.justify()) ||
                        this.contentJustify.equals(Justify.SPACE_EVENLY.justify())){
                    this.contentWidth += Math.abs(ml + element.getPrefWidth() + mr);
                }
                else{
                    this.contentWidth += Math.abs(this.gap + ml + element.getPrefWidth() + mr);
                }
            }
        }
        else{
            this.contentWidth = maxWidthChild;
            if(maxWidthChildMargin != null){
                this.contentWidth += maxWidthChildMargin.getLeft();
                this.contentWidth += maxWidthChildMargin.getRight();
            }
        }
    }

    /**
     * Calculate the content height when a new element is added.
     * Is also used in other methods to recalculate the dimensions of the
     * content and of the container when changing the alignment, justify, orientation etc.
     */
    private void calculateContentHeightOnAdd(Region element){
        double mt = 0;
        double mb = 0;
        Margin margin = MarginSetter.getMargin(element);
        if(margin != null){
            mt = margin.getTop();
            mb = margin.getBottom();
        }
        if(flexDirection.equals(Direction.COLUMN.direction())){
            if(container.getChildren().size() == 0 || container.getChildren().indexOf(element) == 0){
                this.contentHeight += Math.abs(mt + element.getPrefHeight() + mb);
            }
            else{
                if(this.contentJustify.equals(Justify.SPACE_BETWEEN.justify()) ||
                        this.contentJustify.equals(Justify.SPACE_AROUND.justify()) ||
                        this.contentJustify.equals(Justify.SPACE_EVENLY.justify())){
                    this.contentHeight += Math.abs(mt + element.getPrefHeight() + mb);
                }
                else{
                    this.contentHeight += Math.abs(this.gap + mt + element.getPrefHeight() + mb);
                }
            }
        }
        else{
            this.contentHeight = maxHeightChild;
            if(maxHeightChildMargin != null){
                this.contentHeight += maxHeightChildMargin.getTop();
                this.contentHeight += maxHeightChildMargin.getBottom();
            }
        }
    }

    /**
     * Set the container width by adding the left and right padding and content width
     */
    private void setContainerWidth(){
        double containerWidth = Math.abs(padding.getLeft() + this.contentWidth + padding.getRight());
        if(containerWidth < this.width){
            this.container.setPrefWidth(this.width);
        }
        else{
            this.container.setPrefWidth(containerWidth);
        }
    }

    /**
     * Set the container width by adding the top and bottom padding and content height
     */
    private void setContainerHeight(){
        double containerHeight = Math.abs(padding.getTop() + this.contentHeight + padding.getBottom());
        if(containerHeight < this.height){
            this.container.setPrefHeight(this.height);
        }
        else{
            this.container.setPrefHeight(containerHeight);
        }
    }

    /**
     * Find the max height and max width from all children by comparing the new added element
     * dimensions with maxWidthChild and maxHeightChild to make the container wrap the entire content
     * properly. The content's height will be maxHeightChild + its top and bottom margin when the orientation
     * is set to row so the container height will be this value plus the padding if the set height
     * value is less or equal, else the container height will remain unchanged. Same for maxChildWidth.
     * It also stores that element's margin.
     */
    private void calculateMaxDimensionsChild(Region region){
        if(region.getPrefWidth() > this.maxWidthChild){
            this.maxWidthChild = region.getPrefWidth();
            Margin margin = MarginSetter.getMargin(region);
            if(margin != null){
                this.maxWidthChildMargin = margin;
            }
        }
        if(region.getPrefHeight() > this.maxHeightChild){
            this.maxHeightChild = region.getPrefHeight();
            Margin margin = MarginSetter.getMargin(region);
            if(margin != null){
                this.maxHeightChildMargin = margin;
            }
        }
    }

    /**
     * Calculate the max width and max height by comparing maxWidthChild and maxHeightChild
     * with all elements from the container after the chosen element to remove was removed
     * to make the container fit the entire content
     */
    private void calculateMaxDimensionsChildOnRemove(Region element){
        if(element.getPrefWidth() == maxWidthChild || element.getPrefHeight() == maxHeightChild){
            resetMaxDimensionChild();
            container.getChildren().forEach(el -> {
                Region currEl = (Region)el;
                calculateMaxDimensionsChild(currEl);
            });
        }
    }

    private void resetMaxDimensionChild(){
        this.maxWidthChild = 0;
        this.maxHeightChild = 0;
    }

    private final Set<String> DIRECTION = new HashSet<>(
            Set.of(Direction.ROW.direction(), Direction.COLUMN.direction())
    );

    private final Set<String> ALIGNMENT = new HashSet<>(
            Set.of(
                    Alignment.START.alignment(),
                    Alignment.CENTER.alignment(),
                    Alignment.END.alignment()));


    private final Set<String> JUSTIFY = new HashSet<>(
            Set.of(
                    Justify.START.justify(),
                    Justify.CENTER.justify(),
                    Justify.END.justify(),
                    Justify.SPACE_BETWEEN.justify(),
                    Justify.SPACE_AROUND.justify(),
                    Justify.SPACE_EVENLY.justify()));

}
