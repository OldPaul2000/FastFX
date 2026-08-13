package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.simplecomponents.stylinghelpers.ComponentStyleTracker;
import com.example.fastfx.src.simplecomponents.stylinghelpers.MainProps;
import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.ScrollBarSkin;
import javafx.scene.control.skin.ScrollPaneSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import java.util.HashSet;
import java.util.Set;

public class SScrollPane extends ScrollPane implements SComponent {
    private Margin margin;
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

    public SScrollPane () {
        this.margin = new Margin(0, 0, 0, 0);
        this.styleTracker = new ComponentStyleTracker();
        initElements();
        initAllowedStyleProperties();
    }

    private void initElements () {
        setSkin(new ScrollPaneSkin(this));
        Set<Node> scrollBars = lookupAll(".scroll-bar");
        scrollBars.forEach(el -> {
            ScrollBar currScrollBar = (ScrollBar) el;
            if (currScrollBar.getOrientation() == Orientation.VERTICAL) {
                this.verticalScrollBar = currScrollBar;
            } else if (currScrollBar.getOrientation() == Orientation.HORIZONTAL) {
                this.horizontalScrollBar = currScrollBar;
            }
        });
        verticalScrollBar.setSkin(new ScrollBarSkin(verticalScrollBar));
        horizontalScrollBar.setSkin(new ScrollBarSkin(horizontalScrollBar));
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
        this.corner = (StackPane) lookup(".corner");
    }

