package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.simplecomponents.stylinghelpers.ComponentStyleTracker;
import com.example.fastfx.src.simplecomponents.stylinghelpers.MainProps;
import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
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

public class SScrollBar extends ScrollBar implements SComponent {
    private Margin margin;
    private Region thumb;
    private Region incrementButton;
    private Region decrementButton;
    private Region incrementArrow;
    private Region decrementArrow;

    private ComponentStyleTracker styleTracker;

    public SScrollBar () {
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

    public SScrollBar horizontalThumb (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(thumb);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollBar incrementButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollBar decrementButton (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementButton);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollBar incrementArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(incrementArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    public SScrollBar decrementArrow (PseudoElement pseudoElement) {
        styleTracker.setCurrentComponent(decrementArrow);
        pseudoElement.apply();
        styleTracker.setCurrentComponent(this);
        return this;
    }

    @Override
    public SScrollBar x (double value) {
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
    public SScrollBar y (double value) {
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
    public SScrollBar width (double value) {
        if (styleTracker.isAllowedProperty(MainProps.WIDTH)) {
            styleTracker.getCurrentComponent().setPrefWidth(value);
        }
        return this;
    }

    @Override
    public SScrollBar height (double value) {
        if (styleTracker.isAllowedProperty(MainProps.HEIGHT)) {
            styleTracker.getCurrentComponent().setPrefHeight(value);
        }
        return this;
    }

    @Override
    public SScrollBar scaleX (double value) {
        styleTracker.getCurrentComponent().setScaleX(value);
        return this;
    }

    @Override
    public SScrollBar scaleY (double value) {
        styleTracker.getCurrentComponent().setScaleY(value);
        return this;
    }

    @Override
    public SScrollBar translateX (double value) {
        styleTracker.getCurrentComponent().setTranslateX(value);
        return this;
    }

    @Override
    public SScrollBar translateY (double value) {
        styleTracker.getCurrentComponent().setTranslateY(value);
        return this;
    }

    @Override
    public SScrollBar rotate (double value) {
        styleTracker.getCurrentComponent().setRotate(value);
        return this;
    }

    @Override
    public SScrollBar padding (double value) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollBar padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public SScrollBar topPadding (double value) {
        PaddingUtil.setTopPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar rightPadding (double value) {
        PaddingUtil.setRightPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomPadding (double value) {
        PaddingUtil.setBottomPadding(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar leftPadding (double value) {
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
    public SScrollBar margin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(value);
        }
        return this;
    }

    @Override
    public SScrollBar margin (double topBottom, double rightLeft) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setMargin(topBottom, rightLeft);
        }
        return this;
    }

    @Override
    public SScrollBar margin (double top, double right, double bottom, double left) {
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
    public SScrollBar topMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setTop(value);
        }
        return this;
    }

    @Override
    public SScrollBar rightMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setRight(value);
        }
        return this;
    }

    @Override
    public SScrollBar bottomMargin (double value) {
        if (styleTracker.getCurrentComponent() == this) {
            margin.setBottom(value);
        }
        return this;
    }

    @Override
    public SScrollBar leftMargin (double value) {
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
    public SScrollBar bgColor (String color) {
        BackgroundSetter.setBackgroundColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollBar bgColor (Color color) {
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
    public SScrollBar bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public SScrollBar bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public SScrollBar bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public SScrollBar topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomLeftBgRadius (double value) {
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
    public SScrollBar bgInsets (double value) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollBar bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public SScrollBar topInset (double value) {
        BackgroundSetter.setTopInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar rightInset (double value) {
        BackgroundSetter.setRightInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomInset (double value) {
        BackgroundSetter.setBottomInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar leftInset (double value) {
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
    public SScrollBar border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public SScrollBar border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public SScrollBar topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollBar rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollBar bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollBar leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollBar topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollBar rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollBar bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollBar leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SScrollBar borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public SScrollBar topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public SScrollBar rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public SScrollBar bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(styleTracker.getCurrentComponent(), borderStyle);
        return this;
    }

    @Override
    public SScrollBar leftBorderStyle (String borderStyle) {
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
    public SScrollBar borderWidth (double value) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollBar borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public SScrollBar topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar leftBorderWidth (double value) {
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
    public SScrollBar borderColor (String colorValue) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public SScrollBar borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollBar borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public SScrollBar topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public SScrollBar rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public SScrollBar bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(styleTracker.getCurrentComponent(), colorValue);
        return this;
    }

    @Override
    public SScrollBar leftBorderColor (String colorValue) {
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
    public SScrollBar borderColor (Color color) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollBar borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollBar borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(styleTracker.getCurrentComponent(), top, right, top, right);
        return this;
    }

    @Override
    public SScrollBar topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollBar rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollBar bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(styleTracker.getCurrentComponent(), color);
        return this;
    }

    @Override
    public SScrollBar leftBorderColor (Color color) {
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
    public SScrollBar borderOpacity (double value) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollBar borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public SScrollBar topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar leftBorderOpacity (double value) {
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
    public SScrollBar borderRadius (double value) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollBar borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(styleTracker.getCurrentComponent(), topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public SScrollBar topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomLeftBorderRadius (double value) {
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
    public SScrollBar borderInsets (double value) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SScrollBar borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(styleTracker.getCurrentComponent(), top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public SScrollBar topBorderInset (double value) {
        BorderSetter.setTopBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(styleTracker.getCurrentComponent(), value);
        return this;
    }

    @Override
    public SScrollBar leftBorderInset (double value) {
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
    public SScrollBar removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public SScrollBar removeTopBorder () {
        BorderSetter.setTopBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public SScrollBar removeRightBorder () {
        BorderSetter.setRightBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public SScrollBar removeBottomBorder () {
        BorderSetter.setBottomBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public SScrollBar removeLeftBorder () {
        BorderSetter.setLeftBorder(styleTracker.getCurrentComponent(), Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        BorderSetter.setTopLeftBorderRadius(styleTracker.getCurrentComponent(), 0);
        return this;
    }

    @Override
    public SScrollBar shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        styleTracker.getCurrentComponent().setShape(svgPath);
        return this;
    }

    @Override
    public SScrollBar shape (Shape shape) {
        styleTracker.getCurrentComponent().setShape(shape);
        return this;
    }

    public SScrollBar orientation(Orientation orientation){
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
