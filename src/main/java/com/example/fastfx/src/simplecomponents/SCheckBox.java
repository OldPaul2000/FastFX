package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.FontSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.skin.CheckBoxSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SCheckBox extends CheckBox implements SLabeled {
    private Margin margin;
    private Region currentComponentToStyle;
    private StackPane box;
    private StackPane mark;

    public SCheckBox(){
        this.margin = new Margin(0);
        initElements();
    }

    public SCheckBox(String text){
        setText(text);
        this.margin = new Margin(0);
        initElements();
    }

    private void initElements(){
        setSkin(new CheckBoxSkin(this));
        this.currentComponentToStyle = this;
        this.box = (StackPane) lookup(".box");
        this.mark = (StackPane) lookup(".mark");
    }

    public StackPane getBox(){
        return box;
    }

    public StackPane getMark(){
        return mark;
    }

    public SCheckBox mark(PseudoElement pseudoElement){
        currentComponentToStyle = mark;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public SCheckBox box(PseudoElement pseudoElement){
        currentComponentToStyle = box;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    @Override
    public SCheckBox x (double value) {
        currentComponentToStyle.setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public SCheckBox y (double value) {
        currentComponentToStyle.setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public SCheckBox width (double value) {
        currentComponentToStyle.setPrefWidth(value);
        return this;
    }

    @Override
    public SCheckBox height (double value) {
        currentComponentToStyle.setPrefHeight(value);
        return this;
    }

    @Override
    public SCheckBox scaleX (double value) {
        currentComponentToStyle.setScaleX(value);
        return this;
    }

    @Override
    public SCheckBox scaleY (double value) {
        currentComponentToStyle.setScaleY(value);
        return this;
    }

    @Override
    public SCheckBox translateX (double value) {
        currentComponentToStyle.setTranslateX(value);
        return this;
    }

    @Override
    public SCheckBox translateY (double value) {
        currentComponentToStyle.setTranslateY(value);
        return this;
    }

    @Override
    public SCheckBox rotate (double value) {
        currentComponentToStyle.setRotate(value);
        return this;
    }

    @Override
    public SCheckBox padding (double value) {
        PaddingUtil.setPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SCheckBox topPadding (double value) {
        PaddingUtil.setTopPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox rightPadding (double value) {
        PaddingUtil.setRightPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomPadding (double value) {
        PaddingUtil.setBottomPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox leftPadding (double value) {
        PaddingUtil.setLeftPadding(currentComponentToStyle, value);
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
    public SCheckBox margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public SCheckBox margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public SCheckBox topMargin(double value){
        margin.setTop(value);
        return this;
    }

    @Override
    public SCheckBox rightMargin(double value){
        margin.setRight(value);
        return this;
    }

    @Override
    public SCheckBox bottomMargin(double value){
        margin.setBottom(value);
        return this;
    }

    @Override
    public SCheckBox leftMargin(double value){
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
    public SCheckBox bgColor (String color) {
        BackgroundSetter.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SCheckBox bgColor (Color color) {
        BackgroundSetter.setBackgroundColor(currentComponentToStyle, color);
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
    public SCheckBox bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public SCheckBox bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public SCheckBox bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public SCheckBox topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomLeftBgRadius (double value) {
        BackgroundSetter.setBottomLeftBackgroundRadius(currentComponentToStyle, value);
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
    public SCheckBox bgInsets (double value) {
        BackgroundSetter.setInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public SCheckBox topInset (double value) {
        BackgroundSetter.setTopInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox rightInset (double value) {
        BackgroundSetter.setRightInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomInset (double value) {
        BackgroundSetter.setBottomInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox leftInset (double value) {
        BackgroundSetter.setLeftInset(currentComponentToStyle, value);
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
    public SCheckBox border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(currentComponentToStyle, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public SCheckBox border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(currentComponentToStyle, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public SCheckBox topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SCheckBox rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SCheckBox bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SCheckBox leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SCheckBox topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SCheckBox rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SCheckBox bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SCheckBox leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SCheckBox borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SCheckBox topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SCheckBox rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SCheckBox bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SCheckBox leftBorderStyle (String borderStyle) {
        BorderSetter.setLeftBorderStyle(currentComponentToStyle, borderStyle);
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
    public SCheckBox borderWidth (double value) {
        BorderSetter.setBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SCheckBox topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox leftBorderWidth (double value) {
        BorderSetter.setLeftBorderWidth(currentComponentToStyle, value);
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
    public SCheckBox borderColor (String colorValue) {
        BorderSetter.setBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SCheckBox borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SCheckBox topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SCheckBox rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SCheckBox bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SCheckBox leftBorderColor (String colorValue) {
        BorderSetter.setLeftBorderColor(currentComponentToStyle, colorValue);
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
    public SCheckBox borderColor (Color color) {
        BorderSetter.setBorderColor(this, color);
        return this;
    }

    @Override
    public SCheckBox borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(currentComponentToStyle, top, right, top, right);
        return this;
    }

    @Override
    public SCheckBox topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SCheckBox rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SCheckBox bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SCheckBox leftBorderColor (Color color) {
        BorderSetter.setLeftBorderColor(currentComponentToStyle, color);
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
    public SCheckBox borderOpacity (double value) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SCheckBox topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox leftBorderOpacity (double value) {
        BorderSetter.setLeftBorderOpacity(currentComponentToStyle, value);
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
    public SCheckBox borderRadius (double value) {
        BorderSetter.setBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public SCheckBox topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomLeftBorderRadius (double value) {
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, value);
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
    public SCheckBox borderInsets (double value) {
        BorderSetter.setBorderInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SCheckBox borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public SCheckBox topBorderInset (double value) {
        BorderSetter.setTopBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SCheckBox leftBorderInset (double value) {
        BorderSetter.setLeftBorderInset(currentComponentToStyle, value);
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
    public SCheckBox removeBorder () {
        currentComponentToStyle.setBorder(null);
        return this;
    }

    @Override
    public SCheckBox removeTopBorder () {
        BorderSetter.setTopBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SCheckBox removeRightBorder () {
        BorderSetter.setRightBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SCheckBox removeBottomBorder () {
        BorderSetter.setBottomBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SCheckBox removeLeftBorder () {
        BorderSetter.setLeftBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SCheckBox shape(String svg){
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        currentComponentToStyle.setShape(svgPath);
        return this;
    }

    @Override
    public SCheckBox shape(Shape shape){
        currentComponentToStyle.setShape(shape);
        return this;
    }

    @Override
    public SCheckBox font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public SCheckBox font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public SCheckBox font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public SCheckBox font (String fontFamily, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public SCheckBox fontColor (Color color) {
        FontSetter.setFontColor(this, color);
        return this;
    }

    @Override
    public SCheckBox fontColor (String color) {
        FontSetter.setFontColor(this, color);
        return this;
    }

    @Override
    public Color getFontColor () {
        return FontSetter.getFontColor(this);
    }

    @Override
    public String getFontColorHex () {
        return FontSetter.getFontColorHex(this);
    }

    @Override
    public SCheckBox fontFamily (String fontFamily) {
        FontSetter.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public SCheckBox fontPosture (FontPosture fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public SCheckBox fontPosture (String fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public SCheckBox fontWeight (FontWeight fontWeight) {
        FontSetter.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public SCheckBox fontSize (double fontSize) {
        FontSetter.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }
}
