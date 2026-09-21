package com.example.fastfx.src.fastfxcomponents.fdropdownlist;

import com.example.fastfx.src.fastfxcomponents.FLabeled;
import com.example.fastfx.src.fastfxcomponents.PseudoElement;
import com.example.fastfx.src.fastfxcomponents.PseudoElements;
import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.layouts.Padding;
import com.example.fastfx.src.stylehelpers.ComponentStyleTracker;
import com.example.fastfx.src.stylehelpers.MainProps;
import com.example.fastfx.src.util.color.ColorUtil;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.FontUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import com.example.fastfx.src.util.ui.UIUtil;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.ScrollBarSkin;
import javafx.scene.control.skin.ScrollPaneSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FDropdownList<T> extends Pane implements FLabeled {
    private Margin margin;
    private static final double DEFAULT_WIDTH = 120;
    private static final double DEFAULT_HEADER_HEIGHT = 20;
    private static final double DEFAULT_LIST_ITEMS_HEIGHT = 20;
    private static final double DEFAULT_ICONS_DIMENSION_PERCENTAGE = 0.75;

    private BooleanProperty displayCurrentSelectedItemProperty;
    private BooleanProperty displayPromptTextProperty;
    private BooleanProperty displaySelectedItemIconProperty;
    private BooleanProperty displayItemsIconProperty;
    private BooleanProperty listIsOpenedProperty;
    private double selectedItemIconPaneWidth;
    private double itemIconPaneWidth;
    private double itemsSpacing;
    private DoubleProperty contentHeightProperty;
    private double headerIconDimensionPercent;
    private double openButtonIconDimensionPercent;
    private double selectedItemIconDimensionPercent;
    private double itemIconDimensionPercent;
    private double expandHeight;
    private Padding headerPadding;

    private String promptText;
    private String listHeaderButtonText;

    // FDropdownList header-related elements
    private Pane listHeader;
    private Pane headerIconPane;
    private Pane headerPaneIcon;
    private ImageView imageViewHeaderIcon;
    private Pane selectedItemPane;
    private Pane openButtonPane;
    private Pane openButtonPaneIcon;
    private ImageView openButtonImageViewIcon;
    private Label listHeaderSelectedItemLabel;
    private Label promptLabel;

    // FDropdownList list-related elements
    private ObjectProperty<T> selectedValueProperty;
    private ScrollPane listContentPane;
    private Pane listContent;
    private List<T> itemsValues;
    private List<Pane> listItemPanes;
    private List<Node> nodeItems;
    private List<Pane> selectedItemIconPanes;
    private List<Pane> itemIconPanes;
    private List<Pane> itemPaneIcons;
    private List<ImageView> itemImageViewIcons;
    private Pane selectedItemPaneIcon;
    private ImageView selectedItemImageViewIcon;

    // ScrollPane(listContentPane)-related elements
    private ScrollBar verticalScrollBar;
    private ScrollBar horizontalScrollBar;
    private Region verticalThumb;
    private Region horizontalThumb;
    private Region incrementVButton;
    private Region decrementVButton;
    private Region incrementHButton;
    private Region decrementHButton;
    private Region incrementVArrow;
    private Region decrementVArrow;
    private Region incrementHArrow;
    private Region decrementHArrow;
    private StackPane corner;

    private ComponentStyleTracker styleTracker;

    public Pane getContent () {
        return listContent;
    }

    public FDropdownList () {
        this.margin = new Margin(0);
        this.displayCurrentSelectedItemProperty = new SimpleBooleanProperty(false);
        this.displayPromptTextProperty = new SimpleBooleanProperty(true);
        this.displaySelectedItemIconProperty = new SimpleBooleanProperty(false);
        this.displayItemsIconProperty = new SimpleBooleanProperty(false);
        this.listIsOpenedProperty = new SimpleBooleanProperty(false);
        this.selectedValueProperty = new SimpleObjectProperty<>();
        this.itemsSpacing = 0;
        this.contentHeightProperty = new SimpleDoubleProperty(0);
        this.headerIconDimensionPercent = DEFAULT_ICONS_DIMENSION_PERCENTAGE;
        this.openButtonIconDimensionPercent = DEFAULT_ICONS_DIMENSION_PERCENTAGE;
        this.selectedItemIconDimensionPercent = DEFAULT_ICONS_DIMENSION_PERCENTAGE;
        this.itemIconDimensionPercent = DEFAULT_ICONS_DIMENSION_PERCENTAGE;
        this.expandHeight = 0;
        this.headerPadding = new Padding(0);

        this.itemsValues = new ArrayList<>();
        this.nodeItems = new ArrayList<>();
        this.listItemPanes = new ArrayList<>();
        this.selectedItemIconPanes = new ArrayList<>();
        this.itemIconPanes = new ArrayList<>();
        this.itemPaneIcons = new ArrayList<>();
        this.itemImageViewIcons = new ArrayList<>();
        this.styleTracker = new ComponentStyleTracker();
        initElements();
        initAllowedStyleProperties();
        initElementsConfiguration();
        initElementsBehavior();
    }

    private void initElements () {
        this.listHeader = new Pane();
        this.headerIconPane = new Pane();
        this.headerPaneIcon = new Pane();
        this.imageViewHeaderIcon = null;
        this.selectedItemPane = new Pane();
        this.openButtonPane = new Pane();
        this.openButtonPaneIcon = new Pane();
        this.openButtonImageViewIcon = null;
        this.listHeaderSelectedItemLabel = new Label();
        this.promptLabel = new Label();
        this.listContentPane = new ScrollPane();
        this.listContent = new Pane();
        listContentPane.setSkin(new ScrollPaneSkin(listContentPane));
        Set<Node> scrollBars = listContentPane.lookupAll(".scroll-bar");
        scrollBars.forEach(el -> {
            ScrollBar currScrollBar = (ScrollBar) el;
            if (currScrollBar.getOrientation() == Orientation.VERTICAL) {
                this.verticalScrollBar = currScrollBar;
            }
            else if (currScrollBar.getOrientation() == Orientation.HORIZONTAL) {
                this.horizontalScrollBar = currScrollBar;
            }
        });
        this.verticalScrollBar.setSkin(new ScrollBarSkin(verticalScrollBar));
        this.horizontalScrollBar.setSkin(new ScrollBarSkin(horizontalScrollBar));
        this.verticalThumb = (Region) verticalScrollBar.lookup(".thumb");
        this.horizontalThumb = (Region) horizontalScrollBar.lookup(".thumb");
        this.incrementVButton = (Region) verticalScrollBar.lookup(".increment-button");
        this.decrementVButton = (Region) verticalScrollBar.lookup(".decrement-button");
        this.incrementHButton = (Region) horizontalScrollBar.lookup(".increment-button");
        this.decrementHButton = (Region) horizontalScrollBar.lookup(".decrement-button");
        this.incrementVArrow = (Region) verticalScrollBar.lookup(".increment-arrow");
        this.decrementVArrow = (Region) verticalScrollBar.lookup(".decrement-arrow");
        this.incrementHArrow = (Region) horizontalScrollBar.lookup(".increment-arrow");
        this.decrementHArrow = (Region) horizontalScrollBar.lookup(".decrement-arrow");
        this.corner = (StackPane) listContentPane.lookup(".corner");

        UIUtil.add(listHeader, headerIconPane);
        UIUtil.add(listHeader, selectedItemPane);
        UIUtil.add(listHeader, openButtonPane);
        UIUtil.add(selectedItemPane, listHeaderSelectedItemLabel);
        UIUtil.add(selectedItemPane, promptLabel);
        UIUtil.add(openButtonPane, openButtonPaneIcon);
        UIUtil.add(this, listHeader);
        UIUtil.add(this, listContentPane);
        listContentPane.setContent(listContent);

        BackgroundUtil.setBackgroundColor(this, Color.TRANSPARENT);
    }

    /**
     * Initializes the elements layout behavior based on changing the entire component width<br>
     * and the list header elements layout behavior based on changing the listHeader height.
     */
    private void initElementsConfiguration () {
        prefWidthProperty().addListener((prop, oldVal, newVal) -> {
            listHeader.setPrefWidth((double) newVal);
            double selectedItemPaneWidth;
            if (headerIconPane.isVisible() && openButtonPane.isVisible()) {
                selectedItemPaneWidth = Math.abs((double) newVal - headerIconPane.getPrefWidth() - openButtonPane.getPrefWidth());
            }
            else if (headerIconPane.isVisible()) {
                selectedItemPaneWidth = Math.abs((double) newVal - headerIconPane.getPrefWidth());
            }
            else if (openButtonPane.isVisible()) {
                selectedItemPaneWidth = Math.abs((double) newVal - openButtonPane.getPrefWidth());
            }
            else {
                selectedItemPaneWidth = Math.abs((double) newVal);
            }
            selectedItemPaneWidth = Math.abs(selectedItemPaneWidth - headerPadding.getLeft() - headerPadding.getRight());
            selectedItemPane.setPrefWidth(selectedItemPaneWidth);
            listHeaderSelectedItemLabel.setPrefWidth(selectedItemPaneWidth);
            promptLabel.setPrefWidth(selectedItemPaneWidth);
            openButtonPane.setLayoutX(selectedItemPane.getLayoutX() + selectedItemPane.getPrefWidth());

            listContentPane.setPrefWidth((double) newVal);
            if (verticalScrollBar.isVisible()) {
                listContent.setPrefWidth((double) newVal - getVScrollBar().getPrefWidth());
            }
            else {
                listContent.setPrefWidth((double) newVal);
            }
            listItemPanes.forEach(itemPane -> {
                itemPane.setPrefWidth(listContent.getPrefWidth());
            });
        });
        listHeader.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            headerIconPane.setPrefWidth((double) newVal);
            headerIconPane.setPrefHeight((double) newVal);
            selectedItemPane.setPrefHeight((double) newVal);
            openButtonPane.setPrefWidth((double) newVal);
            openButtonPane.setPrefHeight((double) newVal);
            if (openButtonPaneIcon != null) {
                openButtonPaneIcon.setLayoutX(Math.abs((double) newVal / 2 - openButtonPane.getPrefWidth() / 2));
                openButtonPaneIcon.setLayoutY(Math.abs((double) newVal / 2 - openButtonPane.getPrefHeight() / 2));
            }
            else if (openButtonImageViewIcon != null) {
                openButtonImageViewIcon.setLayoutX(Math.abs((double) newVal / 2 - openButtonPane.getPrefWidth() / 2));
                openButtonImageViewIcon.setLayoutY(Math.abs((double) newVal / 2 - openButtonPane.getPrefHeight() / 2));
            }
            double selectedItemPaneWidth;
            if (headerIconPane.isVisible() && openButtonPane.isVisible()) {
                selectedItemPaneWidth = Math.abs((double) newVal - headerIconPane.getPrefWidth() - openButtonPane.getPrefWidth());
            }
            else if (headerIconPane.isVisible()) {
                selectedItemPaneWidth = Math.abs((double) newVal - headerIconPane.getPrefWidth());
            }
            else if (openButtonPane.isVisible()) {
                selectedItemPaneWidth = Math.abs((double) newVal - openButtonPane.getPrefWidth());
            }
            else {
                selectedItemPaneWidth = Math.abs((double) newVal);
            }
            selectedItemPaneWidth = Math.abs(selectedItemPaneWidth - headerPadding.getLeft() - headerPadding.getRight());
            selectedItemPane.setPrefWidth(selectedItemPaneWidth);
            selectedItemPane.setLayoutX(headerIconPane.getPrefWidth());
            openButtonPane.setLayoutX(selectedItemPane.getLayoutX() + selectedItemPane.getPrefWidth());
            listHeaderSelectedItemLabel.setPrefWidth(selectedItemPaneWidth);
            listHeaderSelectedItemLabel.setPrefHeight((double) newVal);
            promptLabel.setPrefWidth(selectedItemPaneWidth);
            promptLabel.setPrefHeight((double) newVal);
            if (this.imageViewHeaderIcon != null) {
                double iconWidth = headerIconPane.getPrefWidth() * headerIconDimensionPercent;
                double iconHeight = headerIconPane.getPrefHeight() * headerIconDimensionPercent;
                imageViewHeaderIcon.setFitWidth(iconWidth);
                imageViewHeaderIcon.setFitHeight(iconHeight);
                imageViewHeaderIcon.setLayoutX(headerIconPane.getPrefWidth() / 2 - iconWidth / 2);
                imageViewHeaderIcon.setLayoutY(headerIconPane.getPrefHeight() / 2 - iconHeight / 2);
            }
            else if (this.headerPaneIcon != null) {
                double iconWidth = headerIconPane.getPrefWidth() * headerIconDimensionPercent;
                double iconHeight = headerIconPane.getPrefHeight() * headerIconDimensionPercent;
                headerPaneIcon.setPrefWidth(iconWidth);
                headerPaneIcon.setPrefHeight(iconHeight);
                headerPaneIcon.setLayoutX(headerIconPane.getPrefWidth() / 2 - iconWidth / 2);
                headerPaneIcon.setLayoutY(headerIconPane.getPrefHeight() / 2 - iconHeight / 2);
            }
            if (this.openButtonImageViewIcon != null) {
                double iconWidth = openButtonPane.getPrefWidth() * openButtonIconDimensionPercent;
                double iconHeight = openButtonPane.getPrefHeight() * openButtonIconDimensionPercent;
                openButtonImageViewIcon.setFitWidth(iconWidth);
                openButtonImageViewIcon.setFitHeight(iconHeight);
                openButtonImageViewIcon.setLayoutX(openButtonPane.getPrefWidth() / 2 - iconWidth / 2);
                openButtonImageViewIcon.setLayoutY(openButtonPane.getPrefHeight() / 2 - iconHeight / 2);
            }
            else if (this.openButtonPaneIcon != null) {
                double iconWidth = openButtonPane.getPrefWidth() * openButtonIconDimensionPercent;
                double iconHeight = openButtonPane.getPrefHeight() * openButtonIconDimensionPercent;
                openButtonPaneIcon.setPrefWidth(iconWidth);
                openButtonPaneIcon.setPrefHeight(iconHeight);
                openButtonPaneIcon.setLayoutX(openButtonPane.getPrefWidth() / 2 - iconWidth / 2);
                openButtonPaneIcon.setLayoutY(openButtonPane.getPrefHeight() / 2 - iconHeight / 2);
            }
            listContentPane.setLayoutY((double) newVal);
        });

        setPrefWidth(DEFAULT_WIDTH);
        setPrefHeight(DEFAULT_HEADER_HEIGHT);

        listHeader.setPrefHeight(DEFAULT_HEADER_HEIGHT);
        listHeader.setLayoutX(0);
        listHeader.setLayoutY(0);
        headerIconPane.setLayoutY(0);
        selectedItemPane.setLayoutX(headerIconPane.getPrefWidth());
        selectedItemPane.setLayoutY(0);
        listHeaderSelectedItemLabel.setLayoutX(0);
        listHeaderSelectedItemLabel.setLayoutY(0);
        promptLabel.setLayoutX(0);
        promptLabel.setLayoutY(0);
        openButtonPane.setLayoutY(0);

        listContentPane.setLayoutX(0);
        listContentPane.setLayoutY(listHeader.getPrefHeight());

        initListContentResizeBasedOnVScrollBar();
        vBarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        hBarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        verticalScrollBar.setPrefWidth(20);
        horizontalScrollBar.setPrefHeight(20);
        listContentPane.setMinHeight(0);
        listContentPane.setPrefHeight(0);
    }

    /**
     * Initializes the list content width resizing based on vertical scroll bar visibility.
     */
    private void initListContentResizeBasedOnVScrollBar () {
        verticalScrollBar.visibleProperty().addListener((prop, oldVal, newVal) -> {
            if (newVal) {
                listContent.setPrefWidth(listContentPane.getPrefWidth() - verticalScrollBar.getPrefWidth());
            }
            else {
                listContent.setPrefWidth(listContentPane.getPrefWidth());
            }
            listItemPanes.forEach(itemPane -> {
                itemPane.setPrefWidth(listContent.getPrefWidth());
            });
        });
        verticalScrollBar.prefWidthProperty().addListener((prop, oldVal, newVal) -> {
            if (verticalScrollBar.isVisible()) {
                listContent.setPrefWidth(listContentPane.getPrefWidth() - (double) newVal);
            }
            listItemPanes.forEach(itemPane -> {
                itemPane.setPrefWidth(listContent.getPrefWidth());
            });
        });
    }

    private void initElementsBehavior () {
        headerIconPane.visibleProperty().addListener((prop, oldVal, newVal) -> {
            arrangeHeaderComponentsBasedOnIconPaneVisibility(newVal);
        });
        openButtonPane.visibleProperty().addListener((prop, oldVal, newVal) -> {
            arrangeHeaderComponentsBasedOnOpenButtonVisibility(newVal);
        });
        displaySelectedItemIconProperty.addListener((prop, oldVal, newVal) -> {
            arrangeListItemChildrenBasedOnSelectedItemIconVisibility(newVal);
        });
        displayItemsIconProperty.addListener((prop, oldVal, newVal) -> {
            arrangeListItemChildrenBasedOnItemIconVisibility(newVal);
        });
        headerIconPane.setVisible(false);
        openButtonPane.setVisible(true);
        arrangeHeaderComponentsBasedOnIconPaneVisibility(headerIconPane.isVisible());
        arrangeHeaderComponentsBasedOnOpenButtonVisibility(openButtonPane.isVisible());
        arrangeListItemChildrenBasedOnSelectedItemIconVisibility(displaySelectedItemIconProperty.get());
        arrangeListItemChildrenBasedOnItemIconVisibility(displayItemsIconProperty.get());
    }

    /**
     * Arranges the elements in the list header taking into account if the<br>
     * icon pane is visible or not.
     */
    // FINISHED
    private void arrangeHeaderComponentsBasedOnIconPaneVisibility (boolean isVisible) {
        double selectedItemPaneWidth;
        double openButtonPaneLayoutX;
        if (!isVisible) {
            selectedItemPane.setLayoutX(headerPadding.getLeft());
            if (openButtonPane.isVisible()) {
                selectedItemPaneWidth = listHeader.getPrefWidth() - openButtonPane.getPrefWidth();
                openButtonPaneLayoutX = listHeader.getPrefWidth() - openButtonPane.getPrefWidth();
            }
            else {
                selectedItemPaneWidth = listHeader.getPrefWidth();
                openButtonPaneLayoutX = listHeader.getPrefWidth();
            }
            openButtonPaneLayoutX = Math.abs(openButtonPaneLayoutX - headerPadding.getRight());
        }
        else {
            selectedItemPane.setLayoutX(headerIconPane.getLayoutX() + headerIconPane.getPrefWidth());
            if (openButtonPane.isVisible()) {
                selectedItemPaneWidth = listHeader.getPrefWidth() - headerIconPane.getPrefWidth() - openButtonPane.getPrefWidth();
            }
            else {
                selectedItemPaneWidth = listHeader.getPrefWidth() - headerIconPane.getPrefWidth();
            }
            openButtonPaneLayoutX = listHeader.getPrefWidth() - openButtonPane.getPrefWidth() - headerPadding.getRight();
        }
        selectedItemPaneWidth = Math.abs(selectedItemPaneWidth - headerPadding.getLeft() - headerPadding.getRight());
        selectedItemPane.setPrefWidth(selectedItemPaneWidth);
        openButtonPane.setLayoutX(openButtonPaneLayoutX);
        listHeaderSelectedItemLabel.setPrefWidth(selectedItemPaneWidth);
        promptLabel.setPrefWidth(selectedItemPaneWidth);
    }

    /**
     * Arranges the list header elements taking into account the<br>
     * open button pane visibility.
     */
    // FINISHED
    private void arrangeHeaderComponentsBasedOnOpenButtonVisibility (boolean isVisible) {
        double selectedItemPaneWidth;
        double openButtonPaneLayoutX;
        if (!isVisible) {
            openButtonPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, openListEvent);
            selectedItemPane.addEventHandler(MouseEvent.MOUSE_CLICKED, openListEvent);
            if (headerIconPane.isVisible()) {
                selectedItemPaneWidth = listHeader.getPrefWidth() - headerIconPane.getPrefWidth();
                openButtonPaneLayoutX = listHeader.getPrefWidth() - openButtonPane.getPrefWidth();
            }
            else {
                selectedItemPaneWidth = listHeader.getPrefWidth();
                openButtonPaneLayoutX = listHeader.getPrefWidth();
            }
            openButtonPaneLayoutX = Math.abs(openButtonPaneLayoutX - headerPadding.getRight());
        }
        else {
            openButtonPane.addEventHandler(MouseEvent.MOUSE_CLICKED, openListEvent);
            selectedItemPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, openListEvent);
            if (headerIconPane.isVisible()) {
                selectedItemPaneWidth = listHeader.getPrefWidth() - headerIconPane.getPrefWidth() - openButtonPane.getPrefWidth();
            }
            else {
                selectedItemPaneWidth = listHeader.getPrefWidth() - openButtonPane.getPrefWidth();
            }
            openButtonPaneLayoutX = Math.abs(listHeader.getPrefWidth() - openButtonPane.getPrefWidth() - headerPadding.getRight());
        }
        selectedItemPaneWidth = Math.abs(selectedItemPaneWidth - headerPadding.getLeft() - headerPadding.getRight());
        selectedItemPane.setPrefWidth(selectedItemPaneWidth);
        openButtonPane.setLayoutX(openButtonPaneLayoutX);
        listHeaderSelectedItemLabel.setPrefWidth(selectedItemPaneWidth);
        promptLabel.setPrefWidth(selectedItemPaneWidth);
    }

    // FINISHED
    private void arrangeListItemChildrenBasedOnSelectedItemIconVisibility (boolean isVisible) {
        if (!isVisible && !displayItemsIconProperty.get()) {
            nodeItems.forEach(element -> {
                if (element instanceof ImageView) {
                    ((ImageView) element).setFitWidth(listContent.getPrefWidth());
                }
                else {
                    ((Region) element).setPrefWidth(listContent.getPrefWidth());
                }
                element.setLayoutX(0);
            });
        }
        else if (isVisible && displayItemsIconProperty.get()) {
            selectedItemIconPanes.forEach(element -> {
                element.setLayoutX(0);
            });
            itemIconPanes.forEach(element -> {
                element.setLayoutX(selectedItemIconPaneWidth);
            });
            nodeItems.forEach(element -> {
                if (element instanceof ImageView) {
                    ((ImageView) element).setFitWidth(listContent.getPrefWidth() - selectedItemIconPaneWidth - itemIconPaneWidth);
                }
                else {
                    ((Region) element).setPrefWidth(listContent.getPrefWidth() - selectedItemIconPaneWidth - itemIconPaneWidth);
                }
                element.setLayoutX(selectedItemIconPaneWidth + itemIconPaneWidth);
            });
        }
        else if (isVisible) {
            selectedItemIconPanes.forEach(element -> {
                element.setLayoutX(0);
            });
            nodeItems.forEach(element -> {
                if (element instanceof ImageView) {
                    ((ImageView) element).setFitWidth(listContent.getPrefWidth() - selectedItemIconPaneWidth);
                }
                else {
                    ((Region) element).setPrefWidth(listContent.getPrefWidth() - selectedItemIconPaneWidth);
                }
                element.setLayoutX(selectedItemIconPaneWidth);
            });
        }
        else if (displayItemsIconProperty.get()) {
            itemIconPanes.forEach(element -> {
                element.setLayoutX(0);
            });
            nodeItems.forEach(element -> {
                if (element instanceof ImageView) {
                    ((ImageView) element).setFitWidth(listContent.getPrefWidth() - itemIconPaneWidth);
                }
                else {
                    ((Region) element).setPrefWidth(listContent.getPrefWidth() - itemIconPaneWidth);
                }
                element.setLayoutX(itemIconPaneWidth);
            });
        }
    }

    // FINISHED
    private void arrangeListItemChildrenBasedOnItemIconVisibility (boolean isVisible) {
        if (!isVisible && !displaySelectedItemIconProperty.get()) {
            nodeItems.forEach(element -> {
                if (element instanceof ImageView) {
                    ((ImageView) element).setFitWidth(listContent.getPrefWidth());
                }
                else {
                    ((Region) element).setPrefWidth(listContent.getPrefWidth());
                }
                element.setLayoutX(0);
            });
        }
        else if (isVisible && displaySelectedItemIconProperty.get()) {
            selectedItemIconPanes.forEach(element -> {
                element.setLayoutX(0);
            });
            itemIconPanes.forEach(element -> {
                element.setLayoutX(selectedItemIconPaneWidth);
            });
            nodeItems.forEach(element -> {
                if (element instanceof ImageView) {
                    ((ImageView) element).setFitWidth(listContent.getPrefWidth() - selectedItemIconPaneWidth - itemIconPaneWidth);
                }
                else {
                    ((Region) element).setPrefWidth(listContent.getPrefWidth() - selectedItemIconPaneWidth - itemIconPaneWidth);
                }
                element.setLayoutX(selectedItemIconPaneWidth + itemIconPaneWidth);
            });
        }
        else if (isVisible) {
            itemIconPanes.forEach(element -> {
                element.setLayoutX(0);
            });
            nodeItems.forEach(element -> {
                if (element instanceof ImageView) {
                    ((ImageView) element).setFitWidth(listContent.getPrefWidth() - itemIconPaneWidth);
                }
                else {
                    ((Region) element).setPrefWidth(listContent.getPrefWidth() - itemIconPaneWidth);
                }
                element.setLayoutX(itemIconPaneWidth);
            });
        }
        else if (displaySelectedItemIconProperty.get()) {
            selectedItemIconPanes.forEach(element -> {
                element.setLayoutX(0);
            });
            nodeItems.forEach(element -> {
                if (element instanceof ImageView) {
                    ((ImageView) element).setFitWidth(listContent.getPrefWidth() - selectedItemIconPaneWidth);
                }
                else {
                    ((Region) element).setPrefWidth(listContent.getPrefWidth() - selectedItemIconPaneWidth);
                }
                element.setLayoutX(selectedItemIconPaneWidth);
            });
        }
    }

    /**
     * Adds a new item in the list.
     *
     * @param item the item to be added
     */
    public FDropdownList<T> addItem (T item) {
        if (!itemsValues.contains(item)) {
            itemsValues.add(item);
            if (item instanceof Node) {
                Pane listItem = createNodeListItem((Region) item);
                listItem.setLayoutX(0);
                listItem.setLayoutY(contentHeightProperty.get());
                contentHeightProperty.set(contentHeightProperty.get() + DEFAULT_LIST_ITEMS_HEIGHT + itemsSpacing);
                double minHeightValue = Math.max(contentHeightProperty.get() - itemsSpacing, expandHeight - 2);
                listContent.setPrefHeight(minHeightValue);
                UIUtil.add(listContent, listItem);
            }
            else if (item instanceof Integer || item instanceof Double || item instanceof String || item instanceof Character) {
                Pane listItem = createBasicValueListItem(item);
                listItem.setLayoutX(0);
                listItem.setLayoutY(contentHeightProperty.get());
                contentHeightProperty.set(contentHeightProperty.get() + DEFAULT_LIST_ITEMS_HEIGHT + itemsSpacing);
                double minHeightValue = Math.max(contentHeightProperty.get() - itemsSpacing, expandHeight - 2);
                listContent.setPrefHeight(minHeightValue);
                UIUtil.add(listContent, listItem);
            }
        }
        return this;
    }

    /**
     * Initializes selection behavior for the new added element.
     *
     * @param item The pane which represents visually the item in the list
     */
    private void addSelectItemEvent (Pane item) {
        item.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                int itemIndex = listItemPanes.indexOf((Pane) e.getSource());
                T currValue = itemsValues.get(itemIndex);
                if (selectedItemPaneIcon != null) {
                    if (selectedValueProperty.get() != null) {
                        Pane selectedItemIconParent = (Pane) UIUtil.getParent(selectedItemPaneIcon);
                        UIUtil.remove(selectedItemIconParent, selectedItemPaneIcon);
                    }
                    Pane selectedItemIconPane = selectedItemIconPanes.get(itemIndex);
                    selectedItemPaneIcon.setPrefWidth(selectedItemIconPane.getPrefWidth() * selectedItemIconDimensionPercent);
                    selectedItemPaneIcon.setPrefHeight(selectedItemIconPane.getPrefHeight() * selectedItemIconDimensionPercent);
                    selectedItemPaneIcon.setLayoutX(selectedItemIconPane.getPrefWidth() / 2 - selectedItemPaneIcon.getPrefWidth() / 2);
                    selectedItemPaneIcon.setLayoutY(selectedItemIconPane.getPrefHeight() / 2 - selectedItemPaneIcon.getPrefHeight() / 2);
                    UIUtil.add(selectedItemIconPane, selectedItemPaneIcon);
                }
                else if (selectedItemImageViewIcon != null) {
                    if (selectedValueProperty.get() != null) {
                        Pane selectedItemIconParent = (Pane) UIUtil.getParent(selectedItemImageViewIcon);
                        UIUtil.remove(selectedItemIconParent, selectedItemImageViewIcon);
                    }
                    Pane selectedItemIconPane = selectedItemIconPanes.get(itemIndex);
                    selectedItemImageViewIcon.setFitWidth(selectedItemIconPane.getPrefWidth() * selectedItemIconDimensionPercent);
                    selectedItemImageViewIcon.setFitHeight(selectedItemIconPane.getPrefHeight() * selectedItemIconDimensionPercent);
                    selectedItemImageViewIcon.setLayoutX(selectedItemIconPane.getPrefWidth() / 2 - selectedItemImageViewIcon.getFitWidth() / 2);
                    selectedItemImageViewIcon.setLayoutY(selectedItemIconPane.getPrefHeight() / 2 - selectedItemImageViewIcon.getFitHeight() / 2);
                    UIUtil.add(selectedItemIconPane, selectedItemImageViewIcon);
                }
                selectedValueProperty.set(currValue);
                promptLabel.setVisible(false);
                if (currValue instanceof Integer || currValue instanceof Double || currValue instanceof String || currValue instanceof Character) {
                    listHeaderSelectedItemLabel.setText(currValue + "");
                    listHeaderSelectedItemLabel.setVisible(true);
                }
                else {
                    // When item type is Node(To implement)
                }
            }
        });
    }

    /**
     * Initializes the positioning and resizing behavior for the item icon.
     *
     * @param itemIconPane The container which contains the icon itself
     */
    private void addItemIconPositioningEvent (Pane itemIconPane) {
        int iconPaneIndex = itemIconPanes.indexOf(itemIconPane);
        Pane paneIcon = itemPaneIcons.get(iconPaneIndex);
        ImageView imageViewIcon = itemImageViewIcons.get(iconPaneIndex);
        itemIconPane.prefWidthProperty().addListener((prop, oldVal, newVal) -> {
            if (paneIcon != null) {
                paneIcon.setPrefWidth((double) newVal * itemIconDimensionPercent);
                paneIcon.setLayoutX((double) newVal / 2 - paneIcon.getPrefWidth() / 2);
            }
            else if (imageViewIcon != null) {
                imageViewIcon.setFitWidth((double) newVal * itemIconDimensionPercent);
                imageViewIcon.setLayoutX((double) newVal / 2 - imageViewIcon.getFitWidth() / 2);
            }
        });
        itemIconPane.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            if (paneIcon != null) {
                paneIcon.setPrefHeight((double) newVal * itemIconDimensionPercent);
                paneIcon.setLayoutY((double) newVal / 2 - paneIcon.getPrefHeight() / 2);
            }
            else if (imageViewIcon != null) {
                imageViewIcon.setFitHeight((double) newVal * itemIconDimensionPercent);
                imageViewIcon.setLayoutY((double) newVal / 2 - imageViewIcon.getFitHeight() / 2);
            }
        });
    }

    // FINISHED

    /**
     * Initializes the positioning and resizing behavior for the selected item icon.
     *
     * @param selectedItemIconPane The container which contains the icon itself
     */
    private void addSelectedItemIconPositioningEvent (Pane selectedItemIconPane) {
        selectedItemIconPane.prefWidthProperty().addListener((prop, oldVal, newVal) -> {
            if (UIUtil.contains(selectedItemIconPane, selectedItemPaneIcon)) {
                selectedItemPaneIcon.setPrefWidth((double) newVal * selectedItemIconDimensionPercent);
                selectedItemPaneIcon.setLayoutX((double) newVal / 2 - selectedItemPaneIcon.getPrefWidth() / 2);
            }
            else if (UIUtil.contains(selectedItemIconPane, selectedItemImageViewIcon)) {
                selectedItemImageViewIcon.setFitWidth((double) newVal * selectedItemIconDimensionPercent);
                selectedItemImageViewIcon.setLayoutX((double) newVal / 2 - selectedItemPaneIcon.getPrefWidth() / 2);
            }
        });
        selectedItemIconPane.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            if (UIUtil.contains(selectedItemIconPane, selectedItemPaneIcon)) {
                selectedItemPaneIcon.setPrefHeight((double) newVal * selectedItemIconDimensionPercent);
                selectedItemPaneIcon.setLayoutY((double) newVal / 2 - selectedItemPaneIcon.getPrefHeight() / 2);
            }
            else if (UIUtil.contains(selectedItemIconPane, selectedItemImageViewIcon)) {
                selectedItemImageViewIcon.setFitHeight((double) newVal * selectedItemIconDimensionPercent);
                selectedItemImageViewIcon.setLayoutY((double) newVal / 2 - selectedItemPaneIcon.getPrefHeight() / 2);
            }
        });
    }

    /**
     * Creates a list item when T is a type of Integer, Double, String or Character.
     *
     * @param item The value which will be visible in the list item container
     */
    // FINISHED
    private Pane createBasicValueListItem (T item) {
        Pane listItemPane = new Pane();
        Pane selectedItemIconPane = new Pane();
        Pane itemIconPane = new Pane();
        Label label = new Label(item + "");
        listItemPane.prefWidthProperty().addListener((prop, oldVal, newVal) -> {
            label.setPrefWidth((double) newVal);
            if (!displaySelectedItemIconProperty.get() && !displayItemsIconProperty.get()) {
                label.setPrefWidth((double) newVal);
            }
            else if (displaySelectedItemIconProperty.get() && displayItemsIconProperty.get()) {
                label.setPrefWidth((double) newVal - selectedItemIconPane.getPrefWidth() - itemIconPane.getPrefWidth());
            }
            else if (displaySelectedItemIconProperty.get()) {
                label.setPrefWidth((double) newVal - selectedItemIconPane.getPrefWidth());
            }
            else if (displayItemsIconProperty.get()) {
                label.setPrefWidth((double) newVal - itemIconPane.getPrefWidth());
            }
        });
        listItemPane.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            selectedItemIconPaneWidth = (double) newVal;
            itemIconPaneWidth = (double) newVal;
            itemIconPane.setPrefWidth((double) newVal);
            itemIconPane.setPrefHeight((double) newVal);
            selectedItemIconPane.setPrefWidth((double) newVal);
            selectedItemIconPane.setPrefHeight((double) newVal);
            label.setPrefHeight((double) newVal);
            if (!displaySelectedItemIconProperty.get() && !displayItemsIconProperty.get()) {
                label.setPrefWidth(listContent.getPrefWidth());
                label.setLayoutX(0);
            }
            else if (displaySelectedItemIconProperty.get() && displayItemsIconProperty.get()) {
                selectedItemIconPane.setLayoutX(0);
                itemIconPane.setLayoutX((double) newVal);
                label.setPrefWidth(listContent.getPrefWidth() - selectedItemIconPane.getPrefWidth() - itemIconPane.getPrefWidth());
                label.setLayoutX(selectedItemIconPane.getPrefWidth() + itemIconPane.getPrefWidth());
            }
            else if (displaySelectedItemIconProperty.get()) {
                selectedItemIconPane.setLayoutX(0);
                label.setPrefWidth(listContent.getPrefWidth() - selectedItemIconPane.getPrefWidth());
                label.setLayoutX(selectedItemIconPane.getPrefWidth());
            }
            else if (displayItemsIconProperty.get()) {
                itemIconPane.setLayoutX(0);
                label.setPrefWidth(listContent.getPrefWidth() - itemIconPane.getPrefWidth());
                label.setLayoutX(itemIconPane.getPrefWidth());
            }
        });
        addSelectItemEvent(listItemPane);
        addSelectedItemIconPositioningEvent(selectedItemIconPane);

        listItemPane.setPrefWidth(listContentPane.getPrefWidth());
        listItemPane.setPrefHeight(DEFAULT_LIST_ITEMS_HEIGHT);
        listItemPanes.add(listItemPane);
        selectedItemIconPanes.add(selectedItemIconPane);
        itemIconPanes.add(itemIconPane);
        nodeItems.add(label);
        selectedItemIconPane.setVisible(displaySelectedItemIconProperty.get());
        itemIconPane.setVisible(displayItemsIconProperty.get());

        BackgroundUtil.setBackgroundOpacity(selectedItemIconPane, 0);
        BackgroundUtil.setBackgroundOpacity(itemIconPane, 0);
        BackgroundUtil.setBackgroundOpacity(label, 0);
        UIUtil.add(listItemPane, selectedItemIconPane);
        UIUtil.add(listItemPane, itemIconPane);
        UIUtil.add(listItemPane, label);

        addListItemToStyleTracker(listItemPane);
        addItemLabelToStyleTracker(label);

        return listItemPane;
    }

    /**
     * Creates a list item when T is a type of Node.
     *
     * @param region The component which will be visible in the list item container
     */
    private Pane createNodeListItem (Region region) {
        Pane listItemPane = new Pane();
        Pane selectedItemIconPane = new Pane();
        Pane itemIconPane = new Pane();
        listItemPane.prefWidthProperty().addListener((prop, oldVal, newVal) -> {
            if (!displaySelectedItemIconProperty.get() && !displayItemsIconProperty.get()) {
                region.setPrefWidth((double) newVal);
            }
            else if (displaySelectedItemIconProperty.get() && displayItemsIconProperty.get()) {
                region.setPrefWidth((double) newVal - selectedItemIconPane.getPrefWidth() - itemIconPane.getPrefWidth());
            }
            else if (displaySelectedItemIconProperty.get()) {
                region.setPrefWidth((double) newVal - selectedItemIconPane.getPrefWidth());
            }
            else if (displayItemsIconProperty.get()) {
                region.setPrefWidth((double) newVal - itemIconPane.getPrefWidth());
            }
        });
        listItemPane.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            selectedItemIconPaneWidth = (double) newVal;
            itemIconPaneWidth = (double) newVal;
            itemIconPane.setPrefWidth((double) newVal);
            itemIconPane.setPrefHeight((double) newVal);
            selectedItemIconPane.setPrefWidth((double) newVal);
            selectedItemIconPane.setPrefHeight((double) newVal);
            region.setPrefHeight((double) newVal);
            if (!displaySelectedItemIconProperty.get() && !displayItemsIconProperty.get()) {
                region.setPrefWidth(listContent.getPrefWidth());
                region.setLayoutX(0);
            }
            else if (displaySelectedItemIconProperty.get() && displayItemsIconProperty.get()) {
                selectedItemIconPane.setLayoutX(0);
                itemIconPane.setLayoutX((double) newVal);
                region.setPrefWidth(listContent.getPrefWidth() - selectedItemIconPane.getPrefWidth() - itemIconPane.getPrefWidth());
                region.setLayoutX(selectedItemIconPane.getPrefWidth() + itemIconPane.getPrefWidth());
            }
            else if (displaySelectedItemIconProperty.get()) {
                selectedItemIconPane.setLayoutX(0);
                region.setPrefWidth(listContent.getPrefWidth() - selectedItemIconPane.getPrefWidth());
                region.setLayoutX(selectedItemIconPane.getPrefWidth());
            }
            else if (displayItemsIconProperty.get()) {
                itemIconPane.setLayoutX(0);
                region.setPrefWidth(listContent.getPrefWidth() - itemIconPane.getPrefWidth());
                region.setLayoutX(itemIconPane.getPrefWidth());
            }
        });
        addSelectItemEvent(listItemPane);
        addSelectedItemIconPositioningEvent(selectedItemIconPane);

        listItemPane.setPrefWidth(listContentPane.getPrefWidth());
        listItemPane.setPrefHeight(DEFAULT_LIST_ITEMS_HEIGHT);
        listItemPanes.add(listItemPane);
        listItemPanes.add(listItemPane);
        selectedItemIconPanes.add(selectedItemIconPane);
        itemIconPanes.add(itemIconPane);
        nodeItems.add(region);
        selectedItemIconPane.setVisible(displaySelectedItemIconProperty.get());
        itemIconPane.setVisible(displayItemsIconProperty.get());

        BackgroundUtil.setBackgroundOpacity(selectedItemIconPane, 0);
        BackgroundUtil.setBackgroundOpacity(itemIconPane, 0);
        UIUtil.add(listItemPane, selectedItemIconPane);
        UIUtil.add(listItemPane, itemIconPane);
        UIUtil.add(listItemPane, region);

        addListItemToStyleTracker(listItemPane);

        return listItemPane;
    }

    // FINISHED

    /**
     * Returns the current selected value.
     */
    public T getValue () {
        return selectedValueProperty.get();
    }

    // FINISHED

    /**
     * Removes the specified item.
     */
    public FDropdownList<T> removeItem (T item) {
        removeItem(itemsValues.indexOf(item));
        return this;
    }

    // FINISHED

    /**
     * Removes an item from the specified index.
     */
    public FDropdownList<T> removeItem (int index) {
        if (index >= 0 && index < itemsValues.size()) {
            UIUtil.remove(listContent, listItemPanes.get(index));
            Pane listItemPane = listItemPanes.get(index);
            Region nodeItem = (Region) nodeItems.get(index);
            styleTracker.removeComponent(listItemPane);
            styleTracker.removeComponent(nodeItem);
            listItemPanes.remove(index);
            nodeItems.remove(index);
            selectedItemIconPanes.remove(index);
            if (!itemIconPanes.isEmpty() && index < itemIconPanes.size()) {
                itemIconPanes.remove(index);
            }
            if (!itemImageViewIcons.isEmpty() && index < itemImageViewIcons.size()) {
                itemImageViewIcons.remove(index);
            }
            if (!itemPaneIcons.isEmpty() && index < itemPaneIcons.size()) {
                itemPaneIcons.remove(index);
            }
            if (index == itemsValues.indexOf(selectedValueProperty.get())) {
                selectedValueProperty.set(null);
                listHeaderSelectedItemLabel.setText("");
            }
            itemsValues.remove(index);
            rearrangeListItems();
            double minHeightValue = Math.max(contentHeightProperty.get() - itemsSpacing, expandHeight - 2);
            listContent.setPrefHeight(minHeightValue);
        }
        return this;
    }

    /**
     * Removes last item from the list.
     */
    public FDropdownList<T> removeLastItem () {
        removeItem(itemsValues.size() - 1);
        return this;
    }

    private EventHandler<MouseEvent> openListEvent = (e) -> {
        if (e.getButton() == MouseButton.PRIMARY) {
            openList(!listIsOpenedProperty.get());
        }
    };

    /**
     * Opens the list by changing the list content pane height.
     */
    public void openList (boolean open) {
        listIsOpenedProperty.set(open);
        if (listIsOpenedProperty.get()) {
            listContentPane.setPrefHeight(expandHeight);
        }
        else {
            listContentPane.setPrefHeight(0);
        }
    }

    // FINISHED
    private void addListItemToStyleTracker (Pane component) {
        Set<String> notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(component, notAllowedProperties);
    }

    private void addItemLabelToStyleTracker (Label label) {
        Set<String> notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(label, notAllowedProperties);
    }

    private void initAllowedStyleProperties () {
        Set<String> notAllowedProperties = new HashSet<>();
        // DropdownList-related elements
        styleTracker.addComponent(this, notAllowedProperties);
        styleTracker.setCurrentComponent(this);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(listHeader, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(headerIconPane, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(promptLabel, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(openButtonPane, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(listContentPane, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        styleTracker.addComponent(listContent, notAllowedProperties);

        // ScrollPane(listContentPane)-related elements
        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(verticalScrollBar, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.WIDTH);
        styleTracker.addComponent(horizontalScrollBar, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.TRANSLATE_X);
        notAllowedProperties.add(MainProps.TRANSLATE_Y);
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(verticalThumb, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.TRANSLATE_X);
        notAllowedProperties.add(MainProps.TRANSLATE_Y);
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(horizontalThumb, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementVButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(decrementVButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementHButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(decrementHButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementVArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(decrementVArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementHArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(decrementHArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(corner, notAllowedProperties);
    }

    // DropdownList-related elements
    public Pane getListHeader () {
        return listHeader;
    }

    public Pane getHeaderIconPane () {
        return headerIconPane;
    }

    public Pane getSelectedItemPane () {
        return selectedItemPane;
    }

    public Pane getOpenButtonPane () {
        return openButtonPane;
    }

    public Label getSelectedItemLabel () {
        return listHeaderSelectedItemLabel;
    }

    public Label getPromptLabel () {
        return promptLabel;
    }

    public Label getListHeaderSelectedItemLabel () {
        return listHeaderSelectedItemLabel;
    }

    public ScrollPane getListContentPane () {
        return listContentPane;
    }

    public Pane getListContent () {
        return listContent;
    }

    //ScrollPane(listContentPane)-related elements
    public ScrollBar getVScrollBar () {
        return verticalScrollBar;
    }

    public ScrollBar getHScrollBar () {
        return horizontalScrollBar;
    }

    public Region getVThumb () {
        return verticalThumb;
    }

    public Region getHThumb () {
        return horizontalThumb;
    }

    public Region getIncrementVButton () {
        return incrementVButton;
    }

    public Region getDecrementVButton () {
        return decrementVButton;
    }

    public Region getIncrementHButton () {
        return incrementHButton;
    }

    public Region getDecrementHButton () {
        return decrementHButton;
    }

    public Region getIncrementVArrow () {
        return incrementVArrow;
    }

    public Region getDecrementVArrow () {
        return decrementVArrow;
    }

    public Region getIncrementHArrow () {
        return incrementHArrow;
    }

    public Region getDecrementHArrow () {
        return decrementHArrow;
    }

    public StackPane getCorner () {
        return corner;
    }

    // FDropdownList-related pseudo-elements
    public FDropdownList<T> listHeader (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(listHeader);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> headerIconPane (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(headerIconPane);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> listHeaderSelectedItemLabel (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(listHeaderSelectedItemLabel);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> prompt (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(promptLabel);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> openButtonPane (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(openButtonPane);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> listContentPane (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(listContentPane);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> listContent (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(listContent);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> listItem (PseudoElements pseudoElement) {
        for (int i = 0; i < listItemPanes.size(); i++) {
            styleTracker.setCurrentComponent(listItemPanes.get(i));
            pseudoElement.apply(i);
        }
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> listItem (PseudoElement pseudoElement) {
        for (int i = 0; i < listItemPanes.size(); i++) {
            styleTracker.setCurrentComponent(listItemPanes.get(i));
            pseudoElement.apply();
        }
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> itemLabel (PseudoElements pseudoElement) {
        for (int i = 0; i < nodeItems.size(); i++) {
            Region currItem = (Region) nodeItems.get(i);
            if (currItem instanceof Label) {
                styleTracker.setCurrentComponent(currItem);
                pseudoElement.apply(i);
            }
        }
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> itemLabel (PseudoElement pseudoElement) {
        for (int i = 0; i < nodeItems.size(); i++) {
            Region currItem = (Region) nodeItems.get(i);
            if (currItem instanceof Label) {
                styleTracker.setCurrentComponent(currItem);
                pseudoElement.apply();
            }
        }
        styleTracker.setCurrentComponent(this);
        return this;
    }

    // ScrollPane(listContentPane)-related pseudo-elements
    public FDropdownList<T> verticalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> horizontalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> verticalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> horizontalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> incrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> decrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> incrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> decrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> incrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> decrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> incrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> decrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FDropdownList<T> corner (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(corner);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    @Override
    public FDropdownList<T> x (double value) {
        if (styleTracker.isAllowedProperty(MainProps.X)) {
            styleTracker.getCurrentComponent().setLayoutX(value);
        }
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FDropdownList<T> y (double value) {
        if (styleTracker.isAllowedProperty(MainProps.Y)) {
            styleTracker.getCurrentComponent().setLayoutY(value);
        }
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FDropdownList<T> width (double value) {
        if (styleTracker.isAllowedProperty(MainProps.WIDTH)) {
            styleTracker.getCurrentComponent().setPrefWidth(value);
        }
        return this;
    }

    @Override
    public FDropdownList<T> height (double value) {
        if (styleTracker.isAllowedProperty(MainProps.HEIGHT)) {
            if (styleTracker.getCurrentComponent() == this) {
                setListContentPaneHeight(value);
            }
            else {
                styleTracker.getCurrentComponent().setPrefHeight(value);
            }
        }
        return this;
    }

    private void setListContentPaneHeight (double value) {
        value = Math.abs(value - listHeader.getPrefHeight());
        if (listIsOpenedProperty.get()) {
            listContentPane.setPrefHeight(value);
        }
        expandHeight = value;
    }

    @Override
    public FDropdownList<T> scaleX (double value) {
        styleTracker.getCurrentComponent().setScaleX(value);
        return this;
    }

    @Override
    public FDropdownList<T> scaleY (double value) {
        styleTracker.getCurrentComponent().setScaleY(value);
        return this;
    }

    @Override
    public FDropdownList<T> translateX (double value) {
        styleTracker.getCurrentComponent().setTranslateX(value);
        return this;
    }

    @Override
    public FDropdownList<T> translateY (double value) {
        styleTracker.getCurrentComponent().setTranslateY(value);
        return this;
    }

    @Override
    public FDropdownList<T> rotate (double value) {
        styleTracker.getCurrentComponent().setRotate(value);
        return this;
    }

    @Override
    public FDropdownList<T> padding (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            PaddingUtil.setPadding(styleTracker.getCurrentComponent(), value);
        }
        else if (styleTracker.getCurrentComponent() == listHeader) {
            headerPadding.set(value);
            rearrangeHeaderComponentsBasedOnPadding();
        }
        return this;
    }

    @Override
    public FDropdownList<T> padding (double topBottom, double rightLeft) {
        if (styleTracker.getCurrentComponent() == this) {
            PaddingUtil.setPadding(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        }
        else if (styleTracker.getCurrentComponent() == listHeader) {
            headerPadding.set(topBottom, rightLeft);
            rearrangeHeaderComponentsBasedOnPadding();
        }
        return this;
    }

    @Override
    public FDropdownList<T> padding (double top, double right, double bottom, double left) {
        if (styleTracker.getCurrentComponent() == this) {
            PaddingUtil.setPadding(styleTracker.getCurrentComponent(), top, right, bottom, left);
        }
        else if (styleTracker.getCurrentComponent() == listHeader) {
            headerPadding.set(top, right, bottom, left);
            rearrangeHeaderComponentsBasedOnPadding();
        }
        return this;
    }

    @Override
    public FDropdownList<T> topPadding (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            PaddingUtil.setTopPadding(styleTracker.getCurrentComponent(), value);
        }
        else if (styleTracker.getCurrentComponent() == listHeader) {
            headerPadding.setTop(value);
            rearrangeHeaderComponentsBasedOnPadding();
        }
        return this;
    }

    @Override
    public FDropdownList<T> rightPadding (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            PaddingUtil.setRightPadding(styleTracker.getCurrentComponent(), value);
        }
        else if (styleTracker.getCurrentComponent() == listHeader) {
            headerPadding.setRight(value);
            rearrangeHeaderComponentsBasedOnPadding();
        }
        return this;
    }

    @Override
    public FDropdownList<T> bottomPadding (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            PaddingUtil.setBottomPadding(styleTracker.getCurrentComponent(), value);
        }
        else if (styleTracker.getCurrentComponent() == listHeader) {
            headerPadding.setBottom(value);
            rearrangeHeaderComponentsBasedOnPadding();
        }
        return this;
    }

    @Override
    public FDropdownList<T> leftPadding (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            PaddingUtil.setLeftPadding(styleTracker.getCurrentComponent(), value);
        }
        else if (styleTracker.getCurrentComponent() == listHeader) {
            headerPadding.setLeft(value);
            rearrangeHeaderComponentsBasedOnPadding();
        }
        return this;
    }

    private void rearrangeHeaderComponentsBasedOnPadding () {
        double selectedItemPaneWidth;
        if (headerIconPane.isVisible() && openButtonPane.isVisible()) {
            selectedItemPaneWidth = Math.abs(getPrefWidth() - headerIconPane.getPrefWidth() - openButtonPane.getPrefWidth());
        }
        else if (headerIconPane.isVisible()) {
            selectedItemPaneWidth = Math.abs(getPrefWidth() - headerIconPane.getPrefWidth());
        }
        else if (openButtonPane.isVisible()) {
            selectedItemPaneWidth = Math.abs(getPrefWidth() - openButtonPane.getPrefWidth());
        }
        else {
            selectedItemPaneWidth = Math.abs(getPrefWidth());
        }
        selectedItemPaneWidth = Math.abs(selectedItemPaneWidth - headerPadding.getLeft() - headerPadding.getRight());
        selectedItemPane.setPrefWidth(selectedItemPaneWidth);
        listHeaderSelectedItemLabel.setPrefWidth(selectedItemPaneWidth);
        promptLabel.setPrefWidth(selectedItemPaneWidth);
        headerIconPane.setLayoutX(headerPadding.getLeft());
        selectedItemPane.setLayoutX(headerIconPane.getLayoutX() + headerIconPane.getPrefWidth());
        openButtonPane.setLayoutX(selectedItemPane.getLayoutX() + selectedItemPane.getPrefWidth());
    }

    @Override
    public double getTopPadding () {
        return PaddingUtil.getTopPadding(this);
    }

    @Override
    public double getRightPadding () {
        return PaddingUtil.getRightPadding(this);
    }

    @Override
    public double getBottomPadding () {
        return PaddingUtil.getBottomPadding(this);
    }

    @Override
    public double getLeftPadding () {
        return PaddingUtil.getLeftPadding(this);
    }

    @Override
    public FDropdownList<T> margin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(value);
        }
        return this;
    }

    @Override
    public FDropdownList<T> margin (double topBottom, double rightLeft) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(topBottom, rightLeft);
        }
        return this;
    }

    @Override
    public FDropdownList<T> margin (double top, double right, double bottom, double left) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(top, right, bottom, left);
        }
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FDropdownList<T> topMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setTop(value);
        }
        return this;
    }

    @Override
    public FDropdownList<T> rightMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setRight(value);
        }
        return this;
    }

    @Override
    public FDropdownList<T> bottomMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setBottom(value);
        }
        return this;
    }

    @Override
    public FDropdownList<T> leftMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setLeft(value);
        }
        return this;
    }

    @Override
    public double getTopMargin () {
        return margin.getTop();
    }

    @Override
    public double getRightMargin () {
        return margin.getRight();
    }

    @Override
    public double getBottomMargin () {
        return margin.getBottom();
    }

    @Override
    public double getLeftMargin () {
        return margin.getLeft();
    }

    @Override
    public FDropdownList<T> bgColor (String color) {
        BackgroundUtil.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FDropdownList<T> bgColor (Color color) {
        BackgroundUtil.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public String getBgColorHex () {
        return BackgroundUtil.getBackgroundHexColor(this);
    }

    @Override
    public Color getBgColor () {
        return BackgroundUtil.getBackgroundColor(this);
    }

    @Override
    public FDropdownList<T> bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FDropdownList<T> bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FDropdownList<T> bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FDropdownList<T> topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bottomLeftBgRadius (double value) {
        BackgroundUtil.setBottomLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopLeftBgRadius () {
        return BackgroundUtil.getTopLeftBackgroundRadius(this);
    }

    @Override
    public double getTopRightBgRadius () {
        return BackgroundUtil.getTopRightBackgroundRadius(this);
    }

    @Override
    public double getBottomRightBgRadius () {
        return BackgroundUtil.getBottomRightBackgroundRadius(this);
    }

    @Override
    public double getBottomLeftBgRadius () {
        return BackgroundUtil.getBottomLeftBackgroundRadius(this);
    }

    @Override
    public FDropdownList<T> bgInsets (double value) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FDropdownList<T> bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FDropdownList<T> topInset (double value) {
        BackgroundUtil.setTopInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> rightInset (double value) {
        BackgroundUtil.setRightInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bottomInset (double value) {
        BackgroundUtil.setBottomInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> leftInset (double value) {
        BackgroundUtil.setLeftInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopInset () {
        return BackgroundUtil.getBackgroundInsets(this).getTop();
    }

    @Override
    public double getRightInset () {
        return BackgroundUtil.getBackgroundInsets(this).getRight();
    }

    @Override
    public double getBottomInset () {
        return BackgroundUtil.getBackgroundInsets(this).getBottom();
    }

    @Override
    public double getLeftInset () {
        return BackgroundUtil.getBackgroundInsets(this).getLeft();
    }

    @Override
    public FDropdownList<T> alignment (Pos pos) {
        if (styleTracker.getCurrentComponent() instanceof Labeled) {

        }
        return this;
    }

    @Override
    public FDropdownList<T> border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FDropdownList<T> border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FDropdownList<T> borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FDropdownList<T> topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FDropdownList<T> rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FDropdownList<T> bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FDropdownList<T> leftBorderStyle (String borderStyle) {
        BorderUtil.setLeftBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public String getTopBorderStyle () {
        return BorderUtil.getTopBorderStyle(this);
    }

    @Override
    public String getRightBorderStyle () {
        return BorderUtil.getRightBorderStyle(this);
    }

    @Override
    public String getBottomBorderStyle () {
        return BorderUtil.getBottomBorderStyle(this);
    }

    @Override
    public String getLeftBorderStyle () {
        return BorderUtil.getLeftBorderStyle(this);
    }

    @Override
    public FDropdownList<T> borderWidth (double value) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FDropdownList<T> borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FDropdownList<T> topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> leftBorderWidth (double value) {
        BorderUtil.setLeftBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopBorderWidth () {
        return BorderUtil.getTopBorderWidth(this);
    }

    @Override
    public double getRightBorderWidth () {
        return BorderUtil.getRightBorderWidth(this);
    }

    @Override
    public double getBottomBorderWidth () {
        return BorderUtil.getBottomBorderWidth(this);
    }

    @Override
    public double getLeftBorderWidth () {
        return BorderUtil.getLeftBorderWidth(this);
    }

    @Override
    public FDropdownList<T> borderColor (String colorValue) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FDropdownList<T> borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FDropdownList<T> borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FDropdownList<T> topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FDropdownList<T> rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FDropdownList<T> bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FDropdownList<T> leftBorderColor (String colorValue) {
        BorderUtil.setLeftBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public String getTopBorderColorHex () {
        return BorderUtil.getTopBorderColor(this);
    }

    @Override
    public String getRightBorderColorHex () {
        return BorderUtil.getRightBorderColor(this);
    }

    @Override
    public String getBottomBorderColorHex () {
        return BorderUtil.getBottomBorderColor(this);
    }

    @Override
    public String getLeftBorderColorHex () {
        return BorderUtil.getLeftBorderColor(this);
    }

    @Override
    public FDropdownList<T> borderColor (Color color) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FDropdownList<T> borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FDropdownList<T> borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, top, right);
        return this;
    }

    @Override
    public FDropdownList<T> topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FDropdownList<T> rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FDropdownList<T> bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FDropdownList<T> leftBorderColor (Color color) {
        BorderUtil.setLeftBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public Color getTopBorderColor () {
        return Color.valueOf(BorderUtil.getTopBorderColor(this));
    }

    @Override
    public Color getRightBorderColor () {
        return Color.valueOf(BorderUtil.getRightBorderColor(this));
    }

    @Override
    public Color getBottomBorderColor () {
        return Color.valueOf(BorderUtil.getBottomBorderColor(this));
    }

    @Override
    public Color getLeftBorderColor () {
        return Color.valueOf(BorderUtil.getLeftBorderColor(this));
    }

    @Override
    public FDropdownList<T> borderOpacity (double value) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FDropdownList<T> borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FDropdownList<T> topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> leftBorderOpacity (double value) {
        BorderUtil.setLeftBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopBorderOpacity () {
        return BorderUtil.getTopBorderOpacity(this);
    }

    @Override
    public double getRightBorderOpacity () {
        return BorderUtil.getRightBorderOpacity(this);
    }

    @Override
    public double getBottomBorderOpacity () {
        return BorderUtil.getBottomBorderOpacity(this);
    }

    @Override
    public double getLeftBorderOpacity () {
        return BorderUtil.getLeftBorderOpacity(this);
    }

    @Override
    public FDropdownList<T> borderRadius (double value) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FDropdownList<T> borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FDropdownList<T> topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bottomLeftBorderRadius (double value) {
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopLeftBorderRadius () {
        return BorderUtil.getBorderRadius(this).getTopLeftHorizontalRadius();
    }

    @Override
    public double getTopRightBorderRadius () {
        return BorderUtil.getBorderRadius(this).getTopRightHorizontalRadius();
    }

    @Override
    public double getBottomRightBorderRadius () {
        return BorderUtil.getBorderRadius(this).getBottomRightHorizontalRadius();
    }

    @Override
    public double getBottomLeftBorderRadius () {
        return BorderUtil.getBorderRadius(this).getBottomLeftHorizontalRadius();
    }

    @Override
    public FDropdownList<T> borderInsets (double value) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FDropdownList<T> borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FDropdownList<T> topBorderInset (double value) {
        BorderUtil.setTopBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FDropdownList<T> leftBorderInset (double value) {
        BorderUtil.setLeftBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopBorderInset () {
        return BorderUtil.getBorderInsets(this).getTop();
    }

    @Override
    public double getRightBorderInset () {
        return BorderUtil.getBorderInsets(this).getRight();
    }

    @Override
    public double getBottomBorderInset () {
        return BorderUtil.getBorderInsets(this).getBottom();
    }

    @Override
    public double getLeftBorderInset () {
        return BorderUtil.getBorderInsets(this).getLeft();
    }

    @Override
    public FDropdownList<T> removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public FDropdownList<T> removeTopBorder () {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FDropdownList<T> removeRightBorder () {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FDropdownList<T> removeBottomBorder () {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FDropdownList<T> removeLeftBorder () {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FDropdownList<T> shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        styleTracker.getCurrentComponent().setShape(svgPath);
        return this;
    }

    @Override
    public FDropdownList<T> shape (Shape shape) {
        styleTracker.getCurrentComponent().setShape(shape);
        return this;
    }

    @Override
    public FDropdownList<T> font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(listHeaderSelectedItemLabel, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public FDropdownList<T> font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontUtil.setFont(listHeaderSelectedItemLabel, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public FDropdownList<T> font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontUtil.setFont(listHeaderSelectedItemLabel, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public FDropdownList<T> font (String fontFamily, double fontSize) {
        FontUtil.setFont(listHeaderSelectedItemLabel, fontFamily, fontSize);
        return this;
    }

    @Override
    public FDropdownList<T> fontColor (Color color) {
        FontUtil.setFontColor(listHeaderSelectedItemLabel, color);
        return this;
    }

    @Override
    public FDropdownList<T> fontColor (String color) {
        FontUtil.setFontColor(listHeaderSelectedItemLabel, color);
        return this;
    }

    @Override
    public Color getFontColor () {
        return (Color) listHeaderSelectedItemLabel.getTextFill();
    }

    @Override
    public String getFontColorHex () {
        return ColorUtil.getHexFromColor((Color) listHeaderSelectedItemLabel.getTextFill());
    }

    @Override
    public FDropdownList<T> fontFamily (String fontFamily) {
        FontUtil.setFontFamily(listHeaderSelectedItemLabel, fontFamily);
        return this;
    }

    @Override
    public FDropdownList<T> fontPosture (FontPosture fontPosture) {
        FontUtil.setFontPosture(listHeaderSelectedItemLabel, fontPosture);
        return this;
    }

    @Override
    public FDropdownList<T> fontPosture (String fontPosture) {
        FontUtil.setFontPosture(listHeaderSelectedItemLabel, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return listHeaderSelectedItemLabel.getFont().getStyle();
    }

    @Override
    public FDropdownList<T> fontWeight (FontWeight fontWeight) {
        FontUtil.setFontWeight(listHeaderSelectedItemLabel, fontWeight);
        return this;
    }

    @Override
    public FDropdownList<T> fontSize (double fontSize) {
        FontUtil.setFontSize(listHeaderSelectedItemLabel, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return listHeaderSelectedItemLabel.getFont().getSize();
    }

    @Override
    public FDropdownList<T> textAlignment (TextAlignment textAlignment) {
        return this;
    }

    // ScrollPane(listContentPane)-related methods
    public FDropdownList<T> content (Node node) {
        listContentPane.setContent(node);
        return this;
    }

    public FDropdownList<T> hBarPolicy (ScrollPane.ScrollBarPolicy scrollBarPolicy) {
        listContentPane.setHbarPolicy(scrollBarPolicy);
        return this;
    }

    public FDropdownList<T> vBarPolicy (ScrollPane.ScrollBarPolicy scrollBarPolicy) {
        listContentPane.setVbarPolicy(scrollBarPolicy);
        return this;
    }

    public FDropdownList<T> vMin (double value) {
        listContentPane.setVmin(value);
        return this;
    }

    public FDropdownList<T> vMax (double value) {
        listContentPane.setVmax(value);
        return this;
    }

    public FDropdownList<T> hMin (double value) {
        listContentPane.setHmin(value);
        return this;
    }

    public FDropdownList<T> hMax (double value) {
        listContentPane.setHmax(value);
        return this;
    }

    public FDropdownList<T> hValue (double value) {
        listContentPane.setHvalue(value);
        return this;
    }

    public FDropdownList<T> vValue (double value) {
        listContentPane.setVvalue(value);
        return this;
    }

    public FDropdownList<T> fitToWidth (boolean fit) {
        listContentPane.setFitToWidth(fit);
        return this;
    }

    public FDropdownList<T> fitToHeight (boolean fit) {
        listContentPane.setFitToHeight(fit);
        return this;
    }

    public FDropdownList<T> pannable (boolean pannable) {
        listContentPane.setPannable(pannable);
        return this;
    }

    public FDropdownList<T> minViewportWidth (double value) {
        listContentPane.setMinViewportWidth(value);
        return this;
    }

    public FDropdownList<T> minViewportHeight (double value) {
        listContentPane.setMinViewportHeight(value);
        return this;
    }

    public FDropdownList<T> prefViewportWidth (double value) {
        listContentPane.setPrefViewportWidth(value);
        return this;
    }

    public FDropdownList<T> prefViewportHeight (double value) {
        listContentPane.setPrefViewportHeight(value);
        return this;
    }

    public FDropdownList<T> viewportBounds (Bounds bounds) {
        listContentPane.setViewportBounds(bounds);
        return this;
    }

    // FDropdownList-related
    public FDropdownList<T> promptText (String text) {
        promptLabel.setText(text);
        return this;
    }

    public FDropdownList<T> setSelectedItem (T item) {
        setSelectedItem(itemsValues.indexOf(item));
        return this;
    }

    // FINISHED

    /**
     * Gives the possibility to select an item externally from the specified index.
     */
    public FDropdownList<T> setSelectedItem (int index) {
        if (index >= 0 && index < itemsValues.size()) {
            T item = itemsValues.get(index);
            Pane selectedIconParent;
            if (selectedItemPaneIcon != null) {
                selectedIconParent = (Pane) selectedItemPaneIcon.getParent();
                if (selectedIconParent != null) {
                    UIUtil.remove(selectedIconParent, selectedItemPaneIcon);
                }
                Pane newValueSelectedItemIconPane = selectedItemIconPanes.get(index);
                UIUtil.add(newValueSelectedItemIconPane, selectedItemPaneIcon);
                selectedItemPaneIcon.setPrefWidth(newValueSelectedItemIconPane.getPrefWidth() * selectedItemIconDimensionPercent);
                selectedItemPaneIcon.setPrefHeight(newValueSelectedItemIconPane.getPrefHeight() * selectedItemIconDimensionPercent);
                selectedItemPaneIcon.setLayoutX(newValueSelectedItemIconPane.getPrefWidth() / 2 - selectedItemPaneIcon.getPrefWidth() / 2);
                selectedItemPaneIcon.setLayoutY(newValueSelectedItemIconPane.getPrefHeight() / 2 - selectedItemPaneIcon.getPrefHeight() / 2);
            }
            else if (selectedItemImageViewIcon != null) {
                selectedIconParent = (Pane) selectedItemImageViewIcon.getParent();
                if (selectedIconParent != null) {
                    UIUtil.remove(selectedIconParent, selectedItemImageViewIcon);
                }
                Pane newValueSelectedItemIconPane = selectedItemIconPanes.get(index);
                UIUtil.add(newValueSelectedItemIconPane, selectedItemImageViewIcon);
                selectedItemImageViewIcon.setFitWidth(newValueSelectedItemIconPane.getPrefWidth() * selectedItemIconDimensionPercent);
                selectedItemImageViewIcon.setFitHeight(newValueSelectedItemIconPane.getPrefHeight() * selectedItemIconDimensionPercent);
                selectedItemImageViewIcon.setLayoutX(newValueSelectedItemIconPane.getPrefWidth() / 2 - selectedItemImageViewIcon.getFitWidth() / 2);
                selectedItemImageViewIcon.setLayoutY(newValueSelectedItemIconPane.getPrefHeight() / 2 - selectedItemImageViewIcon.getFitHeight() / 2);
            }
            selectedValueProperty.set(item);
            promptLabel.setVisible(false);
            if (item instanceof Integer || item instanceof Double || item instanceof String || item instanceof Character) {
                listHeaderSelectedItemLabel.setText(selectedValueProperty.get() + "");
                listHeaderSelectedItemLabel.setVisible(true);
            }
            else if (item instanceof Node) {
                // When item type is Node(To implement)
            }
        }
        return this;
    }

    // FINISHED

    /**
     * Sets the header icon as a Pane component(can change the shape using SVG code).
     *
     * @param icon The Pane to be displayed
     */
    public FDropdownList<T> setHeaderIcon (Pane icon) {
        UIUtil.removeAll(headerIconPane);
        UIUtil.add(headerIconPane, icon);
        imageViewHeaderIcon = null;
        headerPaneIcon = icon;
        double iconWidth = headerIconPane.getPrefWidth() * headerIconDimensionPercent;
        double iconHeight = headerIconPane.getPrefHeight() * headerIconDimensionPercent;
        headerPaneIcon.setPrefWidth(iconWidth);
        headerPaneIcon.setPrefHeight(iconHeight);
        headerPaneIcon.setLayoutX(headerIconPane.getPrefWidth() / 2 - iconWidth / 2);
        headerPaneIcon.setLayoutY(headerIconPane.getPrefHeight() / 2 - iconHeight / 2);
        return this;
    }

    // FINISHED

    /**
     * Sets the header icon as an ImageView icon.
     *
     * @param iconImage The image to be displayed
     */
    public FDropdownList<T> setHeaderIcon (Image iconImage) {
        ImageView icon = new ImageView();
        icon.setImage(iconImage);
        imageViewHeaderIcon = icon;
        UIUtil.removeAll(headerIconPane);
        UIUtil.add(headerIconPane, imageViewHeaderIcon);
        headerPaneIcon = null;
        double width = headerIconPane.getPrefWidth() * headerIconDimensionPercent;
        double height = headerIconPane.getPrefHeight() * headerIconDimensionPercent;
        imageViewHeaderIcon.setFitWidth(width);
        imageViewHeaderIcon.setFitHeight(height);
        imageViewHeaderIcon.setLayoutX(headerIconPane.getPrefWidth() / 2 - width / 2);
        imageViewHeaderIcon.setLayoutY(headerIconPane.getPrefHeight() / 2 - height / 2);
        return this;
    }

    /**
     * Sets the open button icon as a Pane component(can change the shape using SVG code).
     *
     * @param icon The Pane to be displayed
     */
    public FDropdownList<T> setOpenButtonIcon (Pane icon) {
        UIUtil.removeAll(openButtonPane);
        UIUtil.add(openButtonPane, icon);
        openButtonImageViewIcon = null;
        openButtonPaneIcon = icon;
        double iconWidth = openButtonPane.getPrefWidth() * headerIconDimensionPercent;
        double iconHeight = openButtonPane.getPrefHeight() * headerIconDimensionPercent;
        openButtonPaneIcon.setPrefWidth(iconWidth);
        openButtonPaneIcon.setPrefHeight(iconHeight);
        openButtonPaneIcon.setLayoutX(openButtonPane.getPrefWidth() / 2 - iconWidth / 2);
        openButtonPaneIcon.setLayoutY(openButtonPane.getPrefHeight() / 2 - iconHeight / 2);
        return this;
    }

    /**
     * Sets the open button icon as an ImageView icon.
     *
     * @param iconImage The image to be displayed
     */
    public FDropdownList<T> setOpenButtonIcon (Image iconImage) {
        ImageView icon = new ImageView();
        icon.setImage(iconImage);
        openButtonImageViewIcon = icon;
        UIUtil.removeAll(openButtonPane);
        UIUtil.add(openButtonPane, openButtonImageViewIcon);
        openButtonPaneIcon = null;
        double width = openButtonPane.getPrefWidth() * openButtonIconDimensionPercent;
        double height = openButtonPane.getPrefHeight() * openButtonIconDimensionPercent;
        openButtonImageViewIcon.setFitWidth(width);
        openButtonImageViewIcon.setFitHeight(height);
        openButtonImageViewIcon.setLayoutX(openButtonPane.getPrefWidth() / 2 - width / 2);
        openButtonImageViewIcon.setLayoutY(openButtonPane.getPrefHeight() / 2 - height / 2);
        return this;
    }

    // FINISHED

    /**
     * Sets the header icon to be visible or not.
     */
    public FDropdownList<T> displayHeaderIcon (boolean display) {
        headerIconPane.setVisible(display);
        return this;
    }

    // FINISHED

    /**
     * Sets the selected item icon to be visible or not.
     */
    public FDropdownList<T> displaySelectedItemIcon (boolean display) {
        displaySelectedItemIconProperty.set(display);
        selectedItemIconPanes.forEach(iconPane -> {
            iconPane.setVisible(display);
        });
        return this;
    }

    // FINISHED

    /**
     * Sets the item icon to be visible or not.
     */
    public FDropdownList<T> displayItemIcon (boolean display) {
        displayItemsIconProperty.set(display);
        itemIconPanes.forEach(iconPane -> {
            iconPane.setVisible(display);
        });
        return this;
    }

    // FINISHED

    /**
     * Sets the selected item icon as a Pane component(can change the shape using SVG code).
     *
     * @param icon The Pane to be displayed
     */
    public FDropdownList<T> setSelectedItemIcon (Pane icon) {
        selectedItemIconPanes.forEach(iconPane -> {
            UIUtil.removeAll(iconPane);
        });
        Pane selectedItemIconPane;
        selectedItemPaneIcon = icon;
        if (selectedValueProperty.get() != null) {
            int itemIndex = itemsValues.indexOf(selectedValueProperty.get());
            selectedItemIconPane = selectedItemIconPanes.get(itemIndex);
            UIUtil.add(selectedItemIconPane, selectedItemPaneIcon);
            double iconWidth = selectedItemIconPane.getPrefWidth() * selectedItemIconDimensionPercent;
            double iconHeight = selectedItemIconPane.getPrefHeight() * selectedItemIconDimensionPercent;
            selectedItemPaneIcon.setPrefWidth(iconWidth);
            selectedItemPaneIcon.setPrefHeight(iconHeight);
            selectedItemPaneIcon.setLayoutX(selectedItemIconPane.getPrefWidth() / 2 - iconWidth / 2);
            selectedItemPaneIcon.setLayoutY(selectedItemIconPane.getPrefHeight() / 2 - iconHeight / 2);
        }
        return this;
    }

    // FINISHED

    /**
     * Sets the selected item icon as an ImageView icon.
     *
     * @param icon The image to be displayed
     */
    public FDropdownList<T> setSelectedItemIcon (Image icon) {
        selectedItemIconPanes.forEach(iconPane -> {
            UIUtil.removeAll(iconPane);
        });
        Pane selectedItemIconPane;
        selectedItemImageViewIcon = new ImageView();
        selectedItemImageViewIcon.setImage(icon);
        if (selectedValueProperty.get() != null) {
            int itemIndex = itemsValues.indexOf(selectedValueProperty.get());
            selectedItemIconPane = selectedItemIconPanes.get(itemIndex);
            UIUtil.add(selectedItemIconPane, selectedItemImageViewIcon);
            double iconWidth = selectedItemIconPane.getPrefWidth() * selectedItemIconDimensionPercent;
            double iconHeight = selectedItemIconPane.getPrefHeight() * selectedItemIconDimensionPercent;
            selectedItemImageViewIcon.setFitWidth(iconWidth);
            selectedItemImageViewIcon.setFitHeight(iconHeight);
            selectedItemImageViewIcon.setLayoutX(selectedItemIconPane.getPrefWidth() / 2 - iconWidth / 2);
            selectedItemImageViewIcon.setLayoutY(selectedItemIconPane.getPrefHeight() / 2 - iconHeight / 2);
        }
        return this;
    }

    // FINISHED

    /**
     * Sets the item icon as a Pane component(can change the shape using SVG code).
     *
     * @param itemIndex Represents the item's index from the list
     * @param icon      The Pane to be displayed
     */
    public FDropdownList<T> setItemIcon (int itemIndex, Pane icon) {
        if (itemIndex >= 0 && itemIndex < itemIconPanes.size()) {
            Pane iconPane = itemIconPanes.get(itemIndex);
            UIUtil.removeAll(iconPane);
            UIUtil.add(iconPane, icon);
            double iconWidth = iconPane.getPrefWidth() * itemIconDimensionPercent;
            double iconHeight = iconPane.getPrefHeight() * itemIconDimensionPercent;
            icon.setPrefWidth(iconWidth);
            icon.setPrefHeight(iconHeight);
            icon.setLayoutX(iconPane.getPrefWidth() / 2 - iconWidth / 2);
            icon.setLayoutY(iconPane.getPrefHeight() / 2 - iconHeight / 2);
            // Updates both array lists because they have the same length every time
            if (itemPaneIcons.size() < itemIndex + 1) {
                for (int i = itemPaneIcons.size(); i < itemIndex + 1; i++) {
                    itemPaneIcons.add(i, null);
                    itemImageViewIcons.add(i, null);
                }
            }
            itemPaneIcons.set(itemIndex, icon);
            itemImageViewIcons.set(itemIndex, null);
            addItemIconPositioningEvent(iconPane);
        }
        return this;
    }

    // FINISHED

    /**
     * Sets the item icon as an ImageView icon.
     *
     * @param itemIndex Represents the item's index from the list
     * @param icon      The image to be displayed
     */
    public FDropdownList<T> setItemIcon (int itemIndex, Image icon) {
        if (itemIndex >= 0 && itemIndex < itemIconPanes.size()) {
            Pane iconPane = itemIconPanes.get(itemIndex);
            UIUtil.removeAll(iconPane);
            ImageView imageView = new ImageView();
            imageView.setImage(icon);
            UIUtil.add(iconPane, imageView);
            double iconWidth = iconPane.getPrefWidth() * itemIconDimensionPercent;
            double iconHeight = iconPane.getPrefHeight() * itemIconDimensionPercent;
            imageView.setFitWidth(iconWidth);
            imageView.setFitHeight(iconHeight);
            imageView.setLayoutX(iconPane.getPrefWidth() / 2 - iconWidth / 2);
            imageView.setLayoutY(iconPane.getPrefHeight() / 2 - iconHeight / 2);
            // Updates both array lists because they have the same length every time
            if (itemPaneIcons.size() < itemIndex + 1) {
                for (int i = itemPaneIcons.size(); i < itemIndex + 1; i++) {
                    itemPaneIcons.add(i, null);
                    itemImageViewIcons.add(i, null);
                }
            }
            itemImageViewIcons.set(itemIndex, imageView);
            itemPaneIcons.set(itemIndex, null);
            addItemIconPositioningEvent(iconPane);
        }
        return this;
    }

    // FINISHED

    /**
     * Sets the space between list items.
     */
    public FDropdownList<T> itemsSpacing (double value) {
        if (value >= 0) {
            itemsSpacing = value;
            rearrangeListItems();
        }
        return this;
    }

    // FINISHED
    private void rearrangeListItems () {
        double contentHeight = 0;
        for (int i = 0; i < listItemPanes.size(); i++) {
            Pane currItem = listItemPanes.get(i);
            currItem.setLayoutY(contentHeight);
            contentHeight += currItem.getPrefHeight() + itemsSpacing;
        }
        contentHeightProperty.set(contentHeight);
        double minHeightValue = Math.max(contentHeightProperty.get() - itemsSpacing, expandHeight - 2);
        listContent.setPrefHeight(minHeightValue);
    }

    // FINISHED

    /**
     * Sets the open button to be visible or not.
     */
    public FDropdownList<T> displayOpenButton (boolean display) {
        openButtonPane.setVisible(display);
        return this;
    }

    // FINISHED

    /**
     * Sets the prompt text to be visible or not.
     */
    public FDropdownList<T> displayPromptText (boolean display) {
        displayPromptTextProperty.set(display);
        displayCurrentSelectedItemProperty.set(!display);
        return this;
    }

    // FINISHED

    /**
     * Displays the current selected item in the list header if there is<br>
     * and item selected.
     */
    public FDropdownList<T> displayCurrentSelectedItem (boolean display) {
        displayCurrentSelectedItemProperty.set(display);
        displayPromptTextProperty.set(!display);
        return this;
    }

    // FINISHED
    public FDropdownList<T> setHeaderIconDimensionPercent (int value) {
        if (value >= 0 && value <= 100) {
            headerIconDimensionPercent = (double) value / 100;
            if (headerPaneIcon != null) {
                headerPaneIcon.setPrefWidth(headerIconPane.getPrefWidth() * headerIconDimensionPercent);
                headerPaneIcon.setPrefHeight(headerIconPane.getPrefHeight() * headerIconDimensionPercent);
                headerPaneIcon.setLayoutX(headerIconPane.getPrefWidth() / 2 - headerPaneIcon.getPrefWidth() / 2);
                headerPaneIcon.setLayoutY(headerIconPane.getPrefHeight() / 2 - headerPaneIcon.getPrefHeight() / 2);
            }
            else if (imageViewHeaderIcon != null) {
                imageViewHeaderIcon.setFitWidth(headerIconPane.getPrefWidth() * headerIconDimensionPercent);
                imageViewHeaderIcon.setFitHeight(headerIconPane.getPrefHeight() * headerIconDimensionPercent);
                imageViewHeaderIcon.setLayoutX(headerIconPane.getPrefWidth() / 2 - imageViewHeaderIcon.getFitWidth() / 2);
                imageViewHeaderIcon.setLayoutY(headerIconPane.getPrefHeight() / 2 - imageViewHeaderIcon.getFitHeight() / 2);
            }
        }
        return this;
    }

    // FINISHED
    public FDropdownList<T> setSelectedItemIconDimensionPercent (int value) {
        if (value >= 0 && value <= 100) {
            selectedItemIconDimensionPercent = (double) value / 100;
            if (selectedItemPaneIcon != null) {
                Pane parent = (Pane) selectedItemPaneIcon.getParent();
                if (parent != null) {
                    selectedItemPaneIcon.setPrefWidth(parent.getPrefWidth() * selectedItemIconDimensionPercent);
                    selectedItemPaneIcon.setPrefHeight(parent.getPrefHeight() * selectedItemIconDimensionPercent);
                    selectedItemPaneIcon.setLayoutX(parent.getPrefWidth() / 2 - selectedItemPaneIcon.getPrefWidth() / 2);
                    selectedItemPaneIcon.setLayoutY(parent.getPrefHeight() / 2 - selectedItemPaneIcon.getPrefHeight() / 2);
                }
            }
            else if (selectedItemImageViewIcon != null) {
                Pane parent = (Pane) selectedItemImageViewIcon.getParent();
                if (parent != null) {
                    selectedItemImageViewIcon.setFitWidth(parent.getPrefWidth() * selectedItemIconDimensionPercent);
                    selectedItemImageViewIcon.setFitHeight(parent.getPrefHeight() * selectedItemIconDimensionPercent);
                    selectedItemImageViewIcon.setLayoutX(parent.getPrefWidth() / 2 - selectedItemImageViewIcon.getFitWidth() / 2);
                    selectedItemImageViewIcon.setLayoutY(parent.getPrefHeight() / 2 - selectedItemImageViewIcon.getFitHeight() / 2);
                }
            }
        }
        return this;
    }

    public FDropdownList<T> setOpenButtonIconDimensionPercent (int value) {
        if (value >= 0 && value <= 100) {
            openButtonIconDimensionPercent = (double) value / 100;
            if (openButtonPaneIcon != null) {
                openButtonPaneIcon.setPrefWidth(openButtonPane.getPrefWidth() * openButtonIconDimensionPercent);
                openButtonPaneIcon.setPrefHeight(openButtonPane.getPrefHeight() * openButtonIconDimensionPercent);
                openButtonPaneIcon.setLayoutX(openButtonPane.getPrefWidth() / 2 - openButtonPaneIcon.getPrefWidth() / 2);
                openButtonPaneIcon.setLayoutY(openButtonPane.getPrefHeight() / 2 - openButtonPaneIcon.getPrefHeight() / 2);
            }
            else if (openButtonImageViewIcon != null) {
                openButtonImageViewIcon.setFitWidth(openButtonPane.getPrefWidth() * openButtonIconDimensionPercent);
                openButtonImageViewIcon.setFitHeight(openButtonPane.getPrefHeight() * openButtonIconDimensionPercent);
                openButtonImageViewIcon.setLayoutX(openButtonPane.getPrefWidth() / 2 - openButtonImageViewIcon.getFitWidth() / 2);
                openButtonImageViewIcon.setLayoutY(openButtonPane.getPrefHeight() / 2 - openButtonImageViewIcon.getFitHeight() / 2);
            }
        }
        return this;
    }

    // FINISHED
    public FDropdownList<T> setItemIconDimensionPercent (int value) {
        if (value >= 0 && value <= 100) {
            itemIconDimensionPercent = (double) value / 100;
            for (int i = 0; i < itemIconPanes.size(); i++) {
                Pane currItemIconPane = itemIconPanes.get(i);
                Pane paneIcon = null;
                ImageView imageViewIcon = null;
                if (i < itemPaneIcons.size() && i < itemImageViewIcons.size()) {
                    paneIcon = itemPaneIcons.get(i);
                    imageViewIcon = itemImageViewIcons.get(i);
                    if (paneIcon != null) {
                        paneIcon.setPrefWidth(currItemIconPane.getPrefWidth() * itemIconDimensionPercent);
                        paneIcon.setPrefHeight(currItemIconPane.getPrefHeight() * itemIconDimensionPercent);
                        paneIcon.setLayoutX(currItemIconPane.getPrefWidth() / 2 - paneIcon.getPrefWidth() / 2);
                        paneIcon.setLayoutY(currItemIconPane.getPrefHeight() / 2 - paneIcon.getPrefHeight() / 2);
                    }
                    else if (imageViewIcon != null) {
                        imageViewIcon.setFitWidth(currItemIconPane.getPrefWidth() * itemIconDimensionPercent);
                        imageViewIcon.setFitHeight(currItemIconPane.getPrefHeight() * itemIconDimensionPercent);
                        imageViewIcon.setLayoutX(currItemIconPane.getPrefWidth() / 2 - imageViewIcon.getFitWidth() / 2);
                        imageViewIcon.setLayoutY(currItemIconPane.getPrefHeight() / 2 - imageViewIcon.getFitHeight() / 2);
                    }
                }
            }
        }
        return this;
    }

    public BooleanProperty getListIsOpenedProperty () {
        return listIsOpenedProperty;
    }

    public ObjectProperty<T> getSelectedValueProperty () {
        return selectedValueProperty;
    }

    public DoubleProperty getContentHeightProperty () {
        return contentHeightProperty;
    }

    /**
     * Returns the height value the listContentPane(ScrollPane) will have<br>
     * when the list is opened. Useful for adding animation for opening the list.
     */
    public double getExpandHeight () {
        return expandHeight;
    }

    public List<Pane> getListItemPanes () {
        return List.copyOf(listItemPanes);
    }
}
