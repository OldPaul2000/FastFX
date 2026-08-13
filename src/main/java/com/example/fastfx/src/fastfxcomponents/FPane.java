package com.example.fastfx.src.fastfxcomponents;

import com.example.fastfx.src.layouts.Margin;
import com.example.fastfx.src.util.style.BackgroundUtil;
import com.example.fastfx.src.util.style.BorderUtil;
import com.example.fastfx.src.util.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class FPane extends Pane implements FComponent {
    private Margin margin;

    public FPane () {
        this.margin = new Margin(0, 0, 0, 0);
    }

    @Override
    public FPane x (double value) {
        setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public FPane y (double value) {
        setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public FPane width (double value) {
        setPrefWidth(value);
        return this;
    }

    @Override
    public FPane height (double value) {
        setPrefHeight(value);
        return this;
    }

    @Override
    public FPane scaleX (double value) {
        setScaleX(value);
        return this;
    }

    @Override
    public FPane scaleY (double value) {
        setScaleY(value);
        return this;
    }

    @Override
    public FPane translateX (double value) {
        setTranslateX(value);
        return this;
    }

    @Override
    public FPane translateY (double value) {
        setTranslateY(value);
        return this;
    }

    @Override
    public FPane rotate (double value) {
        setRotate(value);
        return this;
    }

    @Override
    public FPane padding (double value) {
        PaddingUtil.setPadding(this, value);
        return this;
    }

    @Override
    public FPane padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FPane topPadding (double value) {
        PaddingUtil.setTopPadding(this, value);
        return this;
    }

    @Override
    public FPane rightPadding (double value) {
        PaddingUtil.setRightPadding(this, value);
        return this;
    }

    @Override
    public FPane bottomPadding (double value) {
        PaddingUtil.setBottomPadding(this, value);
        return this;
    }

    @Override
    public FPane leftPadding (double value) {
        PaddingUtil.setLeftPadding(this, value);
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
    public FPane margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public FPane margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public FPane topMargin (double value) {
        margin.setTop(value);
        return this;
    }

    @Override
    public FPane rightMargin (double value) {
        margin.setRight(value);
        return this;
    }

    @Override
    public FPane bottomMargin (double value) {
        margin.setBottom(value);
        return this;
    }

    @Override
    public FPane leftMargin (double value) {
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
    public FPane bgColor (String color) {
        BackgroundUtil.setBackgroundColor(this, color);
        return this;
    }

    @Override
    public FPane bgColor (Color color) {
        BackgroundUtil.setBackgroundColor(this, color);
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
    public FPane bgOpacity (double value) {
        BackgroundUtil.setBackgroundOpacity(this, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundUtil.getBackgroundOpacity(this);
    }

    @Override
    public FPane bgRadius (double value) {
        BackgroundUtil.setBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FPane bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundUtil.setBackgroundRadius(this, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FPane bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundUtil.setBackgroundRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundUtil.getBackgroundRadius(this);
    }

    @Override
    public FPane topLeftBgRadius (double value) {
        BackgroundUtil.setTopLeftBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FPane topRightBgRadius (double value) {
        BackgroundUtil.setTopRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FPane bottomRightBgRadius (double value) {
        BackgroundUtil.setBottomRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public FPane bottomLeftBgRadius (double value) {
        BackgroundUtil.setBottomLeftBackgroundRadius(this, value);
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
    public FPane bgInsets (double value) {
        BackgroundUtil.setInsets(this, value);
        return this;
    }

    @Override
    public FPane bgInsets (double topBottom, double rightLeft) {
        BackgroundUtil.setInsets(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane bgInsets (double top, double right, double bottom, double left) {
        BackgroundUtil.setInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundUtil.getBackgroundInsets(this);
    }

    @Override
    public FPane topInset (double value) {
        BackgroundUtil.setTopInset(this, value);
        return this;
    }

    @Override
    public FPane rightInset (double value) {
        BackgroundUtil.setRightInset(this, value);
        return this;
    }

    @Override
    public FPane bottomInset (double value) {
        BackgroundUtil.setBottomInset(this, value);
        return this;
    }

    @Override
    public FPane leftInset (double value) {
        BackgroundUtil.setLeftInset(this, value);
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
    public FPane border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderUtil.setBorder(this, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FPane border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderUtil.setBorder(this, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FPane topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane topBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setTopBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setRightBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setBottomBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderUtil.setLeftBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FPane borderStyle (String borderStyle) {
        BorderUtil.setBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FPane topBorderStyle (String borderStyle) {
        BorderUtil.setTopBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FPane rightBorderStyle (String borderStyle) {
        BorderUtil.setRightBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FPane bottomBorderStyle (String borderStyle) {
        BorderUtil.setBottomBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public FPane leftBorderStyle (String borderStyle) {
        BorderUtil.setLeftBorderStyle(this, borderStyle);
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
    public FPane borderWidth (double value) {
        BorderUtil.setBorderWidth(this, value);
        return this;
    }

    @Override
    public FPane borderWidth (double topBottom, double rightLeft) {
        BorderUtil.setBorderWidth(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderWidth (double top, double right, double bottom, double left) {
        BorderUtil.setBorderWidth(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FPane topBorderWidth (double value) {
        BorderUtil.setTopBorderWidth(this, value);
        return this;
    }

    @Override
    public FPane rightBorderWidth (double value) {
        BorderUtil.setRightBorderWidth(this, value);
        return this;
    }

    @Override
    public FPane bottomBorderWidth (double value) {
        BorderUtil.setBottomBorderWidth(this, value);
        return this;
    }

    @Override
    public FPane leftBorderWidth (double value) {
        BorderUtil.setLeftBorderWidth(this, value);
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
    public FPane borderColor (String colorValue) {
        BorderUtil.setBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FPane borderColor (String topBottom, String rightLeft) {
        BorderUtil.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderColor (String top, String right, String bottom, String left) {
        BorderUtil.setBorderColor(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FPane topBorderColor (String colorValue) {
        BorderUtil.setTopBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FPane rightBorderColor (String colorValue) {
        BorderUtil.setRightBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FPane bottomBorderColor (String colorValue) {
        BorderUtil.setBottomBorderColor(this, colorValue);
        return this;
    }

    @Override
    public FPane leftBorderColor (String colorValue) {
        BorderUtil.setLeftBorderColor(this, colorValue);
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
    public FPane borderColor (Color color) {
        BorderUtil.setBorderColor(this, color);
        return this;
    }

    @Override
    public FPane borderColor (Color topBottom, Color rightLeft) {
        BorderUtil.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderColor (Color top, Color right, Color bottom, Color left) {
        BorderUtil.setBorderColor(this, top, right, top, right);
        return this;
    }

    @Override
    public FPane topBorderColor (Color color) {
        BorderUtil.setTopBorderColor(this, color);
        return this;
    }

    @Override
    public FPane rightBorderColor (Color color) {
        BorderUtil.setRightBorderColor(this, color);
        return this;
    }

    @Override
    public FPane bottomBorderColor (Color color) {
        BorderUtil.setBottomBorderColor(this, color);
        return this;
    }

    @Override
    public FPane leftBorderColor (Color color) {
        BorderUtil.setLeftBorderColor(this, color);
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
    public FPane borderOpacity (double value) {
        BorderUtil.setBorderOpacity(this, value);
        return this;
    }

    @Override
    public FPane borderOpacity (double topBottom, double rightLeft) {
        BorderUtil.setBorderOpacity(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderOpacity (double top, double right, double bottom, double left) {
        BorderUtil.setBorderOpacity(this, top, right, bottom, left);
        return this;
    }

    @Override
    public FPane topBorderOpacity (double value) {
        BorderUtil.setTopBorderOpacity(this, value);
        return this;
    }

    @Override
    public FPane rightBorderOpacity (double value) {
        BorderUtil.setRightBorderOpacity(this, value);
        return this;
    }

    @Override
    public FPane bottomBorderOpacity (double value) {
        BorderUtil.setBottomBorderOpacity(this, value);
        return this;
    }

    @Override
    public FPane leftBorderOpacity (double value) {
        BorderUtil.setLeftBorderOpacity(this, value);
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
    public FPane borderRadius (double value) {
        BorderUtil.setBorderRadius(this, value);
        return this;
    }

    @Override
    public FPane borderRadius (double topBottom, double rightLeft) {
        BorderUtil.setBorderRadius(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderUtil.setBorderRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderUtil.getBorderRadius(this);
    }

    @Override
    public FPane topLeftBorderRadius (double value) {
        BorderUtil.setTopLeftBorderRadius(this, value);
        return this;
    }

    @Override
    public FPane topRightBorderRadius (double value) {
        BorderUtil.setTopRightBorderRadius(this, value);
        return this;
    }

    @Override
    public FPane bottomRightBorderRadius (double value) {
        BorderUtil.setBottomRightBorderRadius(this, value);
        return this;
    }

    @Override
    public FPane bottomLeftBorderRadius (double value) {
        BorderUtil.setBottomLeftBorderRadius(this, value);
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
    public FPane borderInsets (double value) {
        BorderUtil.setBorderInsets(this, value);
        return this;
    }

    @Override
    public FPane borderInsets (double topBottom, double rightLeft) {
        BorderUtil.setBorderInsets(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public FPane borderInsets (double top, double right, double bottom, double left) {
        BorderUtil.setBorderInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderUtil.getBorderInsets(this);
    }

    @Override
    public FPane topBorderInset (double value) {
        BorderUtil.setTopBorderInset(this, value);
        return this;
    }

    @Override
    public FPane rightBorderInset (double value) {
        BorderUtil.setRightBorderInset(this, value);
        return this;
    }

    @Override
    public FPane bottomBorderInset (double value) {
        BorderUtil.setBottomBorderInset(this, value);
        return this;
    }

    @Override
    public FPane leftBorderInset (double value) {
        BorderUtil.setLeftBorderInset(this, value);
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
    public FPane removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public FPane removeTopBorder () {
        BorderUtil.setTopBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopLeftBorderRadius(this, 0);
        BorderUtil.setTopRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public FPane removeRightBorder () {
        BorderUtil.setRightBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setTopRightBorderRadius(this, 0);
        BorderUtil.setBottomRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public FPane removeBottomBorder () {
        BorderUtil.setBottomBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomRightBorderRadius(this, 0);
        BorderUtil.setBottomLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public FPane removeLeftBorder () {
        BorderUtil.setLeftBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderUtil.setBottomLeftBorderRadius(this, 0);
        BorderUtil.setTopLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public FPane shape (String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        setShape(svgPath);
        return this;
    }

    @Override
    public FPane shape (Shape shape) {
        setShape(shape);
        return this;
    }

    public void add (Region component) {
        getChildren().add(component);
    }

    public void remove (Region component) {
        getChildren().remove(component);
    }
}
