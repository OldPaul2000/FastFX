package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.FontSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SButton extends Button implements SLabeled {
    private Margin margin;

    public SButton(){
        this.margin = new Margin(0);
    }

    public SButton(String text){
        setText(text);
        this.margin = new Margin(0);
    }

    @Override
    public SButton x (double value) {
        setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public SButton y (double value) {
        setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public SButton width (double value) {
        setPrefWidth(value);
        return this;
    }

    @Override
    public SButton height (double value) {
        setPrefHeight(value);
        return this;
    }

    @Override
    public SButton scaleX (double value) {
        setScaleX(value);
        return this;
    }

    @Override
    public SButton scaleY (double value) {
        setScaleY(value);
        return this;
    }

    @Override
    public SButton translateX (double value) {
        setTranslateX(value);
        return this;
    }

    @Override
    public SButton translateY (double value) {
        setTranslateY(value);
        return this;
    }

    @Override
    public SButton rotate (double value) {
        setRotate(value);
        return this;
    }

    @Override
    public SButton padding (double value) {
        PaddingUtil.setPadding(this, value);
        return this;
    }

    @Override
    public SButton padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(this, top, right, bottom, left);
        return this;
    }

    @Override
    public SButton topPadding (double value) {
        PaddingUtil.setTopPadding(this, value);
        return this;
    }

    @Override
    public SButton rightPadding (double value) {
        PaddingUtil.setRightPadding(this, value);
        return this;
    }

    @Override
    public SButton bottomPadding (double value) {
        PaddingUtil.setBottomPadding(this, value);
        return this;
    }

    @Override
    public SButton leftPadding (double value) {
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
    public SButton margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public SButton margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public SButton topMargin(double value){
        margin.setTop(value);
        return this;
    }

    @Override
    public SButton rightMargin(double value){
        margin.setRight(value);
        return this;
    }

    @Override
    public SButton bottomMargin(double value){
        margin.setBottom(value);
        return this;
    }

    @Override
    public SButton leftMargin(double value){
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
    public SButton bgColor (String color) {
        BackgroundSetter.setBackgroundColor(this, color);
        return this;
    }

    @Override
    public SButton bgColor (Color color) {
        BackgroundSetter.setBackgroundColor(this, color);
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
    public SButton bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(this, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public SButton bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(this, value);
        return this;
    }

    @Override
    public SButton bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(this, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public SButton bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public SButton topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(this, value);
        return this;
    }

    @Override
    public SButton topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public SButton bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public SButton bottomLeftBgRadius (double value) {
        BackgroundSetter.setBottomLeftBackgroundRadius(this, value);
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
    public SButton bgInsets (double value) {
        BackgroundSetter.setInsets(this, value);
        return this;
    }

    @Override
    public SButton bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public SButton topInset (double value) {
        BackgroundSetter.setTopInset(this, value);
        return this;
    }

    @Override
    public SButton rightInset (double value) {
        BackgroundSetter.setRightInset(this, value);
        return this;
    }

    @Override
    public SButton bottomInset (double value) {
        BackgroundSetter.setBottomInset(this, value);
        return this;
    }

    @Override
    public SButton leftInset (double value) {
        BackgroundSetter.setLeftInset(this, value);
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
    public SButton border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(this, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public SButton border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(this, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public SButton topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SButton rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SButton bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SButton leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SButton topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SButton rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SButton bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SButton leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SButton borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public SButton topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public SButton rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public SButton bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public SButton leftBorderStyle (String borderStyle) {
        BorderSetter.setLeftBorderStyle(this, borderStyle);
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
    public SButton borderWidth (double value) {
        BorderSetter.setBorderWidth(this, value);
        return this;
    }

    @Override
    public SButton borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(this, top, right, bottom, left);
        return this;
    }

    @Override
    public SButton topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(this, value);
        return this;
    }

    @Override
    public SButton rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(this, value);
        return this;
    }

    @Override
    public SButton bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(this, value);
        return this;
    }

    @Override
    public SButton leftBorderWidth (double value) {
        BorderSetter.setLeftBorderWidth(this, value);
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
    public SButton borderColor (String colorValue) {
        BorderSetter.setBorderColor(this, colorValue);
        return this;
    }

    @Override
    public SButton borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(this, top, right, bottom, left);
        return this;
    }

    @Override
    public SButton topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(this, colorValue);
        return this;
    }

    @Override
    public SButton rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(this, colorValue);
        return this;
    }

    @Override
    public SButton bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(this, colorValue);
        return this;
    }

    @Override
    public SButton leftBorderColor (String colorValue) {
        BorderSetter.setLeftBorderColor(this, colorValue);
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
    public SButton borderColor (Color color) {
        BorderSetter.setBorderColor(this, color);
        return this;
    }

    @Override
    public SButton borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(this, top, right, top, right);
        return this;
    }

    @Override
    public SButton topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(this, color);
        return this;
    }

    @Override
    public SButton rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(this, color);
        return this;
    }

    @Override
    public SButton bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(this, color);
        return this;
    }

    @Override
    public SButton leftBorderColor (Color color) {
        BorderSetter.setLeftBorderColor(this, color);
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
    public SButton borderOpacity (double value) {
        BorderSetter.setBorderOpacity(this, value);
        return this;
    }

    @Override
    public SButton borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(this, top, right, bottom, left);
        return this;
    }

    @Override
    public SButton topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(this, value);
        return this;
    }

    @Override
    public SButton rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(this, value);
        return this;
    }

    @Override
    public SButton bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(this, value);
        return this;
    }

    @Override
    public SButton leftBorderOpacity (double value) {
        BorderSetter.setLeftBorderOpacity(this, value);
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
    public SButton borderRadius (double value) {
        BorderSetter.setBorderRadius(this, value);
        return this;
    }

    @Override
    public SButton borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public SButton topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(this, value);
        return this;
    }

    @Override
    public SButton topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(this, value);
        return this;
    }

    @Override
    public SButton bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(this, value);
        return this;
    }

    @Override
    public SButton bottomLeftBorderRadius (double value) {
        BorderSetter.setBottomLeftBorderRadius(this, value);
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
    public SButton borderInsets (double value) {
        BorderSetter.setBorderInsets(this, value);
        return this;
    }

    @Override
    public SButton borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SButton borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public SButton topBorderInset (double value) {
        BorderSetter.setTopBorderInset(this, value);
        return this;
    }

    @Override
    public SButton rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(this, value);
        return this;
    }

    @Override
    public SButton bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(this, value);
        return this;
    }

    @Override
    public SButton leftBorderInset (double value) {
        BorderSetter.setLeftBorderInset(this, value);
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
    public SButton removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public SButton removeTopBorder () {
        BorderSetter.setTopBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(this, 0);
        BorderSetter.setTopRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public SButton removeRightBorder () {
        BorderSetter.setRightBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(this, 0);
        BorderSetter.setBottomRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public SButton removeBottomBorder () {
        BorderSetter.setBottomBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(this, 0);
        BorderSetter.setBottomLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public SButton removeLeftBorder () {
        BorderSetter.setLeftBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(this, 0);
        BorderSetter.setTopLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public SButton shape(String svg){
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        setShape(svgPath);
        return this;
    }

    @Override
    public SButton shape(Shape shape){
        setShape(shape);
        return this;
    }

    @Override
    public SButton font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public SButton font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public SButton font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public SButton font (String fontFamily, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public SButton fontColor (Color color) {
        FontSetter.setFontColor(this, color);
        return this;
    }

    @Override
    public SButton fontColor (String color) {
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
    public SButton fontFamily (String fontFamily) {
        FontSetter.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public SButton fontPosture (FontPosture fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public SButton fontPosture (String fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public SButton fontWeight (FontWeight fontWeight) {
        FontSetter.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public SButton fontSize (double fontSize) {
        FontSetter.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }
}
