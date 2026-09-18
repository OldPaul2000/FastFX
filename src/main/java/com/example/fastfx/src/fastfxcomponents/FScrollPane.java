package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.stylehelpers.ComponentStyleTracker;
import com.example.fastfx.src.stylehelpers.MainProps;
import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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

public class FScrollPane extends ScrollPane implements FComponent {
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

    public FScrollPane () {
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

    public FScrollPane verticalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane horizontalScrollBar (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalScrollBar);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane verticalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(verticalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane horizontalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(horizontalThumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane incrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane decrementVButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane incrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane decrementHButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane incrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane decrementVArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementVArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane incrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane decrementHArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementHArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollPane corner (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(corner);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    @Override
    public FScrollPane x (double value) {
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
    public FScrollPane y (double value) {
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
    public FScrollPane width (double value) {
        if (styleTracker.isAllowedProperty(MainProps.WIDTH)) {
            styleTracker.getCurrentComponent().setPrefWidth(value);
        }
        return this;
    }

    @Override
    public FScrollPane height (double value) {
        if (styleTracker.isAllowedProperty(MainProps.HEIGHT)) {
            styleTracker.getCurrentComponent().setPrefHeight(value);
        }
        return this;
    }

    @Override
    public FScrollPane scaleX (double value) {
        styleTracker.getCurrentComponent().setScaleX(value);
        return this;
    }

    @Override
    public FScrollPane scaleY (double value) {
        styleTracker.getCurrentComponent().setScaleY(value);
        return this;
    }

    @Override
    public FScrollPane translateX (double value) {
        styleTracker.getCurrentComponent().setTranslateX(value);
        return this;
    }

    @Override
    public FScrollPane translateY (double value) {
        styleTracker.getCurrentComponent().setTranslateY(value);
        return this;
    }

    @Override
    public FScrollPane rotate (double value) {
        styleTracker.getCurrentComponent().setRotate(value);
        return this;
    }

    @Override
    public FScrollPane padding (double value) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollPane padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FScrollPane topPadding (double value) {
        PaddingUtil.setTopPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane rightPadding (double value) {
        PaddingUtil.setRightPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomPadding (double value) {
        PaddingUtil.setBottomPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane leftPadding (double value) {
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
    public FScrollPane margin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(value);
        }
        return this;
    }

    @Override
    public FScrollPane margin (double topBottom, double rightLeft) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(topBottom, rightLeft);
        }
        return this;
    }

    @Override
    public FScrollPane margin (double top, double right, double bottom, double left) {
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
    public FScrollPane topMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setTop(value);
        }
        return this;
    }

    @Override
    public FScrollPane rightMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setRight(value);
        }
        return this;
    }

    @Override
    public FScrollPane bottomMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setBottom(value);
        }
        return this;
    }

    @Override
    public FScrollPane leftMargin (double value) {
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
    public FScrollPane bgColor (String color) {
        BackgroundUtil.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollPane bgColor (Color color) {
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
    public FScrollPane bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FScrollPane bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FScrollPane bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FScrollPane topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomLeftBgRadius (double value) {
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
    public FScrollPane bgInsets (double value) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollPane bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FScrollPane topInset (double value) {
        BackgroundUtil.setTopInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane rightInset (double value) {
        BackgroundUtil.setRightInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomInset (double value) {
        BackgroundUtil.setBottomInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane leftInset (double value) {
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
    public FScrollPane alignment(Pos pos){
        // do nothing
        return this;
    }

    @Override
    public FScrollPane border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FScrollPane border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FScrollPane topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollPane rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollPane bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollPane leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollPane topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollPane rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollPane bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollPane leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollPane borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FScrollPane topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FScrollPane rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FScrollPane bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FScrollPane leftBorderStyle (String borderStyle) {
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
    public FScrollPane borderWidth (double value) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollPane borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FScrollPane topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane leftBorderWidth (double value) {
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
    public FScrollPane borderColor (String colorValue) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FScrollPane borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollPane borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FScrollPane topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FScrollPane rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FScrollPane bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FScrollPane leftBorderColor (String colorValue) {
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
    public FScrollPane borderColor (Color color) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollPane borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollPane borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, top, right);
        return this;
    }

    @Override
    public FScrollPane topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollPane rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollPane bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollPane leftBorderColor (Color color) {
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
    public FScrollPane borderOpacity (double value) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollPane borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FScrollPane topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane leftBorderOpacity (double value) {
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
    public FScrollPane borderRadius (double value) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollPane borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FScrollPane topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomLeftBorderRadius (double value) {
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
    public FScrollPane borderInsets (double value) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollPane borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FScrollPane topBorderInset (double value) {
        BorderUtil.setTopBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollPane leftBorderInset (double value) {
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
    public FScrollPane removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public FScrollPane removeTopBorder () {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FScrollPane removeRightBorder () {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FScrollPane removeBottomBorder () {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FScrollPane removeLeftBorder () {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FScrollPane shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        styleTracker.getCurrentComponent().setShape(svgPath);
        return this;
    }

    @Override
    public FScrollPane shape (Shape shape) {
        styleTracker.getCurrentComponent().setShape(shape);
        return this;
    }

    // ScrollPane-related methods
    public FScrollPane content (Node node) {
        setContent(node);
        return this;
    }

    public FScrollPane hBarPolicy (ScrollBarPolicy scrollBarPolicy) {
        setHbarPolicy(scrollBarPolicy);
        return this;
    }

    public FScrollPane vBarPolicy (ScrollBarPolicy scrollBarPolicy) {
        setVbarPolicy(scrollBarPolicy);
        return this;
    }

    public FScrollPane vMin (double value) {
        setVmin(value);
        return this;
    }

    public FScrollPane vMax (double value) {
        setVmax(value);
        return this;
    }

    public FScrollPane hMin (double value) {
        setHmin(value);
        return this;
    }

    public FScrollPane hMax (double value) {
        setHmax(value);
        return this;
    }

    public FScrollPane hValue (double value) {
        setHvalue(value);
        return this;
    }

    public FScrollPane vValue (double value) {
        setVvalue(value);
        return this;
    }

    public FScrollPane fitToWidth (boolean fit) {
        setFitToWidth(fit);
        return this;
    }

    public FScrollPane fitToHeight (boolean fit) {
        setFitToHeight(fit);
        return this;
    }

    public FScrollPane pannable (boolean pannable) {
        setPannable(pannable);
        return this;
    }

    public FScrollPane minViewportWidth (double value) {
        setMinViewportWidth(value);
        return this;
    }

    public FScrollPane minViewportHeight (double value) {
        setMinViewportHeight(value);
        return this;
    }

    public FScrollPane prefViewportWidth (double value) {
        setPrefViewportWidth(value);
        return this;
    }

    public FScrollPane prefViewportHeight (double value) {
        setPrefViewportHeight(value);
        return this;
    }

    public FScrollPane viewportBounds (Bounds bounds) {
        setViewportBounds(bounds);
        return this;
    }
}