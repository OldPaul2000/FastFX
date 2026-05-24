package com.example.fastfx.src.fcomponents;

import com.example.fastfx.src.fcomponents.flistener.ResizeListener;
import com.example.fastfx.src.fcomponents.propertymanager.PropertyManager2;
import com.example.fastfx.src.fcomponents.pseudoclass.PseudoClass;
import com.example.fastfx.src.style.layouts.Margin;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Set;

public class FLabel extends Label implements FLabeled {
    private PropertyManager2 propertyManager;
    private ChangeListener<Number> resizeWidthListener;
    private ChangeListener<Number> resizeHeightListener;
    private Set<ResizeListener> resizeListeners;

    public FLabel(){
        propertyManager = new PropertyManager2(this);
    }

    public FLabel hover(PseudoClass hover){
        propertyManager.hover(hover);
        return this;
    }

    public FLabel click(PseudoClass click){
        propertyManager.click(click);
        return this;
    }
    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    @Override
    public FLabel x (double x) {
        propertyManager.x(x);
        return this;
    }

    @Override
    public FLabel x (double x, String unit) {
        propertyManager.x(x, unit);
        return this;
    }

    @Override
    public FLabel x (String rawValue) {
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
    public FLabel y (double y) {
        propertyManager.y(y);
        return this;
    }

    @Override
    public FLabel y (double y, String unit) {
        propertyManager.y(y, unit);
        return this;
    }

    @Override
    public FLabel y (String rawValue) {
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
    public FLabel width (double value) {
        propertyManager.width(value);
        return this;
    }

    @Override
    public FLabel width (String value) {
        propertyManager.width(value);
        return this;
    }

    @Override
    public String getRawWidth () {
        return propertyManager.getRawWidth();
    }

    @Override
    public FLabel height (double value) {
        propertyManager.height(value);
        return this;
    }

    @Override
    public FLabel height (String value) {
        propertyManager.height(value);
        return this;
    }

    @Override
    public String getRawHeight () {
        return propertyManager.getRawHeight();
    }

    @Override
    public FLabel scaleX (double value) {
        propertyManager.scaleX(value);
        return this;
    }

    @Override
    public FLabel scaleX (String value) {
        propertyManager.scaleX(value);
        return this;
    }

    @Override
    public String getRawScaleX () {
        return propertyManager.getRawScaleX();
    }

    @Override
    public FLabel scaleY (double value) {
        propertyManager.scaleY(value);
        return this;
    }

    @Override
    public FLabel scaleY (String value) {
        propertyManager.scaleY(value);
        return this;
    }

    @Override
    public String getRawScaleY () {
        return propertyManager.getRawScaleY();
    }

    @Override
    public FLabel translateX (double translateX) {
        propertyManager.translateX(translateX);
        return this;
    }

    @Override
    public FLabel translateX (double translateX, String unit) {
        propertyManager.translateX(translateX, unit);
        return this;
    }

    @Override
    public FLabel translateX (String rawValue) {
        propertyManager.translateX(rawValue);
        return this;
    }

    @Override
    public String getRawTranslateX () {
        return propertyManager.getRawTranslateX();
    }

    @Override
    public FLabel translateY (double translateY) {
        propertyManager.translateY(translateY);
        return this;
    }

    @Override
    public FLabel translateY (double translateY, String unit) {
        propertyManager.translateY(translateY, unit);
        return this;
    }

    @Override
    public FLabel translateY (String rawValue) {
        propertyManager.translateY(rawValue);
        return this;
    }

    @Override
    public String getRawTranslateY () {
        return propertyManager.getRawTranslateY();
    }

    @Override
    public FLabel rotate (double value) {
        propertyManager.rotate(value);
        return this;
    }

    @Override
    public FLabel rotate (String rawValue) {
        propertyManager.rotate(rawValue);
        return this;
    }

    @Override
    public String getRawRotate () {
        return propertyManager.getRawRotate();
    }

    @Override
    public FLabel padding (double padding) {
        propertyManager.padding(padding);
        return this;
    }

    @Override
    public FLabel padding (double topBottom, double rightLeft) {
        propertyManager.padding(topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel padding (double top, double right, double bottom, double left) {
        propertyManager.padding(top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel padding (String rawValue) {
        propertyManager.padding(rawValue);
        return this;
    }

    @Override
    public String getRawPadding () {
        return propertyManager.getRawPadding();
    }

    @Override
    public FLabel margin (double margin) {
        propertyManager.margin(margin);
        return this;
    }

    @Override
    public FLabel margin (double topBottom, double rightLeft) {
        propertyManager.margin(topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel margin (double top, double right, double bottom, double left) {
        propertyManager.margin(top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel margin (String rawValue) {
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
    public FLabel top (double value) {
        propertyManager.top(value);
        return this;
    }

    @Override
    public FComponent top (String rawValue) {
        return this;
    }

    @Override
    public double getTop(){
        return 0;
    }

    @Override
    public FComponent right (double value) {
        propertyManager.right(value);
        return this;
    }

    @Override
    public FComponent right (String rawValue) {
        return this;
    }

    @Override
    public double getRight(){
        return 0;
    }

    @Override
    public FComponent bottom (double value) {
        propertyManager.bottom(value);
        return this;
    }

    @Override
    public FComponent bottom (String rawValue) {
        return this;
    }

    @Override
    public double getBottom(){
        return 0;
    }

    @Override
    public FComponent left (double value) {
        propertyManager.left(value);
        return this;
    }

    @Override
    public FComponent left (String rawValue) {
        return this;
    }

    @Override
    public double getLeft(){
        return 0;
    }
    // **********************************************************
    //                                                          *
    //                      Basic properties                    *
    //                                                          *
    // **********************************************************
    // *********************************************************************************************
    // **********************************************************
    //                                                          *
    //                      Custom properties                   *
    //                                                          *
    // **********************************************************
    // ******************************************
    //            Background properties         *
    // ******************************************
    @Override
    public FLabel bgColor (String color) {
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
    public FLabel bgOpacity (double opacity) {
        propertyManager.bgOpacity(opacity);
        return this;
    }

    @Override
    public FLabel bgOpacity (String value) {
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
    public FLabel bgRadius (double radius) {
        propertyManager.bgRadius(radius);
        return this;
    }

    @Override
    public FLabel bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        propertyManager.bgRadius(topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public FLabel bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        propertyManager.bgRadius(topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public FLabel bgRadius (String value) {
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
    public FLabel topLeftBgRadius (double radius) {
        propertyManager.topLeftBgRadius(radius);
        return this;
    }

    @Override
    public FLabel topLeftBgRadius (String value) {
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
    public FLabel topRightBgRadius (double radius) {
        propertyManager.topRightBgRadius(radius);
        return this;
    }

    @Override
    public FLabel topRightBgRadius (String value) {
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
    public FLabel bottomRightBgRadius (double radius) {
        propertyManager.bottomRightBgRadius(radius);
        return this;
    }

    @Override
    public FLabel bottomRightBgRadius (String value) {
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
    public FLabel bottomLeftBgRadius (double radius) {
        propertyManager.bottomLeftBgRadius(radius);
        return this;
    }

    @Override
    public FLabel bottomLeftBgRadius (String value) {
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
    public FLabel bgInsets (double insets) {
        propertyManager.bgInsets(insets);
        return this;
    }

    @Override
    public FLabel bgInsets (double topBottom, double rightLeft) {
        propertyManager.bgInsets(topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel bgInsets (double top, double right, double bottom, double left) {
        propertyManager.bgInsets(top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel bgInsets (String value) {
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
    public FLabel topInset (double inset) {
        propertyManager.topBgInset(inset);
        return this;
    }

    @Override
    public double getTopInset () {
        return propertyManager.getTopBgInset();
    }

    @Override
    public FLabel rightInset (double inset) {
        propertyManager.rightBgInset(inset);
        return this;
    }

    @Override
    public double getRightInset () {
        return propertyManager.getRightBgInset();
    }

    @Override
    public FLabel bottomInset (double inset) {
        propertyManager.bottomBgInset(inset);
        return this;
    }

    @Override
    public double getBottomInset () {
        return propertyManager.getBottomBgInset();
    }

    @Override
    public FLabel leftInset (double inset) {
        propertyManager.leftBgInset(inset);
        return this;
    }

    @Override
    public double getLeftInset () {
        return propertyManager.getLeftBgInset();
    }
    // ******************************************
    //            Background properties         *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Border properties           *
    // ******************************************
    @Override
    public FLabel border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        propertyManager.border(colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public FLabel border (String colorValue, String borderStyle, double radius, double borderWidth) {
        propertyManager.border(colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public FLabel border (String border) {
        propertyManager.border(border);
        return this;
    }

    @Override
    public String getRawBorder () {
        return propertyManager.getRawBorder();
    }

    @Override
    public FLabel topBorder (String colorValue, String borderStyle, double borderWidth) {
        propertyManager.topBorder(colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel topBorder (String topBorder) {
        propertyManager.topBorder(topBorder);
        return this;
    }

    @Override
    public String getRawTopBorder () {
        return propertyManager.getRawTopBorder();
    }

    @Override
    public FLabel rightBorder (String colorValue, String borderStyle, double borderWidth) {
        propertyManager.rightBorder(colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel rightBorder (String rightBorder) {
        propertyManager.rightBorder(rightBorder);
        return this;
    }

    @Override
    public String getRawRightBorder () {
        return propertyManager.getRawRightBorder();
    }

    @Override
    public FLabel bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        propertyManager.bottomBorder(colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel bottomBorder (String bottomBorder) {
        propertyManager.bottomBorder(bottomBorder);
        return this;
    }

    @Override
    public String getRawBottomBorder () {
        return propertyManager.getRawBottomBorder();
    }

    @Override
    public FLabel leftBorder (String colorValue, String borderStyle, double borderWidth) {
        propertyManager.leftBorder(colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public FLabel leftBorder (String leftBorder) {
        propertyManager.leftBorder(leftBorder);
        return this;
    }

    @Override
    public String getRawLeftBorder () {
        return propertyManager.getRawLeftBorder();
    }

    @Override
    public FLabel borderStyle (String borderStyle) {
        propertyManager.borderStyle(borderStyle);
        return this;
    }

    @Override
    public String getBorderStyle () {
        return propertyManager.getBorderStyle();
    }

    @Override
    public FLabel borderWidth (double width) {
        propertyManager.borderWidth(width);
        return this;
    }

    @Override
    public FLabel borderWidth (double topBottom, double rightLeft) {
        propertyManager.borderWidth(topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderWidth (double top, double right, double bottom, double left) {
        propertyManager.borderWidth(top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel borderWidth (String borderWidth) {
        propertyManager.borderWidth(borderWidth);
        return this;
    }

    @Override
    public String getRawBorderWidth () {
        return propertyManager.getRawBorderWidth();
    }

    @Override
    public FLabel borderColor (String colorValue) {
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
    public FLabel borderOpacity (double opacity) {
        propertyManager.borderOpacity(opacity);
        return this;
    }

    @Override
    public FLabel borderOpacity (double topBottom, double rightLeft) {
        propertyManager.borderOpacity(topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderOpacity (double top, double right, double bottom, double left) {
        propertyManager.borderOpacity(top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel borderOpacity (String borderOpacity) {
        propertyManager.borderOpacity(borderOpacity);
        return this;
    }

    @Override
    public String getRawBorderOpacity () {
        return propertyManager.getRawBorderOpacity();
    }

    @Override
    public FLabel borderRadius (double radius) {
        propertyManager.borderRadius(radius);
        return this;
    }

    @Override
    public FLabel borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        propertyManager.borderRadius(topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public FLabel borderRadius (String borderRadius) {
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
    public FLabel topLeftBorderRadius (double radius) {
        propertyManager.topLeftBorderRadius(radius);
        return this;
    }

    @Override
    public FLabel topLeftBorderRadius (String radius) {
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
    public FLabel topRightBorderRadius (double radius) {
        propertyManager.topRightBorderRadius(radius);
        return this;
    }

    @Override
    public FLabel topRightBorderRadius (String radius) {
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
    public FLabel bottomRightBorderRadius (double radius) {
        propertyManager.bottomRightBorderRadius(radius);
        return this;
    }

    @Override
    public FLabel bottomRightBorderRadius (String radius) {
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
    public FLabel bottomLeftBorderRadius (double radius) {
        propertyManager.bottomLeftBorderRadius(radius);
        return this;
    }

    @Override
    public FLabel bottomLeftBorderRadius (String radius) {
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
    public FLabel borderInsets (double inset) {
        propertyManager.borderInsets(inset);
        return this;
    }

    @Override
    public FLabel borderInsets (double topBottom, double rightLeft) {
        propertyManager.borderInsets(topBottom, rightLeft);
        return this;
    }

    @Override
    public FLabel borderInsets (double top, double right, double bottom, double left) {
        propertyManager.borderInsets(top, right, bottom, left);
        return this;
    }

    @Override
    public FLabel borderInsets (String borderInsets) {
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
    // ******************************************
    //              Border properties           *
    // ******************************************
    // ******************************************
    //            Font related properties       *
    // ******************************************
    @Override
    public void setFont (String fontFamily, FontPosture fontPosture, FontWeight fontWeight, double fontSize) {

    }

    @Override
    public void setFont (String fontFamily, FontPosture fontPosture, double fontSize) {

    }

    @Override
    public void setFont (String fontFamily, FontWeight fontWeight, double fontSize) {

    }

    @Override
    public void setFont (String fontFamily, double fontSize) {

    }

    @Override
    public void setStrFont (String font) {

    }

    @Override
    public String getStrFont () {
        return "";
    }

    @Override
    public void setFontColor (Color color) {

    }

    @Override
    public void setFontColor (String color) {

    }

    @Override
    public void setSFontColor (String color) {

    }

    @Override
    public String getSFontColor () {
        return "";
    }

    @Override
    public void setFontFamily (String fontFamily) {

    }

    @Override
    public void setFontPosture (FontPosture fontPosture) {

    }

    @Override
    public void setSFontPosture (String fontPosture) {

    }

    @Override
    public String getSFontPosture () {
        return "";
    }

    @Override
    public void setFontWeight (FontWeight fontWeight) {

    }

    @Override
    public void setSFontWeight (String fontWeight) {

    }

    @Override
    public String getSFontWeight () {
        return "";
    }

    @Override
    public void setFontSize (double fontSize) {

    }

    @Override
    public void setSFontSize (String fontSize) {

    }

    @Override
    public String getSFontSize () {
        return "";
    }
    // ******************************************
    //            Font related properties       *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //                UI properties             *
    // ******************************************
    //    public void add (Node node) {
    //        getChildren().add(node);
    //        propertyManager.notifyParentWasSet(this);
    //    }
    public void add(FComponent component){
        getChildren().add((Node) component);
        component.notifyAdded(this);
    }

    public void notifyAdded(Region component){
        propertyManager.notifyParentWasSet(component);
    }
    // ******************************************
    //                UI properties             *
    // ******************************************
    // *********************************************************************************************
    // ******************************************
    //              Custom listeners            *
    // ******************************************
    public void resizeListener (ResizeListener resizeListener) {
        if (resizeWidthListener == null || resizeHeightListener == null) {
            resizeWidthListener = (valType, oldVal, newVal) -> {
                notifyResizeListeners((double) newVal, getPrefHeight());
            };
            resizeHeightListener = (valType, oldVal, newVal) -> {
                notifyResizeListeners(getPrefWidth(), (double) newVal);
            };
            prefWidthProperty().addListener(resizeWidthListener);
            prefHeightProperty().addListener(resizeHeightListener);
        }
        resizeListeners.add(resizeListener);
    }

    public void removeResizeListener (ResizeListener resizeListener) {
        if (resizeListeners != null) {
            resizeListeners.remove(resizeListener);
        }
        if (resizeListeners != null && resizeListeners.isEmpty()) {
            prefWidthProperty().removeListener(resizeWidthListener);
            prefHeightProperty().removeListener(resizeHeightListener);
            resizeWidthListener = null;
            resizeHeightListener = null;
        }
    }

    private void notifyResizeListeners (double width, double height) {
        if (resizeListeners != null && !resizeListeners.isEmpty()) {
            resizeListeners.forEach(resizeListener -> {
                resizeListener.apply(width, height);
            });
        }
    }
    // ******************************************
    //              Custom listeners            *
    // ******************************************
    // **********************************************************
    //                                                          *
    //                      Custom properties                   *
    //                                                          *
    // **********************************************************
}
