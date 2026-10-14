package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.layouts.Padding;
import com.example.fastfx.src.stylehelpers.ComponentStyleTracker;
import com.example.fastfx.src.stylehelpers.MainProps;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import com.example.fastfx.src.util.ui.UIUtil;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.*;

public class FTabPane extends Pane implements FLabeled {
    private static final double DEFAULT_TABS_BAR_HEIGHT = 20;
    private static final double DEFAULT_TABS_WIDTH = 80;
    private static final double DEFAULT_TABS_SPACING = 5;
    private static final double DEFAULT_ICONS_DIMENSION_PERCENTAGE = 0.75;
    private static final String DEFAULT_CLOSE_BUTTON_SVG_ICON = "M20.7457 3.32851C20.3552 2.93798 19.722 2.93798 19.3315 3.32851L12.0371 10.6229L4.74275 3.32851C4.35223 2.93798 3.71906 2.93798 3.32854 3.32851C2.93801 3.71903 2.93801 4.3522 3.32854 4.74272L10.6229 12.0371L3.32856 19.3314C2.93803 19.722 2.93803 20.3551 3.32856 20.7457C3.71908 21.1362 4.35225 21.1362 4.74277 20.7457L12.0371 13.4513L19.3315 20.7457C19.722 21.1362 20.3552 21.1362 20.7457 20.7457C21.1362 20.3551 21.1362 19.722 20.7457 19.3315L13.4513 12.0371L20.7457 4.74272C21.1362 4.3522 21.1362 3.71903 20.7457 3.32851Z";

    private Margin margin;
    private ScrollPane tabsBar;
    private Pane tabsBarContent;
    private List<Pane> tabs;
    private List<Pane> tabsCloseButtonPanes;
    private Map<Pane, Label> tabsLabels;
    private Map<Pane, Pane> tabsIconPanes;
    private Map<Pane, Node> tabsIcons;
    private Map<Pane, Pane> tabsCloseButtonIconPanes;
    private Map<Pane, Node> tabsCloseButtonIcons;
    private Map<Pane, ScrollPane> tabPaneViewComponents;
    private Map<String, Integer> tabsNames;

    private Padding tabsBarPadding;
    private Padding tabsBarContentPadding;
    private Map<Pane, Padding> tabsPadding;
    private Padding tabPanePadding;

    private ScrollPane currVisibleViewComponent;
    private double tabsBarHeight;
    private double tabsWidth;
    private double tabsHeight;
    private double tabsSpacing;
    private double tabsIconDimensionPercent;
    private double closeButtonsIconDimensionPercent;
    private double totalTabsBarContentWidth;

    private ComponentStyleTracker styleTracker;

    public FTabPane () {
        this.margin = new Margin(0);
        this.tabsBarPadding = new Padding(0);
        this.tabsBarContentPadding = new Padding(0);
        this.tabsPadding = new HashMap<>();
        this.tabPanePadding = new Padding(0);
        this.styleTracker = new ComponentStyleTracker();

        this.tabsBarHeight = DEFAULT_TABS_BAR_HEIGHT;
        this.tabsWidth = DEFAULT_TABS_WIDTH;
        this.tabsSpacing = DEFAULT_TABS_SPACING;
        this.tabsIconDimensionPercent = DEFAULT_ICONS_DIMENSION_PERCENTAGE;
        this.closeButtonsIconDimensionPercent = DEFAULT_ICONS_DIMENSION_PERCENTAGE;
        this.totalTabsBarContentWidth = 0;

        this.tabsNames = new HashMap<>();
        initElements();
        initComponentsLayoutBehavior();
        initTabSelectionEvent();
        initTabsDragMovementEvent();
        initAllowedElementsProperties();
    }

    private void initElements () {
        this.tabsBar = new ScrollPane();
        this.tabsBarContent = new Pane();
        this.tabs = new ArrayList<>();
        this.tabsLabels = new HashMap<>();
        this.tabsCloseButtonPanes = new ArrayList<>();
        this.tabsIconPanes = new HashMap<>();
        this.tabsIcons = new HashMap<>();
        this.tabsCloseButtonIconPanes = new HashMap<>();
        this.tabsCloseButtonIcons = new HashMap<>();
        this.tabPaneViewComponents = new HashMap<>();
        createTabsBar();
    }

