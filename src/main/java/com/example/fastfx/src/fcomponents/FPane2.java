package com.example.fastfx.src.fcomponents;

import com.example.fastfx.src.fcomponents.flistener.ResizeListener;
import com.example.fastfx.src.fcomponents.property.PropertyManager;
import com.example.fastfx.src.fcomponents.pseudoclass.PseudoClass;
import com.example.fastfx.src.style.layouts.Margin;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

public class FPane2 extends Pane implements FComponent{

    private PropertyManager propertyManager;

    public FPane2(){
        propertyManager = new PropertyManager(this);
    }

    public FComponent hover(PseudoClass hover){
        propertyManager.hover(hover);
        return this;
    }

    public FComponent click(PseudoClass click){
        propertyManager.click(click);
        return this;
    }

    @Override
    public FComponent x (double x) {
        propertyManager.x(x);
        return this;
    }

    @Override
    public FComponent x (double x, String unit) {
        propertyManager.x(x, unit);
        return this;
    }

    @Override
    public FComponent x (String rawValue) {
        propertyManager.x(rawValue);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public String getRawX () {
        return propertyManager.getRawX();
    }

    @Override
    public FComponent y (double y) {
        propertyManager.y(y);
        return this;
    }

    @Override
    public FComponent y (double y, String unit) {
        propertyManager.y(y, unit);
        return this;
    }

    @Override
    public FComponent y (String rawValue) {
        propertyManager.y(rawValue);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public String getRawY () {
        return propertyManager.getRawY();
    }

    @Override
    public FComponent width (double value) {
        propertyManager.width(value);
        return this;
    }

    @Override
    public FComponent width (String value) {
        propertyManager.width(value);
        return this;
    }

    @Override
    public String getRawWidth () {
        return propertyManager.getRawWidth();
    }

    @Override
    public FComponent height (double value) {
        propertyManager.height(value);
        return this;
    }

    @Override
    public FComponent height (String value) {
        propertyManager.height(value);
        return this;
    }

    @Override
    public String getRawHeight () {
        return propertyManager.getRawHeight();
    }

    @Override
    public FComponent scaleX (double value) {
        propertyManager.scaleX(value);
        return this;
    }

    @Override
    public FComponent scaleX (String value) {
        propertyManager.scaleX(value);
        return this;
    }

    @Override
    public String getRawScaleX () {
        return propertyManager.getRawScaleX();
    }

    @Override
    public FComponent scaleY (double value) {
        propertyManager.scaleY(value);
        return this;
    }

    @Override
    public FComponent scaleY (String value) {
        propertyManager.scaleY(value);
        return this;
    }

    @Override
    public String getRawScaleY () {
        return propertyManager.getRawScaleY();
    }

    @Override
    public FComponent translateX (double translateX) {
        propertyManager.translateX(translateX);
        return this;
    }

    @Override
    public FComponent translateX (double translateX, String unit) {
        propertyManager.translateX(translateX, unit);
        return this;
    }

    @Override
    public FComponent translateX (String rawValue) {
        propertyManager.translateX(rawValue);
        return this;
    }

    @Override
    public String getRawTranslateX () {
        return propertyManager.getRawTranslateX();
    }

    @Override
    public FComponent translateY (double translateY) {
        propertyManager.translateY(translateY);
        return this;
    }

    @Override
    public FComponent translateY (double translateY, String unit) {
        propertyManager.translateY(translateY, unit);
        return this;
    }

    @Override
    public FComponent translateY (String rawValue) {
        propertyManager.translateY(rawValue);
        return this;
    }

    @Override
    public String getRawTranslateY () {
        return propertyManager.getRawTranslateY();
    }

    @Override
    public FComponent rotate (double value) {
        propertyManager.rotate(value);
        return this;
    }

    @Override
    public FComponent rotate (String rawValue) {
        propertyManager.rotate(rawValue);
        return this;
    }

    @Override
    public String getRawRotate () {
        return propertyManager.getRawRotate();
    }

    @Override
    public FComponent padding (double padding) {
        propertyManager.padding(padding);
        return this;
    }

    @Override
    public FComponent padding (double topBottom, double rightLeft) {
        propertyManager.padding(topBottom, rightLeft);
        return this;
    }

    @Override
    public FComponent padding (double top, double right, double bottom, double left) {
        propertyManager.padding(top, right, bottom, left);
        return this;
    }

    @Override
    public FComponent padding (String rawValue) {
        propertyManager.padding(rawValue);
        return this;
    }

    @Override
    public String getRawPadding () {
        return propertyManager.getRawPadding();
    }

    @Override
    public FComponent margin (double margin) {
        propertyManager.margin(margin);
        return this;
    }

    @Override
    public FComponent margin (double topBottom, double rightLeft) {
        propertyManager.margin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FComponent margin (double top, double right, double bottom, double left) {
        propertyManager.margin(top, right, bottom, left);
        return this;
    }

    @Override
    public FComponent margin (String rawValue) {
        propertyManager.margin(rawValue);
        return this;
    }

    @Override
    public Margin getMargin () {
        return propertyManager.getMargin();
    }

    @Override
    public String getRawMargin () {
        return propertyManager.getRawMargin();
    }

    @Override
    public FComponent bgColor (String color) {
        propertyManager.bgColor(color);
        return this;
    }

    @Override
    public String getBgColor () {
        return propertyManager.getBgColor();
    }

    @Override
    public String getRawBgColor () {
        return propertyManager.getRawBgColor();
    }

    @Override
    public FComponent bgOpacity (double opacity) {
        propertyManager.bgOpacity(opacity);
        return this;
    }

    @Override
    public FComponent bgOpacity (String value) {
        propertyManager.bgOpacity(value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return propertyManager.getBgOpacity();
    }

    @Override
    public String getRawBgOpacity () {
        return propertyManager.getRawBgOpacity();
    }

    @Override
    public FComponent bgRadius (double radius) {
        propertyManager.bgRadius(radius);
        return this;
    }

    @Override
    public FComponent bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        propertyManager.bgRadius(topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FComponent bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        propertyManager.bgRadius(topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public FComponent bgRadius (String value) {
        propertyManager.bgRadius(value);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return propertyManager.getBgRadius();
    }

    @Override
    public String getRawBgRadius () {
        return propertyManager.getRawBgRadius();
    }

    @Override
    public FComponent topLeftBgRadius (double radius) {
        propertyManager.topLeftBgRadius(radius);
        return this;
    }

    @Override
    public FComponent topLeftBgRadius (String value) {
        propertyManager.topLeftBgRadius(value);
        return this;
    }

    @Override
    public double getTopLeftBgRadius () {
        return propertyManager.getTopLeftBgRadius();
    }

    @Override
    public String getRawTopLeftBgRadius () {
        return propertyManager.getRawTopLeftBgRadius();
    }

    @Override
    public FComponent topRightBgRadius (double radius) {
        propertyManager.topRightBgRadius(radius);
        return this;
    }

    @Override
    public FComponent topRightBgRadius (String value) {
        propertyManager.topRightBgRadius(value);
        return this;
    }

    @Override
    public double getTopRightBgRadius () {
        return propertyManager.getTopRightBgRadius();
    }

    @Override
    public String getRawTopRightBgRadius () {
        return propertyManager.getRawTopRightBgRadius();
    }

    @Override
    public FComponent bottomRightBgRadius (double radius) {
        propertyManager.bottomRightBgRadius(radius);
        return this;
    }

    @Override
    public FComponent bottomRightBgRadius (String value) {
        propertyManager.bottomRightBgRadius(value);
        return this;
    }

    @Override
    public double getBottomRightBgRadius () {
        return propertyManager.getBottomRightBgRadius();
    }

    @Override
    public String getRawBottomRightBgRadius () {
        return propertyManager.getRawBottomRightBgRadius();
    }

    @Override
    public FComponent bottomLeftBgRadius (double radius) {
        propertyManager.bottomLeftBgRadius(radius);
        return this;
    }

    @Override
    public FComponent bottomLeftBgRadius (String value) {
        propertyManager.bottomLeftBgRadius(value);
        return this;
    }

    @Override
    public double getBottomLeftBgRadius () {
        return propertyManager.getBottomLeftBgRadius();
    }

    @Override
    public String getRawBottomLeftBgRadius () {
        return propertyManager.getRawBottomLeftBgRadius();
    }

    @Override
    public FComponent bgInsets (double insets) {
        propertyManager.bgInsets(insets);
        return this;
    }

    @Override
    public FComponent bgInsets (double topBottom, double rightLeft) {
        propertyManager.bgInsets(topBottom, rightLeft);
        return this;
    }

    @Override
    public FComponent bgInsets (double top, double right, double bottom, double left) {
        propertyManager.bgInsets(top, right, bottom, left);
        return this;
    }

    @Override
    public FComponent bgInsets (String value) {
        propertyManager.bgInsets(value);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return propertyManager.getBgInsets();
    }

    @Override
    public String getRawBgInsets () {
        return propertyManager.getRawBgInsets();
    }

    @Override
    public FComponent topInset (double inset) {
        propertyManager.topInset(inset);
        return this;
    }

    @Override
    public double getTopInset () {
        return propertyManager.getTopInset();
    }

    @Override
    public FComponent rightInset (double inset) {
        propertyManager.rightInset(inset);
        return this;
    }

    @Override
    public double getRightInset () {
        return propertyManager.getRightInset();
    }

    @Override
    public FComponent bottomInset (double inset) {
        propertyManager.bottomInset(inset);
        return this;
    }

    @Override
    public double getBottomInset () {
        return propertyManager.getBottomInset();
    }

    @Override
    public FComponent leftInset (double inset) {
        propertyManager.leftInset(inset);
        return this;
    }

    @Override
    public double getLeftInset () {
        return propertyManager.getLeftInset();
    }

    @Override
    public FComponent border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        propertyManager.border(colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FComponent border (String colorValue, String borderStyle, double radius, double borderWidth) {
        propertyManager.border(colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FComponent border (String border) {
        propertyManager.border(border);
        return this;
    }

    @Override
    public String getRawBorder () {
        return propertyManager.getRawBorder();
    }

    @Override
    public FComponent topBorder (String colorValue, String borderStyle, double borderWidth) {
        propertyManager.topBorder(colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FComponent topBorder (String topBorder) {
        propertyManager.topBorder(topBorder);
        return this;
    }

    @Override
    public String getRawTopBorder () {
        return propertyManager.getRawTopBorder();
    }

    @Override
    public FComponent rightBorder (String colorValue, String borderStyle, double borderWidth) {
        propertyManager.rightBorder(colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FComponent rightBorder (String rightBorder) {
        propertyManager.rightBorder(rightBorder);
        return this;
    }

    @Override
    public String getRawRightBorder () {
        return propertyManager.getRawRightBorder();
    }

    @Override
    public FComponent bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        propertyManager.bottomBorder(colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FComponent bottomBorder (String bottomBorder) {
        propertyManager.bottomBorder(bottomBorder);
        return this;
    }

    @Override
    public String getRawBottomBorder () {
        return propertyManager.getRawBottomBorder();
    }

    @Override
    public FComponent leftBorder (String colorValue, String borderStyle, double borderWidth) {
        propertyManager.leftBorder(colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FComponent leftBorder (String leftBorder) {
        propertyManager.leftBorder(leftBorder);
        return this;
    }

    @Override
    public String getRawLeftBorder () {
        return propertyManager.getRawLeftBorder();
    }

    @Override
    public FComponent borderStyle (String borderStyle) {
        propertyManager.borderStyle(borderStyle);
        return this;
    }

    @Override
    public String getBorderStyle () {
        return propertyManager.getBorderStyle();
    }

    @Override
    public FComponent borderWidth (double width) {
        propertyManager.borderWidth(width);
        return this;
    }

    @Override
    public FComponent borderWidth (double topBottom, double rightLeft) {
        propertyManager.borderWidth(topBottom, rightLeft);
        return this;
    }

    @Override
    public FComponent borderWidth (double top, double right, double bottom, double left) {
        propertyManager.borderWidth(top, right, bottom, left);
        return this;
    }

    @Override
    public FComponent borderWidth (String borderWidth) {
        propertyManager.borderWidth(borderWidth);
        return this;
    }

    @Override
    public String getRawBorderWidth () {
        return propertyManager.getRawBorderWidth();
    }

    @Override
    public FComponent borderColor (String colorValue) {
        propertyManager.borderColor(colorValue);
        return this;
    }

    @Override
    public String getBorderColor () {
        return propertyManager.getBorderColor();
    }

    @Override
    public String getRawBorderColor () {
        return propertyManager.getRawBorderColor();
    }

    @Override
    public FComponent borderOpacity (double opacity) {
        propertyManager.borderOpacity(opacity);
        return this;
    }

    @Override
    public FComponent borderOpacity (double topBottom, double rightLeft) {
        propertyManager.borderOpacity(topBottom, rightLeft);
        return this;
    }

    @Override
    public FComponent borderOpacity (double top, double right, double bottom, double left) {
        propertyManager.borderOpacity(top, right, bottom, left);
        return this;
    }

    @Override
    public FComponent borderOpacity (String borderOpacity) {
        propertyManager.borderOpacity(borderOpacity);
        return this;
    }

    @Override
    public String getRawBorderOpacity () {
        return propertyManager.getRawBorderOpacity();
    }

    @Override
    public FComponent borderRadius (double radius) {
        propertyManager.borderRadius(radius);
        return this;
    }

    @Override
    public FComponent borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        propertyManager.borderRadius(topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public FComponent borderRadius (String borderRadius) {
        propertyManager.borderRadius(borderRadius);
        return this;
    }

    @Override
    public String getRawBorderRadius () {
        return propertyManager.getRawBorderRadius();
    }

    @Override
    public CornerRadii getBorderRadius () {
        return propertyManager.getBorderRadius();
    }

    @Override
    public FComponent topLeftBorderRadius (double radius) {
        propertyManager.topLeftBorderRadius(radius);
        return this;
    }

    @Override
    public FComponent topLeftBorderRadius (String radius) {
        propertyManager.topLeftBorderRadius(radius);
        return this;
    }

    @Override
    public double getTopLeftBorderRadius () {
        return propertyManager.getTopLeftBorderRadius();
    }

    @Override
    public String getRawTopLeftBorderRadius () {
        return propertyManager.getRawTopLeftBorderRadius();
    }

    @Override
    public FComponent topRightBorderRadius (double radius) {
        propertyManager.topRightBorderRadius(radius);
        return this;
    }

    @Override
    public FComponent topRightBorderRadius (String radius) {
        propertyManager.topRightBorderRadius(radius);
        return this;
    }

    @Override
    public double getTopRightBorderRadius () {
        return propertyManager.getTopRightBorderRadius();
    }

    @Override
    public String getRawTopRightBorderRadius () {
        return propertyManager.getRawTopRightBorderRadius();
    }

    @Override
    public FComponent bottomRightBorderRadius (double radius) {
        propertyManager.bottomRightBorderRadius(radius);
        return this;
    }

    @Override
    public FComponent bottomRightBorderRadius (String radius) {
        propertyManager.bottomRightBorderRadius(radius);
        return this;
    }

    @Override
    public double getBottomRightBorderRadius () {
        return propertyManager.getBottomRightBorderRadius();
    }

    @Override
    public String getRawBottomRightBorderRadius () {
        return propertyManager.getRawBottomRightBorderRadius();
    }

    @Override
    public FComponent bottomLeftBorderRadius (double radius) {
        propertyManager.bottomLeftBorderRadius(radius);
        return this;
    }

    @Override
    public FComponent bottomLeftBorderRadius (String radius) {
        propertyManager.bottomLeftBorderRadius(radius);
        return this;
    }

    @Override
    public double getBottomLeftBorderRadius () {
        return propertyManager.getBottomLeftBorderRadius();
    }

    @Override
    public String getRawBottomLeftBorderRadius () {
        return propertyManager.getRawBottomLeftBorderRadius();
    }

    @Override
    public FComponent borderInsets (double inset) {
        propertyManager.borderInsets(inset);
        return this;
    }

    @Override
    public FComponent borderInsets (double topBottom, double rightLeft) {
        propertyManager.borderInsets(topBottom, rightLeft);
        return this;
    }

    @Override
    public FComponent borderInsets (double top, double right, double bottom, double left) {
        propertyManager.borderInsets(top, right, bottom, left);
        return this;
    }

    @Override
    public FComponent borderInsets (String borderInsets) {
        propertyManager.borderInsets(borderInsets);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return propertyManager.getBorderInsets();
    }

    @Override
    public String getRawBorderInsets () {
        return propertyManager.getRawBorderInsets();
    }

    @Override
    public void removeBorder () {
        propertyManager.removeBorder();
    }

    @Override
    public void removeTopBorder () {
        propertyManager.removeTopBorder();
    }

    @Override
    public void removeRightBorder () {
        propertyManager.removeRightBorder();
    }

    @Override
    public void removeBottomBorder () {
        propertyManager.removeBottomBorder();
    }

    @Override
    public void removeLeftBorder () {
        propertyManager.removeLeftBorder();
    }

    @Override
    public void add (Node node) {

    }

    @Override
    public void resizeListener (ResizeListener resizeListener) {

    }

    @Override
    public void removeResizeListener (ResizeListener resizeListener) {

    }
}
