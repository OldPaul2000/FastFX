package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.cssstylehelpers.ComponentStyleTracker;
import com.example.fastfx.src.cssstylehelpers.MainProps;
import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.skin.ScrollBarSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

import java.util.HashSet;
import java.util.Set;

public class FScrollBar extends ScrollBar implements FComponent {
    private Margin margin;
    private Region thumb;
    private Region incrementButton;
    private Region decrementButton;
    private Region incrementArrow;
    private Region decrementArrow;

    private ComponentStyleTracker styleTracker;

    public FScrollBar () {
        this.margin = new Margin(0, 0, 0, 0);
        this.styleTracker = new ComponentStyleTracker();
        initElements();
        initAllowedStyleProperties();
    }

    private void initElements () {
        setSkin(new ScrollBarSkin(this));
        this.thumb = (Region) lookup(".thumb");
        this.incrementButton = (Region) lookup(".increment-button");
        this.decrementButton = (Region) lookup(".decrement-button");
        this.incrementArrow = (Region) lookup(".increment-arrow");
        this.decrementArrow = (Region) lookup(".decrement-arrow");
    }

    private void initAllowedStyleProperties () {
        Set<String> notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(this, notAllowedProperties);
        styleTracker.setCurrentComponent(this);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        notAllowedProperties.add(MainProps.TRANSLATE_X);
        notAllowedProperties.add(MainProps.TRANSLATE_Y);
        notAllowedProperties.add(MainProps.WIDTH);
        notAllowedProperties.add(MainProps.HEIGHT);
        styleTracker.addComponent(thumb, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(decrementButton, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(incrementArrow, notAllowedProperties);

        notAllowedProperties = new HashSet<>();
        notAllowedProperties.add(MainProps.X);
        notAllowedProperties.add(MainProps.Y);
        styleTracker.addComponent(decrementArrow, notAllowedProperties);
    }

    public Region getThumb () {
        return thumb;
    }

    public Region getIncrementButton () {
        return incrementButton;
    }

    public Region getDecrementButton () {
        return decrementButton;
    }

    public Region getIncrementArrow () {
        return incrementArrow;
    }

    public Region getDecrementArrow () {
        return decrementArrow;
    }

    public FScrollBar horizontalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(thumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollBar incrementButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollBar decrementButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollBar incrementArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public FScrollBar decrementArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    @Override
    public FScrollBar x (double value) {
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
    public FScrollBar y (double value) {
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
    public FScrollBar width (double value) {
        if (styleTracker.isAllowedProperty(MainProps.WIDTH)) {
            styleTracker.getCurrentComponent().setPrefWidth(value);
        }
        return this;
    }

    @Override
    public FScrollBar height (double value) {
        if (styleTracker.isAllowedProperty(MainProps.HEIGHT)) {
            styleTracker.getCurrentComponent().setPrefHeight(value);
        }
        return this;
    }

    @Override
    public FScrollBar scaleX (double value) {
        styleTracker.getCurrentComponent().setScaleX(value);
        return this;
    }

    @Override
    public FScrollBar scaleY (double value) {
        styleTracker.getCurrentComponent().setScaleY(value);
        return this;
    }

    @Override
    public FScrollBar translateX (double value) {
        styleTracker.getCurrentComponent().setTranslateX(value);
        return this;
    }

    @Override
    public FScrollBar translateY (double value) {
        styleTracker.getCurrentComponent().setTranslateY(value);
        return this;
    }

    @Override
    public FScrollBar rotate (double value) {
        styleTracker.getCurrentComponent().setRotate(value);
        return this;
    }

    @Override
    public FScrollBar padding (double value) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollBar padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FScrollBar topPadding (double value) {
        PaddingUtil.setTopPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar rightPadding (double value) {
        PaddingUtil.setRightPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomPadding (double value) {
        PaddingUtil.setBottomPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar leftPadding (double value) {
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
    public FScrollBar margin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(value);
        }
        return this;
    }

    @Override
    public FScrollBar margin (double topBottom, double rightLeft) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(topBottom, rightLeft);
        }
        return this;
    }

    @Override
    public FScrollBar margin (double top, double right, double bottom, double left) {
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
    public FScrollBar topMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setTop(value);
        }
        return this;
    }

    @Override
    public FScrollBar rightMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setRight(value);
        }
        return this;
    }

    @Override
    public FScrollBar bottomMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setBottom(value);
        }
        return this;
    }

    @Override
    public FScrollBar leftMargin (double value) {
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
    public FScrollBar bgColor (String color) {
        BackgroundUtil.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollBar bgColor (Color color) {
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
    public FScrollBar bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FScrollBar bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FScrollBar bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FScrollBar topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomLeftBgRadius (double value) {
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
    public FScrollBar bgInsets (double value) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollBar bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FScrollBar topInset (double value) {
        BackgroundUtil.setTopInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar rightInset (double value) {
        BackgroundUtil.setRightInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomInset (double value) {
        BackgroundUtil.setBottomInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar leftInset (double value) {
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
    public FScrollBar border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FScrollBar border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FScrollBar topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollBar rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollBar bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollBar leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollBar topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollBar rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollBar bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollBar leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FScrollBar borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FScrollBar topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FScrollBar rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FScrollBar bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public FScrollBar leftBorderStyle (String borderStyle) {
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
    public FScrollBar borderWidth (double value) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollBar borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FScrollBar topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar leftBorderWidth (double value) {
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
    public FScrollBar borderColor (String colorValue) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FScrollBar borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollBar borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FScrollBar topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FScrollBar rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FScrollBar bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public FScrollBar leftBorderColor (String colorValue) {
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
    public FScrollBar borderColor (Color color) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollBar borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollBar borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(styleTracker.getCurrentComponent(), top, right, top, right);
        return this;
    }

    @Override
    public FScrollBar topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollBar rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollBar bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public FScrollBar leftBorderColor (Color color) {
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
    public FScrollBar borderOpacity (double value) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollBar borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public FScrollBar topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar leftBorderOpacity (double value) {
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
    public FScrollBar borderRadius (double value) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollBar borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FScrollBar topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomLeftBorderRadius (double value) {
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
    public FScrollBar borderInsets (double value) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FScrollBar borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FScrollBar topBorderInset (double value) {
        BorderUtil.setTopBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public FScrollBar leftBorderInset (double value) {
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
    public FScrollBar removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public FScrollBar removeTopBorder () {
        BorderUtil.setTopBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FScrollBar removeRightBorder () {
        BorderUtil.setRightBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FScrollBar removeBottomBorder () {
        BorderUtil.setBottomBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FScrollBar removeLeftBorder () {
        BorderUtil.setLeftBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderUtil.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public FScrollBar shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        styleTracker.getCurrentComponent().setShape(svgPath);
        return this;
    }

    @Override
    public FScrollBar shape (Shape shape) {
        styleTracker.getCurrentComponent().setShape(shape);
        return this;
    }

    public FScrollBar orientation (Orientation orientation) {
        setOrientation(orientation);
        return this;
    }

    public void add (Region component) {
        getChildren().add(component);
    }

    public void remove (Region component) {
        getChildren().remove(component);
    }
}