    private void createTabsBar () {
        tabsBar.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            tabsBarContent.setPrefHeight((double) newVal);
            tabs.forEach(tab -> {
                tab.setPrefHeight((double) newVal);
            });
        });
        BackgroundUtil.setBackgroundColor(tabsBarContent, Color.RED);
        tabsBar.setPrefHeight(DEFAULT_TABS_BAR_HEIGHT);
        tabsBar.setLayoutX(0);
        tabsBar.setLayoutY(0);
        tabsBar.setContent(tabsBarContent);
        tabsBar.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        tabsBar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        UIUtil.add(this, tabsBar);
    }

    private void initComponentsLayoutBehavior () {
        prefWidthProperty().addListener((prop, oldVal, newVal) -> {
            double width = (double) newVal - tabPanePadding.getLeft() - tabPanePadding.getRight();
            tabsBar.setPrefWidth(width);
            if (currVisibleViewComponent != null) {
                currVisibleViewComponent.setPrefWidth(width);
            }
        });
        prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            double viewComponentHeight = (double) newVal - tabsBar.getPrefHeight() - tabPanePadding.getTop() - tabPanePadding.getBottom();
            if (currVisibleViewComponent != null) {
                currVisibleViewComponent.setPrefHeight(viewComponentHeight);
            }
        });
    }

    private void initTabSelectionEvent () {
        tabsBarContent.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (!mouseWasMoved) {
                Node currEl = (Node) e.getTarget();
                Pane tab = null;
                Pane tabCloseButton = null;
                while (currEl != null) {
                    if (currEl == tabsBarContent) {
                        break;
                    }
                    else if (currEl instanceof Pane && tabs.contains(currEl)) {
                        tab = (Pane) currEl;
                    }
                    else if (currEl instanceof Pane && tabsCloseButtonPanes.contains(currEl)) {
                        tabCloseButton = (Pane) currEl;
                    }
                    currEl = currEl.getParent();
                }
                if (tabCloseButton == null && tab != null) {
                    currVisibleViewComponent.setVisible(false);
                    ScrollPane currViewPane = tabPaneViewComponents.get(tab);
                    currViewPane.setVisible(true);
                    currVisibleViewComponent = currViewPane;
                }
                else if (tab != null) {
                    closeTab(tabs.indexOf(tab));
                }
            }
        });
    }

    private Node currTabForDrag;
    private final double TAB_MOVE_FLAG_OFFSET_PERCENT = 0.5;
    private double currTabForDragMouseXPositionOffset;
    private int lastTabIndex;
    private double lastTabLayoutX;
    private boolean mouseWasMoved = false;
    private boolean isCloseButtonPressed;
    private double lastX;

    private void initTabsDragMovementEvent () {
        tabsBarContent.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            mouseWasMoved = false;
            currTabForDrag = (Node) e.getTarget();
            while (true) {
                if (currTabForDrag == tabsBarContent) {
                    break;
                }
                else if (currTabForDrag instanceof Pane && tabs.contains((Pane) currTabForDrag)) {
                    isCloseButtonPressed = false;
                    lastTabIndex = tabs.indexOf(currTabForDrag);
                    lastTabLayoutX = currTabForDrag.getLayoutX();
                    setCurrentTabViewOrder(lastTabIndex);
                    break;
                }
                else if (currTabForDrag instanceof Pane && tabsCloseButtonPanes.contains(currTabForDrag)) {
                    isCloseButtonPressed = true;
                    break;
                }
                currTabForDrag = currTabForDrag.getParent();
            }
        });
        tabsBarContent.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            mouseWasMoved = true;
            if (!isCloseButtonPressed) {
                double x = Math.max(e.getX() - currTabForDragMouseXPositionOffset, tabsBarContentPadding.getLeft());
                int barToReplaceIndex;
                barToReplaceIndex = (int) ((x + tabsWidth * TAB_MOVE_FLAG_OFFSET_PERCENT) / (tabsWidth + tabsSpacing));
                if (barToReplaceIndex < tabs.size() && barToReplaceIndex != lastTabIndex) {
                    Pane temp = tabs.get(lastTabIndex);
                    tabs.set(lastTabIndex, tabs.get(barToReplaceIndex));
                    tabs.set(barToReplaceIndex, temp);
                    double tempX = lastTabLayoutX;
                    tabs.get(barToReplaceIndex).setLayoutX(tabs.get(lastTabIndex).getLayoutX());
                    tabs.get(lastTabIndex).setLayoutX(tempX);
                    lastTabIndex = barToReplaceIndex;
                    lastTabLayoutX = tabs.get(lastTabIndex).getLayoutX();
                }
                currTabForDrag.setLayoutX(x);
                lastX = e.getX();
            }
        });
        tabsBarContent.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (!tabs.isEmpty() && !isCloseButtonPressed) {
                tabs.get(lastTabIndex).setLayoutX(tabsBarContentPadding.getLeft() + lastTabIndex * (tabsWidth + tabsSpacing));
            }
        });
    }

    private void setCurrentTabViewOrder (int currTabIndex) {
        for (int i = 0; i < tabs.size(); i++) {
            if (i == currTabIndex) {
                tabs.get(i).setViewOrder(0);
            }
            else {
                tabs.get(i).setViewOrder(1);
            }
        }
    }

    /**
     * Adds a component to be displayed in the tab pane and at the same time<br>
     * it adds a new tab in the tabs bar.
     *
     * @param viewComponent the component to be displayed(Pane, Button, etc.)
     * @param tabName       the displayed text inside the tab
     *
     */
    public FTabPane addTab (String tabName, Node viewComponent) {
        return addTab(tabName, null, viewComponent);
    }

    /**
     * Adds a component to be displayed in the tab pane and at the same time<br>
     * it adds a new tab in the tabs bar.
     *
     * @param viewComponent the component to be displayed(Pane, Button, etc.)
     * @param tabIcon       the displayed icon inside the tab
     * @param tabName       the displayed text inside the tab
     *
     */
    public FTabPane addTab (String tabName, Node tabIcon, Node viewComponent) {
        Pane tab = createNewTab(tabName, tabIcon);
        tabs.add(tab);
        tab.setLayoutX(totalTabsBarContentWidth);
        tab.setLayoutY(tabsBarContentPadding.getTop());
        totalTabsBarContentWidth += tabsWidth + tabsSpacing;
        double minTabsBarContentWidth = Math.max(totalTabsBarContentWidth, tabsBar.getPrefWidth());
        tabsBarContent.setPrefWidth(minTabsBarContentWidth);
        ScrollPane scrollPaneView = new ScrollPane();
        scrollPaneView.setContent(viewComponent);
        if (currVisibleViewComponent != null) {
            currVisibleViewComponent.setVisible(false);
        }
        scrollPaneView.setVisible(true);
        tabPaneViewComponents.put(tab, scrollPaneView);
        scrollPaneView.setPrefWidth(getPrefWidth() - tabPanePadding.getLeft() - tabPanePadding.getRight());
        scrollPaneView.setPrefHeight(getPrefHeight() - tabsBar.getPrefHeight() - tabPanePadding.getTop() - tabPanePadding.getBottom());
        scrollPaneView.setLayoutX(tabPanePadding.getLeft());
        scrollPaneView.setLayoutY(tabsBar.getPrefHeight() + tabPanePadding.getTop());
        currVisibleViewComponent = scrollPaneView;
        UIUtil.add(tabsBarContent, tab);
        UIUtil.add(this, scrollPaneView);
        return this;
    }

    private Pane createNewTab (String tabName, Node tabIcon) {
        Pane tab = new Pane();
        Pane iconPane = new Pane();
        Label tabNameLabel = new Label(createTabName(tabName));
        Pane closeButtonPane = new Pane();
        Pane closeButtonIcon = new Pane();
        tabsIconPanes.put(tab, iconPane);
        tabsCloseButtonPanes.add(closeButtonPane);
        tabsCloseButtonIconPanes.put(tab, closeButtonPane);
        tabsCloseButtonIcons.put(tab, closeButtonIcon);
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(DEFAULT_CLOSE_BUTTON_SVG_ICON);
        closeButtonIcon.setShape(svgPath);
        Padding padding = new Padding(0);
        tabsPadding.put(tab, padding);
        tab.prefWidthProperty().addListener((prop, oldVal, newVal) -> {
            tabNameLabel.setPrefWidth((double) newVal - padding.getLeft() - iconPane.getPrefWidth() - closeButtonPane.getPrefWidth() - padding.getRight());
            closeButtonPane.setLayoutX(padding.getLeft() + iconPane.getPrefWidth() + tabNameLabel.getPrefWidth());
        });
        tab.prefHeightProperty().addListener((prop, oldVal, newVal) -> {
            double tabElementsHeight = (double) newVal - padding.getTop() - padding.getBottom();
            iconPane.setPrefWidth(tabElementsHeight);
            iconPane.setPrefHeight(tabElementsHeight);
            tabNameLabel.setPrefHeight(tabElementsHeight);
            closeButtonPane.setPrefWidth(tabElementsHeight);
            closeButtonPane.setPrefHeight(tabElementsHeight);
            tabNameLabel.setPrefWidth(tab.getPrefWidth() - iconPane.getPrefWidth() - closeButtonPane.getPrefWidth());
            tabNameLabel.setLayoutX(tabsPadding.get(tab).getLeft() + iconPane.getPrefWidth());
            closeButtonPane.setLayoutX(tabsPadding.get(tab).getLeft() + iconPane.getPrefWidth() + tabNameLabel.getPrefWidth());
            Node currTabIcon = tabsIcons.get(tab);
            double tabIconWidth = (double) newVal * tabsIconDimensionPercent;
            double tabIconHeight = (double) newVal * tabsIconDimensionPercent;
            double tabIconX = (double) newVal / 2 - tabIconWidth / 2;
            double tabIconY = (double) newVal / 2 - tabIconHeight / 2;
            if (currTabIcon != null) {
                if (currTabIcon instanceof ImageView) {
                    ((ImageView) currTabIcon).setFitWidth(tabIconWidth);
                    ((ImageView) currTabIcon).setFitHeight(tabIconWidth);
                }
                else {
                    ((Region) currTabIcon).setPrefWidth(tabIconWidth);
                    ((Region) currTabIcon).setPrefHeight(tabIconHeight);
                }
                currTabIcon.setLayoutX(tabIconX);
                currTabIcon.setLayoutY(tabIconY);
            }
            Node currCloseButtonIcon = tabsCloseButtonIcons.get(tab);
            double closeButtonIconWidth = (double) newVal * tabsIconDimensionPercent;
            double closeButtonIconHeight = (double) newVal * tabsIconDimensionPercent;
            double closeButtonX = (double) newVal / 2 - closeButtonIconWidth / 2;
            double closeButtonY = (double) newVal / 2 - closeButtonIconHeight / 2;
            if (currCloseButtonIcon instanceof ImageView) {
                ((ImageView) currCloseButtonIcon).setFitWidth(closeButtonIconWidth);
                ((ImageView) currCloseButtonIcon).setFitHeight(closeButtonIconWidth);
            }
            else {
                ((Region) currCloseButtonIcon).setPrefWidth(closeButtonIconWidth);
                ((Region) currCloseButtonIcon).setPrefHeight(closeButtonIconHeight);
            }
            currCloseButtonIcon.setLayoutX(closeButtonX);
            currCloseButtonIcon.setLayoutY(closeButtonY);
        });
        tab.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            currTabForDragMouseXPositionOffset = e.getX();
        });
        tab.setPrefWidth(tabsWidth);
        tab.setPrefHeight(tabsBar.getPrefHeight() - tabsBarContentPadding.getTop() - tabsBarContentPadding.getBottom());

        //        BackgroundUtil.setBackgroundOpacity(iconPane, 0);
        //        BackgroundUtil.setBackgroundOpacity(tabNameLabel, 0);
        //        BackgroundUtil.setBackgroundOpacity(closeButtonPane, 0);
        BackgroundUtil.setBackgroundColor(iconPane, Color.BLACK);
        BackgroundUtil.setBackgroundColor(tabNameLabel, Color.ORANGE);
        BackgroundUtil.setBackgroundColor(closeButtonIcon, Color.BLUE);
        BackgroundUtil.setBackgroundColor(tab, Color.GREEN);

        if (tabIcon != null) {
            UIUtil.add(iconPane, tabIcon);
        }
        UIUtil.add(closeButtonPane, closeButtonIcon);
        UIUtil.add(tab, iconPane);
        UIUtil.add(tab, tabNameLabel);
        UIUtil.add(tab, closeButtonPane);
        return tab;
    }

    /**
     * Stores the tab name and also updates the index of the tab name.<br>
     * It also returns the new created tab name when a new tab is added<br>
     * along with its index if there are more tabs with the same name.<br>
     * e.g. Cars -> Cars(2) -> Cars(3)...
     *
     * @param name the user input name
     */
    private String createTabName (String name) {
        int index = 0;
        if (tabsNames.containsKey(name)) {
            index = tabsNames.get(name) + 1;
        }
        tabsNames.put(name, index);
        if (index > 0) {
            name += " (" + index + ")";
        }
        return name;
    }

    public FTabPane tabsBarHeight (double value) {
        tabsBar.setPrefHeight(value);
        return this;
    }

    public FTabPane tabsWidth (double value) {
        tabsWidth = value;
        tabs.forEach(tab -> {
            tab.setPrefWidth(value);
        });
        rearrangeTabs();
        return this;
    }

    public FTabPane tabsSpacing (double value) {
        tabsSpacing = value;
        rearrangeTabs();
        return this;
    }

    private void rearrangeTabs () {
        totalTabsBarContentWidth = tabsBarContentPadding.getLeft();
        for (int i = 0; i < tabs.size(); i++) {
            Pane currTab = tabs.get(i);
            currTab.setLayoutX(totalTabsBarContentWidth);
            totalTabsBarContentWidth += currTab.getPrefWidth() + tabsSpacing;
        }
    }

    /**
     * Closes a tab from the specified index.
     *
     * @param index represents the tab index in the order of displayed tabs
     */
    public FTabPane closeTab (int index) {
        if (index >= 0 && !tabs.isEmpty()) {
            Pane currTab = tabs.get(index);
            if (currVisibleViewComponent != null) {
                currVisibleViewComponent.setVisible(false);
            }
            if (tabs.size() == 1) {
                currVisibleViewComponent = null;
            }
            else if (index < tabs.size() - 1) {
                Pane nextTab = tabs.get(index + 1);
                currVisibleViewComponent = tabPaneViewComponents.get(nextTab);
                currVisibleViewComponent.setVisible(true);
            }
            else if (index == tabs.size() - 1) {
                Pane prevTab = tabs.get(index - 1);
                currVisibleViewComponent = tabPaneViewComponents.get(prevTab);
                currVisibleViewComponent.setVisible(true);
            }
            UIUtil.remove(tabsBarContent, currTab);
            tabsCloseButtonPanes.remove(tabsCloseButtonIconPanes.get(currTab));
            tabs.remove(currTab);
            tabsLabels.remove(currTab);
            tabsIconPanes.remove(currTab);
            tabsIcons.remove(currTab);
            rearrangeTabs();
        }
        return this;
    }

    public FTabPane setCloseButtonIcon (Image image) {
        tabsIconPanes.forEach((tab, iconPane) -> {
            Node currIcon = tabsIcons.get(tab);
            UIUtil.remove(iconPane, currIcon);
            ImageView icon = new ImageView();
            icon.setImage(image);
            tabsIcons.put(tab, icon);
            double width = iconPane.getPrefWidth() * closeButtonsIconDimensionPercent;
            double height = iconPane.getPrefHeight() * closeButtonsIconDimensionPercent;
            double x = iconPane.getPrefWidth() / 2 - width / 2;
            double y = iconPane.getPrefHeight() / 2 - height / 2;
            icon.setFitWidth(width);
            icon.setFitHeight(height);
            icon.setLayoutX(x);
            icon.setLayoutY(y);
            UIUtil.add(iconPane, icon);
        });
        return this;
    }

    public FTabPane setCloseButtonIcon (Shape shape) {
        tabsIconPanes.forEach((tab, iconPane) -> {
            Node currIcon = tabsIcons.get(tab);
            UIUtil.remove(iconPane, currIcon);
            Pane icon = new Pane();
            icon.setShape(shape);
            tabsIcons.put(tab, icon);
            double width = iconPane.getPrefWidth() * closeButtonsIconDimensionPercent;
            double height = iconPane.getPrefHeight() * closeButtonsIconDimensionPercent;
            double x = iconPane.getPrefWidth() / 2 - width / 2;
            double y = iconPane.getPrefHeight() / 2 - height / 2;
            icon.setPrefWidth(width);
            icon.setPrefHeight(height);
            icon.setLayoutX(x);
            icon.setLayoutY(y);
            UIUtil.add(iconPane, icon);
        });
        return this;
    }

    public FTabPane setTabIcon (int tabIndex, Shape shape) {
        if (tabIndex >= 0 && tabIndex < tabs.size()) {
            Pane currTab = tabs.get(tabIndex);
            Pane currTabIconPane = tabsIconPanes.get(currTab);
            Node currTabIcon = tabsIcons.get(currTab);
            UIUtil.remove(currTabIconPane, currTabIcon);
            Pane icon = new Pane();
            icon.setShape(shape);
            tabsIcons.put(currTab, icon);
            double width = currTabIconPane.getPrefWidth() * tabsIconDimensionPercent;
            double height = currTabIconPane.getPrefHeight() * tabsIconDimensionPercent;
            double x = currTabIconPane.getPrefWidth() / 2 - width / 2;
            double y = currTabIconPane.getPrefHeight() / 2 - height / 2;
            icon.setPrefWidth(width);
            icon.setPrefHeight(height);
            icon.setLayoutX(x);
            icon.setLayoutY(y);
            UIUtil.add(currTabIconPane, icon);
        }
        return this;
    }

    public FTabPane setTabIcon (int tabIndex, Image image) {
        if (tabIndex >= 0 && tabIndex < tabs.size()) {
            Pane currTab = tabs.get(tabIndex);
            Pane currTabIconPane = tabsIconPanes.get(currTab);
            Node currTabIcon = tabsIcons.get(currTab);
            UIUtil.remove(currTabIconPane, currTabIcon);
            ImageView icon = new ImageView();
            icon.setImage(image);
            tabsIcons.put(currTab, icon);
            double width = currTabIconPane.getPrefWidth() * tabsIconDimensionPercent;
            double height = currTabIconPane.getPrefHeight() * tabsIconDimensionPercent;
            double x = currTabIconPane.getPrefWidth() / 2 - width / 2;
            double y = currTabIconPane.getPrefHeight() / 2 - height / 2;
            icon.setFitWidth(width);
            icon.setFitHeight(height);
            icon.setLayoutX(x);
            icon.setLayoutY(y);
            UIUtil.add(currTabIconPane, icon);
        }
        return this;
    }

    public FTabPane setTabIconsSizePercent (int value) {
        if (value >= 0 && value <= 100) {
            tabsIconDimensionPercent = (double) value / 100;
            tabsIconPanes.forEach((tab, iconPane) -> {
                if (!iconPane.getChildren().isEmpty()) {
                    Node icon = iconPane.getChildren().get(0);
                    double width = iconPane.getPrefWidth() * tabsIconDimensionPercent;
                    double height = iconPane.getPrefHeight() * tabsIconDimensionPercent;
                    double x = iconPane.getPrefWidth() / 2 - width / 2;
                    double y = iconPane.getPrefHeight() / 2 - height / 2;
                    if (icon instanceof ImageView) {
                        ((ImageView) icon).setFitWidth(width);
                        ((ImageView) icon).setFitHeight(height);
                    }
                    else {
                        ((Region) icon).setPrefWidth(width);
                        ((Region) icon).setPrefHeight(height);
                    }
                    icon.setLayoutX(x);
                    icon.setLayoutY(y);
                }
            });
        }
        return this;
    }

    public FTabPane setCloseButtonsSizePercent (int value) {
        if (value >= 0 && value <= 100) {
            closeButtonsIconDimensionPercent = (double) value / 100;
            tabsCloseButtonIconPanes.forEach((tab, iconPane) -> {
                if (!iconPane.getChildren().isEmpty()) {
                    Node icon = iconPane.getChildren().get(0);
                    double width = iconPane.getPrefWidth() * closeButtonsIconDimensionPercent;
                    double height = iconPane.getPrefHeight() * closeButtonsIconDimensionPercent;
                    double x = iconPane.getPrefWidth() / 2 - width / 2;
                    double y = iconPane.getPrefHeight() / 2 - height / 2;
                    if (icon instanceof ImageView) {
                        ((ImageView) icon).setFitWidth(width);
                        ((ImageView) icon).setFitHeight(height);
                    }
                    else {
                        ((Region) icon).setPrefWidth(width);
                        ((Region) icon).setPrefHeight(height);
                    }
                    icon.setLayoutX(x);
                    icon.setLayoutY(y);
                }
            });
        }
        return this;
    }

    private void initAllowedElementsProperties () {
        Set<String> notAllowedProperties = new HashSet<>();
        styleTracker.addComponent(this, notAllowedProperties);
        styleTracker.setCurrentComponent(this);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.SCALE_X);
        notAllowedProperties.add(MainProps.SCALE_Y);
        notAllowedProperties.add(MainProps.WIDTH);
        styleTracker.addComponent(tabsBar, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.SCALE_X);
        notAllowedProperties.add(MainProps.SCALE_Y);
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(tabsBarContent, notAllowedProperties);
    }

    public FTabPane tabsBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(tabsBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTabPane tabsBarContent (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(tabsBarContent);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTabPane tabs (PseudoElements pseudoElements) {
        for (int i = 0; i < tabs.size(); i++) {
            styleTracker.setCurrentComponent(tabs.get(i));
            pseudoElements.apply(i);
        }
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTabPane tabsLabels (PseudoElements pseudoElements) {
        for (int i = 0; i < tabsLabels.size(); i++) {
            styleTracker.setCurrentComponent(tabsLabels.get(i));
            pseudoElements.apply(i);
        }
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FTabPane tabsIconPanes (PseudoElements pseudoElements) {
        for (int i = 0; i < tabsIconPanes.size(); i++) {
            styleTracker.setCurrentComponent(tabsIconPanes.get(i));
            pseudoElements.apply(i);
        }
        styleTracker.setCurrentComponent(this);
        return this;
    }

    @Override
    public FTabPane x (double value) {
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
    public FTabPane y (double value) {
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
    public FTabPane width (double value) {
        if (styleTracker.isAllowedProperty(MainProps.WIDTH)) {
            styleTracker.getCurrentComponent().setPrefWidth(value);
        }
        return this;
    }

    @Override
    public FTabPane height (double value) {
        if (styleTracker.isAllowedProperty(MainProps.HEIGHT)) {
            styleTracker.getCurrentComponent().setPrefHeight(value);
        }
        return this;
    }

    @Override
    public FTabPane scaleX (double value) {
        if (styleTracker.isAllowedProperty(MainProps.SCALE_X)) {
            styleTracker.getCurrentComponent().setScaleX(value);
        }
        return this;
    }

    @Override
    public FTabPane scaleY (double value) {
        if (styleTracker.isAllowedProperty(MainProps.SCALE_Y)) {
            styleTracker.getCurrentComponent().setScaleY(value);
        }
        return this;
    }

    @Override
    public FTabPane translateX (double value) {
        styleTracker.getCurrentComponent().setTranslateX(value);
        return this;
    }

    @Override
    public FTabPane translateY (double value) {
        styleTracker.getCurrentComponent().setTranslateY(value);
        return this;
    }

    @Override
    public FTabPane rotate (double value) {
        styleTracker.getCurrentComponent().setRotate(value);
        return this;
    }

    @Override
    public FTabPane padding (double value) {
        if (styleTracker.isAllowedProperty(MainProps.PADDING)) {
            if (styleTracker.getCurrentComponent() == tabsBarContent) {
                tabsBarContentPadding.set(value);
                rearrangeTabs();
            }
            else {
                PaddingUtil.setPadding(styleTracker.getCurrentComponent(), value);
            }
        }
        return this;
    }

    @Override
    public FTabPane padding (double topBottom, double rightLeft) {
        if (styleTracker.isAllowedProperty(MainProps.PADDING)) {
            if (styleTracker.getCurrentComponent() == tabsBarContent) {
                tabsBarContentPadding.set(topBottom, rightLeft);
                rearrangeTabs();
            }
            else {
                PaddingUtil.setPadding(styleTracker.getCurrentComponent(), topBottom, rightLeft);
            }
        }
        return this;
    }

    @Override
    public FTabPane padding (double top, double right, double bottom, double left) {
        if (styleTracker.isAllowedProperty(MainProps.PADDING)) {
            if (styleTracker.getCurrentComponent() == tabsBarContent) {
                tabsBarContentPadding.set(top, right, bottom, left);
                rearrangeTabs();
            }
            else {
                PaddingUtil.setPadding(styleTracker.getCurrentComponent(), top, right, bottom, left);
            }
        }
        return this;
    }

    @Override
    public FTabPane topPadding (double value) {
        if (styleTracker.isAllowedProperty(MainProps.PADDING)) {
            if (styleTracker.getCurrentComponent() == tabsBarContent) {
                tabsBarContentPadding.setTop(value);
                rearrangeTabs();
            }
            else {
                PaddingUtil.setTopPadding(styleTracker.getCurrentComponent(), value);
            }
        }
        return this;
    }

    @Override
    public FTabPane rightPadding (double value) {
        if (styleTracker.isAllowedProperty(MainProps.PADDING)) {
            if (styleTracker.getCurrentComponent() == tabsBarContent) {
                tabsBarContentPadding.setRight(value);
                rearrangeTabs();
            }
            else {
                PaddingUtil.setRightPadding(styleTracker.getCurrentComponent(), value);
            }
        }
        return this;
    }

    @Override
    public FTabPane bottomPadding (double value) {
        if (styleTracker.isAllowedProperty(MainProps.PADDING)) {
            if (styleTracker.getCurrentComponent() == tabsBarContent) {
                tabsBarContentPadding.setBottom(value);
                rearrangeTabs();
            }
            else {
                PaddingUtil.setBottomPadding(styleTracker.getCurrentComponent(), value);
            }
        }
        return this;
    }

    @Override
    public FTabPane leftPadding (double value) {
        if (styleTracker.isAllowedProperty(MainProps.PADDING)) {
            if (styleTracker.getCurrentComponent() == tabsBarContent) {
                tabsBarContentPadding.setLeft(value);
                rearrangeTabs();
            }
            else {
                PaddingUtil.setLeftPadding(styleTracker.getCurrentComponent(), value);
            }
        }
        return this;
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
    public FTabPane margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FTabPane margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FTabPane margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FTabPane topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FTabPane rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FTabPane bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FTabPane leftMargin (double value) {
        margin.setLeft(value);
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
    public FTabPane bgColor (String color) {
        BackgroundUtil.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FTabPane bgColor (Color color) {
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
    public FTabPane bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FTabPane bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FTabPane bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FTabPane topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bottomLeftBgRadius (double value) {
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
    public FTabPane bgInsets (double value) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FTabPane bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FTabPane topInset (double value) {
        BackgroundUtil.setTopInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane rightInset (double value) {
        BackgroundUtil.setRightInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bottomInset (double value) {
        BackgroundUtil.setBottomInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane leftInset (double value) {
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
    public FTabPane alignment (Pos pos) {
        //        setAlignment(pos);
        return this;
    }

    @Override
    public FTabPane border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FTabPane border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FTabPane topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTabPane rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTabPane bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTabPane leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTabPane topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTabPane rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTabPane bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTabPane leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FTabPane borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FTabPane topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FTabPane rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FTabPane bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FTabPane leftBorderStyle (String borderStyle) {
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
    public FTabPane borderWidth (double value) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTabPane borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FTabPane topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane leftBorderWidth (double value) {
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
    public FTabPane borderColor (String colorValue) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FTabPane borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTabPane borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FTabPane topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FTabPane rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FTabPane bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FTabPane leftBorderColor (String colorValue) {
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
    public FTabPane borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FTabPane borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTabPane borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, top, right);
        return this;
    }

    @Override
    public FTabPane topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FTabPane rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FTabPane bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FTabPane leftBorderColor (Color color) {
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
    public FTabPane borderOpacity (double value) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FTabPane borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FTabPane topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane leftBorderOpacity (double value) {
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
    public FTabPane borderRadius (double value) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTabPane borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FTabPane topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bottomLeftBorderRadius (double value) {
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
    public FTabPane borderInsets (double value) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FTabPane borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FTabPane topBorderInset (double value) {
        BorderUtil.setTopBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FTabPane leftBorderInset (double value) {
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
    public FTabPane removeBorder () {
        styleTracker.getCurrentComponent().setBorder(null);
        return this;
    }

    @Override
    public FTabPane removeTopBorder () {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FTabPane removeRightBorder () {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FTabPane removeBottomBorder () {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FTabPane removeLeftBorder () {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FTabPane shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        styleTracker.getCurrentComponent().setShape(svgPath);
        return this;
    }

    @Override
    public FTabPane shape (Shape shape) {
        styleTracker.getCurrentComponent().setShape(shape);
        return this;
    }

    @Override
    public FTabPane font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        //        FontUtil.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public FTabPane font (String fontFamily, FontPosture fontPosture, double fontSize) {
        //        FontUtil.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public FTabPane font (String fontFamily, FontWeight fontWeight, double fontSize) {
        //        FontUtil.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public FTabPane font (String fontFamily, double fontSize) {
        //        FontUtil.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public FTabPane fontColor (Color color) {
        //        FontUtil.setFontColor(this, color);
        return this;
    }

    @Override
    public FTabPane fontColor (String color) {
        //        FontUtil.setFontColor(this, color);
        return this;
    }

    @Override
    public Color getFontColor () {
        //        return FontUtil.getFontColor(this);
        return null;
    }

    @Override
    public String getFontColorHex () {
        //        return FontUtil.getFontColorHex(this);
        return "";
    }

    @Override
    public FTabPane fontFamily (String fontFamily) {
        //        FontUtil.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public FTabPane fontPosture (FontPosture fontPosture) {
        //        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public FTabPane fontPosture (String fontPosture) {
        //        FontUtil.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        //        return getFont().getStyle();
        return "";
    }

    @Override
    public FTabPane fontWeight (FontWeight fontWeight) {
        //        FontUtil.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public FTabPane fontSize (double fontSize) {
        //        FontUtil.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        //        return getFont().getSize();
        return 0;
    }

    @Override
    public FTabPane textAlignment (TextAlignment textAlignment) {
        //        setTextAlignment(textAlignment);
        return this;
    }

    // ScrollPane(tabsBar)-related methods
    public FTabPane content (Node node) {
        tabsBar.setContent(node);
        return this;
    }

    public FTabPane hBarPolicy (ScrollPane.ScrollBarPolicy scrollBarPolicy) {
        tabsBar.setHbarPolicy(scrollBarPolicy);
        return this;
    }

    public FTabPane vBarPolicy (ScrollPane.ScrollBarPolicy scrollBarPolicy) {
        tabsBar.setVbarPolicy(scrollBarPolicy);
        return this;
    }

    public FTabPane vMin (double value) {
        tabsBar.setVmin(value);
        return this;
    }

    public FTabPane vMax (double value) {
        tabsBar.setVmax(value);
        return this;
    }

    public FTabPane hMin (double value) {
        tabsBar.setHmin(value);
        return this;
    }

    public FTabPane hMax (double value) {
        tabsBar.setHmax(value);
        return this;
    }

    public FTabPane hValue (double value) {
        tabsBar.setHvalue(value);
        return this;
    }

    public FTabPane vValue (double value) {
        tabsBar.setVvalue(value);
        return this;
    }

    public FTabPane fitToWidth (boolean fit) {
        tabsBar.setFitToWidth(fit);
        return this;
    }

    public FTabPane fitToHeight (boolean fit) {
        tabsBar.setFitToHeight(fit);
        return this;
    }

    public FTabPane pannable (boolean pannable) {
        tabsBar.setPannable(pannable);
        return this;
    }

    public FTabPane minViewportWidth (double value) {
        tabsBar.setMinViewportWidth(value);
        return this;
    }

    public FTabPane minViewportHeight (double value) {
        tabsBar.setMinViewportHeight(value);
        return this;
    }

    public FTabPane prefViewportWidth (double value) {
        tabsBar.setPrefViewportWidth(value);
        return this;
    }

    public FTabPane prefViewportHeight (double value) {
        tabsBar.setPrefViewportHeight(value);
        return this;
    }

    public FTabPane viewportBounds (Bounds bounds) {
        tabsBar.setViewportBounds(bounds);
        return this;
    }
}
