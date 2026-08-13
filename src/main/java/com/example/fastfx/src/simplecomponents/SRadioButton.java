package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.FontSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.skin.RadioButtonSkin;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SRadioButton extends RadioButton implements SLabeled{
    private Margin margin;
    private Region currentComponentToStyle;
    private StackPane radio;
    private StackPane dot;

    public SRadioButton(){
        this.margin = new Margin(0);
        initElements();
    }

    public SRadioButton(String text){
        setText(text);
        this.margin = new Margin(0);
        initElements();
    }

    private void initElements(){
        setSkin(new RadioButtonSkin(this));
        this.currentComponentToStyle = this;
        this.radio = (StackPane) lookup(".radio");
        this.dot = (StackPane) lookup(".dot");
    }

    public StackPane getRadio (){
        return radio;
    }

    public StackPane getDot (){
        return dot;
    }

    public SRadioButton radio(PseudoElement pseudoElement){
        currentComponentToStyle = radio;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    public SRadioButton dot(PseudoElement pseudoElement){
        currentComponentToStyle = dot;
        pseudoElement.apply();
        currentComponentToStyle = this;
        return this;
    }

    @Override
    public SRadioButton x (double value) {
        currentComponentToStyle.setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public SRadioButton y (double value) {
        currentComponentToStyle.setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public SRadioButton width (double value) {
        currentComponentToStyle.setPrefWidth(value);
        return this;
    }

    @Override
    public SRadioButton height (double value) {
        currentComponentToStyle.setPrefHeight(value);
        return this;
    }

    @Override
    public SRadioButton scaleX (double value) {
        currentComponentToStyle.setScaleX(value);
        return this;
    }

    @Override
    public SRadioButton scaleY (double value) {
        currentComponentToStyle.setScaleY(value);
        return this;
    }

    @Override
    public SRadioButton translateX (double value) {
        currentComponentToStyle.setTranslateX(value);
        return this;
    }

    @Override
    public SRadioButton translateY (double value) {
        currentComponentToStyle.setTranslateY(value);
        return this;
    }

    @Override
    public SRadioButton rotate (double value) {
        currentComponentToStyle.setRotate(value);
        return this;
    }

    @Override
    public SRadioButton padding (double value) {
        PaddingUtil.setPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SRadioButton topPadding (double value) {
        PaddingUtil.setTopPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton rightPadding (double value) {
        PaddingUtil.setRightPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomPadding (double value) {
        PaddingUtil.setBottomPadding(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton leftPadding (double value) {
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
    public SRadioButton margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public SRadioButton margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public SRadioButton topMargin(double value){
        margin.setTop(value);
        return this;
    }

    @Override
    public SRadioButton rightMargin(double value){
        margin.setRight(value);
        return this;
    }

    @Override
    public SRadioButton bottomMargin(double value){
        margin.setBottom(value);
        return this;
    }

    @Override
    public SRadioButton leftMargin(double value){
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
    public SRadioButton bgColor (String color) {
        BackgroundSetter.setBackgroundColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SRadioButton bgColor (Color color) {
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
    public SRadioButton bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public SRadioButton bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public SRadioButton bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public SRadioButton topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomLeftBgRadius (double value) {
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
    public SRadioButton bgInsets (double value) {
        BackgroundSetter.setInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public SRadioButton topInset (double value) {
        BackgroundSetter.setTopInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton rightInset (double value) {
        BackgroundSetter.setRightInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomInset (double value) {
        BackgroundSetter.setBottomInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton leftInset (double value) {
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
    public SRadioButton border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(currentComponentToStyle, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public SRadioButton border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(currentComponentToStyle, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public SRadioButton topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SRadioButton rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SRadioButton bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SRadioButton leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(currentComponentToStyle, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SRadioButton topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SRadioButton rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SRadioButton bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SRadioButton leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(currentComponentToStyle, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SRadioButton borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SRadioButton topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SRadioButton rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SRadioButton bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(currentComponentToStyle, borderStyle);
        return this;
    }

    @Override
    public SRadioButton leftBorderStyle (String borderStyle) {
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
    public SRadioButton borderWidth (double value) {
        BorderSetter.setBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SRadioButton topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton leftBorderWidth (double value) {
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
    public SRadioButton borderColor (String colorValue) {
        BorderSetter.setBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SRadioButton borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SRadioButton topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SRadioButton rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SRadioButton bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(currentComponentToStyle, colorValue);
        return this;
    }

    @Override
    public SRadioButton leftBorderColor (String colorValue) {
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
    public SRadioButton borderColor (Color color) {
        BorderSetter.setBorderColor(this, color);
        return this;
    }

    @Override
    public SRadioButton borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(currentComponentToStyle, top, right, top, right);
        return this;
    }

    @Override
    public SRadioButton topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SRadioButton rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SRadioButton bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(currentComponentToStyle, color);
        return this;
    }

    @Override
    public SRadioButton leftBorderColor (Color color) {
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
    public SRadioButton borderOpacity (double value) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public SRadioButton topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton leftBorderOpacity (double value) {
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
    public SRadioButton borderRadius (double value) {
        BorderSetter.setBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(currentComponentToStyle, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public SRadioButton topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomLeftBorderRadius (double value) {
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
    public SRadioButton borderInsets (double value) {
        BorderSetter.setBorderInsets(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(currentComponentToStyle, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SRadioButton borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(currentComponentToStyle, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public SRadioButton topBorderInset (double value) {
        BorderSetter.setTopBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(currentComponentToStyle, value);
        return this;
    }

    @Override
    public SRadioButton leftBorderInset (double value) {
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
    public SRadioButton removeBorder () {
        currentComponentToStyle.setBorder(null);
        return this;
    }

    @Override
    public SRadioButton removeTopBorder () {
        BorderSetter.setTopBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SRadioButton removeRightBorder () {
        BorderSetter.setRightBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SRadioButton removeBottomBorder () {
        BorderSetter.setBottomBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SRadioButton removeLeftBorder () {
        BorderSetter.setLeftBorder(currentComponentToStyle, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(currentComponentToStyle, 0);
        BorderSetter.setTopLeftBorderRadius(currentComponentToStyle, 0);
        return this;
    }

    @Override
    public SRadioButton shape(String svg){
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        currentComponentToStyle.setShape(svgPath);
        return this;
    }

    @Override
    public SRadioButton shape(Shape shape){
        currentComponentToStyle.setShape(shape);
        return this;
    }

    @Override
    public SRadioButton font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public SRadioButton font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public SRadioButton font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public SRadioButton font (String fontFamily, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public SRadioButton fontColor (Color color) {
        FontSetter.setFontColor(this, color);
        return this;
    }

    @Override
    public SRadioButton fontColor (String color) {
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
    public SRadioButton fontFamily (String fontFamily) {
        FontSetter.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public SRadioButton fontPosture (FontPosture fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public SRadioButton fontPosture (String fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public SRadioButton fontWeight (FontWeight fontWeight) {
        FontSetter.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public SRadioButton fontSize (double fontSize) {
        FontSetter.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }
}