    private void initAllowedStyleProperties () {
        Set<String> notAllowedProperties = new HashSet<>();
        styleTracker.addComponent(this, notAllowedProperties);
        styleTracker.setCurrentComponent(this);

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

    public SScrollPane verticalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane horizontalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane verticalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane horizontalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane incrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane decrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane incrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane decrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane incrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane decrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane incrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane decrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollPane corner (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(corner);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    @Override
    public SScrollPane x (double value) {
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
    public SScrollPane y (double value) {
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
    public SScrollPane width (double value) {
        if (styleTracker.isAllowedProperty(MainProps.WIDTH)) {
            styleTracker.getCurrentComponent().setPrefWidth(value);
        }
        return this;
    }

    @Override
    public SScrollPane height (double value) {
        if (styleTracker.isAllowedProperty(MainProps.HEIGHT)) {
            styleTracker.getCurrentComponent().setPrefHeight(value);
        }
        return this;
    }

    @Override
    public SScrollPane scaleX (double value) {
        styleTracker.getCurrentComponent().setScaleX(value);
        return this;
    }

    @Override
    public SScrollPane scaleY (double value) {
        styleTracker.getCurrentComponent().setScaleY(value);
        return this;
    }

    @Override
    public SScrollPane translateX (double value) {
        styleTracker.getCurrentComponent().setTranslateX(value);
        return this;
    }

    @Override
    public SScrollPane translateY (double value) {
        styleTracker.getCurrentComponent().setTranslateY(value);
        return this;
    }

    @Override
    public SScrollPane rotate (double value) {
        styleTracker.getCurrentComponent().setRotate(value);
        return this;
    }

    @Override
    public SScrollPane padding (double value) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollPane padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public SScrollPane topPadding (double value) {
        PaddingUtil.setTopPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane rightPadding (double value) {
        PaddingUtil.setRightPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomPadding (double value) {
        PaddingUtil.setBottomPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane leftPadding (double value) {
        PaddingUtil.setLeftPadding(styleTracker.getCurrentComponent(), value);
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
    public SScrollPane margin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(value);
        }
        return this;
    }

    @Override
    public SScrollPane margin (double topBottom, double rightLeft) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(topBottom, rightLeft);
        }
        return this;
    }

    @Override
    public SScrollPane margin (double top, double right, double bottom, double left) {
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
    public SScrollPane topMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setTop(value);
        }
        return this;
    }

    @Override
    public SScrollPane rightMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setRight(value);
        }
        return this;
    }

    @Override
    public SScrollPane bottomMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setBottom(value);
        }
        return this;
    }

    @Override
    public SScrollPane leftMargin (double value) {
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
    public SScrollPane bgColor (String color) {
        BackgroundSetter.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollPane bgColor (Color color) {
        BackgroundSetter.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public String getBgColorHex () {
        return BackgroundSetter.getBackgroundHexColor(this);
    }

    @Override
    public Color getBgColor () {
        return BackgroundSetter.getBackgroundColor(this);
    }

    @Override
    public SScrollPane bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public SScrollPane bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public SScrollPane bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public SScrollPane topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomLeftBgRadius (double value) {
        BackgroundSetter.setBottomLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopLeftBgRadius () {
        return BackgroundSetter.getTopLeftBackgroundRadius(this);
    }

    @Override
    public double getTopRightBgRadius () {
        return BackgroundSetter.getTopRightBackgroundRadius(this);
    }

    @Override
    public double getBottomRightBgRadius () {
        return BackgroundSetter.getBottomRightBackgroundRadius(this);
    }

    @Override
    public double getBottomLeftBgRadius () {
        return BackgroundSetter.getBottomLeftBackgroundRadius(this);
    }

    @Override
    public SScrollPane bgInsets (double value) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollPane bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public SScrollPane topInset (double value) {
        BackgroundSetter.setTopInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane rightInset (double value) {
        BackgroundSetter.setRightInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomInset (double value) {
        BackgroundSetter.setBottomInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane leftInset (double value) {
        BackgroundSetter.setLeftInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopInset () {
        return BackgroundSetter.getBackgroundInsets(this).getTop();
    }

    @Override
    public double getRightInset () {
        return BackgroundSetter.getBackgroundInsets(this).getRight();
    }

    @Override
    public double getBottomInset () {
        return BackgroundSetter.getBackgroundInsets(this).getBottom();
    }

    @Override
    public double getLeftInset () {
        return BackgroundSetter.getBackgroundInsets(this).getLeft();
    }

    @Override
    public SScrollPane border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public SScrollPane border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public SScrollPane topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollPane rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollPane bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollPane leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollPane topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollPane rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollPane bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollPane leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollPane borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public SScrollPane topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public SScrollPane rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public SScrollPane bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public SScrollPane leftBorderStyle (String borderStyle) {
        BorderSetter.setLeftBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public String getTopBorderStyle () {
        return BorderSetter.getTopBorderStyle(this);
    }

    @Override
    public String getRightBorderStyle () {
        return BorderSetter.getRightBorderStyle(this);
    }

    @Override
    public String getBottomBorderStyle () {
        return BorderSetter.getBottomBorderStyle(this);
    }

    @Override
    public String getLeftBorderStyle () {
        return BorderSetter.getLeftBorderStyle(this);
    }

    @Override
    public SScrollPane borderWidth (double value) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollPane borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public SScrollPane topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane leftBorderWidth (double value) {
        BorderSetter.setLeftBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopBorderWidth () {
        return BorderSetter.getTopBorderWidth(this);
    }

    @Override
    public double getRightBorderWidth () {
        return BorderSetter.getRightBorderWidth(this);
    }

    @Override
    public double getBottomBorderWidth () {
        return BorderSetter.getBottomBorderWidth(this);
    }

    @Override
    public double getLeftBorderWidth () {
        return BorderSetter.getLeftBorderWidth(this);
    }

    @Override
    public SScrollPane borderColor (String colorValue) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public SScrollPane borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollPane borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public SScrollPane topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public SScrollPane rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public SScrollPane bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public SScrollPane leftBorderColor (String colorValue) {
        BorderSetter.setLeftBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public String getTopBorderColorHex () {
        return BorderSetter.getTopBorderColor(this);
    }

    @Override
    public String getRightBorderColorHex () {
        return BorderSetter.getRightBorderColor(this);
    }

    @Override
    public String getBottomBorderColorHex () {
        return BorderSetter.getBottomBorderColor(this);
    }

    @Override
    public String getLeftBorderColorHex () {
        return BorderSetter.getLeftBorderColor(this);
    }

    @Override
    public SScrollPane borderColor (Color color) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollPane borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollPane borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), top, right, top, right);
        return this;
    }

    @Override
    public SScrollPane topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollPane rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollPane bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollPane leftBorderColor (Color color) {
        BorderSetter.setLeftBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public Color getTopBorderColor () {
        return Color.valueOf(BorderSetter.getTopBorderColor(this));
    }

    @Override
    public Color getRightBorderColor () {
        return Color.valueOf(BorderSetter.getRightBorderColor(this));
    }

    @Override
    public Color getBottomBorderColor () {
        return Color.valueOf(BorderSetter.getBottomBorderColor(this));
    }

    @Override
    public Color getLeftBorderColor () {
        return Color.valueOf(BorderSetter.getLeftBorderColor(this));
    }

    @Override
    public SScrollPane borderOpacity (double value) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollPane borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public SScrollPane topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane leftBorderOpacity (double value) {
        BorderSetter.setLeftBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopBorderOpacity () {
        return BorderSetter.getTopBorderOpacity(this);
    }

    @Override
    public double getRightBorderOpacity () {
        return BorderSetter.getRightBorderOpacity(this);
    }

    @Override
    public double getBottomBorderOpacity () {
        return BorderSetter.getBottomBorderOpacity(this);
    }

    @Override
    public double getLeftBorderOpacity () {
        return BorderSetter.getLeftBorderOpacity(this);
    }

    @Override
    public SScrollPane borderRadius (double value) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollPane borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public SScrollPane topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomLeftBorderRadius (double value) {
        BorderSetter.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopLeftBorderRadius () {
        return BorderSetter.getBorderRadius(this).getTopLeftHorizontalRadius();
    }

    @Override
    public double getTopRightBorderRadius () {
        return BorderSetter.getBorderRadius(this).getTopRightHorizontalRadius();
    }

    @Override
    public double getBottomRightBorderRadius () {
        return BorderSetter.getBorderRadius(this).getBottomRightHorizontalRadius();
    }

    @Override
    public double getBottomLeftBorderRadius () {
        return BorderSetter.getBorderRadius(this).getBottomLeftHorizontalRadius();
    }

    @Override
    public SScrollPane borderInsets (double value) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollPane borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public SScrollPane topBorderInset (double value) {
        BorderSetter.setTopBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollPane leftBorderInset (double value) {
        BorderSetter.setLeftBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getTopBorderInset () {
        return BorderSetter.getBorderInsets(this).getTop();
    }

    @Override
    public double getRightBorderInset () {
        return BorderSetter.getBorderInsets(this).getRight();
    }

    @Override
    public double getBottomBorderInset () {
        return BorderSetter.getBorderInsets(this).getBottom();
    }

    @Override
    public double getLeftBorderInset () {
        return BorderSetter.getBorderInsets(this).getLeft();
    }

    @Override
    public SScrollPane removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public SScrollPane removeTopBorder () {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public SScrollPane removeRightBorder () {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public SScrollPane removeBottomBorder () {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public SScrollPane removeLeftBorder () {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public SScrollPane shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        styleTracker.getCurrentComponent().setShape(svgPath);
        return this;
    }

    @Override
    public SScrollPane shape (Shape shape) {
        styleTracker.getCurrentComponent().setShape(shape);
        return this;
    }

    // ScrollPane-related methods
    public SScrollPane content(Node node){
        setContent(node);
        return this;
    }

    public SScrollPane hBarPolicy(ScrollBarPolicy scrollBarPolicy){
        setHbarPolicy(scrollBarPolicy);
        return this;
    }

    public SScrollPane vBarPolicy(ScrollBarPolicy scrollBarPolicy){
        setVbarPolicy(scrollBarPolicy);
        return this;
    }

    public SScrollPane vMin(double value){
        setVmin(value);
        return this;
    }

    public SScrollPane vMax(double value){
        setVmax(value);
        return this;
    }

    public SScrollPane hMin(double value){
        setHmin(value);
        return this;
    }

    public SScrollPane hMax(double value){
        setHmax(value);
        return this;
    }

    public SScrollPane hValue(double value){
        setHvalue(value);
        return this;
    }

    public SScrollPane vValue(double value){
        setVvalue(value);
        return this;
    }

    public SScrollPane fitToWidth(boolean fit){
        setFitToWidth(fit);
        return this;
    }

    public SScrollPane fitToHeight(boolean fit){
        setFitToHeight(fit);
        return this;
    }

    public SScrollPane pannable(boolean pannable){
        setPannable(pannable);
        return this;
    }

    public SScrollPane minViewportWidth(double value){
        setMinViewportWidth(value);
        return this;
    }

    public SScrollPane minViewportHeight(double value){
        setMinViewportHeight(value);
        return this;
    }

    public SScrollPane prefViewportWidth(double value){
        setPrefViewportWidth(value);
        return this;
    }

    public SScrollPane prefViewportHeight(double value){
        setPrefViewportHeight(value);
        return this;
    }

    public SScrollPane viewportBounds(Bounds bounds){
        setViewportBounds(bounds);
        return this;
    }
}