package com.example.fastfx.src.simplecomponents;

import com.example.fastfx.src.style.layouts.Margin;
import com.example.fastfx.src.utility.style.BackgroundSetter;
import com.example.fastfx.src.utility.style.BorderSetter;
import com.example.fastfx.src.utility.style.FontSetter;
import com.example.fastfx.src.utility.style.PaddingUtil;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SHyperlink extends Hyperlink implements SLabeled{
    private Margin margin;

    public SHyperlink (){
        this.margin = new Margin(0, 0, 0, 0);
    }

    public SHyperlink(String text){
        setText(text);
        this.margin = new Margin(0, 0, 0, 0);
    }

    @Override
    public SHyperlink x (double value) {
        setLayoutX(value);
        return this;
    }

    @Override
    public double getX () {
        return getLayoutX();
    }

    @Override
    public SHyperlink y (double value) {
        setLayoutY(value);
        return this;
    }

    @Override
    public double getY () {
        return getLayoutY();
    }

    @Override
    public SHyperlink width (double value) {
        setPrefWidth(value);
        return this;
    }

    @Override
    public SHyperlink height (double value) {
        setPrefHeight(value);
        return this;
    }

    @Override
    public SHyperlink scaleX (double value) {
        setScaleX(value);
        return this;
    }

    @Override
    public SHyperlink scaleY (double value) {
        setScaleY(value);
        return this;
    }

    @Override
    public SHyperlink translateX (double value) {
        setTranslateX(value);
        return this;
    }

    @Override
    public SHyperlink translateY (double value) {
        setTranslateY(value);
        return this;
    }

    @Override
    public SHyperlink rotate (double value) {
        setRotate(value);
        return this;
    }

    @Override
    public SHyperlink padding (double value) {
        PaddingUtil.setPadding(this, value);
        return this;
    }

    @Override
    public SHyperlink padding (double topBottom, double rightLeft) {
        PaddingUtil.setPadding(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink padding (double top, double right, double bottom, double left) {
        PaddingUtil.setPadding(this, top, right, bottom, left);
        return this;
    }

    @Override
    public SHyperlink topPadding (double value) {
        PaddingUtil.setTopPadding(this, value);
        return this;
    }

    @Override
    public SHyperlink rightPadding (double value) {
        PaddingUtil.setRightPadding(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomPadding (double value) {
        PaddingUtil.setBottomPadding(this, value);
        return this;
    }

    @Override
    public SHyperlink leftPadding (double value) {
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
    public SHyperlink margin (double value) {
        margin.setMargin(value);
        return this;
    }

    @Override
    public SHyperlink margin (double topBottom, double rightLeft) {
        margin.setMargin(topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink margin (double top, double right, double bottom, double left) {
        margin.setMargin(top, right, bottom, left);
        return this;
    }

    @Override
    public Margin getMargin () {
        return margin;
    }

    @Override
    public SHyperlink topMargin(double value){
        margin.setTop(value);
        return this;
    }

    @Override
    public SHyperlink rightMargin(double value){
        margin.setRight(value);
        return this;
    }

    @Override
    public SHyperlink bottomMargin(double value){
        margin.setBottom(value);
        return this;
    }

    @Override
    public SHyperlink leftMargin(double value){
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
    public SHyperlink bgColor (String color) {
        BackgroundSetter.setBackgroundColor(this, color);
        return this;
    }

    @Override
    public SHyperlink bgColor (Color color) {
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
    public SHyperlink bgOpacity (double value) {
        BackgroundSetter.setBackgroundOpacity(this, value);
        return this;
    }

    @Override
    public double getBgOpacity () {
        return BackgroundSetter.getBackgroundOpacity(this);
    }

    @Override
    public SHyperlink bgRadius (double value) {
        BackgroundSetter.setBackgroundRadius(this, value);
        return this;
    }

    @Override
    public SHyperlink bgRadius (double topLeftBottomRight, double topRightBottomLeft) {
        BackgroundSetter.setBackgroundRadius(this, topLeftBottomRight, topRightBottomLeft);
        return this;
    }

    @Override
    public SHyperlink bgRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BackgroundSetter.setBackgroundRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBgRadius () {
        return BackgroundSetter.getBackgroundRadius(this);
    }

    @Override
    public SHyperlink topLeftBgRadius (double value) {
        BackgroundSetter.setTopLeftBackgroundRadius(this, value);
        return this;
    }

    @Override
    public SHyperlink topRightBgRadius (double value) {
        BackgroundSetter.setTopRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomRightBgRadius (double value) {
        BackgroundSetter.setBottomRightBackgroundRadius(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomLeftBgRadius (double value) {
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
    public SHyperlink bgInsets (double value) {
        BackgroundSetter.setInsets(this, value);
        return this;
    }

    @Override
    public SHyperlink bgInsets (double topBottom, double rightLeft) {
        BackgroundSetter.setInsets(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink bgInsets (double top, double right, double bottom, double left) {
        BackgroundSetter.setInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBgInsets () {
        return BackgroundSetter.getBackgroundInsets(this);
    }

    @Override
    public SHyperlink topInset (double value) {
        BackgroundSetter.setTopInset(this, value);
        return this;
    }

    @Override
    public SHyperlink rightInset (double value) {
        BackgroundSetter.setRightInset(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomInset (double value) {
        BackgroundSetter.setBottomInset(this, value);
        return this;
    }

    @Override
    public SHyperlink leftInset (double value) {
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
    public SHyperlink border (String colorValue, String borderStyle, double topLeftRadius, double topRightRadius, double bottomRightRadius, double bottomLeftRadius, double topWidth, double rightWidth, double bottomWidth, double leftWidth) {
        BorderSetter.setBorder(this, colorValue, borderStyle, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, topWidth, rightWidth, bottomWidth, leftWidth);
        return this;
    }

    @Override
    public SHyperlink border (String colorValue, String borderStyle, double radius, double borderWidth) {
        BorderSetter.setBorder(this, colorValue, borderStyle, radius, borderWidth);
        return this;
    }

    @Override
    public SHyperlink topBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SHyperlink rightBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SHyperlink bottomBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SHyperlink leftBorder (String colorValue, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(this, colorValue, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SHyperlink topBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setTopBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SHyperlink rightBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setRightBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SHyperlink bottomBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setBottomBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SHyperlink leftBorder (Color color, String borderStyle, double borderWidth) {
        BorderSetter.setLeftBorder(this, color, borderStyle, borderWidth);
        return this;
    }

    @Override
    public SHyperlink borderStyle (String borderStyle) {
        BorderSetter.setBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public SHyperlink topBorderStyle (String borderStyle) {
        BorderSetter.setTopBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public SHyperlink rightBorderStyle (String borderStyle) {
        BorderSetter.setRightBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public SHyperlink bottomBorderStyle (String borderStyle) {
        BorderSetter.setBottomBorderStyle(this, borderStyle);
        return this;
    }

    @Override
    public SHyperlink leftBorderStyle (String borderStyle) {
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
    public SHyperlink borderWidth (double value) {
        BorderSetter.setBorderWidth(this, value);
        return this;
    }

    @Override
    public SHyperlink borderWidth (double topBottom, double rightLeft) {
        BorderSetter.setBorderWidth(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink borderWidth (double top, double right, double bottom, double left) {
        BorderSetter.setBorderWidth(this, top, right, bottom, left);
        return this;
    }

    @Override
    public SHyperlink topBorderWidth (double value) {
        BorderSetter.setTopBorderWidth(this, value);
        return this;
    }

    @Override
    public SHyperlink rightBorderWidth (double value) {
        BorderSetter.setRightBorderWidth(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomBorderWidth (double value) {
        BorderSetter.setBottomBorderWidth(this, value);
        return this;
    }

    @Override
    public SHyperlink leftBorderWidth (double value) {
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
    public SHyperlink borderColor (String colorValue) {
        BorderSetter.setBorderColor(this, colorValue);
        return this;
    }

    @Override
    public SHyperlink borderColor (String topBottom, String rightLeft) {
        BorderSetter.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink borderColor (String top, String right, String bottom, String left) {
        BorderSetter.setBorderColor(this, top, right, bottom, left);
        return this;
    }

    @Override
    public SHyperlink topBorderColor (String colorValue) {
        BorderSetter.setTopBorderColor(this, colorValue);
        return this;
    }

    @Override
    public SHyperlink rightBorderColor (String colorValue) {
        BorderSetter.setRightBorderColor(this, colorValue);
        return this;
    }

    @Override
    public SHyperlink bottomBorderColor (String colorValue) {
        BorderSetter.setBottomBorderColor(this, colorValue);
        return this;
    }

    @Override
    public SHyperlink leftBorderColor (String colorValue) {
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
    public SHyperlink borderColor (Color color) {
        BorderSetter.setBorderColor(this, color);
        return this;
    }

    @Override
    public SHyperlink borderColor (Color topBottom, Color rightLeft) {
        BorderSetter.setBorderColor(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink borderColor (Color top, Color right, Color bottom, Color left) {
        BorderSetter.setBorderColor(this, top, right, top, right);
        return this;
    }

    @Override
    public SHyperlink topBorderColor (Color color) {
        BorderSetter.setTopBorderColor(this, color);
        return this;
    }

    @Override
    public SHyperlink rightBorderColor (Color color) {
        BorderSetter.setRightBorderColor(this, color);
        return this;
    }

    @Override
    public SHyperlink bottomBorderColor (Color color) {
        BorderSetter.setBottomBorderColor(this, color);
        return this;
    }

    @Override
    public SHyperlink leftBorderColor (Color color) {
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
    public SHyperlink borderOpacity (double value) {
        BorderSetter.setBorderOpacity(this, value);
        return this;
    }

    @Override
    public SHyperlink borderOpacity (double topBottom, double rightLeft) {
        BorderSetter.setBorderOpacity(this, topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink borderOpacity (double top, double right, double bottom, double left) {
        BorderSetter.setBorderOpacity(this, top, right, bottom, left);
        return this;
    }

    @Override
    public SHyperlink topBorderOpacity (double value) {
        BorderSetter.setTopBorderOpacity(this, value);
        return this;
    }

    @Override
    public SHyperlink rightBorderOpacity (double value) {
        BorderSetter.setRightBorderOpacity(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomBorderOpacity (double value) {
        BorderSetter.setBottomBorderOpacity(this, value);
        return this;
    }

    @Override
    public SHyperlink leftBorderOpacity (double value) {
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
    public SHyperlink borderRadius (double value) {
        BorderSetter.setBorderRadius(this, value);
        return this;
    }

    @Override
    public SHyperlink borderRadius (double topBottom, double rightLeft) {
        BorderSetter.setBorderRadius(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink borderRadius (double topLeft, double topRight, double bottomRight, double bottomLeft) {
        BorderSetter.setBorderRadius(this, topLeft, topRight, bottomRight, bottomLeft);
        return this;
    }

    @Override
    public CornerRadii getBorderRadius () {
        return BorderSetter.getBorderRadius(this);
    }

    @Override
    public SHyperlink topLeftBorderRadius (double value) {
        BorderSetter.setTopLeftBorderRadius(this, value);
        return this;
    }

    @Override
    public SHyperlink topRightBorderRadius (double value) {
        BorderSetter.setTopRightBorderRadius(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomRightBorderRadius (double value) {
        BorderSetter.setBottomRightBorderRadius(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomLeftBorderRadius (double value) {
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
    public SHyperlink borderInsets (double value) {
        BorderSetter.setBorderInsets(this, value);
        return this;
    }

    @Override
    public SHyperlink borderInsets (double topBottom, double rightLeft) {
        BorderSetter.setBorderInsets(this, topBottom, rightLeft, topBottom, rightLeft);
        return this;
    }

    @Override
    public SHyperlink borderInsets (double top, double right, double bottom, double left) {
        BorderSetter.setBorderInsets(this, top, right, bottom, left);
        return this;
    }

    @Override
    public Insets getBorderInsets () {
        return BorderSetter.getBorderInsets(this);
    }

    @Override
    public SHyperlink topBorderInset (double value) {
        BorderSetter.setTopBorderInset(this, value);
        return this;
    }

    @Override
    public SHyperlink rightBorderInset (double value) {
        BorderSetter.setRightBorderInset(this, value);
        return this;
    }

    @Override
    public SHyperlink bottomBorderInset (double value) {
        BorderSetter.setBottomBorderInset(this, value);
        return this;
    }

    @Override
    public SHyperlink leftBorderInset (double value) {
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
    public SHyperlink removeBorder () {
        this.setBorder(null);
        return this;
    }

    @Override
    public SHyperlink removeTopBorder () {
        BorderSetter.setTopBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopLeftBorderRadius(this, 0);
        BorderSetter.setTopRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public SHyperlink removeRightBorder () {
        BorderSetter.setRightBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setTopRightBorderRadius(this, 0);
        BorderSetter.setBottomRightBorderRadius(this, 0);
        return this;
    }

    @Override
    public SHyperlink removeBottomBorder () {
        BorderSetter.setBottomBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomRightBorderRadius(this, 0);
        BorderSetter.setBottomLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public SHyperlink removeLeftBorder () {
        BorderSetter.setLeftBorder(this, Color.TRANSPARENT, "solid", 0);
        BorderSetter.setBottomLeftBorderRadius(this, 0);
        BorderSetter.setTopLeftBorderRadius(this, 0);
        return this;
    }

    @Override
    public SHyperlink shape(String svg){
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        setShape(svgPath);
        return this;
    }

    @Override
    public SHyperlink shape(Shape shape){
        setShape(shape);
        return this;
    }

    @Override
    public SHyperlink font (String fontFamily, FontWeight fontWeight, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontPosture, fontSize);
        return this;
    }

    @Override
    public SHyperlink font (String fontFamily, FontPosture fontPosture, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontPosture, fontSize);
        return this;
    }

    @Override
    public SHyperlink font (String fontFamily, FontWeight fontWeight, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontWeight, fontSize);
        return this;
    }

    @Override
    public SHyperlink font (String fontFamily, double fontSize) {
        FontSetter.setFont(this, fontFamily, fontSize);
        return this;
    }

    @Override
    public SHyperlink fontColor (Color color) {
        FontSetter.setFontColor(this, color);
        return this;
    }

    @Override
    public SHyperlink fontColor (String color) {
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
    public SHyperlink fontFamily (String fontFamily) {
        FontSetter.setFontFamily(this, fontFamily);
        return this;
    }

    @Override
    public SHyperlink fontPosture (FontPosture fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public SHyperlink fontPosture (String fontPosture) {
        FontSetter.setFontPosture(this, fontPosture);
        return this;
    }

    @Override
    public String getFontPostureString () {
        return getFont().getStyle();
    }

    @Override
    public SHyperlink fontWeight (FontWeight fontWeight) {
        FontSetter.setFontWeight(this, fontWeight);
        return this;
    }

    @Override
    public SHyperlink fontSize (double fontSize) {
        FontSetter.setFontSize(this, fontSize);
        return this;
    }

    @Override
    public double getFontSize () {
        return getFont().getSize();
    }
}
